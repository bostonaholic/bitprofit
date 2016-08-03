(ns bitprofit.formatters)

(defmulti usd type)

(defmethod usd js/String [x]
  (usd (js/Number. x)))

(defmethod usd :default [x]
  (str "$" (.toFixed x 2)))
