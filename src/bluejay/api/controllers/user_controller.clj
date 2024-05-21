(ns bluejay.api.controllers.user-controller
  (:require [ring.util.response :as resp]))

(defn default [req]
  (resp/response "Welcome to bluejay!"))
