(ns montaigne.parser
  ;(:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [instaparse.core :as insta :refer-macros [defparser]]
            [cljs-node-io.core :as io :refer [slurp spit]]
            [cljs-node-io.fs :as fs :refer [mkdir rm-r]]
            [cljs.tools.reader :refer [read-string]]
            [cljs.js :refer [empty-state eval js-eval]]
            [cljs.env :refer [*compiler*]]
            [cljs.pprint :refer [pprint]]
            [xregexp]
            [cuerdas.core :as cue]
            [montaigne.fns :as fns]
            ["@thi.ng/hiccup" :as hiccup :refer [serialize]]
            [cljs-time.format :as date-format]
            [cljs-time.core :as date-core]
            [httpurr.status :as s]
            [httpurr.client :as http]
            [httpurr.client.node :as node]
            [promesa.core :as p]
    ; [eval-soup.core :as eval-soup]
            ))

(println "parser start...")


(defn decode
  [response]
  (update response :body #(js->clj (js/JSON.parse %))))

(defn get!
  [url]
  (p/then (node/get url) decode))



(defn process-response
  [response]
  (condp = (:status response)
    s/ok           (p/resolved (:body response))
    s/not-found    (p/rejected :not-found)
    s/unauthorized (p/rejected :unauthorized)))

(def airports [])
(defn get-airports []
  (p/then 
    (get! "https://cdn.rawgit.com/konsalex/Airport-Autocomplete-JS/3dbde72e/src/airports.json")
    (fn [response]
      (cljs.pprint/pprint response)
      (let [airports_ (process-response response)]
        (set! airports airports_)
      )
      )))

(defn debug [message entity]
  (println (str message "..."))
  (pprint entity)
  (println (str "..." message)))

(defn to-hiccup
  "Used as toHiccup method on cljs vectors and lists.
  Takes no arguments but uses js-this.
  Returns a js array for thi.ng umbrella hiccup compatability."
  []
  (->> (js-this)
       (clj->js)))

;; Add a toHiccup method to cljs vectors and sequences
(doseq [o [[] ()]]
  (-> (.getPrototypeOf js/Object o)
      (.-toHiccup)
      (set! to-hiccup)))

(defn html [hiccup-str]
  (serialize hiccup-str))


(def COLLECTION_START_MARK "# ")
(def ENITITY_START_MARK "## ")
(def ENTITY_DEF_ATTRIBUTE_MARK "@")
(def INDENTANTION_MARK "  ")

(defn find-el-index [els el]
  (loop [arr els n 0]
    (if (= el (first arr))
      n
      (recur (next arr) (inc n)))))

(defn regex-modifiers
  "Returns the modifiers of a regex, concatenated as a string."
  [re]
  (str (if (.-multiline re) "m")
       (if (.-ignoreCase re) "i")))

(defn re-pos
  "Returns a vector of vectors, each subvector containing in order:
   the position of the match, the matched string, and any groups
   extracted from the match."
  [re s]
  (let [re (js/RegExp. (.-source re) (str "g" (regex-modifiers re)))]
    (loop [res []]
      (if-let [m (.exec re s)]
        (recur (conj res (vec (cons (.-index m) m))))
        res))))

(defn find-collections [indexed-lines]
  (let [result (reduce 
          (fn [acc indexed-line]
            (let [line (second indexed-line)]
              (if (clojure.string/starts-with? line COLLECTION_START_MARK)
                (let [collection-name (subs line 2)
                      trimmed (clojure.string/trim collection-name)
                      collection-keyword (keyword trimmed)]
                      (conj acc {:name trimmed :key collection-keyword :start-line (first indexed-line) :lines [] :collection-attrs [] :entity-attrs [] :entities []}))
                (let [last-el (last acc)
                      updated (assoc last-el :lines (conj (:lines last-el) indexed-line))]      
                  (conj (vec (butlast acc)) updated)))))
          [] indexed-lines)]
    result))  


;; from cuerdas https://github.com/funcool/cuerdas/blob/master/src/cuerdas/core.cljc
(defn slice
  "Extracts a section of a string and returns a new string."
  ([s begin]
    (when (string? s)
          (.slice s begin)))
  ([s begin end]
    (when (string? s)
          (.slice s begin end))))

(defn strip-suffix
  "Strip suffix in more efficient way."
  [^String s ^Object suffix]
  (if (clojure.string/ends-with? s suffix)
    (slice s 0 (- (count s) (count (.toString suffix))))
    s))

(defn strip-prefix
  "Strip prefix in more efficient way."
  [^String s ^Object prefix]
  (if (clojure.string/starts-with? s prefix)
    (slice s (count (.toString prefix)) (count s))
    s))

;; end cuerdas


(defn slug [prop-value]
  (cue/slug prop-value))

(defn eval-str [s]
  (eval
    (empty-state)
    (read-string s)
    {:eval        js-eval
      ;  :load (fn [_ cb] (cb {:lang :clj :source "(ns foo.core) (defn add [a b] (+ a b))"}))
      :source-map  false
      :verbose     true
      :context     :expr
      ;  :ns 'foo.core
      :load-macros true}
    (fn [result]
        result
        )))

(defn eval-safe [s]
  (try 
    (eval-str s)
    (catch js/Object e
      (do
        (println "eval code failed...")
        (println s)
        (println "...eval code failed")
        {:value ""}
      )
    )
  )
)        


(def mntgn-parser
  (insta/parser
    "<root> = collection*
     collection = collection-header collection-body

     <collection-header> = <collection-header-mark> <space> collection-name <blankline>
     collection-name = #'[a-zA-Z0-9 ]+'
     collection-body = collection-attrs entity-def-attrs entities

     collection-attr = (collection-inline-attr | collection-multiline-attr)
     collection-attrs = collection-attr*
     <entity-def-attr>  = (entity-inline-def-attr | entity-multiline-def-attr)
     entity-def-attrs = entity-def-attr*

     entities = entity*

     def-attr-name = #'[a-zA-Z0-9.]+'
     <inline-code> = <'`'> #'[^`]+' <'`'>

     entity-inline-def-attr = <entity-inline-attr-mark> def-attr-name <colon> <space> entity-inline-def-attr-val <newline>+
     entity-inline-def-attr-val = inline-code

     entity-multiline-def-attr = entity-multiline-def-attr-header entity-multiline-def-attr-val <newline>*
     <entity-multiline-def-attr-header> = <multiline-attr-header-mark> <space> <'@'> def-attr-name <blankline>
     <entity-def-attr-val-line> = #'[^\n]+' '\n'+
     entity-multiline-def-attr-val = entity-def-attr-val-line+

     collection-attr-name = attribute-name

     collection-inline-attr = collection-attr-name <colon> <space> collection-inline-attr-val <space*> <newline>+
     collection-inline-attr-val = #'[^\n]+'

     collection-multiline-attr = collection-multiline-attr-header collection-multiline-attr-val <newline>*
     <collection-multiline-attr-header> = <multiline-attr-header-mark> <space> collection-attr-name <blankline>
     <collection-attr-val-line> = #'[^\n]+' '\n'+
     collection-multiline-attr-val = collection-attr-val-line+

     entity = entity-header entity-body <blankline>+

     <entity-header> = <entity-header-mark> <space> entity-name <blankline>
     entity-name = #'[a-zA-Z0-9 ]+'
     entity-body = entity-inline-attrs entity-multiline-attrs

     entity-inline-attrs = entity-inline-attr*
     entity-multiline-attrs = entity-multiline-attr* 

     entity-attr-name = attribute-name
     entity-inline-attr = entity-attr-name <colon> <spaces> entity-inline-attr-val <space>* <newline>+
     entity-inline-attr-val = #'[^\n]+'

     entity-multiline-attr = entity-multiline-attr-header (entity-table-attr-val | entity-multiline-attr-val) <newline>*
     <entity-multiline-attr-header> = <multiline-attr-header-mark> <space> entity-attr-name <blankline>
     <entity-attr-val-line> = #'[^|^\n]+' '\n'+
     entity-multiline-attr-val = entity-attr-val-line+

     entity-table-attr-val = row <delimiter-row> row*
     row = column (<'|'> column)+ <newline>
     delimiter-column = space* '-'+ space*
     delimiter-row = delimiter-column (<'|'> delimiter-column)+ <newline>
     column = <space>* #'[^|^\n]+' <space>*

     <attribute-name> = #'[a-zA-Z0-9]+'
     collection-header-mark = '#'
     entity-header-mark = '##'
     multiline-attr-header-mark = '###'
     entity-inline-attr-mark = '@'
     colon = ':'
     space = ' '
     spaces = space+

     newline = '\n'
     blankline = '\n\n'" :output-format :enlive))


(defn get-collection-attributes [els]
  (debug "get-collection-attributes" els)
  (->>
    (filter #(= :collection-attrs (:tag %)) els)
    first
    :content))

(defn get-entity-def-attributes [els]
  (debug "get-entity-def-attributes" els)
  (let [attrs (->>
                (filter #(= :entity-def-attrs (:tag %)) els)
                first
                :content)]
        (do
          (debug "get-entity-def-attributes. done" attrs)
          attrs
          )
        ))

(defn get-entities [els]
  (debug "get-entities" els)
  (->>
    (filter #(= :entities (:tag %)) els)
    first
    :content))

;{:tag :collection-inline-attr, :content ({:tag :collection-attr-name, :content (description)} {:tag :collection-inline-attr-val, :content (Collection of books)}
(defn transform-collection-attr [el]
  (debug "transform-collection-attr" el)
  (let [value-lines-list (->> el :content first :content last :content)
        value-as-str (clojure.string/trim (clojure.string/join value-lines-list))
        attr-name (->> el :content first :content first :content first)]
        (if (and
              (clojure.string/starts-with? value-as-str "```clojure")
              (clojure.string/ends-with? value-as-str "```"))
          (let [val_ (strip-prefix value-as-str "```clojure")
                attr-value (strip-suffix val_ "```")]
              {:name  attr-name
                :type  "code"
                :value attr-value}
              )
          {:name  attr-name
          :value value-as-str}
          )))

(defn is-number [str-value]
  (> (js/parseInt str-value) 0))

(defn is-date [value-as-string]
  (println "is-date" value-as-string)
  ;; YYYY-MM-DD
  (if (= 10 (count value-as-string))
    (let [tokens (clojure.string/split value-as-string "-")]
      (if (= 3 (count tokens))
        (and 
          (is-number (first tokens))
          (is-number (second tokens))
          (is-number (last tokens))
        )
        false
      )
    )
    false))

(defn is-clojure-code [value-as-str]
    (and
        (clojure.string/starts-with? value-as-str "```clojure")
        (clojure.string/ends-with? value-as-str "```")))

(defn is-people [v]
    (and 
        (clojure.string/starts-with? v "@{")
        (clojure.string/ends-with? v "}")))

(defn is-locations [v]
    (and 
        (clojure.string/starts-with? v "*{")
        (clojure.string/ends-with? v "}")))        

(defn is-tags [v]
    (and 
        (clojure.string/starts-with? v "#{")
        (clojure.string/ends-with? v "}")))                

(defn parse-array [v prefix type-key]
    (let [cleaned-v (-> v (strip-prefix prefix) (strip-suffix "}"))
                      items (clojure.string/split cleaned-v ",")
                      items-vec (into [] (map clojure.string/trim items))]
                    (with-meta {:value items-vec} {:type type-key})))

(def date-formatter (date-format/formatters :date))

(defn to-js-date [v]
  (cljs-time.format/parse (cljs-time.format/formatters :date) v))

(defn duration-in-days [d1 d2]
  (println "duration between" d1 d2)
  (date-core/in-days (date-core/interval (to-js-date d1) (to-js-date d2))))

(defn parse-date [v]
  (let [d (to-js-date v)]
    (with-meta {:value v 
                :year (.getYear d) 
                :month (inc (.getMonth d))
                :day (inc (.getDate d))} 
              {:type "date"})
  ))

(defn parse-string-value [val]
  (println "parse-string-value >>>" val)
  (let [v (clojure.string/trim val)]
      (cond
          (nil? v) {:value ""}
          (is-people v) (parse-array v "@{" "people")
          (is-locations v) (parse-array v "*{" "locations")
          (is-tags v) (parse-array v "#{" "tags")
          (is-date v) (parse-date v)
          :else {:value v}
      )))

(defn transform-entity-def-attr [el]
  (debug "transform-entity-def-attr" el)
  (let [attr-name (->> el :content first :content first)
        value-as-str (->> el :content last :content clojure.string/join clojure.string/trim)]
        (if (is-clojure-code value-as-str)
          (let [val_ (strip-prefix value-as-str "```clojure")
                attr-value (strip-suffix val_ "```")]
              {:name  attr-name
                :type  "code"
                :value attr-value}
              )
          {:name  attr-name
          :type  "code"
          :value value-as-str})
        ))

(defn transform-table-value [table-el]
  (let [header-row (first table-el)
        columns (into []
                      (map
                        (fn [col]
                            (->> col :content first clojure.string/trim))
                        (->> header-row :content)))
        rows (drop 1 table-el)
        rows-values (map
                      (fn [row]
                          (map
                            (fn [cell]
                                (->> cell :content first clojure.string/trim))
                            (->> row :content)))
                      rows)
        records (map
                  (fn [row]
                      (let [props
                            (map-indexed
                              (fn [idx val]
                                  {
                                    (keyword (get columns idx)) val})
                              row)]
                            (apply merge props)))
                  rows-values)]
        {:records (into [] records)
        :columns columns}))

(defn transform-entity-inline-attr [el]
  (debug "transform-entity-inline-attr" el)
  (let [tag (->> el :content last :tag)
        attr-name (->> el :content first :content first)
        attr-props (->> el :content last :content first parse-string-value)]
        (debug "done. transform-entity-inline-attr" attr-props)
        (assoc attr-props :name attr-name)))

; TODO if attr is multiline - we need to render markdown.
(defn transform-entity-multiline-attr [el]
  (debug "transform-entity-multiline-attr" el)
  (let [tag (->> el :content last :tag)
        attr-name (->> el :content first :content first)]
        (if (= :entity-table-attr-val tag)
          (let [tag-content (->> el :content last :content)
                table-data (transform-table-value tag-content)]
              {:name  attr-name
                :value (:records table-data)
                :columns (:columns table-data)})
          (if (= :entity-multiline-attr-val tag)
            (let [attr-value (->> el :content last :content clojure.string/join)]
                {:name  attr-name
                  :value attr-value})))))

(defn transform-entity [el]
  (debug "transform-entity" el)
  (let [entity-name (->> el :content first :content first)
        entity-inline-attrs-body (->> el :content last :content first :content)
        entity-multiline-attrs-body (->> el :content last :content last :content)
        entity-body (->> el :content last :content)
        inline-attrs (map transform-entity-inline-attr entity-inline-attrs-body)
        multiline-attrs (map transform-entity-multiline-attr entity-multiline-attrs-body)
        attrs (concat inline-attrs multiline-attrs)
        ent {:name  entity-name :attrs attrs}]
        (reduce 
          (fn [entity attr]
            (assoc entity (keyword (:name attr)) attr)) 
          ent attrs)
        ))

(defn parse-doc [doc]
  (let [doc-str (str doc "\n\n\n\n")
        original-parsed (mntgn-parser doc-str)
        parsed (insta/transform
                  {}
                  original-parsed)]
        (println doc)
        (println "parsed")
        parsed
        ))

(defn remove-code-attrs [ent]
 (reduce 
  (fn [new-ent attr]
   (if (not (= "code" (second (:type attr))))
    (assoc new-ent (first attr) (second attr))
    new-ent)
  )
  {} ent))

(defn eval-attr [ent code-value]
  (debug "evaluate ent attr" ent)
  (let [code-to-eval 
          (str "(let [% " 
            (prn-str (remove-code-attrs (dissoc ent :attrs))) "]" code-value ")")]
        (:value (eval-safe code-to-eval))))



(defn eval-nested-attr [record code-value]
  (debug "eval-nested-attr" record)
  (let [code-to-eval 
          (str "(let [% " 
            (prn-str record) "]" code-value ")")]
        (:value (eval-safe code-to-eval))))        

(defn evaluate-def-attribute-for-each-entity [plain-entities entity-def-attrs]
  (map
    (fn [entity]
      (reduce
        (fn [ent ent-def-attr]
            (debug "evaluate ent def attr" ent-def-attr)
            (let [tokens (clojure.string/split (:name ent-def-attr) ".")]
              (cond
                (= 2 (count tokens)) 
                  (do
                    (let [main-attr (keyword (first tokens))
                          nested-attr (keyword (second tokens))
                          code-value (:value ent-def-attr)
                          old-attr (get ent main-attr)
                          updated-value 
                            (into [] 
                              (map 
                                (fn [row]
                                  (let [attr-val (eval-nested-attr row code-value)]
                                    (debug "new nested attr value" attr-val)
                                    (assoc row nested-attr attr-val))
                                )
                                (:value old-attr)))
                          updated-columns (conj (:columns old-attr) (second tokens))
                          ]
                    (debug "updated-nested-attr-value" updated-value)
                    (println "updated columns" updated-columns)
                    (assoc-in
                      (assoc-in ent [main-attr :value] updated-value)
                      [main-attr :columns] updated-columns
                      )
                  ))
                (= 1 (count tokens))
                  (let [attr-name (keyword (:name ent-def-attr))
                        attr-val (eval-attr ent (:value ent-def-attr))
                        new-attr {:name attr-name :value attr-val}]
                        (assoc ent attr-name attr-val :attrs (concat (:attrs ent) [new-attr])))
                :else ent
            )))
        entity entity-def-attrs))
    plain-entities))

; TODO here we need to update collection attributes
(defn evaluate-collection-attributes [collection]
  (debug "evaluate-collection-attributes" collection)
  (reduce
    (fn [collection collection-attr]
        (println "eval collection code attr" (:type collection-attr) (keyword (:name collection-attr)))
        (if (= "code" (:type collection-attr))
          (let [attr-name (keyword (:name collection-attr))
                ents (map #(dissoc % :attrs) (:entities collection))
                ; TODO we need to path collection name and collection props to eval
                code-to-eval
                  (str "(let [% '" (prn-str ents) "]" (:value collection-attr) ")")
                attr-val (:value (eval-safe code-to-eval))
                new-attr {:name attr-name :value attr-val}]
                  (assoc collection attr-name attr-val :attrs (concat (:attrs collection) [new-attr])))
          ; put back original attribute
          (assoc collection :attrs (concat (:attrs collection) [collection-attr]))
          ))
    ; reset :attrs
    (assoc collection :attrs [])
    (:attrs collection)
    ))

(defn evaluate [parsed-output]
  (debug "evaluate" parsed-output)
  (map
    (fn [collection]
        (let [content (->> collection :content second :content)
              collection-attrs (map transform-collection-attr (get-collection-attributes content))
              entity-def-attrs (map transform-entity-def-attr (get-entity-def-attributes content))
              plain-entities (map transform-entity (get-entities content))
              entities_ (evaluate-def-attribute-for-each-entity plain-entities entity-def-attrs)
              collection_ {:name             (->> collection :content first :content first)
                            :attrs            collection-attrs
                            :entity-def-attrs entity-def-attrs
                            :entities         entities_}
              collection (evaluate-collection-attributes collection_)]
              (debug "evaludated collection" collection_)
              collection))
    parsed-output))

(defn mkdir-safe [dir]
  (try
    (mkdir dir)
    (catch js/Object _)))

(defn render [collections]
  (mkdir-safe "public")
  (doall
    (map
      (fn [collection]
          (println "rendering:" (str "public/" (:name collection)))
          (mkdir-safe (str "public/" (:name collection) "/"))
          (spit (str "public/" (:name collection) "/index.html")
                (:template collection))
          (doall
            (map
              (fn [entity]
                  (println (str "public/" (:name collection) "/" (:id entity) "/"))
                  (mkdir-safe (str "public/" (:name collection) "/" (:id entity) "/"))
                  (spit (str "public/" (:name collection) "/" (:id entity) "/index.html") (:template entity)))
              (:entities collection))))
      collections
      )))

(defn parse [filename]
  (let [doc (slurp filename)
        parsed (parse-doc doc)
        transformed (evaluate parsed)]
        (println "parsed doc")
        (println parsed)
        (debug "transformed doc" transformed)
        (render transformed)
        ;  transformed
        ))


(defn -main [& args]
  (println "start")
  (-> args first parse))

(set! *main-cli-fn* -main)