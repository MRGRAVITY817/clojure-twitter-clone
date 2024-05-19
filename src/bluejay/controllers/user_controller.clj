(ns bluejay.controllers.user-controller
  (:require [ring.util.response :as resp]))

(defn default [req]
  (resp/response "Hello world!"))
