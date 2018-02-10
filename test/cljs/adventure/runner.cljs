(ns adventure.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [adventure.core-test]))

(doo-tests 'adventure.core-test)
