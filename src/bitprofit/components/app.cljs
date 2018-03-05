(ns bitprofit.components.app
  (:require [bitprofit.components.btc-network-vars-form :refer [btc-network-vars-form]]
            [bitprofit.components.error :refer [error]]
            [bitprofit.components.line-chart :refer [duration line-chart]]
            [bitprofit.components.user-vars-form :refer [user-vars-form]]
            [bitprofit.components.table :refer [table]]))

(defn app [state]
  [:div
   [:nav.blue
    [:div.container
     [:nav.nav-wrapper
      [:a.brand-logo "bitprofit.io"]
      [:ul.right
       [:li
        [:a {:href "https://github.com/bostonaholic/bitprofit" :target "_blank"} "Source Code"]]
       [:li
        [:a.btn.orange.tooltipped {:href "https://www.coinbase.com/join/53a04d278317c3e09b00000c"
                                   :target "_blank"
                                   :data-tooltip "Use this referral link to get $10 of free bitcoin"}
         "Buy Bitcoin!"]]]]]]

   [:div.container
    [:div.row
     [error state]]]

   [:div.row
    [:div.col.s6
     [:div.card-panel
      [user-vars-form state]]]

    [:div.col.s6
     [:div.card-panel
      [btc-network-vars-form state]]]]

   #_[:div.row
    [:div.col.s6.push-s6
     [duration state]]]

   #_[:div.row
    [line-chart state]]

   [:div.row
    [table state]]])
