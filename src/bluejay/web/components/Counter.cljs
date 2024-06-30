(ns bluejay.web.components.Counter
  (:require
   [bluejay.web.utils.solid :refer [create-context create-signal use-context]]))

(def CounterContext (create-context))

(defn CounterProvider [props]
  (let [[count set-count] (create-signal 0)
        counter [count {:increment #(set-count inc)
                        :decrement #(set-count dec)}]]
    #jsx
     [CounterContext.Provider {:value counter}
      (.-children props)]))

(defn use-counter []
  (use-context CounterContext))


