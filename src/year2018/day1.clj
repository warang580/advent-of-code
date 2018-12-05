(ns year2018.day1
  (:require [clojure.string :as s]))

(def data (slurp "input/Y2018/D1.txt"))

(def numbers
  (map read-string (s/split-lines data)))

(defn part1 []
  (apply + numbers))

; TODO
(loop [nums [-6, +3, +8, +5, -6]
       seen #{0}
       s 0]
  (if (empty? nums)
    nil
    (let [s' (+ s (first nums))]
      (if (contains? seen s')
        s'
        (recur
          (rest nums)
          (conj seen s')
          s')))))
          

