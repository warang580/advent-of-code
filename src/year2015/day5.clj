(ns year2015.day5
  (:require [clojure.string :as s]))

(defn at-least-3-vowels [str]
  (re-find #"((a|e|i|o|u).*){3,}" str))

(defn double-letters [str]
  (re-find #"(\w)\1" str))

(defn no-ugly-words [str]
  (not (re-find #"(ab|cd|pq|xy)" str)))

(def words (s/split-lines (slurp "input/Y2015/D5.txt")))

(defn part1 []
  (count (filter #(and (at-least-3-vowels %)
                       (double-letters %)
                       (no-ugly-words %))
                 words)))

(defn two-letters-pair [word]
  (re-find #"(\w{2}).*\1" word))

(defn one-letter-repeat [word]
  (re-find #"(\w)\w\1" word))

(defn part2 []
  (count (filter #(and (two-letters-pair %)
                       (one-letter-repeat %))
                 words)))

(comment
  (part1)
  (part2))
