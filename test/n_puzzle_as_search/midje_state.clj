(ns n_puzzle_as_search.midje-state
  (:use [midje.sweet])
  (:require [n-puzzle-as-search.state :refer :all]))

(fact (+ 2 2) => 4)
(fact (+ 2 2) => even?)

(facts
  (:board (->State [[0 1 2] [3 4 5] [6 7 8]] [0 0]))
      => [[0 1 2] [3 4 5] [6 7 8]]
  (:board (->State [[5 4 6] [3 2 0] [1 7 8]] [2 0]))
      => [[5 4 6] [3 2 0] [1 7 8]]
  )

(facts
  (:blank-position (->State [[0 1 2] [3 4 5] [6 7 8]] [0 0]))
      => [0 0]
  (:blank-position (->State [[5 4 6] [3 2 0] [1 7 8]] [1 2]))
      => [1 2]
  )

(facts
  (range 3) => (has every? legal?)
  (range -5 0) => (has not-any? legal?)
  (range 4 10) => (has not-any? legal?)
  )

(facts
  (swap [[0 1 2] [3 4 5] [6 7 8]] [0 0] [0 1])
    => [[1 0 2] [3 4 5] [6 7 8]]
  (swap [[0 1 2] [3 4 5] [6 7 8]] [0 0] [1 0])
    => [[3 1 2] [0 4 5] [6 7 8]]
  (swap [[5 4 6] [3 2 0] [1 7 8]] [1 2] [0 2])
    => [[5 4 0] [3 2 6] [1 7 8]]
  (swap [[5 4 6] [3 2 0] [1 7 8]] [1 2] [2 2])
    => [[5 4 6] [3 2 8] [1 7 0]]
  (swap [[5 4 6] [3 2 0] [1 7 8]] [1 2] [1 1])
    => [[5 4 6] [3 0 2] [1 7 8]]
  )

(facts
  (children (->State [[0 1 2] [3 4 5] [6 7 8]] [0 0]))
    => (just #{(->State [[1 0 2] [3 4 5] [6 7 8]] [0 1])
               (->State [[3 1 2] [0 4 5] [6 7 8]] [1 0])})
  (children (->State [[5 4 6] [3 2 0] [1 7 8]] [1 2]))
    => (just #{(->State [[5 4 0] [3 2 6] [1 7 8]] [0 2])
               (->State [[5 4 6] [3 2 8] [1 7 0]] [2 2])
               (->State [[5 4 6] [3 0 2] [1 7 8]] [1 1])})
  (children (->State [[5 4 6] [2 0 3] [1 7 8]] [1 1]))
    => (just #{(->State [[5 4 6] [0 2 3] [1 7 8]] [1 0])
               (->State [[5 4 6] [2 3 0] [1 7 8]] [1 2])
               (->State [[5 0 6] [2 4 3] [1 7 8]] [0 1])
               (->State [[5 4 6] [2 7 3] [1 0 8]] [2 1])})
  )
