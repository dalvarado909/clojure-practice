(ns robot-simulator 
  (:require
   [clojure.set :as set]))

;https://exercism.org/tracks/clojure/exercises/robot-simulator/iterations

(defn robot
  "Creates a robot at the given coordinates, facing the given direction."
  [coordinates direction]
  ;; function body
  {:coordinates coordinates :bearing direction})

(def turn-right-map
  {:north :east
   :east :south
   :south :west
   :west :north})

(def turn-left-map
  ; Swap keys/vals to get the turn-left map
  (set/map-invert turn-right-map))

(def advance
  {:north #(update % :y inc)
   :east #(update % :x inc)
   :south #(update % :y dec)
   :west #(update % :x dec)})

(defn move
  "Update the robot-state based on a single instruction."
  [robot-state instruction]

  (case instruction
    ; *** Remember that update takes a function that takes the old value that is getting updated. 
    ; *** Also, Remember! In clojure, we can treat a map as a function. We pass in the key and 
    ; get a value!. Let's use this to get a direction from an old direction!"

    \R (update robot-state :bearing turn-right-map)
    \L (update robot-state :bearing turn-left-map)

    ; For advance, the update function needs the :bearing in addition to the old coordinates. 
    ;So, get the *value* of :bearing and pass that as the key to select the map value in 
    ; the advance map. That value holds another update that updates our :coordinates
    \A (update robot-state :coordinates (advance (:bearing robot-state)))

    ; *** Finally, above, we started out with case ids from "R", "L", and "A". 
    ; This was fine when simulate just called move directly with a instruction string of size 1. 
    ; But, when we change simulate to call reduce with a string of multiple commands, 
    ; the reduce calls move with a single character (not a string)!!!! So, we have to 
    ; change the case keys from strings to characters: \R, \L, and \A to get around the 
    ; java.lang.IllegalArgumentException: No matching clause.
    ))

(defn simulate
  "Simulates the robot's movements based on the given instructions
  and updates its state."
  [instructions robot-state]
  ;; function body
  ;; call reduce with the additional 3rd param which is the starting value for move
  (reduce move robot-state instructions))