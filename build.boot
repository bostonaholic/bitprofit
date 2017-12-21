(set-env!
 :source-paths #{"src" "dev"}
 :resource-paths #{"html"}
 :dependencies '[[adzerk/boot-cljs "2.1.4" :scope "test"]
                 [crisptrutski/boot-cljs-test "0.3.4" :scope "test"]
                 [pandeiro/boot-http "0.8.3" :scope "test"]
                 [hashobject/boot-s3 "0.1.3-SNAPSHOT" :scope "test"]
                 [environ "1.1.0" :scope "test"]
                 [boot-deps "0.1.9" :scope "test"]

                 [org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.8.0-alpha2"]
                 [cljs-ajax "0.7.3"]
                 [cljsjs/chartjs "2.6.0-0"]])

(require
 '[adzerk.boot-cljs :refer [cljs]]
 '[crisptrutski.boot-cljs-test :refer [test-cljs]]
 '[pandeiro.boot-http :refer [serve]]
 '[hashobject.boot-s3 :refer [s3-sync]]
 '[environ.core :refer [env]]
 '[boot-deps :refer [ancient]])

(task-options! test-cljs {:js-env :phantom})

(deftask test-once
  "Run tests."
  []
  (merge-env! :source-paths #{"test"})
  (test-cljs :cljs-opts {:optimizations :whitespace}))

(deftask test-auto
  "Run tests and watch for changes."
  []
  (merge-env! :source-paths #{"test"})
  (comp (watch)
        (test-cljs)))

(deftask dev
  "Start development server and watch for changes."
  []
  (comp (serve)
        (watch)
        (cljs)))

(deftask dist
  "Build distribution."
  []
  (comp
   (cljs :optimizations :advanced
         :source-map true)
   (sift :include #{#".boot-env"}
         :invert true)
   (target :dir #{"target"})))

(deftask deploy
  "Deploy distribution."
  []
  (s3-sync :source ""
           :bucket (env :aws-bucket)
           :access-key (env :aws-access-key)
           :secret-key (env :aws-secret-key)))

(deftask noop
  "Noop to install dependencies."
  []
  identity)
