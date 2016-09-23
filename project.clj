(defproject oop-vs-data-modeling "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha12"]
                 [org.clojure/clojurescript "1.9.229" :scope "provided"]]

  :plugins
  [[lein-environ "1.0.3"]
   ;[org.clojure/clojurescript "1.9.229" :scope "provided"]
   [lein-cljsbuild "1.1.3"]]

  :source-paths ["src/main/clj" "src/main/cljc"]
  :test-paths ["src/test/clj" "src/test/cljc"]
  :resource-paths ["src/main/resources"]
  :java-source-paths ["src/main/java"]

  :cljsbuild
  {:builds [{ :source-paths ["src/main/cljs" "src/main/cljc"]
             :compiler { :output-to "resources/public/js/app.js"
                        :optimizations :advanced
                        :pretty-print true}}]}

  :profiles
  {:uberjar {:aot :all}
   :cljs {:plugins [[lein-cljsbuild "1.1.3"]] }
   :dev           [:project/dev :profiles/dev]
   :test          [:project/test :profiles/test]
   :project/dev  {:env {:dev true}
                  :dependencies [[org.clojure/test.check "0.9.0"]]
                  :resource-paths ["src/test/resources"]}
   :project/test {:env {:test true}
                  :resource-paths ["src/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})
