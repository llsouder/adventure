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
    (case keycode
      87 (update-in db [:location :y] dec) ;;up w
      83 (update-in db [:location :y] inc) ;;down s
      68 (update-in db [:location :x] inc) ;;right d
      65 (update-in db [:location :x] dec) ;;left a
      (println (str "unsupported " keycode)))))
