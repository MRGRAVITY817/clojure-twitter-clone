(ns bluejay.api.applications.user.user-service
  (:require [bluejay.api.applications.repo.user-repo :as user-repo]))

;; Queries

(defn get-users
  "Get all users from the users db."
  [user-repo]
  (let [users (#'user-repo/get-users user-repo)]
    (if users
      users
      nil)))
