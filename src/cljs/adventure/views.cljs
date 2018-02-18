(ns adventure.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :as re-com]
            [adventure.subs :as subs]

            [adventure.tiles :as tile]))

(defn title []
  (let [name (re-frame/subscribe [::subs/name])]
    [re-com/title
     :label (str "Hello from " @name)
     :level :level1]))

(defn main-panel []
  [:svg {:width "800"
         :height "600"
         :viewBox "0 0 800 600"
         :xmlns "http://www.w3.org/2000/svg"}
   (tile/colorBlock "brown" 100 100)
   (tile/test-board)])
