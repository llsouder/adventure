(ns adventure.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [adventure.subs :as subs]
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

(defn main-panel []
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
