(ns bluejay.web.pages.Props
  (:require [bluejay.web.utils.solid :as s :refer [create-signal on-cleanup]]))

(defn Greeting [props]
  (let [props (s/merge {:greeting "Hello"
                        :name     "Hoon"} props)]
    #jsx
     [:h1 (:greeting props) " " (:name props) "!"]))

(defn PropsPage []
  (let []
    #jsx
     [:div {:class "p-12"}
      [:h1 {:class "font-bold text-2xl pb-4"}
       "Welcome to the Props page!"]
      [Greeting {:greeting "안녕" :name "World"}]
      [Greeting {:name "조은비"}]
      [Greeting]]))
