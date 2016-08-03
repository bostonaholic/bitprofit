(ns bitprofit.components.app
  (:require [bitprofit.components.btc-network-vars-form :refer [btc-network-vars-form]]
            [bitprofit.components.error :refer [error]]
            [bitprofit.components.line-chart :refer [line-chart]]
            [bitprofit.components.user-vars-form :refer [user-vars-form]]
            [bitprofit.components.table :refer [table]]
            [cljsjs.chartjs]))

(defn app [state]
  [:div
   [:div.top-bar
    [:div.top-bar-title
     [:strong "Bitprofit.io"]]]
   
   [:div.row [:h2 " "]]

   [:div.row
    [:div.row
     [error state]]

    [:div.row
     [:div.medium-5.columns.callout.success
      [user-vars-form state]]

     [:div.medium-6.columns
      [:div.callout.secondary
       [btc-network-vars-form state]]

      [:div.medium-10.columns
       [:label.text-right.middle {:for "months"} "Months"]]
      [:div.medium-2.columns
       [:input#months {:type "text" :value (:months @state)
                       :on-change #(swap! state assoc :months (int (-> % .-target .-value)))}]]]]

    [:div.row
     [line-chart state]]

    [:div.row
     [table state]]]])
