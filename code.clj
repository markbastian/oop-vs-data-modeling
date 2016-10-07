;How many bananas are in sack lunches?
(->> bus
     (filter (comp #{:sack} :type :lunch))
     (map #(get-in % [:lunch :bananas] 0))
     (reduce +))

;How many lunches contain apples?
(count (filter (comp pos? #(or % 0) :bananas :lunch) bus))

;Does the driver have any money?
(some
  (fn [{:keys [role money]}]
    (and (#{:driver} role) (pos? money)))
  bus)

;Students with money and food.
(filter
  (fn [{:keys [role money lunch]}]
    (and (= role :student) money lunch))
  bus)