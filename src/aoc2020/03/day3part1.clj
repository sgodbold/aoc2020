(ns day3part1
  (:require [clojure.string :as str])
  )

(defn hit-tree? [line coord]
  (let [len (count line)
        x (mod coord len)
        c (.charAt line x)]
    (= c (get "#" 0))))

(defn read-path-to-vector [path]
  (str/split-lines (slurp path)))

(defn run [path]
  (loop [map (read-path-to-vector path)
         coord 0
         total 0]
    (if (seq map)
      (if (hit-tree? (first map) coord)
        (recur (rest map) (+ coord 3) (inc total))
        (recur (rest map) (+ coord 3) total))
      total)))
