(ns bluejay.web.pages.Reactivity
  (:require
   [bluejay.web.utils.solid :refer [batch create-signal on untrack]]))

(defn ReactivityPage []
  (let [[first-name set-first-name] (create-signal "Hoon")
        [last-name set-last-name] (create-signal "Wee")
        full-name #(do
                     (println "Full name changed!")
                     (str (first-name) " " (last-name)))
        update-names (fn []
                       (println "Updating names!")
                       (batch (fn []
                                (set-first-name #(str % "n"))
                                (set-last-name #(str % "!")))))
        [a set-a] (create-signal 0)
        [b set-b] (create-signal 0)
        a-and-b #(str "A: " (a) " B: " (untrack b) "!")
        on-a-and-b (on a
                       (fn [a] (str "A: " a " B: " (b) "!"))
                       {:defer true})]
    #jsx
     [:div {:class "p-12"}
      [:h1 {:class "font-bold text-2xl pb-4"}
       "Welcome to the Reactivity page!"]

      [:button {:onClick update-names}
       (str "My name is " (full-name))]

      [:div {:class "flex gap-4 py-4"}
       [:button {:class "p-2 bg-gray-200 rounded"
                 :onClick #(set-a inc)} "Increment A"]
       [:button {:class "p-2 bg-gray-200 rounded"
                 :onClick #(set-b inc)} "Increment B"]]

      [:p (a-and-b)]
      [:p on-a-and-b]]))

