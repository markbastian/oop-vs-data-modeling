(death-duel {:a (arm cleric mace) :b (arm wizard wand)})
=>
[{:a {:name "Bob"
      :class :cleric
      :race :elf
      :HP 15
      :weapon {:name :magic-mace :type #{:magic :blunt} :damage {:physical :2d4 :ice :1d8}}}
  :b {:name "Gandalf"
      :class :wizard
      :race :human
      :HP 10
      :weapon {:name :wand-of-missiles :type #{:wand :magic} :damage {:fire :2d6}}}}
 {:a {:name "Bob"
      :class :cleric
      :race :elf
      :HP 8
      :weapon {:name :magic-mace :type #{:magic :blunt} :damage {:physical :2d4 :ice :1d8}}}
  :b {:name "Gandalf"
      :class :wizard
      :race :human
      :HP 1
      :weapon {:name :wand-of-missiles :type #{:wand :magic} :damage {:fire :2d6}}}}
 {:a {:name "Bob"
      :class :cleric
      :race :elf
      :HP 5
      :weapon {:name :magic-mace :type #{:magic :blunt} :damage {:physical :2d4 :ice :1d8}}}
  :b {:name "Gandalf"
      :class :wizard
      :race :human
      :HP 0
      :weapon {:name :wand-of-missiles :type #{:wand :magic} :damage {:fire :2d6}}}}]
