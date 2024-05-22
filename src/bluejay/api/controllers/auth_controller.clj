(ns bluejay.api.controllers.auth-controller
  (:require [ring.util.response :as resp]))

(defn create-user [req]
  (let [params (:params req)]
    (println params)
    (resp/response (str  "User created" params))))

(defn login [req]
  (let [params (:params req)]
    (println params)
    (resp/response (str  "User logged in" params))))

(defn logout [req]
  (let [params (:params req)]
    (println params)
    (resp/response (str  "User logged out" params))))
