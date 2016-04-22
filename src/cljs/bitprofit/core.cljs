(ns bitprofit.core
  (:require [bitprofit.calculator :as calc]
            [bitprofit.formatters :as format]))

(def app-state (atom {:hash-rate 4730.00
                      :power 1293.00
                      :power-cost 0.12 ;; 0.1231
                      :pool-rate 0.01
                      :difficulty 178678307671.68800000 ; API
                      :block-reward 25.00000000 ; API
                      :bitcoin-to-dollar 448.00 ; API
                      :hardware-cost 800.00}))

(def hash-time (calc/hash-time (:difficulty @app-state) (:hash-rate @app-state)))

(def power-cost-per-year (calc/power-cost-per-year (:power @app-state) (:power-cost @app-state)))

(def blocks-per-year (calc/blocks-per-year hash-time))

(def coins-per-year (calc/coins-per-year (:block-reward @app-state) blocks-per-year))

(def revenue-per-year (calc/revenue-per-year coins-per-year (:bitcoin-to-dollar @app-state)))

(def pool-fees (calc/pool-fees revenue-per-year (:pool-rate @app-state)))

(def profit-per-year (calc/profit-per-year revenue-per-year power-cost-per-year (:pool-rate @app-state)))

(def net-profit (calc/net-profit profit-per-year (:hardware-cost @app-state)))

(def break-even-days (calc/break-even-days profit-per-year (:hardware-cost @app-state)))

(defn main []
  (.log js/console "Coins per Year: " coins-per-year)
  (.log js/console "Revenue per Year:" (format/usd revenue-per-year))
  (.log js/console "Power Cost per Year: " (format/usd power-cost-per-year))
  (.log js/console "Pool Fees:" (format/usd pool-fees))
  (.log js/console "Hardware Costs:" (format/usd (:hardware-cost @app-state)))
  (.log js/console "Profit per Year:" (format/usd profit-per-year))
  (.log js/console "Net Profit: " (format/usd net-profit)))
