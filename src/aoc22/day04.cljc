(ns aoc22.day04
  (:require [clojure.java.io :as io]
            [clojure.set :refer [intersection subset?]]
            [clojure.string :as str]))

(def input (->> (slurp (io/resource "aoc22/day04.txt"))
                (str/split-lines)))

(defn contains-other?
  [[[s1 e1] [s2 e2]]]
  (or
   (and (<= s1 s2)
        (>= e1 e2))
   (and (<= s2 s1)
        (>= e2 e1))))

(defn overlaps?
  [[[s1 e1] [s2 e2]]]
  (and (<= s1 e2) (>= e1 s2)))

(defn solve
  [pred]
  (time
   (->> input
        (map #(str/split % #"-|,"))
        (map #(map parse-long %))
        (map (partial partition 2))
        (map pred)
        (filter true?)
        (count))))

; p1 
(solve contains-other?)
(solve overlaps?)