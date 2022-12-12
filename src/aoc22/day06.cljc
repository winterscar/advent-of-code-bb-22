(ns aoc22.day06
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn duplicates?
  [coll]
  (not= (count (set coll))
        (count coll)))

(map-indexed #(if (duplicates %1) (+4 i) ))

(defn dup-index
  [coll size]
  (loop [i 0
         coll coll]
    (let [window (take size coll)]
      (if (duplicates? window)
        (recur (inc i) (rest coll))
        (+ size i)))))


(def input (slurp (io/resource "aoc22/day06.txt")))

(dup-index input 4)
(dup-index input 14)