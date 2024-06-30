(ns bluejay.web.pages.Async
  (:require [bluejay.web.utils.solid :as s]))

(def Greeting
  (s/lazy
   #(-> (new js/Promise (fn [r] (js/setTimeout r 1000)))
        (.then (fn [_] (js/import "../components/Greeting"))))

;; using async/await
   #_(^:async
      fn []
         (let [_ (js-await (new js/Promise (fn [r] (js/setTimeout r 1000))))]
           (js/import "../components/Greeting")))))

(defn AsyncPage []
  (let []
    #jsx
     [:div {:class "p-12"}
      [:h1 {:class "font-bold text-2xl pb-4"}
       "Welcome to the Async page!"]

      [Greeting]]))
