(ns adventure.game-test
  (:require [adventure.game :as g]
            [adventure.tiles :as tile]
            [cljs.test :as t :include-macros true]))

(def testboard (tile/make-board
                [["x" "x" "x"]
                 ["x" "x" "x"]
                 ["x" "x" "x"]]))

(t/deftest valid-move-test
  (t/testing "keep it on the board! stop the invalid indexes."
    (t/is (= true (g/valid-move? {:x 1 :y 1 :action "none"} :x inc testboard)))
    (t/is (= true (g/valid-move? {:x 1 :y 1 :action "none"} :y inc testboard)))
    (t/is (= false (g/valid-move? {:x 0 :y 0} :x dec testboard)))
    (t/is (= false (g/valid-move? {:x 0 :y 0} :y dec testboard)))
    (t/is (= true (g/valid-move? {:x 2 :y 2} :x dec testboard)))
    (t/is (= true (g/valid-move? {:x 2 :y 2} :y dec testboard)))
    (t/is (= false (g/valid-move? {:x 2 :y 2} :x inc testboard)))
    (t/is (= false (g/valid-move? {:x 2 :y 2} :y inc testboard)))))

