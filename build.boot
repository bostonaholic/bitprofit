(set-env!
 :source-paths #{"src"}
 :resource-paths #{"html"}
 :dependencies '[[adzerk/boot-cljs "2.1.4" :scope "test"]
                 [crisptrutski/boot-cljs-test "0.3.4" :scope "test"]
                 [pandeiro/boot-http "0.8.3" :scope "test"]

                 [org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.8.0-alpha2"]
                 [cljs-ajax "0.5.4"]
                 [cljsjs/chartjs "2.0.1-0"]])

(require
 '[adzerk.boot-cljs :refer [cljs]]
 '[crisptrutski.boot-cljs-test :refer [test-cljs]]
 '[pandeiro.boot-http :refer [serve]])

(task-options! test-cljs {:js-env :phantom})

(deftask test-once []
  (merge-env! :source-paths #{"test"})
  (test-cljs :cljs-opts {:optimizations :whitespace}))

(deftask test-auto []
  (merge-env! :source-paths #{"test"})
  (comp (watch)
        (test-cljs)))

(deftask dev []
  (comp (serve)
        (watch)
        (cljs)))

(deftask dist []
  (cljs :optimizations :advanced))
