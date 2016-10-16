(ns ovdm.dnd
  (:require [clojure.string :as s]
            [clojure.edn :as edn]))

(def wizard
  {:name "Gandalf"
   :class :wizard
   :race :human
   :HP 10 })

(def fighter
  {:name "Grall"
   :class :fighter
   :race :orc
   :HP 20 })

(def cleric
  {:name "Bob"
   :class :cleric
   :race :elf
   :HP 15 })

(def mace
  {:name :magic-mace
   :type #{:magic :blunt}
   :damage {:physical :2d4
            :ice :1d8 }})

(def sword
  {:name :plain-sword
   :type #{:blade}
   :damage {:physical :2d6 }})

(def wand
  {:name :wand-of-missiles
   :type #{:wand :magic}
   :damage {:fire :2d6 }})

(defmulti arm (fn [{:keys [class]} _] class))

(defmethod arm :wizard [player {:keys [type] :as weapon}]
  (cond->
    player
    (some type [:wand :staff])
    (assoc :weapon weapon)))

(defmethod arm :fighter [player {:keys [type] :as weapon}]
  (cond->
    player
    (not-any? type [:wand :staff])
    (assoc :weapon weapon)))

(defmethod arm :cleric [player {:keys [type] :as weapon}]
  (cond->
    player
    (:blunt type)
    (assoc :weapon weapon)))

(defmethod arm :default [player _] player)

(arm cleric mace)
(arm cleric sword)

(arm wizard mace)
(arm wizard wand)

(arm fighter sword)
(arm fighter mace)
(arm fighter wand)

(defn roll [p]
  (let [[n d] (-> p name (s/split #"d") (->> (map edn/read-string)))]
    (reduce + (repeatedly n #(inc (rand-int d))))))

(defn attack [{:keys [weapon]} {:keys [HP] :as defender}]
  (let[damage (reduce + (map roll (-> weapon :damage vals)))]
    (update defender :HP - (min HP damage))))

(defn duel [{:keys [a b]}]
  {:a (attack b a) :b (attack a b)})

(defn death-duel [duellers]
  (loop [[{:keys [a b] :as f} & r] (iterate duel duellers) res []]
    (if (or (zero? (:HP a)) (zero? (:HP b)))
      (conj res f)
      (recur r (conj res f)))))

(-> cleric
    (arm mace)
    (attack wizard))
(duel {:a (arm cleric mace) :b (arm wizard wand)})
(death-duel {:a (arm cleric mace) :b (arm wizard wand)})

