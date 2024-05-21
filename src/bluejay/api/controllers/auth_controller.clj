(ns bluejay.api.controllers.auth-controller)

(defn create-user [req]
  (let [params (:params req)]
    (println params)
    (resp/response "User created")))
