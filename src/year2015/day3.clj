(ns year2015.day3)

(def data (slurp "input/Y2015/D3.txt"))

(defn char->direction [c]
  (case (str c)
    "^" [0  1]
    "v" [0 -1]
    "<" [-1 0]
    ">" [1  0]
    [0 0]))

(defn part1 [data]
  (loop [houses #{[0 0]}
         directions (map char->direction data)
         current [0 0]]
    (if (empty? directions)
      (count houses)
      (recur
        ; Add current house, add next direction, and recur over remaining directions
        (conj houses current)
        (rest directions)
        (mapv + current (first directions))))))

(defn part2 [data]
  (loop [houses #{[0 0]}
         directions (map char->direction data)
         current-santa [0 0]
         current-robot [0 0]
         is-santa? true]
    (if (empty? directions)
      (count houses)
      (if is-santa?
        (recur ; Saving robot house and making Santa move
          (conj houses current-robot)
          (rest directions)
          (mapv + current-santa (first directions))
          current-robot
          false)
        (recur ; Saving Santa house and making robot move
          (conj houses current-santa)
          (rest directions)
          current-santa
          (mapv + current-robot (first directions))
          true)))))

(comment
  (part1 data)
  (part2 data))
