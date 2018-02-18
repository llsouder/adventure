(ns adventure.tiles-test
  (:require  [cljs.test :as t :include-macros true]
             [adventure.tiles :as tile]))

(t/deftest tile-svg-test
  (t/testing "make the svg tile tag"
    (t/is (= [:rect {:width "50"
                     :height "50"
                     :x 10
                     :y 20
                     :style {:fill "rgb(100,100,100)"
                             :stroke-width 3
                             :stroke "rgb(0,0,0)"}}
              "Sorry, your browser does not support inline SVG."]
             (tile/colorBlock "rgb(100,100,100)" 10 20)))))
