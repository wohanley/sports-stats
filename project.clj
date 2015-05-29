(defproject sports "1.0.0-SNAPSHOT"
  :description "A bot that generates dubious sports statistics"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.wohanley/robots "1.0.0-SNAPSHOT"]
                 [org.clojure/data.generators "0.1.2"]]
  :profiles {:dev {:dependencies [[midje "1.6.3"]]}})
