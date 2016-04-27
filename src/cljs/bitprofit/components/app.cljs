(ns bitprofit.components.app
  (:require [bitprofit.components.error :refer [error]]
            [bitprofit.components.form :refer [form]]
            [bitprofit.components.table :refer [table]]))

(defn app [state]
  [:div
   [:div.top-bar
    [:div.top-bar-title
     [:strong "Bitprofit.io"]]]
   
   [:div.row
    [:div.row
     [error state]]

    [:div.row
     [:div.medium-6.columns
      [form state]]]

    [:div.row
     [table state]]]])
