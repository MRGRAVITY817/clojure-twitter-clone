(ns bluejay.web.pages.Async
  (:require
   [bluejay.web.utils.solid :as s :refer [create-resource create-signal]]))

(def Greeting
  (s/lazy
   #(-> (new js/Promise (fn [r] (js/setTimeout r 1000)))
        (.then (fn [_] (js/import "../components/Greeting"))))

;; using async/await
   #_(^:async
      fn []
         (let [_ (js-await (new js/Promise (fn [r] (js/setTimeout r 1000))))]
           (js/import "../components/Greeting")))))

(defn fetch-user [id]
  (-> (js/fetch (str "https://swapi.dev/api/people/" id))
      (.then #(.json %))))

(defn FetchUser []
  (let [[user-id set-user-id] (create-signal 1)
        [user]                (create-resource user-id fetch-user)]
    #jsx
     [:div
      [:input {:type        "number"
               :min         1
               :placeholder "Enter user id"
               :value       (user-id)
               :onInput     #(set-user-id (-> % .-currentTarget .-value))}]
      [:span (when (.-loading user) "Loading...")]
      [:div
       [:pre (js/JSON.stringify (user) nil 2)]]]))

(defn AsyncPage []
  (let []
    #jsx
     [:div {:class "p-12"}
      [:h1 {:class "font-bold text-2xl"}
       "Welcome to the Async page!"]

      [:br]
      [Greeting]

      [:br]
      [FetchUser]]))
