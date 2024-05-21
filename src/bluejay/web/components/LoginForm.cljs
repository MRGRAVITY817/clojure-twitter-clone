(ns bluejay.web.components.LoginForm)

(defn LoginForm []
  #jsx [:div
        [:h1 "Login"]
        [:form {:action "/login" :method "post"}
         [:label "Username: "]
         [:input {:type "text" :name "username"}]
         [:br]
         [:label "Password: "]
         [:input {:type "password" :name "password"}]
         [:br]
         [:input {:type "submit" :value "Login"}]]
        [:a {:href "/register"} "Register"]])
