(ns bluejay.web.components.Profile
  (:require [bluejay.web.utils.solid :refer [For Suspense SuspenseList]]))

(defn- ProfileDetails [props]
  #jsx
   [:h1 {:class "text-2xl font-bold text-gray-800"}
    (-> props :user :name)])
;
(defn- ProfileTimeline [props]
  #jsx
   [:ul {:class "list-disc list-inside"}
    [For {:each (:posts props)}
     (fn [post]
       #jsx [:li (:text post)])]])

(defn- ProfileTrivia [props]
  #jsx
   [:div
    [:h2 {:class "text-xl font-bold text-gray-800"}
     "Fun Facts"]
    [:ul {:class "list-disc list-inside"}
     [For {:each (:trivia props)}
      (fn [fact]
        #jsx [:li (:text fact)])]]])

(defn ProfilePage [props]
  #jsx
   [SuspenseList {:revealOrder "forwards"
                  :tail "collapsed"}
    [ProfileDetails {:user (:user props)}]
    [Suspense {:fallback #jsx [:h2 "Loading posts..."]}
     [ProfileTimeline {:posts (:posts props)}]]
    [Suspense {:fallback #jsx [:h2 "Loading fun facts..."]}
     [ProfileTrivia {:trivia (:trivia props)}]]])


