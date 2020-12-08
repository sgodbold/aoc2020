(ns day2part2
  (:require [clojure.string :as str])
  )

(defn valid? [password char pos1 pos2]
  (let [c (get char 0)
        c1 (.charAt password (dec pos1))
        c2 (.charAt password (dec pos2))]
    (if (= c c1)
      (not (= c c2))
      (= c c2)
      )))

(defn read-path-to-vector [path]
  (str/split-lines (slurp path)))

(defn split-pos1 [line] (str/split line #"-" 2))

(defn split-pos2 [line] (str/split line #" " 2))

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
            p1(get values 2)
            p2(get values 3)]
        (if (valid? pass c p1 p2)
          (recur (rest data) (inc total))
          (recur (rest data) total)))
      total)))
