(ns coordinate-transformation)

;;https://exercism.org/tracks/clojure/exercises/coordinate-transformation/edit

;; DA: Below is a closure. A function that take a param p1 that returns
;; a function that takes param p2 and *uses* p1 to do something. Below, 
;; translate2d actually uses two params for each function, but same concept.
(defn translate2d
  "Returns a function making use of a closure to
   perform a repeatable 2d translation of a coordinate pair.
  Example usage hint: 
   (def move-coordinates-right-2px (translate2d 2 0))
   (def result (move-coordinates-right-2px 4 8))
    ;; result => [6 8]"
  [dx dy]
  (fn [x y] [(+ x dx) (+ y dy)]))

(defn scale2d
  "Returns a function making use of a closure to
   perform a repeatable 2d scale of a coordinate pair."
  [sx sy]
  (fn [x y] [(* x sx) (* y sy)]))

(defn compose-transform
  "Create a composition function that returns a function that 
   combines two functions to perform a repeatable transformation."
  [f g]
  (fn [x y] (let [f-result (f x y)]
              (g (get f-result 0) (get f-result 1)))))

(defn memoize-transform
  "Returns a function that memoizes the last result.
   If the arguments are the same as the last call,
   the memoized result is returned."
  [f]
  ;; Heads up: keep the atom *outside* of the returned function!!!
  (let [memo (atom [nil nil nil])]
    (fn [x y]
      (let [last-x (@memo 0)
            last-y (@memo 1)
            last-result (@memo 2)]
        (if (and (= x last-x) (= y last-y) (some? last-result))
          last-result
          (let [result (f x y)]
            (swap! memo assoc 0 x)
            (swap! memo assoc 1 y)
            (swap! memo assoc 2 result)
            result))))))
