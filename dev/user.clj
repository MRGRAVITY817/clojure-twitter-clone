(ns user
  (:require [integrant.repl :as ig-repl]
            [bluejay.api.system :as system])
  (:gen-class))

(ig-repl/set-prep! (fn [] system/config))

(def go ig-repl/go)
(def halt ig-repl/halt)
(def reset ig-repl/reset)
(def reset-all ig-repl/reset-all)

(comment
  (require 'user)
  (go)
  (halt)
  (reset)
  (reset-all))

