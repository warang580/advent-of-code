(ns year2015.day4)

; https://gist.github.com/jizhang/4325757
(import 'java.security.MessageDigest
        'java.math.BigInteger)
(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(defn is-valid? [secret-key start]
  (= start (subs (md5 secret-key) 0 (count start))))

(defn find-secret [key start]
  (loop [n 1]
    (let [secret (str key n)]
      (if (is-valid? secret start)
        n
        (recur (+ n 1))))))

(defn part1 []
  (find-secret "yzbqklnj" "00000"))

; NOTE: quite slow
(defn part2 []
  (find-secret "yzbqklnj" "000000"))
