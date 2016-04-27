(ns bitprofit.components.error)

(defn error [state]
  (when-not (empty? (:error @state))
    [:div.callout.alert.clearfix
     [:span.float-center (:error @state)]]))
