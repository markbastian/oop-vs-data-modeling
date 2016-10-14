(ns ovdm.inventory
  (:require [clojure.pprint :as pp])
  (:import [java.util UUID Date]
           [java.time ZoneId]))

(def inventory-system
  {:items
   {#uuid"b5ca8dc8-ec69-4a46-b294-4eec6d8a1f44"
    {:title "So Long, and Thanks for All the Fish" :author "Douglas Adams" }
    #uuid"7c4ba485-b161-42fc-b36f-e9c494559a22"
    {:title "Dune" :author "Frank Herbert" }}
   :patrons
   {#uuid"13608583-0838-4fdd-8cc1-14a346f1ce9d"
    {:name "Mark Bastian", :phone "1-208-123-4567" :checkouts {}}
    #uuid"ead7deb7-eb16-4aab-b00a-557497b0d248"
    {:name "Becky Bastian", :phone "1-208-123-4568" :checkouts {}}}})

(defn gen-due-date []
  (-> (java.time.LocalDate/now)
      (.plusWeeks 2)
      (.atStartOfDay (ZoneId/systemDefault))
      .toInstant
      Date/from))

(defn add-item [inventory-system desc]
  (assoc-in inventory-system [:items (UUID/randomUUID)] desc))

(defn checkout [inventory-system patron-id item-id]
  (if (get-in inventory-system [:items item-id :checkout-status])
    inventory-system
    (let [due-date (gen-due-date)
          checkouts (get-in inventory-system [:patrons patron-id :checkouts])]
      (-> inventory-system
          (assoc-in [:items item-id :checkout-status]
                    {:patron-id patron-id :due-date due-date})
          (assoc-in [:patrons patron-id :checkouts]
                    (into checkouts {item-id due-date}))))))

(defn checkin [inventory-system item-id]
  (if-let [patron-id (get-in inventory-system [:items item-id :checkout-status :patron-id])]
    (-> inventory-system
        (update-in [:items item-id] dissoc :checkout-status)
        (update-in [:patrons patron-id :checkouts] dissoc item-id))
    inventory-system))

(defn overdue-items [{:keys [items]} t]
  (not-empty
    (for [[id {:keys [checkout-status]}] items
          :when (some-> checkout-status :due-date (.compareTo t) neg?)]
      id)))

(add-item inventory-system {:title "Core Java" :author "Cay Horstmann"})

(pp/pprint
  (checkout
    inventory-system
    #uuid"13608583-0838-4fdd-8cc1-14a346f1ce9d"
    #uuid"7c4ba485-b161-42fc-b36f-e9c494559a22"))

(overdue-books
  (checkout
    inventory-system
    #uuid"13608583-0838-4fdd-8cc1-14a346f1ce9d"
    #uuid"7c4ba485-b161-42fc-b36f-e9c494559a22")
  #inst"2017-01-01")

(= inventory-system
   (checkin
     (checkout
       inventory-system
       #uuid"13608583-0838-4fdd-8cc1-14a346f1ce9d"
       #uuid"7c4ba485-b161-42fc-b36f-e9c494559a22")
     #uuid"7c4ba485-b161-42fc-b36f-e9c494559a22"))

(pp/pprint
  (add-item
    inventory-system
    {:type :cake-pan :dimensions :9x13}))