(ns adventure.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [adventure.core-test]
              [adventure.game-test]
              [adventure.tiles-test]))

(doo-tests 
           'adventure.game-test
           'adventure.tiles-test)
