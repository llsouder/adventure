(ns adventure.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [adventure.subs :as subs]
            [adventure.guess :as guess]
            [adventure.tiles :as tile]))

(defn action
  [event]
  (let [key-code (.-keyCode event)]
    (do
      (re-frame/dispatch [:location key-code]))))

(defn title []
  (let [name (re-frame/subscribe [::subs/name])]
    [re-com/title
     :label @name
     :level :level1]))

(def bump (js/Audio. "bump.wav"))

(defn about-panel []
  [:div [:h1 "About"]])

(defn home-panel []
  (.addEventListener js/document "keydown"  action)
  (if (= (:action @(re-frame/subscribe [::subs/location])) "bump")
    (.play bump))
  [:div
    (title)
    [:svg {:width "800"
           :height "600"
           :viewBox "0 0 800 600"
           :xmlns "http://www.w3.org/2000/svg"}
     (tile/test-board)
     (tile/drawPlayer @(re-frame/subscribe [::subs/location]))]
   [:h3 (:action @(re-frame/subscribe [::subs/location]))]])

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    :puzzle-1 [guess/page]
    [home-panel]))

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :height "100%"
     :children [[panels @active-panel]]]))
