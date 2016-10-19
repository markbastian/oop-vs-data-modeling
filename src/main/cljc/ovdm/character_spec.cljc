(ns ovdm.character-spec
  #?(:clj (:require [clojure.spec :as s]
                    [clojure.spec.gen :as gen]
                    [clojure.pprint :as pp])
     :cljs (:require [cljs.spec :as s]
             [cljs.spec.gen :as gen]
             [cljs.pprint :as pp])))

{:hit-points 100
 :str 15
 :dex 12}

(s/def ::name string?)

(s/def ::race #{::human ::elf ::half-elf ::orc ::dwarf ::gnome ::halfling})

(s/def ::str (s/int-in 3 19))
(s/def ::dex (s/int-in 3 19))
(s/def ::con (s/int-in 3 19))
(s/def ::int (s/int-in 3 19))
(s/def ::wis (s/int-in 3 19))
(s/def ::cha (s/int-in 3 19))
(s/def ::character-stats (s/keys :req [::str ::dex ::con ::int ::wis ::cha]))

(s/def ::weapon-class #{:ranged :meelee})

(s/def ::character
  (s/keys :req [::name ::race ::character-stats ::weapon-class]))


(pp/pprint (gen/sample (s/gen ::character)))