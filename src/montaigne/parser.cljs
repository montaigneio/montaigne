(ns montaigne.parser
  (:require-macros [hiccups.core :as hiccups :refer [html]])
  (:require [instaparse.core :as insta :refer-macros [defparser]]
            [cljs-node-io.core :as io :refer [slurp spit]]
            [cljs.tools.reader :refer [read-string]]
            [cljs.js :refer [empty-state eval js-eval]]
            [cljs.env :refer [*compiler*]]
            [cljs.pprint :refer [pprint]]
            [cuerdas.core :as cue]
            ))

(println "parser start...")

(defn slug [prop-name entity]
      (cue/slug (get entity prop-name)))

(defn eval-str [s]
      (eval
        (empty-state)
        (read-string s)
        {:eval       js-eval
         :source-map false
         :context    :expr}
        (fn [result] result)))


(def mntgn-parser
  (insta/parser
    "<root> = collection+
     collection = collection-header collection-body
     
     <collection-header> = <collection-header-mark> <space> collection-name <blankline>
     collection-name = #'[a-zA-Z0-9 ]+'
     collection-body = collection-attrs entity-def-attrs entities
     
     collection-attr = (collection-inline-attr | collection-multiline-attr)
     collection-attrs = collection-attr*
     entity-def-attrs = entity-inline-def-attr*
     entities = entity*
     
     entity-inline-def-attr = <entity-inline-attr-mark> entity-inline-def-attr-name <semicolon> <space> entity-inline-def-attr-val <newline>+
     entity-inline-def-attr-name = attribute-name
     entity-inline-def-attr-val = inline-code
     
     inline-code = <'`'> '(' #'[a-zA-Z0-9 :%./]+' ')' <'`'>

     collection-attr-name = attribute-name

     collection-inline-attr = collection-attr-name <semicolon> <space> collection-inline-attr-val <newline>+
     collection-inline-attr-val = #'[a-zA-Z0-9àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœ\\-’\\'.?!\\:;, ]+'

     collection-multiline-attr = collection-multiline-attr-header collection-multiline-attr-val <newline>*
     collection-multiline-attr-header = <multiline-attr-header-mark> <space> collection-attr-name <blankline>
     <collection-attr-val-line> = #'[a-zA-Z0-9àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœ\\(\\)\\[\\]\\{\\}\\-’\\'.?!\\:;, %`\t]+' '\n'+
     collection-multiline-attr-val = collection-attr-val-line+
     

     entity = entity-header entity-body <newline>+
     
     <entity-header> = <entity-header-mark> <space> entity-name <blankline>
     entity-name = #'[a-zA-Z0-9 ]+'
     entity-body = (entity-inline-attr | entity-multiline-attr)+
     
     entity-attr-name = attribute-name
     entity-inline-attr = entity-attr-name <semicolon> <space> entity-inline-attr-val <newline>+
     entity-inline-attr-val = 
        people-list | 
        tags-list |
        places-list | 
        #'[a-zA-Z0-9àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœ\\-’\\'.?!\\:;, ]+'
     
     entity-multiline-attr = entity-multiline-attr-header (entity-table-attr-val | entity-multiline-attr-val) <newline>*
     <entity-multiline-attr-header> = <multiline-attr-header-mark> <space> entity-attr-name <blankline>
     <entity-attr-val-line> = #'[a-zA-Z0-9àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœ\\(\\)\\[\\]\\{\\}\\-’\\'.?!\\:;, ]+' '\n'+
     entity-multiline-attr-val = entity-attr-val-line+

     entity-table-attr-val = row <delimiter-row> row*
     row = column (<'|'> column)+ <newline>
     delimiter-column = space* '-'+ space*
     delimiter-row = delimiter-column (<'|'> delimiter-column)+ <newline>
     column = <space>* (date |
              places-list | 
              people-list | 
              #'[a-zA-Z0-9àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœ\\-’\\', ]+') <space>*


     people-list = <'@{'> #'[a-zA-Z0-9àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœ\\-’\\', ]+' <'}'>
     tags-list = <'#{'> #'[a-zA-Z0-9àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœ\\-’\\', ]+' <'}'>
     places-list = <'${'> #'[a-zA-Z0-9àèìòùÀÈÌÒÙáéíóúýÁÉÍÓÚÝâêîôûÂÊÎÔÛãñõÃÑÕäëïöüÿÄËÏÖÜŸçÇßØøÅåÆæœ\\-’\\', ]+' <'}'>

     <attribute-name> = #'[a-zA-Z0-9]+'
     collection-header-mark = '#'
     entity-header-mark = '##'
     multiline-attr-header-mark = '###'
     entity-inline-attr-mark = '@'
     <text> = #'[^`#*\n{2}]+'
     indentation = (space space) | '\t'
     semicolon = ':'
     space = ' '
     
     comma = ','
     date = #'[0-9]' #'[0-9]' #'[0-9]' #'[0-9]' '-' #'[0-9]' #'[0-9]' '-' #'[0-9]' #'[0-9]'
     punctuation = '.' | '?' | '!' | ':' | ';'
     newline = #'\n'
     blankline = #'\n\n'" :output-format :enlive))


(defn get-collection-attributes [els]
      (->>
        (filter #(= :collection-attrs (:tag %)) els)
        first
        :content))

(defn get-entity-def-attributes [els]
      (filter #(= :entity-def-attrs (:tag %)) els))

(defn get-entities [els]
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
            value-as-str (cue/trim (cue/join value-lines-list))
            attr-name (->> el :content first :content first :content first)]
           (if (and
                  (cue/starts-with? value-as-str "```clojure")
                  (cue/ends-with? value-as-str "```"))
            (let [val_ (cue/strip-prefix value-as-str "```clojure")
                  attr-value (cue/strip-suffix val_ "```")]
                  {:name  attr-name
                   :type "code" 
                   :value attr-value}
                  )
            {:name  attr-name
             :value value-as-str}
           ) 
           ))


(defn transform-entity-def-attr [el]
      (let [attr-name (->> el :content first :content first :content first)
            attr-value (->> el :content first :content last :content first :content cue/join)]
           {:name  attr-name
            :type "code"
            :value attr-value}))

(defn transform-table-value [table-el]
      (let [header-row (first table-el)
            columns (into []
                          (map
                            (fn [col]
                                (->> col :content first cue/trim))
                            (->> header-row :content)))
            rows (drop 1 table-el)
            rows-values (map
                          (fn [row]
                              (map
                                (fn [cell]
                                    (->> cell :content first cue/trim))
                                (->> row :content)))
                          rows)
            records (map
                      (fn [row]
                          (let [props (map-indexed
                                        (fn [idx val]
                                            {
                                             (keyword (get columns idx)) val})
                                        row)]
                               (apply merge props)))
                      rows-values)]
           records))

(defn transform-entity-attr [el]
      (println "attr>>" (->> el :content last :tag))
      (pprint el)
      (let [tag (->> el :content last :tag)
            attr-name (->> el :content first :content first)]
           (if (= :entity-table-attr-val tag)
             (let [tag-content (->> el :content last :content)
                   attr-value (transform-table-value tag-content)]
                  {:name  attr-name
                   :value attr-value})
             (if (= :entity-multiline-attr-val tag)
               (let [attr-value (->> el :content last :content (cue/join ""))]
                    {:name  attr-name
                     :value attr-value})
               (let [attr-value (->> el :content last :content first)]
                    {:name  attr-name
                     :value attr-value})))))

(defn transform-entity [el]
      (pprint el)
      (let [entity-name (->> el :content first :content first)
            entity-body (->> el :content last :content)
            attrs (map transform-entity-attr entity-body)]
           {:name  entity-name
            :attrs attrs}))

(defn parse-doc [doc]
  (let [original-parsed (mntgn-parser doc)
      parsed (insta/transform
                     {
                      :date
                      (fn [hello]
                          (println ">>>" hello)
                          "")
                      ;  :column cue/trim
                      :people-list
                      (fn [value]
                          (map cue/trim
                               (cue/split value ",")))
                      :tags-list
                      (fn [value]
                          (map cue/trim
                               (cue/split value ",")))
                      :places-list
                      (fn [value]
                          (map cue/trim
                               (cue/split value ",")))}
                     original-parsed)]
    (println doc)
    (pprint original-parsed)
    parsed                     
                     )
)

(defn evaluate-def-attribute-for-each-entity [plain-entities]
  (map
                          (fn [entity]
                              (reduce
                                (fn [ent ent-def-attr]
                                    (let [attr-name (keyword (:name ent-def-attr))
                                          code-to-eval
                                          (str "(let [% " (prn-str (dissoc ent :attrs)) "]" (:value ent-def-attr) ")")
                                          attr-val (:value (eval-str code-to-eval))
                                          new-attr {:name attr-name :value attr-val}]
                                         ; (println "code to eval" code-to-eval)
                                         ; (println (eval-str code-to-eval))
                                         ; (println "done eval")
                                         (assoc ent attr-name attr-val :attrs (concat (:attrs ent) [new-attr])))
                                    )
                                entity entity-def-attrs))
                          plain-entities)
)

(defn evaluate [parsed-output]
  (map
              (fn [collection]
                  (let [content (->> collection :content second :content)
                        attrs (map transform-collection-attr (get-collection-attributes content))
                        entity-def-attrs (map transform-entity-def-attr (get-entity-def-attributes content))
                        plain-entities (map transform-entity (get-entities content))
                        entities (evaluate-def-attribute-for-each-entity plain-entities)]
                       {:name             (->> collection :content first :content first)
                        :attrs            attrs
                        :entity-def-attrs entity-def-attrs
                        :entities         entities}))
              parsed-output)
)

(defn parse [filename]
      (let [doc (slurp filename)
            parsed (parse-doc doc)
            transformed (evaluate parsed)]
           (pprint transformed)
           transformed))


 (defn -main [& args]
      (println "start")
       (-> args first parse))

 (set! *main-cli-fn* -main)