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
        [user {:keys [mutate refetch]}] (create-resource user-id fetch-user)]
    #jsx
     [:div
      [:div {:class "flex gap-2"}
       [:input {:type        "number"
                :min         1
                :placeholder "Enter user id"
                :value       (user-id)
                :onInput     #(set-user-id (-> % .-currentTarget .-value))}]
       [:button {:class "bg-blue-500 text-white px-4 py-2 rounded"
                 :onClick refetch}
        "Fetch user"]]
      [:div {:class "flex gap-2"}
       [:input {:type        "text"
                :placeholder "Mutate user"
                :onInput     #(mutate (-> % .-currentTarget .-value))}]]
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
