
(ns app.main
  (:require [respo.core :refer [render! clear-cache! realize-ssr!]]
            [app.comp.container :refer [comp-container]]
            [app.updater :refer [updater]]
            [app.schema :as schema]
            [app.config :as config]
            [reel.util :refer [listen-devtools!]]
            [reel.core :refer [reel-updater refresh-reel]]
            [reel.schema :as reel-schema]
            [cljs.reader :refer [read-string]]
            ["highlight.js" :as hljs]
            ["highlight.js/lib/languages/clojure" :as clojure-lang]))

(defonce *reel
  (atom (-> reel-schema/reel (assoc :base schema/store) (assoc :store schema/store))))

(defn dispatch! [op op-data]
  (comment println "Dispatch:" op)
  (reset! *reel (reel-updater updater @*reel op op-data)))

(def mount-target (.querySelector js/document ".app"))

(defn render-app! [renderer]
  (renderer mount-target (comp-container @*reel) #(dispatch! %1 %2)))

(def ssr? (some? (js/document.querySelector "meta.respo-ssr")))

(defn main! []
  (println "Running mode:" (if config/dev? "dev" "release"))
  (.registerLanguage hljs "clojure" clojure-lang)
  (if ssr? (render-app! realize-ssr!))
  (render-app! render!)
  (add-watch *reel :changes (fn [] (render-app! render!)))
  (listen-devtools! "a" dispatch!)
  (.addEventListener
   js/window
   "beforeunload"
   (fn [] (.setItem js/localStorage (:storage-key config/site) (pr-str (:store @*reel)))))
  (let [raw (.getItem js/localStorage (:storage-key config/site))]
    (when (some? raw) (dispatch! :hydrate-storage (read-string raw))))
  (dispatch! :pick-case (rand-int 10))
  (println "App started."))

(defn reload! []
  (clear-cache!)
  (reset! *reel (refresh-reel @*reel schema/store updater))
  (println "Code updated."))
