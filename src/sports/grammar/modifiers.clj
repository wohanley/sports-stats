;;;; Generate modifiers for a statistic. These are phrases that
;;;; a) change the scope of a cited statistic and b) can be formed into
;;;; arbitrarily long chains, e.g. "in Halifax, on top of tall buildings, with
;;;; a large crowd".

(ns sports.grammar.modifiers
  (:require [robots.grammar.generative :refer [terminate]]
            [clojure.data.generators :refer [weighted]]))


(def modifiers
  "Grammars that produce modifiers. Functions generally produce one of some set
  of modifiers that are mutually exclusive, while modifiers that aren't mutually
  exclusive with any others are simply listed."
  
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

   "on astroturf"
   "on ley lines"
   "underground"
   "under retractable roofs"


   ;; players

   "after hearing a motivational speaker"
   "on leg day"
   "when it's more than one player's birthday"
   "if players missed their naps"
   "after group therapy sessions"


   ;; clothes

   (fn [] (rand-nth ["in new uniforms" "in old uniforms" "in dirty uniforms"
                     "in clean uniforms" "in starched uniforms"]))

   "wearing wool socks"


   ;; audience

   (fn []
     (rand-nth ["before a large crowd" "before a small crowd"
                "before a rowdy crowd" "in loud buildings"
                "in quiet buildings"]))

   "with world leaders attending"
   "with the pope watching"

   
   ;; time of year

   (fn []
     (str "during "
          (rand-nth ["spring" "summer" "fall" "autumn" "winter" "holidays"])))


   ;; elevation

   (fn []
     (let [height-unit #(rand-nth ["feet" "inches" "meters" "yards"
                                   "centimeters"])
           height #(str (rand-int 5000) " " (height-unit))]
       (str (rand-nth ["above " "below "]) (height))))

   
   ;; weather

   (fn []
     (rand-nth ["during rain" "when it's sunny" "under clouds" "on windy days"
                "on calm days" "in fog" "when it's wet but not raining"
                "with rainbows visible"]))
   
   
   ;; biome

   (fn []
     (str "in "
          (rand-nth ["subtropical climates" "tundra" "nemoral climates"
                     "prairies" "deserts" "swamps" "mangrove forests"
                     "flood plains" "rainforests" "coniferous forests"])))


   ;; region

   (fn []
     (rand-nth ["along the equator" "in the Arctic" "in North America"
                "in Europe" "in South America" "in Africa" "in Asia"
                "in Oceania" "in the Western Hemisphere"
                "in the Eastern hemisphere" "in the Northern Hemisphere"
                "in the Southern Hemisphere" "in the Mediterranean"]))

   
   ;; idk

   "when patting each other's butts regularly"])


(defn some-modifiers []
  (clojure.string/join " "
                       (terminate (take
                                   (+ (rand-int 5) 1)
                                   (shuffle modifiers)))))
