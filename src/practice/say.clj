(ns say)
(require '[clojure.string :as str])

(def ones
  {1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"})

(def tens
  {2 "twenty"
   3 "thirty"
   4 "forty"
   5 "fifty"
   6 "sixty"
   7 "seventy"
   8 "eighty"
   9 "ninety"})

(defn say-ones
  [n]
  (ones n))

(defn say-tens
  [n]
  (tens n))

(defn say-hundreds
  [n]
  (if (> n 0)
    (str (ones n) " hundred ") ""))

(defn char-to-int
  [c]
  (- (int c) (int \0)))

(defn do-chunk-21-999
  [num]
  (let [reversed-num-str (reverse (str num))
        ones-digit (char-to-int (nth reversed-num-str 0))
        tens-digit (char-to-int (nth reversed-num-str 1))
        hundreds-digit (char-to-int (nth reversed-num-str 2 0))]
    (str/trim (str
               (say-hundreds hundreds-digit)
               (say-tens tens-digit)
               (if (and (> (int tens-digit) 1) (> (int ones-digit) 0)) "-" "")
               (say-ones ones-digit)))))

(defn do-chunk
  [num]
  (cond
    (< num 20) (say-ones num)
    (< num 1000) (do-chunk-21-999 num)))

(defn say-chunk
  [chunk name]
  (if (not (zero? chunk))
    (str (do-chunk chunk) name) ""))

(defn number [num] ;; <- arglist goes here
  (if (or (< num 0) (> num 999999999999))
    (throw (IllegalArgumentException. "Out of range."))

    (let [hundreds-chunk (rem num 1000)
          thousands-chunk (rem (int (/ num 1000)) 1000)
          millions-chunk (rem (int (/ num 1000000)) 1000)
          billions-chunk (rem (int (/ num 1000000000)) 1000)]
      (cond
        (= num 0) "zero"
        (< num 1000000000000) (str/trim (str
                                         (say-chunk billions-chunk " billion ")
                                         (say-chunk millions-chunk " million ")
                                         (say-chunk thousands-chunk " thousand ")
                                         (say-chunk hundreds-chunk "")))))))
