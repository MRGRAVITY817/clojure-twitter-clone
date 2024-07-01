(ns bluejay.web.tests.todo-list.test
  (:require ["vitest" :refer [describe expect test]]
            ["@solidjs/testing-library" :refer [render]]
            ["@testing-library/user-event" :as user-event]
            [bluejay.web.components.TodoList :refer [TodoList]]))

(def user (.setup user-event))

(describe
 "<TodoList />"
 (fn []
   (test
    "renders an input and a button"
    (fn []
      (let [{:keys [getByPlaceholderText getByText]} (render TodoList)
            input (getByPlaceholderText "new todo here")
            button (getByText "Add Todo")]
        (-> (expect input) .toBeInTheDocument)
        (-> (expect button) .toBeInTheDocument))))

   (test
    "adds a todo"
    (^:async
     fn []
        (let [{:keys [getByPlaceholderText getByText]} (render TodoList)
              input  (getByPlaceholderText "new todo here")
              button (getByText "Add Todo")
              act    (-> (.type user input "test input 1")
                         (.then #(.click user button)))]
          (-> act
              (.then
               #(do (.toHaveValue (expect input) "")
                    (.toBeInTheDocument (expect (getByText "test input 1")))))))))))
