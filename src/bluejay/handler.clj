(ns bluejay.handler
  (:require [reitit.ring :as ring]
            [reitit.ring.middleware.parameters :as parameters]
            [ring.util.response :as resp]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [bluejay.controllers.user-controller :as user-ctl]))

(defn my-middleware [handler]
  (fn [req]
    (let [resp (handler req)]
      (if (resp/response? resp)
        resp
        (user-ctl/default req)))))

(defn app [db]
  (ring/ring-handler
   (ring/router
    [["/" {:handler user-ctl/default}]])

   (ring/routes
    (ring/create-resource-handler
     {:path "/"})
    (ring/create-default-handler
     {:not-found (constantly {:status 404 :body "Not found"})}))

   {:data {:db db
           :middleware [my-middleware
                        parameters/parameters-middleware
                        wrap-keyword-params]}}))
