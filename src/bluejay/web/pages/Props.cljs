(ns bluejay.web.pages.Props
  (:require
   [bluejay.web.utils.solid :as s :refer [children create-signal merge-props
                                          split-props]]))

(defn Greeting [props]
  (let [props (merge-props {:greeting "Hello"
                            :name     "Hoon"} props)]
    #jsx
     [:h1 (:greeting props) " " (:name props) "!"]))

(defn Farewell [props]
  (let [[local others] (split-props props [:farewell :name])
        {:keys [farewell name]} local]
    #jsx
     [:h1 {:& others}
      farewell " " name "!"]))

(defn ColoredList [props]
  (let [c (children #(:children props))
        colored-c #(->> (c)
                        (map (fn [el]
                               #jsx [:div {:class (:color props)} el]))
                        (vec))]
    #jsx
     [:ul {:class "list-disc list-inside"}
      (colored-c)]))

(defn PropsPage []
  (let [[color set-color] (create-signal "text-blue-500")]
    #jsx
     [:div {:class "p-12"}
      [:h1 {:class "font-bold text-2xl pb-4"}
       "Welcome to the Props page!"]
      [Greeting {:greeting "안녕" :name "World"}]
      [Greeting {:name "조은비"}]
      [Greeting]

      [:br]
      [:br]

      [Farewell {:class "font-bold text-2xl text-red-500"
                 :farewell "Goodbye" :name "Hoon"}]
      [Farewell {:class "font-bold text-2xl text-blue-500"
                 :farewell "Goodbye" :name "Eunbee"}]

      [ColoredList {:color (color)}
       [:li "One"]
       [:li "Two"]
       [:li "Three"]]

      [:div {:class "flex flex-col gap-1 items-start"}
       [:button {:class "p-2 bg-gray-200 rounded"
                 :onClick #(set-color "text-red-500")}
        "Change color to red"]
       [:button {:class "p-2 bg-gray-200 rounded"
                 :onClick #(set-color "text-blue-500")}
        "Change color to blue"]]]))
