(ns bitprofit.formatters)

(defmulti usd type)

(defmethod usd js/String [x]
  (usd (js/Number. x)))

(defmethod usd :default [x]
  (str "$ "
       (-> x
           (.toFixed 2)
           (.replace (js/RegExp. "(\\d)(?=(\\d{3})+\\.)" "g") "$1,"))))
