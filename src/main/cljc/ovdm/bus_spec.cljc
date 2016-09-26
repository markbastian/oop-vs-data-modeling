(ns ovdm.bus-spec
  (:require
    #?(:clj [clojure.spec :as s] :cljs [cljs.spec :as s])
    #?(:clj [clojure.spec.gen :as gen] :cljs [cljs.spec.impl.gen :as gen])
    #?(:clj [clojure.pprint :as pp] :cljs [cljs.pprint :as pp])))

(s/def ::student-role #{:student})
(s/def ::driver-role #{:driver})

(s/def ::money (s/double-in :min 0.0 :max 100.0))

(s/def ::lunch-type #{:sack :box})
(s/def ::apples (s/int-in 0 100))
(s/def ::sandwiches (s/int-in 0 100))
(s/def ::bananas (s/int-in 0 100))
(s/def ::lunch (s/keys :req [::lunch-type ::apples ::sandwiches ::bananas]))

(s/def ::driver (s/keys :req [::driver-role ::money]))

(s/def ::student
  (s/or :lunch (s/keys :req [::student-role ::lunch] :opt [::money])
        :money (s/keys :req [::student-role ::money] :opt [::lunch])))

(s/def ::bus (s/cat :driver ::driver :students (s/+ ::student)))

(-> ::bus
    s/gen
    gen/generate
    pp/pprint)

