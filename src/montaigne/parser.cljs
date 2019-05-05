(ns montaigne.parser
  (:require [instaparse.core :as insta :refer-macros [defparser]]
            [cljs-node-io.core :as io :refer [slurp spit]]
            [cljs-node-io.fs :as fs :refer [mkdir rm-r]]
            [cljs.pprint :refer [pprint]]
            [xregexp]
            [cuerdas.core :as cue]
            [montaigne.fns :as fns]
            [montaigne.markdown :as md]
            ["@thi.ng/hiccup" :as hiccup]
            ))

(println "parser start...")

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


(defn eval-safe [s]
  (try 
    (fns/eval-str s)
    (catch js/Object e
      (do
        (println "eval code failed...")
        (println "error:")
        (println e)
        (println s)
        (println "...eval code failed")
        {:value ""}))))        


(def mntgn-parser
  (insta/parser
    "<root> = page* collection*
     collection = collection-header collection-body
     page = page-header collection-body

     <page-header> = <collection-header-mark> <space> <'@'> page-name <blankline>
     page-name = #'[a-zA-Z0-9 ]+'

     <collection-header> = <collection-header-mark> <space> collection-name <blankline>
     collection-name = #'[a-zA-Z0-9 ]+'
     collection-body = collection-attrs entity-def-attrs entities

     collection-attr = (collection-inline-attr | collection-multiline-attr)
     collection-attrs = collection-attr*
     <entity-def-attr>  = (entity-inline-def-attr | entity-multiline-def-attr)
     entity-def-attrs = entity-def-attr*

     entities = entity*

     def-attr-name = #'[a-zA-Z0-9.-]+'
     <inline-code> = <'`'> #'[^`]+' <'`'>

     entity-inline-def-attr = <entity-inline-attr-mark> def-attr-name <colon> <space> entity-inline-def-attr-val <space>* <newline>+
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
     entity-name = #'[^\n]+'
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

(defn is-multiline-code [value-as-str]
  (and
    (clojure.string/starts-with? value-as-str "```clojure")
    (clojure.string/ends-with? value-as-str "```")))

(defn is-sinlge-line-code [value-as-str]
  (and
    (clojure.string/starts-with? value-as-str "`")
    (clojure.string/ends-with? value-as-str "`")))  

;{:tag :collection-inline-attr, :content ({:tag :collection-attr-name, :content (description)} {:tag :collection-inline-attr-val, :content (Collection of books)}
(defn transform-collection-attr [el]
  (debug "transform-collection-attr" el)
  (let [value-lines-list (->> el :content first :content last :content)
        value-as-str (clojure.string/trim (clojure.string/join value-lines-list))
        attr-name (->> el :content first :content first :content first)]
        (if (is-multiline-code value-as-str)
          (let [val_ (strip-prefix value-as-str "```clojure")
                attr-value (strip-suffix val_ "```")]
              {:name  attr-name
                :type  "code"
                :value attr-value})
          (if (is-sinlge-line-code value-as-str)
            (let [val_ (strip-prefix value-as-str "`")
                  attr-value (strip-suffix val_ "`")]
                {:name  attr-name
                  :type  "code"
                  :value attr-value})
            {:name  attr-name
             :value value-as-str}
            ))))

(defn is-number [str-value]
  (> (js/parseInt str-value) 0))

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

(defn parse-string-value [val]
  (println "parse-string-value >>>" val)
  (let [v (clojure.string/trim val)]
      (cond
          (nil? v) {:value ""}
          (is-people v) (parse-array v "@{" "people")
          (is-locations v) (parse-array v "*{" "locations")
          (is-tags v) (parse-array v "#{" "tags")
          ; (is-date v) (parse-date v)
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
  ; (debug "transform-entity-inline-attr" el)
  (let [tag (->> el :content last :tag)
        attr-name (->> el :content first :content first)
        attr-props (->> el :content last :content first parse-string-value)]
        (debug "done. transform-entity-inline-attr" attr-props)
        (assoc attr-props :name attr-name)))

; TODO if attr is multiline - we need to render markdown.
(defn transform-entity-multiline-attr [el]
  ; (debug "transform-entity-multiline-attr" el)
  (let [tag (->> el :content last :tag)
        attr-name (->> el :content first :content first)]
        (if (= :entity-table-attr-val tag)
          (let [tag-content (->> el :content last :content)
                table-data (transform-table-value tag-content)
                records (:records table-data)
                columns (if (empty? records) [] (:columns table-data))]
              {:name  attr-name
               :value records
               :columns columns})
          (if (= :entity-multiline-attr-val tag)
            (let [attr-value (->> el :content last :content clojure.string/join md/to-html)]
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
  (println "parse doc start...")
  (let [doc-str (str doc "\n\n\n\n")
        original-parsed (mntgn-parser doc-str)]
        (println "parsed doc...")
        original-parsed
        ))

(defn remove-code-attrs [ent]
 (reduce
  (fn [new-ent attr]
    (if (not (= "code" (second (:type attr))))
      (assoc new-ent (first attr) (second attr))
      new-ent))
  {} ent))

(defn eval-attr [ent code-value data]
  (debug "eval-attr" (:name ent))
  (debug "eval-attr-with-data" (:name data))
  (let [code-to-eval 
          (str "(let [% " 
              (prn-str (remove-code-attrs (dissoc ent :attrs))) 
            "]" code-value ")")]
        (:value (eval-safe code-to-eval))))


(defn eval-nested-attr [record ent code-value data]
  (debug "eval-nested-attr" record)
  (debug "eval-nested-attr-with-data" (:name data))
  (let [code-to-eval 
          (str "(let [% " 
            (prn-str record) 
            "%% " (prn-str (remove-code-attrs (dissoc ent :attrs)))
            ; " %data " (prn-str data)
            "]" code-value ")")]
        (:value (eval-safe code-to-eval))))        

(defn evaluate-def-attribute-for-each-entity [plain-entities entity-def-attrs data]
  (map
    (fn [entity]
      (reduce
        (fn [ent ent-def-attr]
            (debug "evaluate ent def attr" (:name ent-def-attr))
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
                                  (let [attr-val (eval-nested-attr row ent code-value data)]
                                    (debug "new nested attr value" attr-val)
                                    (assoc row nested-attr attr-val))
                                )
                                (:value old-attr)))
                          updated-columns 
                          (if (empty? (:columns old-attr)) 
                            [] 
                            (conj (:columns old-attr) (second tokens)))
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
                        attr-val (eval-attr ent (:value ent-def-attr) data)
                        new-attr {:name attr-name :value attr-val}]
                        (assoc ent attr-name new-attr :attrs (concat (:attrs ent) [new-attr])))
                :else ent
            )))
        entity entity-def-attrs))
    plain-entities))

(defn build-collection-attr-eval-code [collection collection-attr]
  (if (= "data" (:name collection))
      ; name of the variable will be `%prop-name`
      (str "(def %" (:name collection-attr) " " (:value collection-attr) ")")
      (let [ents (map #(dissoc % :attrs) (:entities collection))]
        (str "(let [% '" (prn-str ents) "]" (:value collection-attr) ")"))
  ))

; TODO here we need to update collection attributes
(defn evaluate-collection-attributes [collection]
  (debug "evaluate-collection-attributes" collection)
  (reduce
    (fn [collection collection-attr]
        (println "eval collection code attr" (:type collection-attr) (keyword (:name collection-attr)))
        (if (= "code" (:type collection-attr))
          (let [attr-name (keyword (:name collection-attr))
                code-to-eval (build-collection-attr-eval-code collection collection-attr)
                attr-val (:value (eval-safe code-to-eval))
                new-attr {:name attr-name :value attr-val}]
                  (println "evaluated collection attr")
                  (assoc collection attr-name new-attr :attrs (concat (:attrs collection) [new-attr])))
          ; put back original attribute
          (assoc collection :attrs (concat (:attrs collection) [collection-attr]))
          
          ))
    ; reset :attrs
    (assoc collection :attrs [])
    (:attrs collection)
    ))

(defn evaluate-page-attributes [page all-records data]
  (debug "evaluate-page-attributes" page)
  (reduce
    (fn [page page-attr]
        (println "eval page code attr" (:type page-attr) (keyword (:name page-attr)))
        (if (= "code" (:type page-attr))
          (let [attr-name (keyword (:name page-attr))
                code-to-eval
                  (str "(let [% '" (prn-str all-records) 
                              "]" (:value page-attr) ")")
                attr-val (:value (eval-safe code-to-eval))
                new-attr {:name attr-name :value attr-val}]
                  (assoc page attr-name new-attr :attrs (concat (:attrs page) [new-attr])))
          ; put back original attribute
          (assoc page :attrs (concat (:attrs page) [page-attr]))
          ))
    ; reset :attrs
    (assoc page :attrs [])
    (:attrs page)
    ))  

(defn evaluate-collection [name content collection-attrs data]
  (debug "evaluate-collection" name)
  (let [entity-def-attrs (map transform-entity-def-attr (get-entity-def-attributes content))
        plain-entities (map transform-entity (get-entities content))
        entities_ (evaluate-def-attribute-for-each-entity plain-entities entity-def-attrs data)
        collection_ {
          :name name
          :type "collection"
          :attrs collection-attrs
          :entity-def-attrs entity-def-attrs
          :entities entities_}
        collection (evaluate-collection-attributes collection_)]
        (debug "evaluated collection" name)
        (debug "evaluated collection attrs" collection-attrs)
        collection))


(defn get-collection-name [envlive-struct]
  (->> envlive-struct :content first :content first))

(defn get-data [data-collection]
  (debug "get-data" data-collection)
  (let [tag (:tag data-collection)
        name (get-collection-name data-collection)
        content (->> data-collection :content second :content)
        collection-attrs (map transform-collection-attr (get-collection-attributes content))
        data-collection (evaluate-collection name content collection-attrs {})]
      (into [] (:attrs data-collection))
      
  ))


(defn evaluate [parsed-output]
  ; records are collections and pages. pages are not evaluated
  (let [data-collection (first (filter #(= (get-collection-name %) "data") parsed-output))
        data (get-data data-collection)
        collections-or-pages  (remove #(= (get-collection-name %) "data") parsed-output)
        records 
        (map
          (fn [collection-or-page]
            (debug "evaluate each collection or page" collection-or-page)
            (let [tag (:tag collection-or-page)
                  name (get-collection-name collection-or-page)
                  content (->> collection-or-page :content second :content)
                  collection-attrs (map transform-collection-attr (get-collection-attributes content))]
              (if (= :page tag)
                ;page
                {:name name
                  :type "page"
                  :attrs collection-attrs}
                ;collection
                (evaluate-collection name content collection-attrs data)
              )
            )
          )
          collections-or-pages)]
    (map
      (fn [collection-or-page]
        (if (= "page" (:type collection-or-page))
          (evaluate-page-attributes collection-or-page records data)
          collection-or-page
        )
      )
      records
    )      
    ))

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
          (if (= "index" (:name collection))
            (do
              (spit (str "public/index.html")
                    (->> collection :template :value))
            )
            (do
              (mkdir-safe (str "public/" (:name collection) "/"))
              (spit (str "public/" (:name collection) "/index.html")
                    (->> collection :template :value))
            )
          )
          
          (doall
            (map
              (fn [entity]
                  (println (str "public/" (:name collection) "/" (->> entity :id :value) "/"))
                  (mkdir-safe (str "public/" (:name collection) "/" (->> entity :id :value) "/"))
                  (spit (str "public/" (:name collection) "/" (->> entity :id :value) "/index.html") (->> entity :template :value)))
              (:entities collection))))
      collections
      )))

(defn parse [filename]
  (let [doc (fns/read-file filename)
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
