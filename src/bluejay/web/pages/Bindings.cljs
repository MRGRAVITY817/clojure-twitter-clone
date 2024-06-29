(ns bluejay.web.pages.Bindings
  (:require
   [bluejay.web.utils.solid :refer [create-signal on-cleanup]]))

(def position-signal (create-signal {:x 0 :y 0}))

(defn handle-mouse-move [event]
  (let [[_ set-pos] position-signal]
    #_(println "Mouse moved!" event)
    (set-pos {:x (.-clientX event)
              :y (.-clientY event)})))

(def colors [:red :blue :green])
(def color-signal (create-signal :red))

(defn- SimpleButton [props]
  #jsx
   [:div
    [:button {:ref (.-ref props)}
     "Click me!"]])

(defn clickOutside [el accessor]
  (let [on-click (fn [e] (and (not (.contains el (.-target e)))
                              ((accessor))))]
    (js/document.body.addEventListener "click" on-click)
    (on-cleanup #(js/document.body.removeEventListener "click" on-click))))

(defn BindingsPage []
  (let [[pos _] position-signal
        [color set-color] color-signal
        [show set-show] (create-signal false)]
    #jsx
     [:div  {:class "p-24 min-h-screen min-w-screen"
             :onMouseMove handle-mouse-move
             :ref #(println "Ref!" %)}
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
        "Color: " (color)]]

      [:h1 {:className "font-bold text-2xl pt-4"}
       "Forwarding Ref"]
      [SimpleButton {:ref #(println "Button Ref!" %)}]

      [:h1 {:className "font-bold text-2xl pt-4"}
       "Using 'use:' directive"]
      [Show {:when (show)
             :fallback #jsx
                        [:button {:onClick #(set-show true)}
                         "Open Modal"]}
       [:div {:class "p-16 bg-gray-600"
              :use:clickOutside #(set-show false)}
        "Some Modal"]]]))




