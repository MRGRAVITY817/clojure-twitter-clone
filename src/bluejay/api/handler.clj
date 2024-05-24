(ns bluejay.api.handler
  (:require [reitit.ring :as ring]
            [reitit.ring.middleware.parameters :as parameters]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [bluejay.api.controllers.user-controller :as user-ctl]
            [bluejay.api.controllers.auth-controller :as auth-ctl]))

(def repo-middleware
  "Put implementation of repositories in the request map."
  {:name ::repos
   :compile (fn [{:keys [repos]} _]
              (fn [handler]
                (fn [req]
                  (handler (assoc req :repos repos)))))})

(defn app [repos]
  (ring/ring-handler
   (ring/router
    [["/" {:handler #'user-ctl/default}]
     ["/auth"
      ["/create-account" {:post {:handler #'auth-ctl/create-account}}]
      ["/login"          {:post {:handler #'auth-ctl/login}}]
      ["/logout"         {:post {:handler #'auth-ctl/logout}}]]]

    {:data {:repos repos
            :middleware [parameters/parameters-middleware
                         wrap-keyword-params
                         repo-middleware]}})

   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-resource-handler
     {:path "/"})
    (ring/create-default-handler
     {:not-found          (constantly {:status 404 :body "Not found"})
      :method-not-allowed (constantly {:status 405 :body "Method not allowed"})
      :not-acceptable     (constantly {:status 406 :body "Not acceptable"})}))))

