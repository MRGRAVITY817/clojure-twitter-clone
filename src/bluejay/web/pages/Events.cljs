(ns bluejay.web.pages.Events
  (:require
   [bluejay.web.utils.solid :refer [create-signal]]))

(def position-signal (create-signal {:x 0 :y 0}))

(defn handle-mouse-move [event]
  (let [[_ set-pos] position-signal]
    #_(println "Mouse moved!" event)
    (set-pos {:x (.-clientX event)
              :y (.-clientY event)})))

(defn EventsPage []
  (let [[pos _] position-signal]
    #jsx
     [:div  {:className "p-24 min-h-screen min-w-screen"
             :onMouseMove handle-mouse-move}
      [:h1 {:className "font-bold text-2xl pb-4"}
       "Mouse Position"]
      [:div {:className "grid grid-cols-3 gap-4"}
       [:div {:className "col-span-3"}
        [:div {:className "bg-gray-200 p-4"}
         [:p "X: " (:x (pos))]
         [:p "Y: " (:y (pos))]]]]]))

