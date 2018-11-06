
(let [[a b & the-rest] [:a :b :c :d]]
  (println a b the-rest))
;; => :a :b (:c :d)

(let [{a :a d :d} {:a "A" :b "B" :c "C" :d "D"}]
  (println a d))
;; => A D

(let [{:keys [a b], :as all} {:a "A" :b "B" :c "C" :d "D"}]
  (println a b all))
;; => A B {:a A :b B :c C :d D}
