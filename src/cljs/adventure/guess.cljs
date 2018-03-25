(ns adventure.guess
  (:require [re-com.core :as re-com]
            [re-frame.core :as re-frame] ;;need re-frame for :ontouchstart to work.
            [reagent.core :as reagent]))


(defn set-hash! [loc]
  (set! (.-hash js/window.location) loc))

(defn check-value [answer value]
  (if (= (str answer) @value)
    (do
      (set-hash! "/")
      (println "you win"))
    (println "nope" answer @value)))

(defn flip-tags
  "text on the front and back of the panel and the location where
  onclick will send the user."
  [front-text back-text loc]
  [:div {:class "flip-container" :onClick #(set-hash! loc) }
   [:div {:class "flipper"}
   [:div {:class "front"}
    [:h1 front-text]]
   [:div {:class "back"}
    [:h1 back-text]]]])

(defn page []
  (let [answer (rand-int 10)
        text-val (reagent/atom "")]
    (set-hash! "/puzzle-1")
    [:div [:h1 "Find your way out."]
   [re-com/throbber
    :size :large]
     [re-com/h-box
      :children [(flip-tags 1 "maybe" "/win")
                 (flip-tags 2 "nope" "/die")
                 (flip-tags 3 "maybe" "/die")]]
   [re-com/input-text
    :on-change #(reset! text-val %)
    :model text-val
    :placeholder "1-10"]
   [re-com/button
    :label "Done"
    :on-click #(check-value answer text-val)]]))
