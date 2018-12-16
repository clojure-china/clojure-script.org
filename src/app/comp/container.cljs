
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.core
             :refer
             [defcomp cursor-> action-> mutation-> <> div button textarea span a]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [respo-md.comp.md :refer [comp-md comp-md-block]]
            [app.config :refer [dev?]]
            [app.util :refer [highlighter]]
            [app.comp.showcase :refer [comp-showcase]]
            [build.util :refer [inline-resource]]))

(defcomp
 comp-footer
 ()
 (div
  {:style {:background-color "#292d2e", :height 200, :padding 16}}
  (div
   {:style {:max-width 800, :margin :auto}}
   (a
    {:style {:color (hsl 240 80 80), :text-decoration :none},
     :href "https://github.com/clojure-china/clojure-script.org",
     :target "_blank"}
    (<> "Site built with shadow-cljs & Respo.")))))

(defcomp
 comp-header
 ()
 (div
  {:style {:padding 16}}
  (div
   {:style (merge ui/row-parted {:max-width 800, :margin :auto})}
   (div
    {:style (merge ui/row-center {:cursor :pointer}),
     :on-click (fn [e d! m!] (d! :pick-case (rand-int 10)))}
    (div
     {:style {:background-image (str "url(http://cdn.tiye.me/logo/cljs.png)"),
              :width 40,
              :height 40,
              :background-position "center center",
              :background-size :cover}})
    (=< 8 nil)
    (a
     {:href "https://clojurescript.org/",
      :inner-text "(Unofficial)",
      :style {:color (hsl 240 80 80), :font-family ui/font-fancy, :text-decoration :none}}))
   (div {}))))

(defcomp
 comp-resources
 ()
 (div
  {:style {:max-width 800, :margin :auto, :padding 16}}
  (comp-md-block (inline-resource "content/resource.md") {:class-name "content"})))

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
   (div
    {}
    (a
     {:href (:url info),
      :target "_blank",
      :style {:text-decoration :none,
              :font-size 16,
              :color (hsl 240 90 70),
              :line-height "1.4em",
              :font-family ui/font-fancy}}
     (<> (:name info))))
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
  {:style {:max-width 800, :margin :auto, :padding 16}}
  (comp-md-block (inline-resource "content/quote.md") {:class-name "content"})
  (div
   {:style {:margin-top 48}}
   (<>
    "Learning"
    {:font-size 24, :font-weight 100, :font-family ui/font-normal, :vertical-align :middle})
   (=< 16 nil)
   (a
    {:style (merge
             ui/button
             {:vertical-align :middle,
              :border-radius "16px",
              :padding "0 16px",
              :font-family ui/font-fancy,
              :font-size 20,
              :text-decoration :none,
              :line-height "32px",
              :display :inline-block}),
     :href "http://clojurescript.io/",
     :target "_blank"}
    (<> "Get an online REPL")))
  (comp-md-block (inline-resource "content/learning.md") {:class-name "content"})
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
    {:name "figwheel-main",
     :logo nil,
     :url "https://figwheel.org",
     :description "Figwheel Main builds your ClojureScript code and hot loads it as you are coding!"})
   (comp-tool-card
    {:name "cljs.main",
     :logo nil,
     :url "https://clojurescript.org/guides/quick-start",
     :description "clj --main cljs.main --compile hello-world.core --repl"}))))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store)]
   (div
    {:style (merge ui/global {:background-color :white, :font-size 16})}
    (div
     {:style {:background-image "linear-gradient(to right, #0f2242, #2452a1)"}}
     (comp-header)
     (div
      {:style {:color :white,
               :font-weight 300,
               :font-family ui/font-fancy,
               :font-size 20,
               :margin :auto,
               :text-align :center,
               :max-width 800,
               :padding "0 16px"}}
      (comp-md-block
       (inline-resource "content/clojure-is.md")
       {:class-name "content on-dark"}))
     (comp-showcase (:case-idx store)))
    (=< nil 32)
    (comp-tools)
    (comp-resources)
    (=< nil 16)
    (comp-footer)
    (when dev? (cursor-> :reel comp-reel states reel {})))))
