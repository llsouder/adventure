(ns adventure.tiles-test
  (:require  [cljs.test :as t :include-macros true]
             [adventure.tiles :as tile]))

(t/deftest tile-svg-test
  (t/testing "make the svg tile tag"
    (t/is (= [:rect {:key "10,20"
                     :width "50"
                     :height "50"
                     :x 10
                     :y 20
                     :style {:fill "rgb(100,100,100)"
                             :stroke-width 3
                             :stroke "rgb(0,0,0)"}}
              "Sorry, your browser does not support inline SVG."]
             (tile/colorBlock "rgb(100,100,100)" 10 20)))))

(def testboard {:tiles [["x" "x" "x"]
                        ["x" "x" "x"]
                        ["x" "x" "x"]]
                :x-size 3
                :y-size 3})

(t/deftest valid-move-test
  (t/testing "keep it on the board! stop the invalid indexes."
    (t/is (= true (tile/valid-move? {:x 1 :y 1 :action "none"} :x inc testboard)))
    (t/is (= true (tile/valid-move? {:x 1 :y 1 :action "none"} :y inc testboard)))

    (t/is (= false (tile/valid-move? {:x 0 :y 0} :x dec testboard)))
    (t/is (= false (tile/valid-move? {:x 0 :y 0} :y dec testboard)))

    (t/is (= true (tile/valid-move? {:x 2 :y 2} :x dec testboard)))
    (t/is (= true (tile/valid-move? {:x 2 :y 2} :y dec testboard)))
    (t/is (= false (tile/valid-move? {:x 2 :y 2} :x inc testboard)))
    (t/is (= false (tile/valid-move? {:x 2 :y 2} :y inc testboard)))))
