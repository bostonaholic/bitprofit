(ns bitprofit.core
  (:require [bitprofit.btc-price-index :as price-index]
            [bitprofit.calculator :as calc]
            [bitprofit.formatters :as format]
            [bitprofit.toshi :as toshi]
            [reagent.core :as reagent]))

(defonce app-state (reagent/atom {:error nil
                                  :hash-rate 4730.00
                                  :power 1293.00
                                  :power-cost 0.1231
                                  :pool-rate 0.01
                                  :difficulty 178678307671.68800000
                                  :block-reward 25.00000000
                                  :bitcoin-to-dollar 450.00
                                  :hardware-cost 800.00}))

(defn table [state]
  (let [hash-time (calc/hash-time (:difficulty @state) (:hash-rate @state))
        power-cost-per-year (calc/power-cost-per-year (:power @state) (:power-cost @state))
        blocks-per-year (calc/blocks-per-year hash-time)
        coins-per-year (calc/coins-per-year (:block-reward @state) blocks-per-year)
        revenue-per-year (calc/revenue-per-year coins-per-year (:bitcoin-to-dollar @state))
        pool-fees (calc/pool-fees revenue-per-year (:pool-rate @state))
        profit-per-year (calc/profit-per-year revenue-per-year power-cost-per-year (:pool-rate @state))
        net-profit (calc/net-profit profit-per-year (:hardware-cost @state))
        break-even-days (calc/break-even-days profit-per-year (:hardware-cost @state))]
    [:table
     [:thead
      [:tr
       [:th "Bitcoins"]
       [:th "Revenue (USD)"]
       [:th "Power Cost (USD)"]
       [:th "Pool Fees"]
       [:th "Hardware Costs"]
       [:th "Profit"]
       [:th "Net Profit"]]]
     [:tbody
      [:tr
       [:td coins-per-year]
       [:td (format/usd revenue-per-year)]
       [:td (format/usd power-cost-per-year)]
       [:td (format/usd pool-fees)]
       [:td (format/usd (:hardware-cost @state))]
       [:td (format/usd profit-per-year)]
       [:td (format/usd net-profit)]]]]))

(defn error [state]
  (when-not (empty? (:error @state))
    [:div.callout.alert.clearfix
     [:span.float-center (:error @state)]]))

(defn app [state]
  [:div
   [error state]
   [table state]])

(defn load-from-btc-price-index! []
  (price-index/get-last (fn [amt] (swap! app-state assoc :bitcoin-to-dollar amt))
                        (fn [response] (swap! app-state assoc :error "Unable to retrieve the latest BTC amount."))))

(defn load-from-toshi! []
  (toshi/get-latest (fn [response]
                      (swap! app-state assoc :difficulty (get response "difficulty"))
                      (swap! app-state assoc :block-reward (/ (get response "reward") 100000000.00)))
                    (fn [response])))

(defn main [] 
  (load-from-btc-price-index!)
  (load-from-toshi!)
  (reagent/render-component [app app-state] (.getElementById js/document "bitprofit")))
