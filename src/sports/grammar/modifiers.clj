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
   "on arms day"
   "when it's more than one player's birthday"
   "if players missed their naps"
   "after group therapy sessions"
   "if the players hadn't had their cuddles"
   "when more than two wives are pregnant"
   "when someone's kids are teething"
   "after a presser"
   


   ;; clothes

   (fn [] (rand-nth ["in new uniforms" "in old uniforms" "in dirty uniforms"
                     "in clean uniforms" "in starched uniforms"]))

   "wearing wool socks"
   "wearing lace panties"
   "thinking sexually suggestive thoughts"
   "not wearing underwear"
   
   
   ;; audience

   (fn []
     (rand-nth ["before a large crowd" "before a small crowd"
                "before a rowdy crowd" "in loud buildings"
                "in quiet buildings""in Michael's arts and crafts superstore"
                "when only their mothers are looking"
                ]))

   "with world leaders attending"
   "with the pope watching"
   "with beyonce in attendance"

   
   ;; time of year

   (fn []
     (str "during "
          (rand-nth ["spring" "summer" "fall" "autumn" "winter" "holidays"
                     "ramadan" ])))


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
                "with rainbows visible""when it's humid"
                "when you've caught a chill"
                "when the weather keeps changing its mind" ]))
   
   
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
                "in the Southern Hemisphere" "in the Mediterranean" "in space"
                "on the moon" "standing in the ocean"   ]))

   
   ;; idk

   "when patting each other's butts regularly"])


(defn some-modifiers []
  (clojure.string/join " "
                       (terminate (take
                                   (+ (rand-int 5) 1)
                                   (shuffle modifiers)))))
