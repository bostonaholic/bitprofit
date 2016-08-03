(ns bitprofit.btc-price-index
  (:require [bitprofit.ajax :as ajax]))

(defonce api "https://api.bitcoinaverage.com")

(defn ticker [api]
  (str api "/ticker"))

(defn global [ticker]
  (str ticker "/global"))

(defn usd [global]
  (str global "/USD"))

(defn get-last [handler error-handler]
  (ajax/GET (-> api ticker global usd)
            #(handler (get % "last"))
            error-handler))
