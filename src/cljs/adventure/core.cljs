(ns adventure.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [adventure.events :as events]
            [adventure.views :as views]
            [adventure.config :as config]))

(def canvas (.getElementById js/document "drawing1" ))
(def ctx (.getContext canvas "2d"))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(set! (.-fillStyle ctx) "rgb(255,0,0)")
(.beginPath ctx)
(.rect ctx 100 100 50 30)
(.fill ctx)

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
 
