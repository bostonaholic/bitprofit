(set-env!
 :source-paths #{"src" "dev"}
 :resource-paths #{"html"}
 :dependencies '[[adzerk/boot-cljs "2.1.5" :scope "test"]
                 [crisptrutski/boot-cljs-test "0.3.4" :scope "test"]
                 [pandeiro/boot-http "0.8.3" :scope "test"]
                 [hashobject/boot-s3 "0.1.3-SNAPSHOT" :scope "test"]
                 [environ "1.1.0" :scope "test"]
                 [boot-deps "0.1.9" :scope "test"]

                 [org.clojure/clojure "1.10.0-RC5"]
                 [org.clojure/clojurescript "1.10.439"]
                 [reagent "0.8.1"]
                 [cljs-ajax "0.8.0"]
                 [cljsjs/chartjs "2.7.3-0"]

                 ;; overrides for clojure 1.9.0 compatibility
                 [bouncycastle/bcprov-jdk16-nosign "140"]
                 [pandect "0.6.1"]])

(require
 '[adzerk.boot-cljs :refer [cljs]]
 '[clojure.java.shell :as shell]
 '[clojure.string :as str]
 '[crisptrutski.boot-cljs-test :refer [test-cljs]]
 '[pandeiro.boot-http :refer [serve]]
 '[hashobject.boot-s3 :refer [s3-sync]]
 '[environ.core :refer [env]]
 '[boot-deps :refer [ancient]]
 '[boot.util :refer [info]])

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

(deftask replace-version-metadata!
  "Replaces the version metadata in the target/index.html"
  []
  (with-post-wrap fileset
    (info "Replacing version metadata...\n")
    (let [os-name (-> "uname" shell/sh :out str/trim)
          sha (or (env :circle-sha1)
                  (-> (shell/sh "git" "rev-parse" "HEAD") :out str/trim))]
      (condp = os-name
        "Darwin" (shell/sh "sed" "-i" "" (str "s/HEAD/" sha "/") "target/index.html")
        "Linux"  (shell/sh "sed" "-i"    (str "s/HEAD/" sha "/") "target/index.html")))))

(deftask build
  "Build distribution."
  []
  (comp
   (cljs :optimizations :advanced
         :source-map true)
   (sift :include #{#".boot-env"}
         :invert true)
   (target)
   (replace-version-metadata!)))

(deftask deploy
  "Deploy distribution."
  []
  (set-env! :resource-paths #{"target"})
  (s3-sync :source "" ; target/
           :bucket (env :aws-bucket)
           :access-key (env :aws-access-key)
           :secret-key (env :aws-secret-key)))

(deftask noop
  "Noop to install dependencies."
  []
  identity)
