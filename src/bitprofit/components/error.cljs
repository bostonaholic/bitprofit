(ns bitprofit.components.error)

(defn error [state]
  (when-not (empty? (:error @state))
    [:div.card-panel.red.white-text
     [:span (:error @state)]]))
