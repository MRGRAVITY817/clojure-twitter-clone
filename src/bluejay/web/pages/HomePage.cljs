(ns bluejay.web.pages.HomePage
  (:require
   [bluejay.web.utils.solid :refer [create-effect create-memo create-signal
                                    Show For Index Switch Match Dynamic Portal ErrorBoundary]]))

(defn fib [n]
  (if (<= n 1)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(defn red-thing []
  #jsx [:div {:style {:color "red"}} "This is red!"])
(defn blue-thing []
  #jsx [:div {:style {:color "blue"}} "This is blue!"])
(defn green-thing []
  #jsx [:div {:style {:color "green"}} "This is green!"])

(def color-options
  {:red   red-thing
   :blue  blue-thing
   :green green-thing})

(defn ErrorButton []
  (throw (js/Error. "An error occurred!"))
  #jsx [:button "Throw an error"])

(defn HomePage []
  (let [[count set-count] (create-signal 0)
        double-count      #(* 2 (count))
        _                 (create-effect #(println "Count is now" (count)))
        fib-count         (create-memo #(fib (count)))
        [logged-in set-logged-in] (create-signal false)
        [color set-color]  (create-signal :red)]

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
       [For {:each (doall (range 1 3))}
        (fn [value index]
          #jsx [:li (str "- Index: "  (index) ", Value: " value)])]]

      [:div {:style {:height "20px"}}]

      [:ul
       [Index {:each (vec (range 3))}
        (fn [value index]
          #jsx [:li (str "- Index: "  index ", Value: " (value))])]]

      [:div {:style {:height "20px"}}]

      [:select {:value   (color)
                :onInput #(set-color (-> % .-currentTarget .-value))}
       [For {:each (keys color-options)}
        (fn [value _]
          #jsx [:option {:value value} (str value)])]]

      [Dynamic {:component (get color-options (color))}]

      [:div {:style {:height "20px"}}]

      [:div {:style {:width "200px"
                     :height "100px"
                     :overflow "hidden"}}
       [:p "Just some text inside a div that has a restricted size."]
       [Portal
        [:div {:style {:position "relative"
                       :z-index "2"
                       :background "#ddd"
                       :padding "1rem"
                       :min-height "200px"
                       :min-width "200px"}}
         [:h1 "Popup"]
         [:p "Some text you might need for something or other."]]]]

      [:div {:style {:height "20px"}}]

      [ErrorBoundary {:fallback (fn [_]
                                  #jsx [:div {:style {:color "red"}} "An error occurred!"])}
       [ErrorButton]]]))

(comment
  (vector 1 2 3 4 5))




