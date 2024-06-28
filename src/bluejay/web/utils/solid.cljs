(ns bluejay.web.utils.solid
  (:require ["solid-js" :as solid :refer [createSignal createEffect createMemo]]))

(def create-signal
  "Create a signal.
   Signals are reactive variables that can be read and written to.

   Example:
   ```
   (let [[count set-count] (createSignal 0)]
     (set-count 1)
     (println (count)) ;; 1
   )
   ```
  "
  createSignal)

(def create-effect
  "Create an effect.
   Effects are reactive side-effects that run when their dependencies change.

   Example:
   ```
   (let [[count set-count] (createSignal 0)]
     (createEffect (fn [] (println (count))))
     (set-count 1) ;; prints 1
     (set-count 2) ;; prints 2
   )
   ```
  "
  createEffect)

(def create-memo
  "Create a memo.
   Memoized values are cached and only recalculated when their dependencies change.
   Most useful for expensive calculations.

   Example:
   ```
   (let [[count set-count] (createSignal 0)
         fib-count (createMemo #(fib (count)))]
     (set-count 1)
     (println (fib-count)) ;; 1
     )
   ```
  "
  createMemo)

(def Show
  "`Show` component.
   Conditionally render children based on a condition.

   Example:
   ```
   [Show {:when (logged-in)
          :fallback #jsx [:p \"You are not logged in!\"]}
    [:p \"You are logged in!\"]]
   ```
   "
  solid/Show)

(def For
  "`For` component.
   Render a list of items.

   Example:
   ```
   [For {:each items}
    (fn [item] [:p item])]
   ```
   "
  solid/For)
