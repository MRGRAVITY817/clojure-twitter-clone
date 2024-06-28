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

(comment
  (defn fib [n]
    (cond (= n 0) 0
          (= n 1) 1
          :else (+ (fib (- n 1)) (fib (- n 2)))))

  (fib 0) ; 0
  (fib 1) ; 1
  (fib 2) ; 1
  (fib 3) ; 2
  (fib 4) ; 3
  (fib 5) ; 5

  (time (fib 10))

  (defn fib-iter [a  b count]
    (if (= count 0)
      b
      (fib-iter (+ a  b) a (- count 1))))

  (defn fib-better [n]
    (fib-iter 1 0 n))

  (fib-better 0) ; 0
  (fib-better 1) ; 1
  (fib-better 2) ; 1
  (fib-better 3) ; 2
  (fib-better 4) ; 3
  (fib-better 5) ; 5

  (time (fib-better 10)))
