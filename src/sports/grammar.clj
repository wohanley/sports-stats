(ns sports.grammar
  (:require [sports.grammar.modifiers :refer [some-modifiers]]))


(defn- magnitude []
  (let [percentage #(str (+ (rand-int 100) 1) "%")
        multiple #(str (+ (rand-int 5) 2) " times")]
    (rand-nth [percentage multiple])))

(defn- quantitative-performance []
  (let [verb (#(rand-nth ["score" "get"]))
        comparison (#(rand-nth ["more" "fewer"]))
        quantity (#(rand-nth ["goals" "points" "touchdowns" "runs" "wins"
                              "losses" "gold stars" "favs" "penalties"
                              "fouls"]))]
    [verb " " magnitude " " comparison " " quantity]))

(defn- qualitative-performance []
  (let [verb #(rand-nth ["have" "get" "score"])
        comparison #(rand-nth ["nicer" "better" "worse"])
        quality #(rand-nth ["batting average" "sportsmanship"
                            "power play percentage" "penalty killing" "running"
                            "focus"])]
    [verb " " magnitude " " comparison " " quality]))

(defn- performance-aspect [] (rand-nth [quantitative-performance
                                        qualitative-performance]))


(defn- team []
  (rand-nth ["the Rangers" "the Canadiens" "the Golden Seals" "the Rainmen"
             "the Sabres" "the Devils" "moon team" "sun team" "the Abbies"
             "the Bears" "real programmers"]))


(defn start []
  (rand-nth [[team " " performance-aspect " " some-modifiers]
             [some-modifiers ", " team " " performance-aspect]]))


