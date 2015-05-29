;;;; Functions that generate modifiers for a statistic. These are phrases that
;;;; a) change the scope of a cited statistic and b) can be formed into
;;;; arbitrarily long chains, e.g. "in Halifax, on top of tall buildings, with
;;;; a large crowd". The private functions in this namespace are NOT guaranteed
;;;; to conform to those rules.

(ns sports.grammar.modifiers
  (:require [robots.grammar.generative :refer [terminate]]
            [clojure.data.generators :refer [weighted]]))


(def modifiers
  
  ;; venue
  
  [(let [in-location (fn []
                       (str "in "
                            (rand-nth ["Halifax" "Charlottetown" "Vancouver"
                                       "Toronto" "Montreal" "the North End"
                                       "space" "Wellington" "Cheonan"
                                       "big cities" "sleepy hamlets"
                                       "a colosseum" "forests" "meadows"
                                       "gender-swapped AUs" "circus tents"])))
         on-location (fn []
                       (str "on "
                            (rand-nth ["hillsides" "mountains" "barges"
                                       "frozen lakes" "top of tall buildings"
                                       "loam" "peat bogs"])))]
     (fn venue []
       (weighted {"at home" 1
                  "away" 1
                  in-location 2
                  on-location 2})))


   ;; audience

   (fn []
     (rand-nth ["before a judge" "with a large crowd" "with a small crowd"]))


   ;; time of year

   (fn []
     (str "during "
          (rand-nth ["spring" "summer" "fall" "autumn" "winter" "holidays"])))])


(defn some-modifiers []
  (clojure.string/join ", "
                       (terminate (take
                                   (+ (rand-int 5) 1)
                                   (shuffle modifiers)))))
