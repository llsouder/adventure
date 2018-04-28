(ns adventure.monster)

(defn square-wave
  "return the value for the specified period."
  [x period]
  (Math.pow -1 (/ (- x (mod x period)) period)))
