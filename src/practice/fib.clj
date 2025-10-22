(ns practice.fib)

; https://yankev.github.io/2017/05/15/fib_clj.html

(defn nth-fib
  "Returns the nth fibonacci number"
  [n]
  (case n
    0 0
    1 1
    (+ (nth-fib (- n 1)) (nth-fib (- n 2)))
    )
  )

(defn fib-sequence-up-to-n
  "Returns the first n numbers of the fibonacci sequence"
  [n]
  (map nth-fib (range n))
  )

; We know fib-sequence-up-to-n is slow because it repeats the same calcs over and over. Let's measure.
;(time (fib-sequence-up-to-n 10))
;"Elapsed time: 0.019004 msecs"
;=> (0 1 1 2 3 5 8 13 21 34)

;; create an atom of an integer array of size 10
;(def a (atom (int-array 10)))
;=> #'practice.fib/a
;; check it out
;(seq @a)
;=> (0 0 0 0 0 0 0 0 0 0)
;; try to set the value of 30 at index 3
;(swap! a assoc 3 30)
;Execution error (ClassCastException) at practice.fib/eval2581 (form-init10107026669925375230.clj:1).
;class [I cannot be cast to class clojure.lang.Associative ([I is in module java.base of loader 'bootstrap'; clojure.lang.Associative is in unnamed module of loader 'app')
;                                                            ; ^^^ swap! is trying to call assoc on "a" (which is an atom of integer array), arrays are not associative (order cannot be changed).
;                                                            ; conclusion: if you want to implement your own memoization function using a simple array, you can create an atom of an int-array, but it will blow when you try to add an element at some index.

;(defn memoize-with-array
;  "Memoizes a function that takes an single int input and returns an int.
;  For our memo lookup, create an atom (shared state that will persist across invocations) of a empty int array"
;  [f]
;  (let [memo (atom (int-array 100))]                        ; create an atom of an int array of size 100
;    (fn [i]
;      (if (> (nth @memo i) 0) (nth @memo i)               ; if a value is found at index i, return it
;                                (let [ret (apply f (list i))]      ; otherwise, call the function. Note we need to convert i to a list
;                                  ;(swap! memo assoc (list i) ret)  ; and add the returned value to the memo array atom
;                                  ret                       ; and return the returned value
;                                  )
;                                ))
;    ))

(defn memoize-with-array
  "Memoizes a function that takes an single int input and returns an int.
  For our memo lookup, create an atom (shared state that will persist across invocations) of a empty int array"
  [f]
  (let [memo (atom (int-array 100))]                        ; create an atom of an int array of size 100
    (fn [i]
      (if (> (nth @memo i) 0) 
        (nth @memo i)               
        ; if a value is found at index i, return it
                                (let [ret (apply f (list i))]      ; otherwise, call the function. Note we need to convert i to a list
                                  ;(swap! memo assoc (list i) ret)  ; and add the returned value to the memo array atom
                                  ret                       ; and return the returned value
                                  )
                                ))
    ))


(def nth-fib-memo
  (memoize-with-array nth-fib))

; Let's memoize fib-sequence-up-to-n to make it never have to lookup a fib more than once
(defn fib-sequence-up-to-n-memo
  "Returns the first n numbers of the fibonacci sequence, but uses a memoization to store intermediate results for quicker lookup"
  [n]
  (map nth-fib-memo (range n))
  )
