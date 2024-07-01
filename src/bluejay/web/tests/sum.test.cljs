(ns bluejay.web.tests.sum.test
  (:require ["vitest" :refer [expect test]]))

(defn sum [a b]
  (+ a b))

(test "adds 1 + 2 to equal 3"
      (fn []
        (expect (sum 1 2) 3)))


