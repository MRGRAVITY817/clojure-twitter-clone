(ns bluejay.api.system
  (:require [integrant.core :as ig]
            [datomic.client.api :as d]
            [bluejay.api.handler :as handler]
            [bluejay.api.models.populate :refer [populate]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

;; Initialize the Jetty adapter
(defmethod ig/init-key :adapter/jetty
  [_ {:keys [handler] :as opts}]
  (run-jetty handler (-> opts
                         (dissoc handler)
                         (assoc :join? false))))

;; Initialize the handler
(defmethod ig/init-key :handler/run-app [_ {:keys [db]}]
  (handler/app db))

;; Initialize the database connection and get Datomic client
(defmethod ig/init-key :database.datomic/client
  [_ {:keys [server-type system]}]
  (let [client (d/client {:server-type server-type
                          :system      system})]
    (populate client)
    client))

;; Close Jetty server
(defmethod ig/halt-key! :adapter/jetty [_ server]
  (.stop server))

(def config
  {:adapter/jetty           {:handler (ig/ref :handler/run-app)
                             :port 3000}
   :handler/run-app         {:db (ig/ref :database.datomic/client)}
   :database.datomic/client {:server-type :datomic-local
                             :system      "bluejay"}})

(defn -main []
  (ig/init config))

