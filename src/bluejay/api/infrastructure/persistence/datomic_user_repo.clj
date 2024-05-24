(ns bluejay.api.infrastructure.persistence.datomic-user-repo
  (:require [bluejay.api.applications.repo.user-repo :refer [UserRepository]]
            [datomic.client.api :as d]))

(defn- user-db-conn  [client]
  (d/connect client {:db-name "users"}))

(defn- d-create-user [client email password-hash username]
  (println "Creating user using Datomic")
  (let [new-user {:user/email email
                  :user/password-hash password-hash
                  :user/username username}
        user (user-db-conn client)]
    (d/transact user {:tx-data [new-user]})))

(defn datomic-user-repo [client]
  (reify UserRepository
    (create-user [_ email password-hash username]
      (d-create-user client email password-hash username))))
