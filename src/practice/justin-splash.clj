(comment
  "infinite lazy seq containing id/UUID, Fav color :red :green :blue, 
   Height that is int between 60/72 inclusive."

  (defn random-color
    []
    (rand-nth [:red :green :blue]))

  (defn random-height
    []
    (rand-nth (range 60 72)))

  (defn inch-to-cent
    [inches]
    (* inches 2.54))
  
  (defn make-foo
    []
    {:id (random-uuid)
     :fav-color (random-color)
     :height (inch-to-cent (random-height))})

;;    https://clojuredocs.org/clojure.core/repeatedly
  (defn make-foos
    [n] 
    (take n
          (repeatedly make-foo)))
  
  )

 