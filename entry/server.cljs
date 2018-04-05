
(ns example.main
  (:require ["http" :as http]))

(defonce *visits (atom {}))

(defn handler [req res]
  (swap! *visits update (.-url req) inc)
  (.writeHead res 200 #js {"Content-Type" "text/html"})
  (.end res (str "<pre>Pages visited " (pr-str @*visits) " times!</pre>")))

(defn main! []
  (let [server (http/createServer #(handler %1 %2))]
    (.listen server 3000)
    (println "server listening on 3000")))

(main!)
