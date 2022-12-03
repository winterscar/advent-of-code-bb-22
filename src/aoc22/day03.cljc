(ns aoc22.day03
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.string :as s]
            [clojure.set]))

(def input (->> (slurp (io/resource "aoc22/day03.txt"))
                (str/split-lines)))
(def char->val
  (merge (zipmap (map char (range (int \a) (inc (int \z)))) (rest (range)))
         (zipmap (map char (range (int \A) (inc (int \Z)))) (drop 27 (range)))))

; p1
(->> input
     (map #(partition (/ (count %) 2) %))
     (map #(map set %))
     (map #(first (apply clojure.set/intersection %)))
     (map char->val)
     (apply +))

; p2
(->> input
     (partition 3)
     (map #(map set %))
     (map #(first (apply clojure.set/intersection %)))
     (map char->val)
     (apply +))