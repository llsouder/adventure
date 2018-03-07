(ns adventure.guess
  (:require [re-com.core :as re-com]
            [reagent.core :as reagent]))


(defn set-hash! [loc]
  (set! (.-hash js/window.location) loc))

(defn check-value [answer value]
  (if (= (str answer) @value)
    (do
      (set-hash! "/")
      (println "you win"))
    (println "nope" answer @value)))


(defn page []
  (let [answer (rand-int 10)
        text-val (reagent/atom "")]
  [:div [:h1 "Quick Guess the number"]
   [re-com/throbber
    :size :large]
   [re-com/input-text
    :on-change #(reset! text-val %)
    :model text-val
    :placeholder "1-10"]
   [re-com/button
    :label "Done"
    :on-click #(check-value answer text-val)]]))
