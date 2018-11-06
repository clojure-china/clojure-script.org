
(def car
  (atom {:make "Audi"
         :model "Q3"}))

; @car
;; {:make "Audi", :model "Q3"}

(swap!
 car
 assoc :model "Q5")
;; {:make "Audi", :model "Q5"}

(reset! car {:make "" :model ""})
;; {:make "", :model ""}
