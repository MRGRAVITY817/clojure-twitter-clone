(ns bluejay.web.App
  (:require ["@solidjs/router" :refer [Router, Route]]
            [bluejay.web.pages.HomePage :refer [HomePage]]
            [bluejay.web.pages.LifeCycle :refer [LifeCycle]]
            [bluejay.web.pages.Events :refer [EventsPage]]
            [bluejay.web.pages.LoginPage :refer [LoginPage]]))

(defn App []
  #jsx [Router
        [Route {:path "/" :component HomePage}]
        [Route {:path "/life-cycle" :component LifeCycle}]
        [Route {:path "/events" :component EventsPage}]
        [Route {:path "/login" :component LoginPage}]])

(def default App)
