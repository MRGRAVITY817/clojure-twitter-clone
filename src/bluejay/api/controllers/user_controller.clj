(ns bluejay.api.controllers.user-controller
  (:require [ring.util.response :as resp]
            [bluejay.api.applications.user.user-service :as user-service]))

(defn default [req]
  (resp/response "Wow what a response!"))

(defn get-users [{:keys [repos]}]
  (let [user-repo (:user-repo repos)
        users (#'user-service/get-users user-repo)]
    (if users
      {:status 200
       :body (str  "Users found" users)}
      {:status 400
       :body "Users not found"})))


