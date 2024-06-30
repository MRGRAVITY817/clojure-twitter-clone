(ns bluejay.web.pages.Reactivity
  (:require
   [bluejay.web.utils.solid :refer [create-signal batch]]))

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
                                (set-last-name #(str % "!")))))]
    #jsx
     [:div {:class "p-12"}
      [:h1 {:class "font-bold text-2xl pb-4"}
       "Welcome to the Reactivity page!"]

      [:button {:onClick update-names}
       (str "My name is " (full-name))]]))
