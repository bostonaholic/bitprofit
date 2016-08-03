(set-env!
 :source-paths #{"src"}
 :resource-paths #{"html"}
 :dependencies '[[adzerk/boot-cljs "1.7.228-1" :scope "test"]
                 [crisptrutski/boot-cljs-test "0.2.1" :scope "test"]
                 [pandeiro/boot-http "0.7.3" :scope "test"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [reagent "0.6.0-alpha"]
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
