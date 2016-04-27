(set-env!
 :resource-paths #{"src/cljs" "src/cljc" "resources/public"}
 :dependencies '[[adzerk/boot-cljs "1.7.228-1" :scope "test"]
                 [pandeiro/boot-http "0.7.3" :scope "test"]
                 [org.clojure/clojure "1.8.0" :sope "provided"]
                 [org.clojure/clojurescript "1.8.40" :scope "provided"]
                 [reagent "0.6.0-alpha"]
                 [cljs-ajax "0.5.4"]
                 [cljsjs/chartjs "2.0.1-0"]])

(require
 '[adzerk.boot-cljs :refer [cljs]]
 '[pandeiro.boot-http :refer [serve]])

(deftask dev []
  (comp (serve)
        (watch)
        (cljs)))

(deftask build []
  (cljs :optimizations :advanced))
