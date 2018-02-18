(ns adventure.tiles)

(defn colorBlock
  "Create svg rectangle vector at location xy with the color string specified by the arg."
  [color x y]
   [:rect {:width "50"
           :height "50"
           :x x
           :y y
           :style {:fill color :stroke-width 3 :stroke "rgb(0,0,0)"}}
    "Sorry, your browser does not support inline SVG."])
