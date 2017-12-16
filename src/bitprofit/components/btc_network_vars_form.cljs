(ns bitprofit.components.btc-network-vars-form)

(defn btc-network-vars-form [state]
  [:form
   [:div.row
    [:div.medium-4.columns
     [:label.text-right.middle {:for "difficulty"} "Difficulty"]]
    [:div.medium-8.columns
     [:input#difficulty {:type "text" :value (:difficulty @state)
                         :on-change #(swap! state assoc :difficulty (-> % .-target .-value))}]]]

   [:div.row
    [:div.medium-4.columns
     [:label.text-right.middle {:for "difficulty-change"} "Difficulty Change (+/-)"]]
    [:div.medium-8.columns
     [:input#difficulty-change {:type "text" :value (:difficulty-change @state)
                                :on-change #(swap! state assoc :difficulty-change (-> % .-target .-value))}]]]

   [:div.row
    [:div.medium-4.columns
     [:label.text-right.middle {:for "block-reward"} "Block Reward"]]
    [:div.medium-8.columns
     [:input#block-reward {:type "text" :value (:block-reward @state)
                           :on-change #(swap! state assoc :block-reward (-> % .-target .-value))}]]]

   [:div.row
    [:div.medium-4.columns
     [:label.text-right.middle {:for "bitcoin-to-dollar"} "BTC to USD ($)"]]
    [:div.medium-8.columns
     [:input#bitcoin-to-dollar {:type "text" :value (:bitcoin-to-dollar @state)
                                :on-change #(swap! state assoc :bitcoin-to-dollar (-> % .-target .-value))}]]]

   [:div.row
    [:div.medium-12.columns
     [:span.float-right "Live Bitcoin data made possible by "
      [:a {:href "https://www.coinbase.com/" :target "_blank"} "Coinbase"]
      " and "
      [:a {:href "https://bitcoin.toshi.io/" :target "_blank"} "Toshi"]
      "."]]]])
