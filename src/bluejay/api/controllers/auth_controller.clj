(ns bluejay.api.controllers.auth-controller
  (:require [ring.util.response :as resp]
            [bluejay.api.applications.auth.auth-service :as auth-service]))

(defn create-account [{:keys [params repos]}]
  (let [user-repo (:user-repo repos)
        user (#'auth-service/create-user params user-repo)]
    (if user
      {:status 201
       :body (str  "User created" user)}
      {:status 400
       :body "User not created"})))

(defn login [req]
  (let [params (:params req)]
    (println params)
    (resp/response (str  "User logged in" params))))

(defn logout [req]
  (let [params (:params req)]
    (println params)
    (resp/response (str  "User logged out" params))))

