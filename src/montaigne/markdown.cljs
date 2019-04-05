(ns montaigne.markdown
    (:require [instaparse.core :as insta :refer-macros [defparser]]
              ["@thi.ng/hiccup" :as hiccup :refer [serialize]]))

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
(def parse-md
  (insta/parser
    "<Blocks> = (Paragraph | Header | List | Ordered | Code | Rule)+
    Header = Line Headerline Blankline+
    <Headerline> = h1 | h2
    h1 = '='+
    h2 = '-'+
    List = Listline+ Blankline+
    Listline = Listmarker Whitespace+ Word (Whitespace Word)* EOL
    <Listmarker> = <'+' | '*' | '-'>
    Ordered = Orderedline+ Blankline+
    Orderedline = Orderedmarker Whitespace* Word (Whitespace Word)* EOL
    <Orderedmarker> = <#'[0-9]+\\.'>
    Code = Codeline+ Blankline+
    Codeline = <Space Space Space Space> (Whitespace | Word)* EOL
    Rule = Ruleline Blankline+
    <Ruleline> = <'+'+ | '*'+ | '-'+>
    Paragraph = Line+ Blankline+
    <Blankline> = Whitespace* EOL
    <Line> = Linepre Word (Whitespace Word)* Linepost EOL
    <Linepre> = (Space (Space (Space)? )? )?
    <Linepost> = Space?
    <Whitespace> = #'(\\ | \\t)+'
    <Space> = ' '
    <Word> = #'\\S+'
    <EOL> = <'\\n'>"))

(def span-elems
  [[#"!\[(\S+)\]\((\S+)\)" (fn [[n href]] (serialize [:img {:src href :alt n}]))]
   [#"\[(\S+)\]\((\S+)\)"  (fn [[n href]] (serialize [:a {:href href} n]))]
   [#"`(\S+)`"             (fn [s] (serialize [:code s]))]
   [#"\*\*(\S+)\*\*"       (fn [s] (serialize [:strong s]))]
   [#"__(\S+)__"           (fn [s] (serialize [:strong s]))]
   [#"\*(\S+)\*"           (fn [s] (serialize [:em s]))]
   [#"_(\S+)_"             (fn [s] (serialize [:em s]))]])

(defn- parse-span [s]
  (let [res (first (filter (complement nil?)
                           (for [[regex func] span-elems]
                             (let [groups (re-matches regex s)]
                               (if groups (func (drop 1 groups)))))))]
    (if (nil? res) s res)))

(defn- output-html [blocks]
  (reduce str
          (for [b blocks]
            (case (first b)
              :List (serialize [:ul (for [li (drop 1 b)] [:li (apply str (map parse-span (drop 1 li)))])])
              :Ordered (serialize [:ol (for [li (drop 1 b)] [:li (apply str (map parse-span (drop 1 li)))])])
              :Header (serialize [(first (last b)) (apply str (map parse-span (take (- (count b) 2) (drop 1 b))))])
              :Code (serialize [:pre [:code (apply str (interpose "<br />" (for [line (drop 1 b)] (apply str (drop 1 line)))))]])
              :Rule (serialize [:hr])
              :Paragraph (serialize [:p (apply str (map parse-span (drop 1 b)))])))))

(def markdown-to-html (comp output-html parse-md))


(defn to-html
  "Parses markup into HTML."
  [markup]
  (try
    (markdown-to-html (str markup "\n\n"))
    (catch js/Object e
        (println "failed to parse markdown" e)
        (println markup)
        markup
    )
  ))