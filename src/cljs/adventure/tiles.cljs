(ns adventure.tiles)


(def board {;;:size-x 5
            ;;:size-y 4
            ;;:tile-size 50
            :tiles [["red" "red" "red" "red" "red"]
                    ["gray" "gray" "gray" "red" "red"]
                    ["gray" "red" "gray" "red" "red"]
                    ["gray" "red" "gray" "gray" "gray"]]})
(defn colorBlock
  "Create svg rectangle vector at location xy with the color string specified by the arg."
  [color x y]
   [:rect {:width "50"
           :height "50"
           :x x
           :y y
           :style {:fill color :stroke-width 3 :stroke "rgb(0,0,0)"}}
    "Sorry, your browser does not support inline SVG."])

(defn draw-row
  [row row-idx]
  (let [y (* row-idx 50)]
  (map-indexed (fn [idx color] (colorBlock color (* 50 idx) y)) row)))

(defn draw-board
  "see map above"
  [board]
  (let [tiles (:tiles board)]
  (map-indexed (fn [idx row] (draw-row row idx)) tiles)))

(defn test-board [] (draw-board board))
