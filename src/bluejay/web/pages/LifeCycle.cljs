(ns bluejay.web.pages.LifeCycle
  (:require
   [bluejay.web.utils.solid :refer [create-signal on-mount]]))

(defn- fetch-photos [set-photos]
  (-> (js/fetch "https://jsonplaceholder.typicode.com/photos?_limit=20")
      (.then #(.json %))
      (.then #(set-photos %))))

(defn LifeCycle []
  (let [[photos set-photos] (create-signal [])]

    (on-mount #(fetch-photos set-photos))

    #jsx
     [:div {:style {:padding "20px"}}
      [:h1 {:className "font-bold text-2xl"}
       "Photos"]
      [:div
       [For {:each (photos)
             :fallback #jsx [:p "Loading..."]}
        (fn [{:keys [thumbnailUrl title]}]
          #jsx
           [:figure
            [:img {:src thumbnailUrl
                   :alt title}]
            [:figcaption title]])]]]))
