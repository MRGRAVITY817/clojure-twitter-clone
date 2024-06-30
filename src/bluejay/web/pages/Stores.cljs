(ns bluejay.web.pages.Stores
  (:require
   [bluejay.web.utils.solid :refer [create-signal create-store produce]]))

(defn- NestedTodo [_props]
  (let [[todo set-todo] (create-signal "")
        [todos set-todos] (create-signal [])
        [_ set-todo-id] (create-signal 0)
        on-change-input (fn [e]
                          (set-todo (-> e .-target .-value)))
        add-todo (fn []
                   (let [[completed set-completed] (create-signal false)]
                     (set-todos (conj (todos) {:id            (set-todo-id inc)
                                               :text          (todo)
                                               :completed     completed
                                               :set-completed set-completed}))
                     (set-todo "")))
        toggle-todo (fn [todo]
                      ((:set-completed todo) not))]

    #jsx
     [:div
      [:h2 {:class "font-semibold text-xl pb-4"}
       "Nested Todo"]
      [:div {:class "flex gap-2"}
       [:input {:value    (todo)
                :onChange on-change-input
                :type     "text"}]
       [:button {:class "bg-blue-500 text-white px-4 py-2 rounded"
                 :onClick add-todo}
        "ADD"]]

      [:ul {:class "pt-2"}
       [For {:each (todos)
             :fallback #jsx [:p "No todos!"]}
        (fn [{:keys [text completed] :as todo}]
          #jsx
           [:li
            [:input {:type     "checkbox"
                     :class    "mr-2"
                     :checked  (completed)
                     :onChange #(toggle-todo todo)}]
            [:span {:style {:text-decoration (if (completed) "line-through" "none")}}
             text]])]]]))

(defn- StoreTodo [_props]
  (let [[todo set-todo] (create-signal "")
        [todos set-todos] (create-store [])
        [_ set-todo-id] (create-signal 0)
        on-change-input (fn [e]
                          (set-todo (-> e .-target .-value)))
        add-todo (fn []
                   #_(set-todos (conj todos {:id        (set-todo-id inc)
                                             :text      (todo)
                                             :completed false}))
                   (set-todos (produce #(.push % {:id        (set-todo-id inc)
                                                  :text      (todo)
                                                  :completed false})))

                   (set-todo ""))
        toggle-todo (fn [id]
                      (set-todos #(= id (:id %)) :completed not))]

    #jsx
     [:div
      [:h2 {:class "font-semibold text-xl pb-4"}
       "Store Todo"]
      [:div {:class "flex gap-2"}
       [:input {:value    (todo)
                :onChange on-change-input
                :type     "text"}]
       [:button {:class "bg-red-500 text-white px-4 py-2 rounded"
                 :onClick add-todo}
        "ADD"]]

      [:ul {:class "pt-2"}
       [For {:each todos
             :fallback #jsx [:p "No todos!"]}
        (fn [{:keys [id text] :as todo}]
          #jsx
           [:li
            [:input {:type     "checkbox"
                     :class    "mr-2"
                     :checked  (:completed todo)
                     :onChange #(toggle-todo id)}]
            [:span {:style {:text-decoration (if (:completed todo) "line-through" "none")}}
             text]])]]]))

(defn StoresPage []
  #jsx
   [:div {:class "p-12"}
    [:h1 {:class "font-bold text-2xl pb-4"}
     "Welcome to the Stores page!"]

    [NestedTodo]
    [:div {:class "pt-8"}]
    [StoreTodo]])
