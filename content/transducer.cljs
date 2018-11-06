
(def xform
  (comp
    (map #(+ 2 %))
    (filter odd?)))

(transduce xform + (range 0 10))
