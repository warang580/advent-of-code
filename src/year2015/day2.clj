(ns year2015.day2
  (:require [clojure.string :as s]))

(def data (slurp "input/Y2015/D2.txt"))

(defn gift-dimensions [data]
  (map (fn [d] (map #(Integer/parseInt %) (s/split d #"x"))) (s/split-lines data)))

(defn area-smallest-side [dim]
  (let [[l w _] (sort dim)]
    (* l w)))

(defn box-area [[l w h]]
  (+ (* 2 l w) (* 2 w h) (* 2 h l)))

(defn wrap [dim]
  (+ (box-area dim)
     (area-smallest-side dim)))

(defn smallest-perimeter [dim]
  (let [[l w _] (sort dim)]
    (+ (* 2 l) (* 2 w))))

(defn cubic-feet [[l w h]]
  (* l w h))

(defn wrap-ribbon [dim]
  (+ (smallest-perimeter dim)
     (cubic-feet dim)))

(defn part1 [data]
  (apply + (map wrap (gift-dimensions data))))

(defn part2 [data]
  (apply + (map wrap-ribbon (gift-dimensions data))))

(comment
  (part1 data)
  (part2 data))
