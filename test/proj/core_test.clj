(ns proj.core-test
  (:require [clojure.test :refer :all]
            [proj.core :refer :all]))

(deftest make-row
  (testing "A row has three elements"
    (is (= 3 (count empty-row)))))

(deftest make-board
  (testing "A board has three elements"
    (is (= 3 (count empty-board)))))

(deftest printing-row
  (testing "'0 -1 1' prints as 'X O'"
    (is (= "\nX O" (printed-row [-1 0 1])))))

(deftest getting-board-column
  (testing "column 2 of '0 -1 1, 0 0 1, 0 0 0' is  -1 0 0"

    (is (= [-1 0 0] (get-board-column [[0 -1 1] [0 0 1] [0 0 0]] 1)))))

(deftest getting-board-columns
  (testing "'0 -1 1, 0 0 1, 0 0 0' columnizes to  '0 0 0, -1 0 0, 1 1 0'"

    (is (= [[0 0 0] [-1 0 0] [1 1 0]] (board-columns [[0 -1 1] [0 0 1] [0 0 0]])))))

(deftest getting-board-at-pos
  (is (= 0  (get-board-at-pos [[0 0 0] [-1 0 0] [1 1 0]] 0 0)))
  (is (= 0  (get-board-at-pos [[0 0 0] [-1 0 0] [1 1 0]] 1 1)))
  (is (= 0  (get-board-at-pos [[0 0 0] [-1 0 0] [1 1 0]] 2 2)))
  (is (= -1 (get-board-at-pos [[0 0 0] [-1 0 0] [1 1 0]] 0 1)))
  (is (= 1  (get-board-at-pos [[0 0 0] [-1 0 0] [1 1 0]] 1 2))))

(deftest getting-cross-lines
  (is (= [[0 0 0] [1 0 0]]  (get-cross-lines [[0 0 0] [-1 0 0] [1 1 0]]))))

(deftest getting-value-count
  (is (= 6 (value-count [[0 -1 1] [0 0 1] [0 0 0]] 0))))

(deftest getting-all-lines
  (is (= 8 (count (all-lines [[0 -1 1] [0 0 1] [0 0 0]])) )) )

(deftest checking-has-win
  (is (= false  (has-win [[0 0 0] [0 0 0] [0 0 0]])))
  (is (= true  (has-win [[1 0 0] [1 0 0] [1 0 0]])))
  (is (= true  (has-win [[-1 0 0] [0 -1 0] [0 0 -1]])))
  (is (= false (has-win [[-1 0 0] [-1 -1 0] [0 -1 0]])))
  (is (= true  (has-win [[1 0 -1] [0 1 -1] [-1 0 1]]))))

(deftest checking-winner
  (is (= nil (winner [[0 0 0] [0 0 0] [0 0 0]])))
  (is (= 1   (winner [[1 0 0] [1 0 0] [1 0 0]])))
  (is (= -1  (winner [[-1 0 0] [0 -1 0] [0 0 -1]])))
  (is (= nil (winner [[-1 0 0] [-1 -1 0] [0 -1 0]])))
  (is (= 1   (winner [[1 0 -1] [0 1 -1] [-1 0 1]]))))

(deftest winning-lines
  (is (= true  ( is-line-win [1 1 1]    )))
  (is (= true  ( is-line-win [-1 -1 -1] )))
  (is (= false ( is-line-win [0 0 0]    )))
  (is (= false ( is-line-win [1 1 -1]   )))
  (is (= false ( is-line-win [1 0 1]    ))))