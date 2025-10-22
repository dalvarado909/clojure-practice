# Interview Study


## defn and fn
Understanding how to define named functions (defn) and anonymous functions (fn), including multi-arity and variadic functions.

```clojure
    (defn greet
      ([] "Hello!")
      ([name] (str "Hello, " name "!")))

    (def my-anon-fn (fn [x] (* x x)))
```

## -> (thread-first) - good for string functions
 This macro inserts the result of the previous expression as the first argument of the next form. This is useful when working with functions that typically take their primary input as the first argument.

```clojure
    (-> "  hello world  "
        clojure.string/trim
        clojure.string/upper-case
        (clojure.string/replace "HELLO" "HI"))
    ;; Expands to: (clojure.string/replace (clojure.string/upper-case (clojure.string/trim "  hello world  ")) "HELLO" "HI")
    ;; Result: "HI WORLD"
```

## ->> (thread-last) - good for collection functions
 This macro inserts the result of the previous expression as the last argument of the next form. This is particularly useful for functions that operate on collections or sequences where the collection is typically the last argument. 

```clojure
    (->> (range 10)
         (filter even?)
         (map #(* % %))
         (take 3))
    ;; Expands to: (take 3 (map #(* % %) (filter even? (range 10))))
    ;; Result: (0 4 16)
```

# cond and if
Conditional expressions for controlling program flow.

```clojure
    (if (> x 5) "Greater" "Not greater")
    (cond
      (< x 0) "Negative"
      (= x 0) "Zero"
      :else "Positive")
```
# map, filter, reduce
Higher-order functions for working with collections and sequences.

```clojure
    (map inc [1 2 3]) ; (2 3 4)
    (filter even? [1 2 3 4]) ; (2 4)
    (reduce + [1 2 3]) ; 6
```

# first, rest, cons, conj
Functions for manipulating sequences and collections.

```clojure
    ; the first three below define a sequence!
    (first [1 2 3]) ; 1
    (rest [1 2 3]) ; (2 3)
    (cons 0 [1 2 3]) ; (0 1 2 3) "Cons-struct *before* the vector"
    
    (conj [1 2] 3) ; [1 2 3] "Con-join *after* the vector"
```

# get, assoc, dissoc
For working with maps.
```clojure
    (def m {:a 1 :b 2})
    (get m :a) ; 1
    (assoc m :c 3) ; {:a 1 :b 2 :c 3}
    (dissoc m :a) ; {:b 2}
```

# require
Require to load a lib.
```clojure
    ; Don't forget the ' and the :as !!
    (require '[clojure.math :as math]
```
