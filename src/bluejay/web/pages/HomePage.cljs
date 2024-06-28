(ns bluejay.web.pages.HomePage
  (:require
   [bluejay.web.utils.solid :refer [create-effect create-memo create-signal]]))

(defn fib [n]
  (if (<= n 1)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(defn HomePage []
  (let [[count set-count] (create-signal 0)
        double-count      #(* 2 (count))
        _                 (create-effect #(println "Count is now" (count)))
        fib-count         (create-memo #(fib (count)))]
    #jsx [:div "Welcome to Bluejay!"
          [:button {:onClick (fn [] (set-count inc))}
           "Click me!"]
          [:p "You clicked me " (count) " times!"]
          [:p "Double that is " (double-count) "!"]
          [:p "The Fibonacci of that is " (fib-count) "!"]]))
