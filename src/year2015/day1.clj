(ns year2015.day1)

(def data (slurp "input/Y2015/D1.txt"))

(defn char->action [c]
  (case (str c)
    "("  1
    ")" -1
    0))

(defn actions [data]
  (mapv char->action data))

(defn part1 [data]
  (apply + (actions data)))

(defn part2 [data]
  (let [a (actions data)]
    (loop [n 1 s 0]
      (let [s' (+ s (nth a (- n 1)))]
        (if (= s' -1)
          n
          (recur (+ n 1) s'))))))

(comment
  (part1 data)
  (part2 data))
