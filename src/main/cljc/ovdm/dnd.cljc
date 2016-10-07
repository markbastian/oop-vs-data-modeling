(ns ovdm.dnd
  (:require [clojure.string :as s]
            [clojure.edn :as edn]))

(def wizard
  {:name "Gandalf"
   :class :wizard
   :race :human })

(def fighter
  {:name "Grall"
   :class :fighter
   :race :orc })

(def cleric
  {:name "Bob"
   :class :cleric
   :race :elf })

(def mace
  {:name :magic-mace
   :type #{:magic :blunt}
   :limits #{:wizard}
   :damage {:physical :2d4 :fire :1d8 }})

(def sword
  {:name :plain-sword
   :type #{:blade}
   :damage {:physical :2d6 }})

(def wand
  {:name :wand-of-missiles
   :type #{:wand :magic}
   :damage {:fire :2d6 }
   :max-charge 20
   :charges 20 })

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
  (cond-> player (:blunt type) (assoc :weapon weapon)))

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

(defn attack [{:keys [weapon] :as attacker} defender]
  (reduce + (map roll (-> weapon :damage vals))))

(attack (arm cleric mace) wizard)


