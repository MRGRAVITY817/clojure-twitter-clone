(ns bluejay.api.applications.auth.auth-service
  (:require [bluejay.api.applications.repo.user-repo :as user-repo]
            [buddy.hashers :as hashers]))

;; Commands
(defn create-user
  "Create a new account"
  [{:keys [email password username]} user-repo]
  (let [password-hash (hashers/derive password {:alg :bcrypt+sha512})]
    (#'user-repo/create-user user-repo email password-hash username)))

;; Queries
