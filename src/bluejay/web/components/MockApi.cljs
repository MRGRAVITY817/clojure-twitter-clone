(ns bluejay.web.components.MockApi
  (:require
   [bluejay.web.utils.solid :refer [create-resource]]))

(defn- fetch-user []
  (js/Promise.
   (fn [resolve]
     (js/setTimeout #(resolve {:name "Ringo Starr"}) 500))))

(def ringo-posts
  [{:id 0 :text "I get by with a little help from my friends"}
   {:id 1 :text "I'd like to be under the sea in an octupus's garden"}
   {:id 2 :text "You got that sand all over your feet"}])

(defn- fetch-posts []
  (js/Promise.
   (fn [resolve]
     (js/setTimeout #(resolve ringo-posts) (* 3000 (js/Math.random))))))

(defn- fetch-trivia []
  (js/Promise.
   (fn [resolve]
     (js/setTimeout #(resolve
                      [{:id 1 :text "The nickname \"Ringo\" came from his habit of wearing numerous rings."}
                       {:id 2 :text "Plays the drums left-handed with a right-handed drum set."}
                       {:id 3 :text "Nominated for one Daytime Emmy Award, but did not win"}])
                    (* 3000 (js/Math.random))))))

(defn fetch-profile-data []
  (let [[user] (create-resource fetch-user)
        [posts] (create-resource fetch-posts)
        [trivia] (create-resource fetch-trivia)]
    {:user user
     :posts posts
     :trivia trivia}))
