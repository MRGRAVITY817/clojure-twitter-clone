(ns bluejay.web.pages.Events
  (:require
   [bluejay.web.utils.solid :refer [create-signal]]))

(def position-signal (create-signal {:x 0 :y 0}))

(defn handle-mouse-move [event]
  (let [[_ set-pos] position-signal]
    #_(println "Mouse moved!" event)
    (set-pos {:x (.-clientX event)
              :y (.-clientY event)})))

(def colors [:red :blue :green])
(def color-signal (create-signal :red))

(defn EventsPage []
  (let [[pos _] position-signal
        [color set-color] color-signal]
    #jsx
     [:div  {:class "p-24 min-h-screen min-w-screen"
             :onMouseMove handle-mouse-move}
      [:h1 {:className "font-bold text-2xl pb-4"}
       "Mouse Position"]
      [:div {:class "grid grid-cols-3 gap-4"}
       [:div {:class "col-span-3"}
        [:div {:class "bg-gray-200 p-4"}
         [:p "X: " (:x (pos))]
         [:p "Y: " (:y (pos))]]]]

      [:h1 {:className "font-bold text-2xl pt-4"}
       "Class List"]

      [:select {:value   (color)
                :onInput #(set-color (-> % .-currentTarget .-value))}
       [For {:each colors}
        (fn [value _]
          #jsx [:option {:value value} (str value)])]]

      [:div {:class "bg-gray-200 p-4"}
       [:p  {:classList {:text-red-500 (= (color) :red)
                         :text-blue-500 (= (color) :blue)
                         :text-green-500 (= (color) :green)}}
        "Color: " (color)]]]))

