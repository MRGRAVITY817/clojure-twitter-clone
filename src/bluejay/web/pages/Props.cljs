(ns bluejay.web.pages.Props
  (:require [bluejay.web.utils.solid :as s :refer [create-signal on-cleanup merge-props split-props]]))

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

(defn PropsPage []
  (let []
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
                 :farewell "Goodbye" :name "Eunbee"}]]))
