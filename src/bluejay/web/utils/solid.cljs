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
   Use `For` when the data of item is important and position of the item can change.

   Example:
   ```
   [:ul
       [For {:each (doall (range 1 16))}
        (fn [value index]
          #jsx [:li (str \"- Index: \"  (index) \", Value: \" value)])]]
   ```
   "
  solid/For)

(def Index
  "`Index` component.
   Render a list of items.
   Use `Index` when the position of the item is important.

   Example:
   ```
   [:ul
       [Index {:each (doall (range 1 16))}
        (fn [value index]
          #jsx [:li (str \"- Index: \"  index \", Value: \" (value))])]]
   ```
   "
  solid/Index)

(def Switch
  "`Switch` component.
   Render the first `Match` that matches the condition.

   Example:
   ```
   [Switch {:fallback #jsx [:p \"Count mod 3 is 2!\"]}
    [Match {:when (= (mod (count) 3) 0)}
     [:p \"Count is divisible by 3!\"]]
    [Match {:when (= (mod (count) 3) 1)}
     [:p \"Count mod 3 is 1!\"]]
   ```
   "
  solid/Switch)

(def Match
  "`Match` component.
   A match arm used in a `Switch` component.

   Example:
   ```
   [Switch {:fallback #jsx [:p \"Count mod 3 is 2!\"]}
    [Match {:when (= (mod (count) 3) 0)}
     [:p \"Count is divisible by 3!\"]]
    [Match {:when (= (mod (count) 3) 1)}
     [:p \"Count mod 3 is 1!\"]]
   ```
   "
  solid/Match)



