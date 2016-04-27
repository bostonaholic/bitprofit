(ns bitprofit.components.app
  (:require [bitprofit.components.btc-network-vars-form :refer [btc-network-vars-form]]
            [bitprofit.components.error :refer [error]]
            [bitprofit.components.user-vars-form :refer [user-vars-form]]
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
     [:div.medium-6.columns.callout.success
      [user-vars-form state]]

     [:div.medium-6.columns
      [btc-network-vars-form state]]]

    [:div.row
     [table state]]]])
