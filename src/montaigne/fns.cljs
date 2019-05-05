
(ns montaigne.fns
  (:require [cuerdas.core :as cue]
            [cljs.tools.reader :refer [read-string]]
            [cljs.js :refer [empty-state eval js-eval]]
            [cljs.env :refer [*compiler*]]
            [cljs-node-io.core :as io :refer [slurp spit]]
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

(defn read-file [filename]
  (let [content (slurp filename)]
    (println "read file")
    content))

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

(def eval-state (empty-state))

(defn eval-str [s]
  (eval
    eval-state
    (read-string s)
    {:eval        js-eval
     :source-map  false
     :verbose     true
     :context     :expr
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


(defn get-json-private [url]
  (println "get-json start" url)
  (let [resp (request "GET" url)
        body (.getBody resp)
        json-resp (decode body)]
      (println "get-json finished" url)
      json-resp))
;
(def http-get-json (memoize get-json-private))

; great circle using haversine formula
(defn convert-degrees-to-radians [degrees]
  (/
    (* degrees js/Math.PI)
    180))

(defn sin [a]
  (.sin js/Math a))

(defn cos [a]
  (.cos js/Math a))

(defn atan [a b]
  (.atan2 js/Math a b))

(defn sqrt [a]
  (.sqrt js/Math a))

(defn floor [a]
  (.floor js/Math a))

(def EART_RADIUS_KM 6371)

(defn calc-distance [lat1 lon1 lat2 lon2]
  (let [dlat (convert-degrees-to-radians (- lat2 lat1))
        dlon (convert-degrees-to-radians (- lon2 lon1))
        lat1-rad (convert-degrees-to-radians lat1)
        lat2-rad (convert-degrees-to-radians lat2)
        dlat-sin (sin (/ dlat 2))
        dlon-sin (sin (/ dlon 2))
        lat1-cos (cos lat1-rad)
        lat2-cos (cos lat2-rad)
        a (+ (* dlat-sin dlat-sin)
             (* dlon-sin dlon-sin lat1-cos lat2-cos))
        c (* 2 (atan (sqrt a) (sqrt (- 1 a))))
        distance (* EART_RADIUS_KM c)]
        (floor distance)))
        
(defn kms-to-miles [kms]
  (* kms 0.621371))        

(defn format-float [n points]
  (js/parseFloat (.toFixed n points)))

; source: https://www.coolearth.org/cool-earth-carbon/ https://carbonfund.org/individuals/
; Carbon Fund estimates they offset 1 metric tonne per $10 USD
; Cool Earth estimates they mitigate 1 metric tonne per 25 pence (.32 USD in Dec 2018)
; carbon impact by metric tonnes, offset cost, mitigation cost
(defn calc-carbon [distance]
  (let [miles (kms-to-miles distance)]
    ; source: http://lipasto.vtt.fi/yksikkopaastot/henkiloliikennee/ilmaliikennee/ilmae.htm
    ; if short-haul, 14.7 ounces/miles = .000416738 metric tonnes/mile
    ; if long-haul, 10.1 ounces/miles = .0002863302 metric tonnes/mile
    (if (> 288 miles)
      (format-float (* miles 0.000416738) 2)
      (format-float (* miles 0.0002863302) 2))))
