(ns day1part2
  (:require [clojure.string :as str])
  )

(defn read-path-to-vector [path]
  (map read-string (str/split-lines (slurp path))))

(defn sum2020? [n m o]
  (== 2020 (+ n m o)))

(defn find-2020-tuple
  ([v]
   (let [result (find-2020-test (first v) (rest v))]
     (cond
       (some? result) result
       (> 3 (count (rest v))) nil
       :else (recur (rest v)))))
  ([n v]
   (let [result (find-2020-test n (first v) (rest v))]
     (cond
       (some? result) result
       (> 2 (count (rest v))) nil
       :else (recur n (rest v)))))
  ([n m v]
   (cond
     (sum2020? n m (first v)) [n m (first v)]
     (empty? (rest v)) nil
     :else (recur n m (rest v))))
  )

(defn run [filepath]
  (let [data (read-path-to-vector filepath)]
    (let [tuple (find-2020-tuple data)]
      (reduce * tuple))))
