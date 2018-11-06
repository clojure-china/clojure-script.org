
(def http (js/require "http"))
;=> a lot of stuff you don't want to see
(def head (clj->js {"Content-Type" "text/plain"}))
;=> #js {:Content-Type "text/plain"}
(def server
  (.createServer http
    (fn [req resp]
      (.writeHead resp 200 head)
      (.end resp "Hello, World\n"))))
;=> #<[object Object]>
(.listen server 1337 "127.0.0.1")
