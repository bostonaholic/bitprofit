(ns bitprofit.components.line-chart
  (:require [cljsjs.chartjs]
            [reagent.core :as reagent]))

(defn total-profit-trend [state]
  {:label "Total Profit"
   :data (take (:months state) (repeatedly #(rand-int 1000)))})

(defn datasets [state]
  [(total-profit-trend state)])

(def month {0 "Jan" 1 "Feb" 2 "Mar"
            3 "Apr" 4 "May" 5 "Jun"
            6 "Jul" 7 "Aug" 8 "Sep"
            9 "Oct" 10 "Nov" 11 "Dec"})

(defn next-months [start months]
  (for [n (range months)]
    (str (month (mod (+ (.getMonth start) n) 12))
         " "
         (int (+ (.getFullYear start)
                 (/ (+ (.getMonth start) n) 12))))))

(defn draw-line-chart [dom-node state]
  (let [ctx (.getContext dom-node "2d")
        config {:type "line"
                :data {:labels (next-months (js/Date.) (:months state))
                       :datasets (datasets state)}}]
    (js/Chart. ctx (clj->js config))))

(defn line-chart-inner []
  (let [dom-node (reagent/atom nil)]
    (reagent/create-class
     {:reagent-render (fn [] [:canvas#line-chart])

      :component-did-mount
      (fn [component]
        (let [node (reagent/dom-node component)]
          (reset! dom-node node)))

      :component-did-update
      (fn [component]
        (let [props (reagent/props component)]
          (draw-line-chart @dom-node props)))})))

(defn line-chart [state]
  [line-chart-inner @state])

(defn duration [state]
  [:input#months {:type "text" :value (:months @state)
                  :on-change #(swap! state assoc :months (int (-> % .-target .-value)))}])
