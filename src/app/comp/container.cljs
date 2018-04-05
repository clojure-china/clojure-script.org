
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.macros
             :refer
             [defcomp cursor-> action-> mutation-> <> div button textarea span a]]
            [verbosely.core :refer [verbosely!]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [respo-md.comp.md :refer [comp-md comp-md-block]]
            [app.schema :refer [dev?]]))

(defcomp
 comp-header
 ()
 (div
  {:style {:background-color (hsl 0 0 0), :padding 8}}
  (div
   {:style (merge ui/row-parted {:width 800, :margin :auto})}
   (div
    {:style {:background-image (str "url(http://cdn.tiye.me/logo/cljs.png)"),
             :width 40,
             :height 40,
             :background-position "center center",
             :background-size :cover}})
   (div
    {}
    (a
     {:href "https://clojurescript.org/guides/quick-start",
      :inner-text "Official site",
      :target "_blank",
      :style {:color (hsl 240 80 80)}})))))

(defcomp
 comp-resources
 ()
 (div
  {}
  (comp-md-block
   "\n### ClojureScript and Clojure\n\nClojureScript has almost the same syntax, same jar files for release libraries.\n\n### ClojureScript and npm\n\nYou can import code from npm modules. You can do js interop to call JavaScript code.\n\n### Learn ClojureScript\n\nLet me gather some links.\n"
   {})))

(defcomp
 comp-showcase
 ()
 (div
  {:width 800}
  (div
   {:class-name "showcase", :style (merge ui/row-center)}
   (comp-md-block
    "\n```clojure\n(ns app.main (:require [\"http\" :as http]))\n\n(defonce *visits (atom {}))\n\n(defn handler [req res]\n  (swap! *visits update (.-url req) inc)\n  (.writeHead res 200 #js {\"Content-Type\" \"text/html\"})\n  (.end res\n    (str \"<pre>Visits \" (pr-str @*visits) \"</pre>\")))\n\n(defn main! []\n  (let [server (http/createServer handler)]\n    (.listen server 3000)\n    (println \"server listening on 3000\")))\n\n(main!)\n```"
    {:style {:background-color (hsl 0 0 0),
             :font-family ui/font-code,
             :color :white,
             :padding 8}})
   (comp-md-block
    "ClojureScript is cool.\n\n* Feature A\n* Feature B\n* Feature C, just like that\n\ncomes with more..."
    {}))))

(defcomp
 comp-tools
 ()
 (div
  {}
  (comp-md-block
   "There are tools for compiling ClojureScript, which is unlike Webpack.."
   {})
  (div
   {:style (merge ui/row-center {:width :auto})}
   (div
    {:style {:width 120,
             :height 120,
             :background-image "url(http://cdn.tiye.me/logo/lumo.png)",
             :background-size :cover,
             :background-position :center}})
   (div
    {:style {:width 120,
             :height 120,
             :background-image "url(http://cdn.tiye.me/logo/shadow-cljs.png)",
             :background-size :cover,
             :background-position :center}})
   (div {} (<> "Figwheel")))))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store)]
   (div
    {:style (merge ui/global)}
    (comp-header)
    (comp-showcase)
    (comp-tools)
    (comp-resources)
    (when dev? (cursor-> :reel comp-reel states reel {})))))
