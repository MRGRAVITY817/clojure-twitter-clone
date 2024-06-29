(ns bluejay.web.pages.LifeCycle
  (:require
   [bluejay.web.utils.solid :refer [create-signal on-cleanup on-mount]]))

(defn- fetch-photos [set-photos]
  (-> (js/fetch "https://jsonplaceholder.typicode.com/photos?_limit=5")
      (.then #(.json %))
      (.then #(set-photos %))))

(defn LifeCycle []
  (let [[photos set-photos] (create-signal [])
        [count set-count] (create-signal 0)
        timer (js/setInterval #(set-count inc) 1000)]

    (on-mount #(fetch-photos set-photos))
    (on-cleanup #(do
                   (println "Cleaning up!")
                   (js/clearInterval timer)))

    #jsx
     [:div {:style {:padding "20px"}}
      [:h1 {:className "font-bold text-2xl pb-4"}
       "Photos"]
      [:div {:class "grid grid-cols-3 gap-4"}
       [For {:each (photos)
             :fallback #jsx [:p "Loading..."]}
        (fn [{:keys [thumbnailUrl title]}]
          #jsx
           [:figure
            [:img {:src thumbnailUrl
                   :alt title}]
            [:figcaption title]])]]

      [:h1 {:className "font-bold text-2xl pt-4"}
       (str "Count: " (count))]]))
