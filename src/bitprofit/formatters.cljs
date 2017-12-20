(ns bitprofit.formatters
  (:require [goog.string :as gstring]
            [goog.string.format :as gformat]))

(defmulti usd type)

(defmethod usd js/String [x]
  (usd (js/Number. x)))

(defmethod usd :default [x]
  (gstring/format "$ %.2f" x))
