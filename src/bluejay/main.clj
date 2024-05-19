(ns bluejay.main
  (:require [integrant.core :as ig]
            [datomic.client.api :as d])
  (:gen-class))

(def db-client
  "Datomic database settings."
  (d/client {:server-type :datomic-local
             :system      "datomic-samples"}))

(d/list-databases db-client {})

(def config
  {:adapter/jetty {:handler (ig/ref :handler/run-app) :port 3000}
   :handler/run-app {:db (ig/ref :database.datomic/connection)}
   :database.datomic/connection {:uri "datomic:mem://bluejay"}})

(defn -main []
  (ig/init config))
