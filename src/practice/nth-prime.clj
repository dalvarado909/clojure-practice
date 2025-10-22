(ns nth-prime)
(require '[clojure.math :as math])


;; Start with the first prime, 2.
;; Iteratively check subsequent odd numbers (3, 5, 7, etc.) for primality.
;; To check if a number is prime, use trial division by all prime numbers up *and including* to its square root.
;; Count the primes you find.
;; Stop when you have found the nth prime number.

(defn remainder-seq
  "for i > 2, seq of remainders of n / i.
  Example: (remainder-seq 6)
   => (0 0 2 1)"
  [n]
  (let [sqrt-of-n (math/sqrt n)]
    (for [i (range 2 (inc sqrt-of-n))]  ;perfect and FAST!!
      ;; (for [i (range 2 n)] ; waaay slow
      ;; (for [i (range 2 sqrt-of-n)]  ; faster but not quite right
      (rem n i))))

(defn has-a-factor?
  "return true if n divides cleanly into some number (zero remainder) less than n"
  [n]
  ;;   (print "has-a-factor? called with n:" n)
  (some zero? (remainder-seq n)))

(defn is-prime
  [i]
  ;;   (print "is-prime with i:" i)
  (cond
    (< i 2) false
    (= i 2) true
    (even? i) false
    (has-a-factor? i) false
    :else true))

(defn nth-prime
  "Returns the nth prime number."
  [n]
  (last
   (take n
         (filter some?
                 ;;  (for [i (range 2 100000)]
                 (for [i (range)]
                   (when (is-prime i)
                     i))))))

(defn print-primes
  []
  (for [i (range 2 10)]
    (when (is-prime i)
      i)))
