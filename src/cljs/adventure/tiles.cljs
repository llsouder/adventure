(ns adventure.tiles)

(def W 50)
(def WSTR (str W))
(def H 50)
(def HSTR (str H))

(def board {:tiles [["x" "x" "x" "x" "x" "g" "g"]
                    ["g" "g" "g" "x" "x" "g" "x"]
                    ["g" "x" "g" "x" "x" "g" "x"]
                    ["g" "x" "g" "x" "x" "g" "x"]
                    ["g" "x" "g" "x" "x" "g" "x"]
                    ["g" "x" "g" "g" "g" "g" "x"]
                    ["g" "x" "x" "x" "x" "x" "x"]
                    ["g" "g" "g" "g" "x" "x" "x"]
                    ["g" "g" "g" "g" "x" "x" "x"]
                    ["g" "g" "g" "g" "x" "x" "x"]
                    ["g" "g" "g" "g" "x" "x" "x"]
                    ["g" "g" "g" "g" "x" "x" "x"]
                    ["g" "g" "g" "g" "x" "x" "x"]
                    ]})
(defn pick
  "Pick color using a single character.  This makes the board data more readable."
  [abbr]
  (case abbr
    "r" "red"
    "g" "gray"
    "x" "green" ;;default
    abbr))

(defn colorBlock
  "Create svg rectangle vector at location xy with the color string specified by the arg."
  [color x y]
   [:rect {:width WSTR
           :height HSTR 
           :x x
           :y y
           :style {:fill color :stroke-width 3 :stroke "rgb(0,0,0)"}}
    "Sorry, your browser does not support inline SVG."])

(defn draw-row
  [row row-idx]
  (let [y (* row-idx H)]
  (map-indexed (fn [idx color] (colorBlock (pick color) (* W idx) y)) row)))

(defn draw-board
  "see map above"
  [board]
  (let [tiles (:tiles board)]
  (map-indexed (fn [idx row] (draw-row row idx)) tiles)))

(defn test-board [] (draw-board board))

(defn drawPlayerAt
  "return the player svg on the board"
  [x y]
  [:circle {:cx x
            :cy y
            :r (/ W 2)
            :stroke "black"
            :stroke-width "3"
            :fill "red"}])

(defn drawPlayer
  [location]
  (let [x (+ (* W (:x location)) (/ W 2))
        y (+ (* H (:y location)) (/ H 2))]
    (drawPlayerAt x y)))
