(ns bluejay.api.system
  (:require [integrant.core :as ig]
            [datomic.client.api :as d]
            [ring.adapter.jetty :refer [run-jetty]]
            [bluejay.api.handler :as handler]
            [bluejay.api.models.populate :refer [populate]]
            [bluejay.api.infrastructure.datomic-repos :refer [datomic-repos]])
  (:gen-class))

;; Initialize the Jetty adapter
(defmethod ig/init-key :adapter/jetty
  [_ {:keys [handler] :as opts}]
  (run-jetty handler (-> opts
                         (dissoc handler)
                         (assoc :join? false))))

;; Initialize the handler
(defmethod ig/init-key :handler/run-app [_ {:keys [repos]}]
  (handler/app repos))

;; Initialize the repositories
(defmethod ig/init-key :applications/repos [_ {:keys [db]}]
  (datomic-repos db))

;; Initialize the database connection and get Datomic DB connection
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
   :handler/run-app         {:repos (ig/ref :applications/repos)}
   :applications/repos      {:db (ig/ref :database.datomic/client)}
   :database.datomic/client {:server-type :datomic-local
                             :system      "bluejay"}})

(defn -main []
  (ig/init config))

