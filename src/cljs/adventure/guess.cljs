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

(defn flip-tags [text]
  [:div {:class "flip-container" :onClick #(set-hash! "/about") }
   [:div {:class "flipper"}
   [:div {:class "front"}
    [:h1 text]]
   [:div {:class "back"}
    [:h1 "Back"]]]])

(defn page []
  (let [answer (rand-int 10)
        text-val (reagent/atom "")]
    (set-hash! "/puzzle-1")
    [:div [:h1 "Find your way out."]
   [re-com/throbber
    :size :large]
     [re-com/h-box
      :children [(flip-tags 1)
                 (flip-tags 2)
                 (flip-tags 3)]]
   [re-com/input-text
    :on-change #(reset! text-val %)
    :model text-val
    :placeholder "1-10"]
   [re-com/button
    :label "Done"
    :on-click #(check-value answer text-val)]]))
