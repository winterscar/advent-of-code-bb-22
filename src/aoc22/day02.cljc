(ns aoc22.day02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn score-given-outcome
  [iop iout]
  (+ (* 3 iout)
     (get-in [[3 1 2] [1 2 3] [2 3 1]]
             [iop iout])))

(defn score-given-play
  [iop iyou]
  (+ (inc iyou)
     (get-in [[3 6 0] [0 3 6] [6 0 3]]
             [iop iyou])))

(defmacro make-score-lookup
  [calc-fn]
  (flatten
   (list 'case 'line
    (for [a [\A \B \C]
          :let [ia (- (int a) (int \A))]
          b [\X \Y \Z]
          :let [ib (- (int b) (int \X))]]
      (list (str a " " b)
            ((resolve calc-fn) ia ib))))))

(time
 (let [plays (->> (slurp (io/resource "aoc22/day02.txt"))
                  (str/split-lines))]
   (println "p1 " (reduce (fn [curr line]
                            (+ curr (make-score-lookup score-given-play))) 0 plays))
   (println "p2 " (reduce (fn [curr line]
                            (+ curr (make-score-lookup score-given-outcome))) 0 plays))))