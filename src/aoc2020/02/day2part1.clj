(ns day2part1
  (:require [clojure.string :as str])
  )

(defn char-count [s c]
  (letfn [(char-count-helper [idx total]
            (let [next (str/index-of s c idx)]
              (if next
                (recur (inc next) (inc total))
                total)))]
    (char-count-helper 0 0)))

(defn valid? [password char min max]
  (let [count (char-count password char)]
    (and
     (>= count min)
     (<= count max))))

(defn read-path-to-vector [path]
  (str/split-lines (slurp path)))

(defn split-min [line] (str/split line #"-" 2))

(defn split-max [line] (str/split line #" " 2))

(defn split-char-pass [line] (str/split line #": " 2))

(defn parse-line [line]
  (let [min-vec (split-min line)
        max-vec (split-max (get min-vec 1))
        char-pass (split-char-pass (get max-vec 1))]
    [(get char-pass 1)
     (get char-pass 0)
     (read-string (get min-vec 0))
     (read-string (get max-vec 0))
     ]))

(defn run [path]
  (loop [data (read-path-to-vector path)
         total 0]
    (if (seq data)
      (let [values (parse-line (first data))
            pass (get values 0)
            c (get values 1)
            mn (get values 2)
            mx (get values 3)]
        (if (valid? pass c mn mx)
          (recur (rest data) (inc total))
          (recur (rest data) total)))
      total)))
