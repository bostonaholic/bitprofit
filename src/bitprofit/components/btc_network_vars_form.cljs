(ns bitprofit.components.btc-network-vars-form)

(defn btc-network-vars-form [state]
  [:form
   [:div.row
    [:div.input-field.col.s6
     [:input#difficulty {:type "text" :value (:difficulty @state)
                         :on-change #(swap! state assoc :difficulty (-> % .-target .-value))}]
     [:label {:for "difficulty"} "Difficulty"]]

    [:div.input-field.col.s6
     [:input#difficulty-change {:type "text" :value (:difficulty-change @state)
                                :on-change #(swap! state assoc :difficulty-change (-> % .-target .-value))}]
     [:label {:for "difficulty-change"} "Difficulty Change (+/-)"]]]

   [:div.row
    [:div.input-field.col.s6
     [:input#block-reward {:type "text" :value (:block-reward @state)
                           :on-change #(swap! state assoc :block-reward (-> % .-target .-value))}]
     [:label {:for "block-reward"} "Block Reward"]]

    [:div.input-field.col.s6
     [:input#bitcoin-to-dollar {:type "text" :value (:bitcoin-to-dollar @state)
                                :on-change #(swap! state assoc :bitcoin-to-dollar (-> % .-target .-value))}]
     [:label {:for "bitcoin-to-dollar"} "BTC to USD ($)"]]]

   [:div.row
    [:div.col.s12.right-align
     [:span "Live Bitcoin data made possible by "
      [:a.tooltipped {:href "https://www.coinbase.com/join/53a04d278317c3e09b00000c"
                      :target "_blank"
                      :data-tooltip "Use my referral link to get $10 of free bitcoin"}
       "Coinbase"]
      "."]]]])
