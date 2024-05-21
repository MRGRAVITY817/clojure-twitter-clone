(ns bluejay.web.components.LoginForm)

(defn LoginForm
  "A login input form. Accepts email and password."
  []
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
