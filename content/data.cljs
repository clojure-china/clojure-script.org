
(->> data
     (partition-by (comp boolean :high))
     (partition 2 1)
     (mapcat (fn [[lbounds rbounds]]
               (let [left-bound (last lbounds)
                     left-val (hi-or-lo left-bound)]
                 (map #(let [right-val (hi-or-lo %)
                             diff (Math/abs (- right-val left-val))]
                         {:extremes [left-bound %]
                          :price-range diff
                          :midpoint (+ (min right-val left-val)
                                       (/ diff 2))})
                      rbounds))))
     (clojure.pprint/pprint))
