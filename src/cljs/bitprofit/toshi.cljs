(ns bitprofit.toshi
  (:require [bitprofit.ajax :as ajax]))

(defonce api "https://bitcoin.toshi.io/api/v0")

(defn blocks [api]
  (str api "/blocks"))

(defn latest [blocks]
  (str blocks "/latest"))

(defn get-latest [handler error-handler]
  (ajax/GET (-> api blocks latest)
            handler
            error-handler))
