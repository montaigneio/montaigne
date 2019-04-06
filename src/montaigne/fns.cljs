
(ns montaigne.fns
  (:require [cuerdas.core :as cue]
            [cljs.tools.reader :refer [read-string]]
            [cljs.js :refer [empty-state eval js-eval]]
            [cljs.env :refer [*compiler*]]
            ["@thi.ng/hiccup" :as hiccup :refer [serialize]]
            [cljs-time.format :as date-format]
            [cljs-time.core :as date-core]
            [httpurr.status :as s]
            [httpurr.client :as http]
            [httpurr.client.node :as node]
            [promesa.core :as p]
            [promesa.async-cljs :refer-macros [async]]
            ["sync-request" :as request]
            [cljs.pprint :refer [pprint]]
            ))

(def ^:private +slug-tr-map+
  (zipmap "ąàáäâãåæăćčĉęèéëêĝĥìíïîĵłľńňòóöőôõðøśșšŝťțŭùúüűûñÿýçżźž"
          "aaaaaaaaaccceeeeeghiiiijllnnoooooooossssttuuuuuunyyczzz"))

(defn slug
  "Transform text into a URL slug."
  [s]
  (some-> (cue/lower s)
          (clojure.string/escape +slug-tr-map+)
          (cue/replace #"[^\w\s]+" "")
          (cue/replace #"\s+" "_")))

(defn str-to-date [v]
  (cljs-time.format/parse (cljs-time.format/formatters :date) v))

(defn duration-in-days [d1 d2]
  (if (and
        (not (nil? d1))
        (not (nil? d2)))
    (inc (date-core/in-days (date-core/interval (str-to-date d1) (str-to-date d2))))))

(defn get-year [s]
  (.getYear (str-to-date s)))

(defn html [hiccup-str]
  (serialize hiccup-str))

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



(defn decode [body]
  (js->clj (js/JSON.parse body) :keywordize-keys true))

(defn get!
  [url]
  (p/then (node/get url) decode))



(defn process-response
  [response]
  (condp = (:status response)
    s/ok           (p/resolved (:body response))
    s/not-found    (p/rejected :not-found)
    s/unauthorized (p/rejected :unauthorized)))


; TODO do memoization for async calls. just in case
; https://cdn.rawgit.com/konsalex/Airport-Autocomplete-JS/3dbde72e/src/airports.json
(defn get-json-private [url]
  (println "get-json" url)
  (let [resp (request "GET" url)
        body (.getBody resp)
        json-resp (decode body)]
      (println "json")
      (println (count (:airports json-resp))) 
      ;(pprint json-resp) 
      json-resp
    
  )
)
;
(def http-get-json (memoize get-json-private))