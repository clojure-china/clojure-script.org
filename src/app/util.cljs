
(ns app.util (:require ["escape-html" :as escape-html] ["highlight.js" :as hljs]))

(def supported-langs {"clojure" "clojure"})

(defn highlighter [code lang]
  (if (contains? supported-langs lang)
    (.-value (.highlight hljs lang code))
    (escape-html code)))
