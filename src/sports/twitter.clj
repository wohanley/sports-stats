(ns sports.twitter
  (:require [robots.grammar.generative :refer [terminate]]
            [sports.grammar :as grammar]
            [twitter.oauth :as oauth]
            [twitter.api.restful :as rest]))

(def creds
  (oauth/make-oauth-creds (:env "API_KEY")
                          (:env "API_SECRET")
                          (:env "ACCESS_TOKEN")
                          (:env "ACCESS_TOKEN_SECRET")))

(defn main
  (loop []
    (rest/statuses-update :oauth-creds creds
                          :params {:status (terminate (grammar/start))})
    (Thread/sleep (* 1000 60 60 6))
    (recur)))
