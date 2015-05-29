(ns sports.grammar
  (:require [robots.grammar.generative :refer [terminate]]
            [clojure.data.generators :refer [weighted]]))


(defn in-location []
  (rand-nth ["Halifax" "Charlottetown" "Vancouver" "Toronto" "Montreal"
             "the North End" "space" "Wellington" "Cheonan" "big cities"
             "sleepy hamlets" "a colosseum" "forests" "meadows"
             "gender-swapped AUs" "circus tents"]))

(defn on-location []
  (rand-nth ["a hillside" "a mountain" "a barge" "frozen lakes"
             "top of tall buildings" "loam" "a peat bog"]))

(defn start []
  (weighted {"at home" 1
             "away" 1
             ["in " in-location] 2
             ["on " on-location] 2}))
