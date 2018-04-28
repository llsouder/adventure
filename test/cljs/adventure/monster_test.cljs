(ns adventure.monster-test
  (:require [adventure.monster :as m]
            [cljs.test :as t :include-macros true]))


(t/deftest square-wave-test
  (t/testing "square-wave testing.")
  ;;period of 10
  (t/is (=  1 (m/square-wave  5 10)))
  (t/is (= -1 (m/square-wave 10 10)))
  (t/is (= -1 (m/square-wave 15 10)))
  (t/is (=  1 (m/square-wave 20 10)))
  ;;period of 20
  (t/is (=  1 (m/square-wave  0 20)))
  (t/is (= -1 (m/square-wave 20 20)))
  (t/is (=  1 (m/square-wave 40 20)))
  (t/is (= -1 (m/square-wave 60 20))))

