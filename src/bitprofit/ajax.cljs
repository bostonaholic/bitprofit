(ns bitprofit.ajax
  (:require [ajax.core :as ajx]))

;; (defn handler [response] ...)
;; (defn error-handler [response] ...)

(defn GET
  ([url handler error-handler]
   (ajx/GET url {:handler handler
                 :error-handler error-handler
                 :response-format :json}))

  ([url params handler error-handler]
   (ajx/GET url {:params params
                 :handler handler
                 :error-handler error-handler})))

(defn POST
  ([url handler error-handler]
   (ajx/POST url {:handler handler
                  :error-handler error-handler}))

  ([url data handler error-handler]
   (ajx/POST url {:params data
                  :handler handler
                  :error-handler error-handler})))

(defn POST-FILE [url form-data handler error-handler]
  (ajx/POST url {:body form-data
                 :handler handler
                 :error-handler error-handler}))

(defn PATCH [url data handler error-handler]
  (ajx/PATCH url {:params data
                  :handler handler
                  :error-handler error-handler}))

(defn DELETE [url handler error-handler]
  (ajx/DELETE url {:handler handler
                   :error-handler error-handler}))
