(ns year2015.day6
  (:require [clojure.string :as s]))

(def input-lines (s/split-lines (slurp "input/Y2015/D6.txt")))

(defn make-grid [w h v]
  {:w w
   :h h
   :l (take (* w h) (repeat v))})

(defn update-light [action [x y] grid update]
  (let [{:keys [w h l]} grid
        g (vec l)
        i (+ (* y (- w 1)) x)
        c (nth g i)
        n (update action c)]
    (assoc-in grid [:l] (assoc g i n))))

(defn grid-positions [x y x' y']
  (for [a (range x (+ x' 1))
        b (range y (+ y' 1))]
    [a b]))

(defn apply-action [action [x y x' y'] init-grid update]
  (loop [positions (grid-positions x y x' y')
         grid init-grid]
    (if (empty? positions)
      grid
      (recur (rest positions)
             (update-light action (first positions) grid update)))))

(defn line->action [l]
  (let [rect (re-seq #"\d+" l)
        [_ action] (re-find #"((?:\w+\s)+)\d+" l)]
    [(s/trim action) rect]))

(defn result-actions [update compute init]
  (loop [lines input-lines
         grid  (make-grid 1000 1000 init)]
    (if (empty? lines)
      (compute (:l grid))
      (let [[action box] (line->action (first lines))]
          (recur (rest lines)
                 (apply-action action (mapv #(Integer/parseInt %) box) grid update))))))

(defn part1 []
  (letfn [(update [a c] (case a
                           "turn on"  true
                           "turn off" false
                           "toggle"   (not c)))
          (compute [l] (count (filter true? l)))]
    (result-actions update compute false)))

(defn part2 []
  (letfn [(update [a c] (case a
                           "turn on"  (+ c 1)
                           "turn off" (max (- c 1) 0)
                           "toggle"   (+ c 2)))
          (compute [l] (apply + l))]
    (result-actions update compute 0)))

; NOTE: these are slow (iterate 1000x over 1,000,000 elements ~= 19s)
(comment
  (time (part1))
  (time (part2)))
