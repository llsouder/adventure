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
