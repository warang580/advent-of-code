(ns year2018.day1
  (:require [clojure.string :as s]))

(def data (slurp "input/Y2018/D1.txt"))

(def numbers
  (map read-string (s/split-lines data)))

(defn part1 []
  (apply + numbers))

(defn first-duplicate [freqs]
  (loop [nums (cycle freqs)
         seen #{0}
         s 0]
    (let [s' (+ s (first nums))]
      (if (contains? seen s')
        s'
        (recur
          (rest nums)
          (conj seen s')
          s')))))

(defn part2 []
  (first-duplicate numbers))

(comment (part1) (part2))

