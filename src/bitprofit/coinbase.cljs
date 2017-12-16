(ns bitprofit.coinbase
  (:require [bitprofit.ajax :as ajax]))

(defonce api "https://api.coinbase.com/v2")

(defn exchange-rates [currency handler error-handler]
  (ajax/GET (str api "/exchange-rates?currency=" currency)
            #(handler (get % "data"))
            error-handler))
