(ns ovdm.bus
  (:require [ovdm.bus-spec :as bs]
            [clojure.spec :as s]))

;https://developer.atlassian.com/blog/2015/08/beautiful-presentations-with-highlight/
;highlight -i code.clj -o out.html --syntax clojure --inline-css --style zellner
;highlight -i code.clj --syntax clojure --style zellner -O rtf -l -z | pbcopy
;highlight -i code.clj --syntax clojure --style zellner -O rtf -l | pbcopy

; Data driven development is based on:
; * observation
; * simplicity
(def bus
  [{:role :driver :money 4.0}
   {:role :student
    :lunch {:type :sack :apples 2 :sandwich 1}}
   {:role :student
    :money 4.0}
   {:role :student
    :lunch {:type :box :bananas 2 :apples 1 :sandwich 1}
    :money 5.25}
   {:role :student :money 10.0}
   {:role :student
    :lunch {:type :sack :bananas 1}
    :money 5.25}])

(s/explain ::bs/bus bus)

(defn money [m]
  (reduce + (map #(:money % 0.0) m)))

(money bus)

(def bank-accounts
  [{:name "Mark" :account 104872 :money 34.54}
   {:name "Sue" :account 23432 :money 100.2}
   {:name "Sam" :account 889 :money 120.43}
   {:name "Kelly" :account 1972 :money 433.45}])

(money bank-accounts)

;How many bananas are in sack lunches?
(->> bus
     (filter (comp #{:sack} :type :lunch))
     (map #(get-in % [:lunch :bananas] 0))
     (reduce +))

;How many lunches contain apples?
(count (filter (comp pos? #(or % 0) :bananas :lunch) bus))

;Does the driver have any money?
(some (fn [{:keys [role money]}]
        (and (#{:driver} role) (pos? money)))
      bus)
