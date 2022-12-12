(ns aoc22.day08
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (->> (slurp (io/resource "aoc22/day08.txt"))
                (str/split-lines)
                (map parse-long)))
(defn part-1
  [_]
  (time
   (->> input
        (partition-by nil?)
        (filter #(not (nil? (first %))))
        (map #(reduce + %))
        (reduce max))))

(defn part-2
  [_]
  (time
   (->> input
        (partition-by nil?)
        (filter #(not (nil? (first %))))
        (map #(reduce + %))
        (sort >)
        (take 3)
        (reduce +))))

(part-1 nil)
(part-2 nil)