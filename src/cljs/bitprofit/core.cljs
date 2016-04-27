(ns bitprofit.core
  (:require [bitprofit.btc-price-index :as price-index]
            [bitprofit.components.app :refer [app]]
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
