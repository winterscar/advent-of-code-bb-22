(ns aoc22.day05
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(let [[_ ia]
      (-> (slurp (io/resource "aoc22/day05.txt"))
          (str/split #"\n\n"))]
  (def input-actions
    (->> ia
         (str/split-lines)
         (map #(str/split % #"([a-z]| )+"))
         (map rest)
         (map #(map parse-long %)))))

(def crates
  (sorted-map
   1 '(r n p g)
   2 '(\t \j \b \l \c \s \v \h)
   3 '(\t \d \b \m \n \l)
   4 '(\r \v \p \s \b)
   5 '(\g \c \q \s \w \m \v \h)
   6 '(\w \q \s \c \d \b \j)
   7 '(\f \q \l)
   8 '(\w \m \h \t \d \l \f \v)
   9 '(\l \p \b \v \m \j \f)))

(defn move
  [count from to crates]
  (let [new-to (->> (crates from)
                    (take-last count)
                    ; reverse uncomment for p1
                    (concat (crates to)))
        new-from (drop-last count (crates from))]
    (assoc crates
           to new-to
           from new-from)))

(->>
 (reduce (fn [acc [c f t]] (move c f t acc)) 
         crates input-actions)
 (map (comp last last))
 (apply str))