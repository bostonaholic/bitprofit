(ns bitprofit.components.table
  (:require [bitprofit.calculator :as calc]
            [bitprofit.formatters :as format]))

(defn table [state]
  (let [hash-time (calc/hash-time (:difficulty @state) (:hash-rate @state))
        blocks-per-year (calc/blocks-per-year hash-time)
        coins-per-year (calc/coins-per-year (:block-reward @state) blocks-per-year)
        revenue-per-year (calc/revenue-per-year coins-per-year (:bitcoin-to-dollar @state))
        power-cost-per-year (calc/power-cost-per-year (:power @state) (:power-cost @state))
        profit-per-year (calc/profit-per-year revenue-per-year power-cost-per-year (:pool-rate @state))
        net-profit (calc/net-profit profit-per-year (:hardware-cost @state))
        pool-fees (calc/pool-fees revenue-per-year (:pool-rate @state))]
    [:table
     [:thead
      [:tr
       [:th "Bitcoins"]
       [:th "Revenue (USD)"]
       [:th "Power Cost (USD)"]
       [:th "Pool Fees (USD)"]
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
