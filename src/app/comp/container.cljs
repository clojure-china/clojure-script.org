
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
 comp-footer
 ()
 (div
  {:style {:background-color "#292d2e", :height 200}}
  (div
   {:style {:width 800, :margin :auto, :padding "16px 0"}}
   (a
    {:style {:color (hsl 240 80 80), :text-decoration :none},
     :href "https://github.com/clojure-china/clojure-script.org",
     :target "_blank"}
    (<> "Site built with ClojureScript & Respo.")))))

(defcomp
 comp-header
 ()
 (div
  {:style {:padding 8}}
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
  {:style {:width 800, :margin :auto}}
  (comp-md-block
   "\n### ClojureScript and Clojure\n\nClojureScript has almost the same syntax, same jar files for release libraries.\n\n### ClojureScript and npm\n\nYou can import code from npm modules. You can do js interop to call JavaScript code.\n\n### Learn ClojureScript\n\nLet me gather some links.\n"
   {})))

(defcomp
 comp-showcase
 ()
 (div
  {:class-name "showcase", :style (merge ui/row-center {:padding "16px"})}
  (comp-md-block
   "\n```clojure\n(ns app.main (:require [\"http\" :as http]))\n\n(defonce *visits (atom {}))\n\n(defn handler [req res]\n  (swap! *visits update (.-url req) inc)\n  (.writeHead res 200 #js {\"Content-Type\" \"text/html\"})\n  (.end res\n    (str \"<pre>Visits \" (pr-str @*visits) \"</pre>\")))\n\n(defn main! []\n  (let [server (http/createServer handler)]\n    (.listen server 3000)\n    (println \"server listening on 3000\")))\n\n(main!)\n```"
   {:style {:background-color :transparent,
            :font-family ui/font-code,
            :color :white,
            :padding 8}})
  (=< 16 nil)
  (comp-md-block
   "ClojureScript is cool.\n\n* Feature A\n* Feature B\n* Feature C, just like that\n\ncomes with more..."
   {:style {:color :white}})))

(defcomp
 comp-tool-card
 (info)
 (div
  {:style (merge
           ui/row-parted
           {:border (str "1px solid " (hsl 0 0 90)),
            :padding "8px 16px",
            :border-radius "8px",
            :margin-top 16})}
  (div
   {}
   (div {} (a {:href (:url info), :target "_blank"} (<> (:name info))))
   (div {} (<> (:description info))))
  (if (some? (:logo info))
    (div
     {:style {:background-image (str "url(" (:logo info) ")"),
              :background-size :cover,
              :background-position :center,
              :width 40,
              :height 40}}))))

(defcomp
 comp-tools
 ()
 (div
  {:style {:width 800, :margin :auto}}
  (comp-md-block
   "There are tools for compiling ClojureScript, which is unlike Webpack.."
   {})
  (div
   {:style (merge {:width :auto})}
   (comp-tool-card
    {:name "shadow-cljs",
     :logo "http://cdn.tiye.me/logo/shadow-cljs.png",
     :url "http://shadow-cljs.org",
     :description "ClojureScript compilation made easy."})
   (comp-tool-card
    {:name "Lumo",
     :logo "http://cdn.tiye.me/logo/lumo.png",
     :url "http://lumo-cljs.org",
     :description "Fast, cross-platform, standalone ClojureScript environment."})
   (comp-tool-card
    {:name "Figwheel",
     :logo nil,
     :url "https://github.com/bhauman/lein-figwheel",
     :description "Figwheel builds your ClojureScript code and hot loads it into the browser as you are coding!"}))))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store)]
   (div
    {:style (merge ui/global {:background-color :white})}
    (div
     {:style {:background-image "linear-gradient(to right, #0f2242, #2452a1)"}}
     (comp-header)
     (comp-showcase))
    (=< nil 32)
    (comp-tools)
    (comp-resources)
    (=< nil 16)
    (comp-footer)
    (when dev? (cursor-> :reel comp-reel states reel {})))))
