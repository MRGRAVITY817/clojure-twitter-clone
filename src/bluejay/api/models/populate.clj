(ns bluejay.api.models.populate
  (:require [datomic.client.api :as d]
            [bluejay.api.models.user :refer [user-db-spec]]))

(defn- create-database [client db-name]
  (d/create-database client {:db-name db-name}))

(defn- define-schema [conn db-schema]
  (d/transact conn {:tx-data db-schema}))

(defn- populate-db [client db-spec]
  (let [db-name (:db-name db-spec)
        db-schema (:schema db-spec)
        db-created (create-database client db-name)]
    (when db-created
      (let [conn (d/connect client {:db-name db-name})]
        (define-schema conn db-schema)))))

(defn populate
  "Populate the database with the schema and data."
  [client]
  (doseq [db-spec [user-db-spec]]
    (populate-db client db-spec)))
