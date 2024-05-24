(ns bluejay.api.applications.auth.auth-service
  (:require [bluejay.api.applications.auth.auth-repository :as auth-repo]))

;; Commands
(defn create-account
  "Create a new account"
  [{:keys [email password]} auth-repo]
  (auth-repo/create-account auth-repo email password))

;; Queries
