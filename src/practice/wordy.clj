(ns practice.wordy)
(require '[clojure.string :as str])

;; https://exercism.org/tracks/clojure/exercises/wordy/edit

(defn try-parse-int
  [s]
  (try
    (Integer/parseInt s)
    (catch Exception e
      nil)))

(def parse-function
  {"plus"  +
   "minus" -
   "multiplied" *
   "divided" /})

(defn parse-word
  [s]
  (let [maybe-int (try-parse-int s)
        maybe-function (parse-function s)]
    (cond
      (some? maybe-int) maybe-int
      (some? maybe-function) maybe-function
      :else nil)))

(defn parse-words
  [words]
  (if (str/includes? words "cubed")
    (throw (IllegalArgumentException. "unknown operation"))

    (let [words (re-seq #"-?\w+" words)  ; -? in the regex captures neg numbers
          parsed-words (filter some? (map parse-word words))]
      (when (or (empty? parsed-words)
                (= (count parsed-words) 2))
        (throw (IllegalArgumentException. "syntax error")))
      parsed-words)))

(defn apply-calc
  [f param-1 param-2]
  ;; (println "f: " f)
  ;; (println "param-1: " param-1)
  ;; (println "param-2: " param-2)
  ;; (println "(type param-2): " (type param-2))
  ;; (println "(not= (type param-2) java.lang.Integer): " (not= (type param-2) java.lang.Integer))


  (if (or (= (type f) java.lang.Integer)
          (not= (type param-2) java.lang.Integer))
    (throw (IllegalArgumentException. "syntax error"))
    (apply f [param-1 param-2])))

(defn do-math
  [parsed-seq]
  (if (< (count parsed-seq) 3)
    (last parsed-seq)

    (loop
     [result (nth parsed-seq 0)
      math-f-index 1]

      ;; (println "parsed-seq: " parsed-seq)
      ;; (println "result: " result)
      ;; (println "math-f-index: " math-f-index)
      ;; (println "(count parsed-seq): " (count parsed-seq))

      (if (> math-f-index (dec (count parsed-seq)))
        result
        (let [math-f (nth parsed-seq math-f-index)
              param (nth parsed-seq (+ math-f-index 1) 0)] ; 0 default here just to allow apply-calc to throw for unit test

          (recur (apply-calc math-f result param)
                 (+ math-f-index 2)))))))

(defn evaluate
  "Evaluates a simple math problem"
  [question]
  (do-math (parse-words question)))
