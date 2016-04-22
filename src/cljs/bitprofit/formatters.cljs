(ns bitprofit.formatters)

(defn usd [x]
  (str "$" (.toFixed x 2)))
