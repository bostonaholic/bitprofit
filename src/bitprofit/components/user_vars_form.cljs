(ns bitprofit.components.user-vars-form)

(defn user-vars-form [state]
  [:form
   [:div.row
    [:div.input-field.col.s6
     [:input#hash-rate {:type "text" :value (:hash-rate @state)
                        :on-change #(swap! state assoc :hash-rate (-> % .-target .-value))}]
     [:label {:for "hash-rate"} "Hash Rate (TH/s)"]]]

   [:div.row
    [:div.input-field.col.s6
     [:input#power {:type "text" :value (:power @state)
                    :on-change #(swap! state assoc :power (-> % .-target .-value))}]
     [:label {:for "power"} "Power (W)"]]

    [:div.input-field.col.s6
     [:input#power-cost {:type "text" :value (:power-cost @state)
                         :on-change #(swap! state assoc :power-cost (-> % .-target .-value))}]
     [:label {:for "power-cost"} "Power Cost ($/kWh)"]]]

   [:div.row
    [:div.input-field.col.s6
     [:input#pool-rate {:type "text" :value (:pool-rate @state)
                        :on-change #(swap! state assoc :pool-rate (-> % .-target .-value))}]
     [:label {:for "pool-rate"} "Pool Fees (%)"]]

    [:div.input-field.col.s6
     [:input#hardware-cost {:type "text" :value (:hardware-cost @state)
                            :on-change #(swap! state assoc :hardware-cost (-> % .-target .-value))}]
     [:label {:for "hardware-cost"} "Hardware Cost ($)"]]]])
