(ns bluejay.web.components.Greeting)

(defn Greeting []
  #jsx
   [:p {:class "text-2xl font-bold text-gray-800"}
    "Hello, World!"])

(def default Greeting)
