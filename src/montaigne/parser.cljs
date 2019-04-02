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
            ; [cljs-time.format :as date-format]
    ; [eval-soup.core :as eval-soup]
            ))

(println "parser start...")



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

;(def date-formatter (date-format/formatters :date))
;(cljs-time.format/parse (cljs-time.format/formatters :date) "2010-03-11")
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
            ; (println "result>>" result)
            result
            )))


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

     def-attr-name = attribute-name
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
      (println "get-collection-attributes..")
      (pprint els)
      (->>
        (filter #(= :collection-attrs (:tag %)) els)
        first
        :content))

(defn get-entity-def-attributes [els]
      (println "get-entity-def-attributes..")
      (pprint els)
      (let [attrs (->>
                    (filter #(= :entity-def-attrs (:tag %)) els)
                    first
                    :content)]
           (do
             (println "get-entity-def-attributes. done.")
             (pprint attrs)
             attrs
             )
           ))

(defn get-entities [els]
      (println "get-entities..")
      (pprint els)
      (->>
        (filter #(= :entities (:tag %)) els)
        first
        :content))

;{:tag :collection-inline-attr, :content ({:tag :collection-attr-name, :content (description)} {:tag :collection-inline-attr-val, :content (Collection of books)}
(defn transform-collection-attr [el]
      (println "transform collection attr")
      (pprint el)
      (pprint (->> el :content first :content last :content))
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
                    (with-meta items-vec {:type type-key}))
    )

(defn parse-string-value [val]
    (println "parse-string-value >>>" val)
    (let [v (clojure.string/trim val)]
        (cond
            (nil? v) ""
            (is-people v) (parse-array v "@{" "people")
            (is-locations v) (parse-array v "*{" "locations")
            (is-tags v) (parse-array v "#{" "tags")
            :else v
        )))

(defn transform-entity-def-attr [el]
      (println "transform-entity-def-attr")
      (pprint el)
      (println "--")
      (pprint (->> el :content first))
      (println "--")
      (pprint (->> el :content last :content clojure.string/join clojure.string/trim))
      (println "--")
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
  (println "transform-entity-inline-attr")
  (println (->> el :content last :tag))
  (pprint el)
  (let [tag (->> el :content last :tag)
        attr-name (->> el :content first :content first)
        attr-value (->> el :content last :content first parse-string-value)]
        {:name  attr-name
        :value attr-value}
        ))

(defn transform-entity-multiline-attr [el]
  (println "transform-entity-multiline-attr")
  (println (->> el :content last :tag))
  (pprint el)
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
      (println "transform entity.")
      (println el)
      (println "transform entity2")
      (pprint (->> el :content last))
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
              (assoc entity (keyword (:name attr)) attr)
            ) ent attrs)
           ))

(defn parse-doc [doc]
  (let [doc-str (str doc "\n\n\n\n")
        original-parsed (mntgn-parser doc-str)
        parsed (insta/transform
                  {}
                  original-parsed)]
        (println doc)
        (println "parsed")
        ;  (println parsed)
        parsed
        ))

(defn parse-collections [collections]
  (map
    (fn [collection]
      (let [content_ (clojure.string/triml (clojure.string/join "\n" (map second (:lines collection))))
            content (str content_)
            parsed (mntgn-parser content)
            ; parsed (insta/transform
            ;       {}
            ;       original-parsed)
            ]
            (println "collection content")
            (println content)
            (println "parsed collection")
            (println parsed)
            ; TODO name of the collection is lost
            (first parsed)
            )
    )
    collections
  )
)        
(defn remove-code-attrs [ent]
 (reduce 
  (fn [new-ent attr]
   (if (not (= "code" (second (:type attr))))
    (assoc new-ent (first attr) (second attr))
    new-ent
   )
  )
  {} ent)
)
(defn eval-attr [ent attr-value]
  (println "evaluate ent attr")
  (let [
        ; TODO disocc only `code` attributes from attr list and also from the ent
        code-to-eval 
          (str "(let [% " 
            (prn-str (remove-code-attrs (dissoc ent :attrs))) "]" attr-value ")")]
        (try
          (:value (eval-str code-to-eval))
          (catch js/Object e
            (do
            (println "failed" e)
              (println "---1")
              (println (dissoc ent :attrs :id))
              (println "---2")
              (println code-to-eval)
              (println "---3")

            ""
            )
          ))))

(defn evaluate-def-attribute-for-each-entity [plain-entities entity-def-attrs]
      (map
        (fn [entity]
            (reduce
              (fn [ent ent-def-attr]
                  (println "evaluate ent attr")
                  ; (pprint ent)
                  (pprint ent-def-attr)
                  (let [attr-name (keyword (:name ent-def-attr))
                        attr-val (eval-attr ent (:value ent-def-attr))
                        new-attr {:name attr-name :value attr-val}]
                       ; (println "code to eval" code-to-eval)
                       ; (println (eval-str code-to-eval))
                       ; (println "done eval")
                       (assoc ent attr-name attr-val :attrs (concat (:attrs ent) [new-attr])))
                  )
              entity entity-def-attrs))
        plain-entities))

; TODO here we need to update collection attributes
(defn evaluate-collection-attributes [collection]
      (println "eval collection attrs")
      (pprint collection)
      (reduce
        (fn [collection collection-attr]
            (println "eval code attr" (:type collection-attr))
            (if (= "code" (:type collection-attr))
              (let [attr-name (keyword (:name collection-attr))
                    ents (map #(dissoc % :attrs) (:entities collection))
                    code-to-eval
                    (str "(let [% '" (prn-str ents) "]" (:value collection-attr) ")")
                    attr-val (:value (eval-str code-to-eval))
                    new-attr {:name attr-name :value attr-val}]
                   (println "coll attr. code to eval" code-to-eval attr-name)
                   (println attr-val)
                   (println "done eval")
                   (assoc collection attr-name attr-val :attrs (concat (:attrs collection) [new-attr])))
              ; put back original attribute
              (assoc collection :attrs (concat (:attrs collection) [collection-attr]))
              ))
        ; reset :attrs
        (assoc collection :attrs [])
        (:attrs collection)
        ))

(defn evaluate [parsed-output]
      (println "evaluate output>>")
      (pprint parsed-output)
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
                  (println "collection data>>>")
                  (pprint collection_)
                 collection))
        parsed-output))

(defn mkdir-safe [dir]
  (try
    (mkdir dir)
    (catch js/Object _
    )
  )
)

(defn render [collections]
      ; (rm-r "public")
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
                      (spit (str "public/" (:name collection) "/" (:id entity) "/index.html") (:template entity))
                      )
                  (:entities collection))))
          collections
          )))

(defn parse [filename]
      (let [doc (slurp filename)
            ; lines (clojure.string/split doc "\n")
            ; indexed-lines (into [] (map-indexed vector lines))
            ; raw-collections (find-collections indexed-lines)
            ; parsed (parse-collections raw-collections)
            parsed (parse-doc doc)
            transformed (evaluate parsed)
            ]
           ;  parsed
          ;  (pprint raw-collections)
           (println "parsed doc")
           (println parsed)
           (println "transformed doc")
           (pprint transformed)
           (render transformed)
           ;  transformed
           ))


(defn -main [& args]
      (println "start")
      (-> args first parse))

(set! *main-cli-fn* -main)