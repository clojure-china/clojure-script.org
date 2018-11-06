
(defn transform* [person]
   (-> person
      (assoc :hair-color :gray)
      (update :age inc)))

(defn calculate* []
   (->> (range 10)
        (filter odd? ,,,)
        (map #(* % %) ,,,)
        (reduce + ,,,)))

(as-> [:foo :bar] v
  (map name v)
  (first v)
  (.substring v 1))
