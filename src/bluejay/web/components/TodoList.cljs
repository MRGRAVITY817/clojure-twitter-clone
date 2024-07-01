(ns bluejay.web.components.TodoList
  (:require
   [bluejay.web.utils.solid :refer [create-signal create-store]]
   [clojure.string :as str]))

(defn TodoList []
  (let [[todo-input set-todo-input] (create-signal "")
        [todos set-todos] (create-store [])
        add-todo #(when (seq (str/trim (todo-input)))
                    (set-todos (count todos) {:id        (count todos)
                                              :text      (todo-input)
                                              :completed false})
                    (set-todo-input ""))
        toggle-todo (fn [id]
                      (set-todos id :completed not))]

    #jsx
     [:div
      [:div
       [:input {:type        "text"
                :placeholder "new todo here"
                :value       (todo-input)
                :onChange    #(set-todo-input (-> % .-target .-value))}]
       [:button {:onClick  add-todo} "Add Todo"]]

      [:div
       [For {:each todos}
        (fn [{:keys [id text] :as todo}]
          #jsx
           [:div
            [:input {:type     "checkbox"
                     :checked  (:completed todo)
                     :onChange #(toggle-todo id)}]
            [:span {:style {:text-decoration (if (:completed todo) "line-through" "none")}}
             text]])]]]))
