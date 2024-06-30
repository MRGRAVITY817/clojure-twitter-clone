(ns bluejay.web.App
  (:require ["@solidjs/router" :refer [Router, Route]]
            [bluejay.web.pages.HomePage :refer [HomePage]]
            [bluejay.web.pages.LifeCycle :refer [LifeCycle]]
            [bluejay.web.pages.Bindings :refer [BindingsPage]]
            [bluejay.web.pages.Props :refer [PropsPage]]
            [bluejay.web.pages.Stores :refer [StoresPage]]
            [bluejay.web.pages.Reactivity :refer [ReactivityPage]]
            [bluejay.web.pages.Async :refer [AsyncPage]]
            [bluejay.web.pages.LoginPage :refer [LoginPage]]))

(defn App []
  #jsx [Router
        [Route {:path "/" :component HomePage}]
        [Route {:path "/life-cycle" :component LifeCycle}]
        [Route {:path "/bindings" :component BindingsPage}]
        [Route {:path "/props" :component PropsPage}]
        [Route {:path "/stores" :component StoresPage}]
        [Route {:path "/reactivity" :component ReactivityPage}]
        [Route {:path "/async" :component AsyncPage}]
        [Route {:path "/login" :component LoginPage}]])

(def default App)
