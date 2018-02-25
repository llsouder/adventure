(ns adventure.events
  (:require [re-frame.core :as re-frame]
            [adventure.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :keypress
 (fn  [db [_ keycode]]
   (assoc db :keypress keycode)))

(re-frame/reg-event-db
 :location
 (fn  [db [_ keycode]]
   (println "location event")
    (case keycode
      (87 75) (update-in db [:location :y] dec) ;;up w k
      (83 74) (update-in db [:location :y] inc) ;;down s j
      (68 76) (update-in db [:location :x] inc) ;;right d l
      (65 72) (update-in db [:location :x] dec) ;;left a h
      (do (println (str "unsupported " keycode))
          db))))
