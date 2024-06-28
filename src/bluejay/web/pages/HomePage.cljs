(ns bluejay.web.pages.HomePage
  (:require
   [bluejay.web.utils.solid :refer [create-effect create-memo create-signal Show]]))

(defn fib [n]
  (if (<= n 1)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(defn HomePage []
  (let [[count set-count] (create-signal 0)
        double-count      #(* 2 (count))
        _                 (create-effect #(println "Count is now" (count)))
        fib-count         (create-memo #(fib (count)))
        [logged-in set-logged-in] (create-signal false)]

    #jsx [:div "Welcome to Bluejay!"
          [:button {:onClick #(set-count inc)}
           "Click me!"]
          [:p "You clicked me " (count) " times!"]
          [:p "Double that is " (double-count) "!"]
          [:p "The Fibonacci of that is " (fib-count) "!"]

          [:div {:style {:height "20px"}}]

          [:div {:style {:display "flex"
                         :flex-direction "column"
                         :justify-content "start"
                         :align-items "start"}}
           [:button {:onClick #(set-logged-in not)}
            (if (logged-in) "Log out" "Log in")]

           [:p (if (logged-in)
                 "You are logged in!"
                 "You are not logged in!")]

           [Show {:when (logged-in)
                  :fallback #jsx [:p "You are not logged in!"]}
            [:p "You are logged in!"]]]

          [:div {:style {:height "20px"}}]

          [:ul
           [For {:each (doall (range 1 16))}
            (fn [value index]
              #jsx [:li (str "- Index: "  (index) ", Value: " value)])]]

          #_(->> (range 1 16)
                 (map #(vector %))
                 (map-indexed (fn [index value] [:p value])))]))

(comment
  (vector 1 2 3 4 5))




