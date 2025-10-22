(ns collatz-conjecture)

;; https://exercism.org/tracks/clojure/exercises/collatz-conjecture/edit

(defn next-num
  [num]
  (cond
    (even? num) (/ num 2)
    (odd? num) (inc (* num 3))))

(defn collatz
  "Returns the number of steps it takes to reach 1 according
  to the rules of the Collatz Conjecture. 
  
   Notes: We want to use recursion but keep track of recursive call count, 
   so use loop with params for next-num and the count. Use a if to bail out 
   if n is 1. Then call recur with the same params as the loop, 
   making sure to update n by calling next-num"
  [num]
  (loop
   [n num
    count 0]
    (if (= n 1)
      count
      (recur (next-num n)
             (inc count)))))