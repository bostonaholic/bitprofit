(ns bitprofit.components.app
  (:require [bitprofit.components.error :refer [error]]
            [bitprofit.components.table :refer [table]]))

(defn app [state]
  [:div
   [error state]
   [table state]])
