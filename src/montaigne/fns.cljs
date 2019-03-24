
(ns montaigne.fns
  (:require [cuerdas.core :as cue]
            ))

(defn slug [prop-value]
      (cue/slug prop-value))
