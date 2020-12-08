(ns day3part2
  (:require [clojure.string :as str])
  )

(defn hit-tree? [line coord]
  (let [len (count line)
        x (mod coord len)
        c (.charAt line x)]
    (= c (get "#" 0))))

(defn num-trees-hit [input-map right down]
  (loop [map input-map
         coord 0
         total 0]
    (if (seq map)
      (if (hit-tree? (first map) coord)
        (recur (nthrest map down) (+ coord right) (inc total))
        (recur (nthrest map down) (+ coord right) total))
      total))
  )

(defn read-path-to-vector [path]
  (str/split-lines (slurp path)))

(defn run [path]
  (let [data (read-path-to-vector path)]
    (reduce * [
               (num-trees-hit data 1 1)
               (num-trees-hit data 3 1)
               (num-trees-hit data 5 1)
               (num-trees-hit data 7 1)
               (num-trees-hit data 1 2)])))
