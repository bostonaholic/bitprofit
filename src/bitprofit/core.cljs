(ns bitprofit.core
  (:require [bitprofit.coinbase :as coinbase]
            [bitprofit.components.app :refer [app]]
            [bitprofit.toshi :as toshi]
            [reagent.core :as reagent]))

(defonce app-state (reagent/atom {:error nil
                                  :months 12

                                  ;; user defined variables
                                  :hash-rate 5060.00
                                  :power 1278.00
                                  :power-cost 0.10
                                  :pool-rate 0.00
                                  :hardware-cost 0.00

                                  ;; BTC network defined variables
                                  :difficulty 178678307671.68800000
                                  :difficulty-change 0.05
                                  :block-reward 25.00000000
                                  :bitcoin-to-dollar 450.00}))

(defn load-from-toshi! []
  (toshi/get-latest (fn [response]
                      (swap! app-state assoc :difficulty (get response "difficulty"))
                      (swap! app-state assoc :block-reward (/ (get response "reward") 100000000.00)))
                    (fn [response])))

(defn main []
  (load-from-toshi!)
  (coinbase/exchange-rates "BTC"
                           (fn [data] (swap! app-state assoc :bitcoin-to-dollar (get-in data ["rates" "USD"])))
                           (fn [response] (swap! app-state assoc :error "Unable to retrieve the latest BTC amount.")))
  (reagent/render-component [app app-state] (.getElementById js/document "bitprofit")))
