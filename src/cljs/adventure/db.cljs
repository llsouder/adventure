(ns adventure.db
  (:require [adventure.tiles :as tiles]))

(def default-db
  {:name         "Adventure"
   :board        (tiles/make-board tiles/tiles)
   :active-panel :home-panel
   ;;player info
   :gold         0
   :health       10
   :location     {:x 6 :y 0 :action "none"}})
