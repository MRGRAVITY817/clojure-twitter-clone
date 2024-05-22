(ns bluejay.api.handler
  (:require [reitit.ring :as ring]
            [reitit.ring.middleware.parameters :as parameters]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [bluejay.api.controllers.user-controller :as user-ctl]
            [bluejay.api.controllers.auth-controller :as auth-ctl]))

(defn my-middleware [handler]
  (fn [req]
    (let [resp (handler req)]
      ;; TODO: Add middleware logic here
      resp)))

(defn app [db]
  (ring/ring-handler
   (ring/router
    [["/" {:handler #'user-ctl/default}]
     ["/auth"
      ["/create-user" {:handler #'auth-ctl/create-user}]]])

   (ring/routes
    (ring/create-resource-handler
     {:path "/"})
    (ring/create-default-handler
     {:not-found          (constantly {:status 404 :body "Not found"})
      :method-not-allowed (constantly {:status 405 :body "Method not allowed"})
      :not-acceptable     (constantly {:status 406 :body "Not acceptable"})}))

   {:data {:db db
           :middleware [my-middleware
                        parameters/parameters-middleware
                        wrap-keyword-params]}}))

