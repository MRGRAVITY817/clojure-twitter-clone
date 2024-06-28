(ns bluejay.web.pages.HomePage
  (:require
   [bluejay.web.utils.solid :refer [create-effect create-memo create-signal
                                    Show For Index Switch Match]]))

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

    #jsx
     [:div "Welcome to Bluejay!"
      [:div {:style {:height "auto"
                     :display "flex"
                     :flex-direction "row"
                     :justify-content "start"
                     :align-items "center"
                     :gap "10px"}}
       [:button {:style   {:padding "0px 10px"
                           :border-radius "5px"
                           :background-color "lightgrey"}
                 :onClick #(set-count dec)}
        "-"]
       [:button {:style   {:padding "0px 10px"
                           :border-radius "5px"
                           :background-color "lightgrey"}
                 :onClick #(set-count inc)}
        "+"]]

      [:p "You clicked me " (count) " times!"]
      [:p "Double that is " (double-count) "!"]
      [:p "The Fibonacci of that is " (fib-count) "!"]

      [:h3 {:style {:margin-top "20px"
                    :margin-bottom "10px"
                    :font-size "20px"}}
       "Using Clojure case statement"]

      (case (mod (count) 3)
        0 #jsx [:p "Count is divisible by 3!"]
        1 #jsx [:p "Count mod 3 is 1!"]
        #jsx [:p "Count mod 3 is 2!"])

      [:h3 {:style {:margin-top "20px"
                    :margin-bottom "10px"
                    :font-size "20px"}}
       "Using SolidJS Switch/Match component"]

      [Switch {:fallback #jsx [:p "Count mod 3 is 2!"]}
       [Match {:when (= (mod (count) 3) 0)}
        [:p "Count is divisible by 3!"]]
       [Match {:when (= (mod (count) 3) 1)}
        [:p "Count mod 3 is 1!"]]]

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

      [:div {:style {:height "20px"}}]

      [:ul
       [Index {:each (doall (range 1 16))}
        (fn [value index]
          #jsx [:li (str "- Index: "  index ", Value: " (value))])]]

      [:div {:style {:height "20px"}}]]))

(comment
  (vector 1 2 3 4 5))




