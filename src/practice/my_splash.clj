(ns practice.my-splash)

(def colors
  [:red :green :blue])

(defn rand-height
  []
  (rand-nth (range 60 73)))

(defn make-user
  []
  {:id (random-uuid)
   :fav-color (rand-nth colors)
   :height (rand-height)})

(defn make-seq
  []
  (repeatedly make-user))

(defn do-it
  [n]
  (take n (make-seq)))

;; interviewer: nipun monga