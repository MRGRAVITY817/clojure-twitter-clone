(ns bluejay.web.App
  (:require ["./App.module.css$default" :as styles]
            ["./logo.svg$default" :as logo]
            ["solid-js" :refer [createSignal]]
            [squint.string :as str]
            [components.LoginForm :refer [LoginForm]]))

(defn Counter [{:keys [init]}]
  (let [[counter setCount] (createSignal init)]
    #jsx [:div
          "Count:" (str/join " " (range (counter)))
          [:ul (vec (interpose " " ["Hello" "world"]))]
          [:div
           [:button
            {:onClick (fn []
                        (setCount (inc (counter))))}
            "Click me"]]]))

(defn App []
  #jsx [:div {:class styles.App}
        [:header {:class styles.header}
         [:img {:src logo
                :class styles.logo
                :alt "logo"}]
         [Counter {:init 5}]
         [LoginForm]]])

(def default App)
