(ns bluejay.web.tests.todo-list.test
  (:require ["vitest" :refer [describe expect test]]
            ["@solidjs/testing-library" :refer [render]]
            [bluejay.web.components.TodoList :refer [TodoList]]))

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
        (-> (expect button) .toBeInTheDocument))))))
