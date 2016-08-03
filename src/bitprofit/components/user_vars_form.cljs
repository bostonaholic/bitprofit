(ns bitprofit.components.user-vars-form)

(defn user-vars-form [state]
  [:form
   [:div.row
    [:div.medium-4.columns
     [:label.text-right.middle {:for "hash-rate"} "Hash Rate (GH/s)"]]
    [:div.medium-8.columns
     [:input#hash-rate {:type "text" :value (:hash-rate @state)
                        :on-change #(swap! state assoc :hash-rate (-> % .-target .-value))}]]]

   [:div.row
    [:div.medium-4.columns
     [:label.text-right.middle {:for "power"} "Power (W)"]]
    [:div.medium-8.columns
     [:input#power {:type "text" :value (:power @state)
                    :on-change #(swap! state assoc :power (-> % .-target .-value))}]]]

   [:div.row
    [:div.medium-4.columns
     [:label.text-right.middle {:for "power-cost"} "Power Cost ($/kWh)"]]
    [:div.medium-8.columns
     [:input#power-cost {:type "text" :value (:power-cost @state)
                         :on-change #(swap! state assoc :power-cost (-> % .-target .-value))}]]]

   [:div.row
    [:div.medium-4.columns
     [:label.text-right.middle {:for "pool-rate"} "Pool Fees"]]
    [:div.medium-8.columns
     [:input#pool-rate {:type "text" :value (:pool-rate @state)
                        :on-change #(swap! state assoc :pool-rate (-> % .-target .-value))}]]]

   [:div.row
    [:div.medium-4.columns
     [:label.text-right.middle {:for "hardware-cost"} "Hardware Cost ($)"]]
    [:div.medium-8.columns
     [:input#hardware-cost {:type "text" :value (:hardware-cost @state)
                            :on-change #(swap! state assoc :hardware-cost (-> % .-target .-value))}]]]])
