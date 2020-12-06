(ns day1part1
  (:require [clojure.string :as str])
  )

(defn read-path-to-vector [path]
  (map read-string (str/split-lines (slurp path))))

(defn adds-to-sum [n, sum]
  (- sum n))

(defn find-sum-in-set [n, sum, s]
  (let [m (adds-to-sum n sum)]
    (if (contains? s m) m nil)))

(defn find-2020-pair-helper [n, v]
  (let [m (find-sum-in-set n 2020 (set v))]
    (cond
      (empty? v) nil
      (some? m) (vec [n m])
      :else (recur (first v) (rest v)))))

(defn find-2020-pair [v]
  (find-2020-pair-helper (first v) (rest v)))

(defn run [filepath]
  (let [data (read-path-to-vector filepath)]
    (let [pair (find-2020-pair data)]
      (reduce * pair))))
