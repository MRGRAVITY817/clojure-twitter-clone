(ns bluejay.api.infrastructure.datomic-repos
  (:require [bluejay.api.infrastructure.persistence.datomic-user-repo :refer [datomic-user-repo]]))

(defn datomic-repos
  "A map of all the Datomic repositories."
  [client]
  {:user-repo (datomic-user-repo client)})
