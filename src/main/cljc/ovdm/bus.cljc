(ns ovdm.bus)

; Data driven development is based on:
; * observation
; * simplicity
(def bus
  [{:role :driver :money 4.0}
   {:role :student :lunch-type :sack :lunch-contents { :apples 2 :sandwich 1}}
   {:role :student :money 4.0}
   {:role :student :lunch-type :box :lunch-contents {:bananas 2 :apples 1 :sandwich 1} :money 5.25}
   {:role :student :money 10.0}
   {:role :student :lunch-type :sack :lunch-contents {:bananas 1} :money 5.25}
   {:role :student }])

(defn money [people] (reduce + (map #(:money % 0.0) people)))

(money bus)

(->> bus
     (filter (comp #{:sack} :lunch-type))
     (map #(get-in % [:lunch-contents :bananas] 0))
     (reduce +))
