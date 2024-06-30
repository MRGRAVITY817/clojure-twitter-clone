(ns bluejay.web.pages.Async
  (:require
   [bluejay.web.components.MockApi :refer [fetch-profile-data]]
   [bluejay.web.components.Profile :refer [ProfilePage]]
   [bluejay.web.utils.solid :as s :refer [create-effect create-resource
                                          create-signal Match Suspense Switch
                                          use-transition]]))

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

(defn FetchProfile []
  (let [{:keys [user posts trivia]} (fetch-profile-data)]
    #jsx
     [Suspense {:fallback #jsx [:h1 "Loading..."]}
      [ProfilePage {:user   (user)
                    :posts  (posts)
                    :trivia (trivia)}]]))

(def sample-content
  {:uno "Lorem ipsum"
   :dos "Hello world"
   :tres "Goodbye world"})

(defn- create-delay []
  (js/Promise.
   (fn [resolve]
     (let [delay (+ 160 (* 420 (js/Math.random)))]
       (js/setTimeout #(resolve delay) delay)))))

(defn Child [props]
  (let [[time-taken] (create-resource create-delay)]
    #jsx
     [:div {:class "p-12"}
      (str "This content is for page \""
           (:page props)
           "\" after "
           (when-let [t (time-taken)] (.toFixed t))
           "ms.")
      [:p (get sample-content (:page props))]]))

(defn Tabs []
  (let [[tab set-tab] (create-signal 0)
        [pending start] (use-transition)
        update-tab (fn [index]
                     #(start (fn [] (set-tab index))))]
    #jsx
     [:div
      [:ul {:class "flex gap-2"}
       [:li {:classList {"border-b border-blue-500" (= (tab) 0)}
             :onClick (update-tab 0)}
        "Uno"]
       [:li {:classList {"border-b border-blue-500" (= (tab) 1)}
             :onClick (update-tab 1)}
        "Dos"]
       [:li {:classList {"border-b border-blue-500"  (= (tab) 2)}
             :onClick (update-tab 2)}
        "Tres"]]
      [:div {:class "tab" :classList {:pending (pending)}}
       [Suspense {:fallback #jsx [:div {:class "loader"} "Loading..."]}
        [Switch
         [Match {:when (= (tab) 0)}
          [Child {:page :uno}]]
         [Match {:when (= (tab) 1)}
          [Child {:page :dos}]]
         [Match {:when (= (tab) 2)}
          [Child {:page :tres}]]]]]]))

(defn AsyncPage []
  #jsx
   [:div {:class "p-12"}
    [:h1 {:class "font-bold text-2xl"}
     "Welcome to the Async page!"]

    [:br]
    [Suspense {:fallback #jsx [:p {:class "text-2xl font-semibold italic text-gray-300"}
                               "Loading..."]}
     [Greeting]]

    [:br]
    [Greeting]

    [:br]
    [FetchUser]

    [:br]
    [FetchProfile]

    [:br]
    [Tabs]])
