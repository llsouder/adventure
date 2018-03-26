(ns adventure.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [reagent.core :as reagent]
            [adventure.subs :as subs]
            [adventure.guess :as guess]
            [adventure.events :as events]
            [adventure.routes :as routes]
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

(defn error-panel []
  [:div [:h1 "error! error! panel not found"]
   [re-com/hyperlink
    :label            "restart"
    :tooltip-position :left-center
    :on-click #(re-frame/dispatch [::events/set-active-panel :home-panel])]])

(defn prize-panel []
  [:div [:h1 "GOLD!"]
   [re-com/hyperlink
    :label            "home"
    :tooltip-position :left-center
    :on-click #(re-frame/dispatch [::events/set-active-panel :home-panel])]])

(defn about-panel []
  (routes/set-hash! "/about")
  [:div [:h1 "About"]
  [re-com/hyperlink
          :label            "Home"
          :tooltip-position :left-center
          :on-click #(re-frame/dispatch [::events/set-active-panel :home-panel])]])

(defn home-panel []
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

(defn escape-panel []
  (routes/set-hash! "/escape")
  [:div [:h1 "You have escaped."]
   [re-com/hyperlink
    :label            "continue"
    :tooltip-position :left-center
    :on-click         #(re-frame/dispatch [::events/set-active-panel :home-panel])]])

(defn die-panel []
  (routes/set-hash! "/die")
  [:div [:h1 "You are DEAD!"]
   [re-com/hyperlink
    :label            "Start Over"
    :tooltip-position :left-center
    :on-click
    #((re-frame/dispatch [::events/initialize-db])
      (re-frame/dispatch [::events/set-active-panel :home-panel]))]])

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    :puzzle-1 [guess/page]
    :escape-panel [escape-panel]
    :die-panel [die-panel]
    :prize-panel [prize-panel]
    [error-panel]))

(defn main-panel []
  (.addEventListener js/document "keydown"  action)
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :height "100%"
     :children [[panels @active-panel]]]))
