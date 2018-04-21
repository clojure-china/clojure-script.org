
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
            [app.schema :refer [dev?]]
            [app.util :refer [highlighter]]
            [app.comp.showcase :refer [comp-showcase]]))

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
  (comp-md-block
   "You may also try [Planck](http://planck-repl.org/), [boot-cljs](https://github.com/boot-clj/boot-cljs) or [lein-cljsbuild](https://github.com/emezeske/lein-cljsbuild/) based on your needs.\n\n### Ecosystem\n\nThere are several libraries in ClojureScript that light up the community. They are famous:\n\n* [Reagent](http://reagent-project.github.io/) - Reagent: Minimalistic React for ClojureScript\n* [Rum](https://github.com/tonsky/rum) - Simple, decomplected, isomorphic HTML UI library for Clojure and ClojureScript\n* [om](https://github.com/omcljs/om) - A powerful interface to React, makes use of its object oriented structures.\n* [cljs-devtools](https://github.com/binaryage/cljs-devtools) - Better presentation of ClojureScript values in Chrome Devtools.\n* [Datascript](https://github.com/tonsky/datascript) - An immutable in-memory database and Datalog query engine in ClojureScript.\n* [re-frame](https://github.com/Day8/re-frame) - A Reagent Framework For Writing SPAs, in Clojurescript.\n* [core.async](https://github.com/clojure/core.async) - Facilities for async programming and communication in Clojure.\n* [sente](https://github.com/ptaoussanis/sente) - Realtime web comms for Clojure/Script.\n\n### ClojureScript and Clojure\n\nClojureScript and Clojure share the same syntax but distinguish by `.cljs` extension name. The most different part is the difference in host platforms, like JavaScript is known as single-threaded and restricted by browser APIs.\n\nLibraries of both sides release code on [Clojars](https://clojars.org/) in jar files.\n\n### ClojureScript and npm\n\nWith JavaScript InterOp, you may call some JavaScript code from in Clojure syntax. ClojureScript is designed to use features from host platform. You are free to import npm modules in ClojureScript, most of them will work correctly, especially in shadow-cljs and Lumo.\n\n### Immutable Data Structure\n\nClojure is a functional programming language. It provides the tools to avoid mutable state, provides functions as first-class objects, and emphasizes recursive iteration instead of side-effect based looping. Meanwhile immutable data structure happens to be a great tool React needs in reducing redundant virtual DOM renderings.\n\n### Communities\n\nJoin us on:\n\n* http://clojureverse.org/\n* http://clojurians.slack.com/\n* https://www.reddit.com/r/Clojure/\n* https://discord.gg/X6yrEjc\n\nAlso cool if you use [Twitter](http://twitter.com/scriptclojure).\n"
   {:class-name "content"})))

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
  (comp-md-block
   "ClojureScript is a modern, functional & immutable data-oriented language with a great standard library that compiles down to self-contained & compact JavaScript bundles. Based on Clojure, it brings Lisp’s elegance and meta-programming to the JavaScript ecosystem.([By @orestis](https://clojureverse.org/t/how-do-you-introduce-clojurescript-to-beginners-in-one-sentence/2004/7))"
   {:class-name "content"})
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
  (comp-md-block
   "ClojureScript shares same syntax with Clojure but with different host APIs and environments. To Learn it:\n\n* [ClojureScript Syntax in 15 minutes](https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes)\n* [Learn X in Y minutes](https://learnxinyminutes.com/docs/clojure/)\n* [ClojureScript: JavaScript Interop](http://www.spacjer.com/blog/2014/09/12/clojurescript-javascript-interop/), [Video](https://lambdaisland.com/episodes/clojurescript-interop)\n* [ClojureScript Cheatsheet](http://cljs.info/cheatsheet/)\n* [Clojure for the Brave and True: Do Things](https://www.braveclojure.com/do-things/)\n* [Understanding Clojure's Persistent Vectors, pt. 1](https://hypirion.com/musings/understanding-persistent-vector-pt-1)\n\n### Compilers\n\nCompiling and hot-swapping ClojureScript programs require tools. Pick one of those as you need:"
   {:class-name "content"})
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
     :description "Figwheel builds your ClojureScript code and hot loads it into the browser as you are coding!"})
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
       "[Clojure](https://clojure.org/)[Script](https://clojurescript.org/) is a dialect of the Lisp and a general-purpose language with an emphasis on functional programming, that runs on JavaScript."
       {:class-name "content on-dark"}))
     (comp-showcase (:case-idx store)))
    (=< nil 32)
    (comp-tools)
    (comp-resources)
    (=< nil 16)
    (comp-footer)
    (when dev? (cursor-> :reel comp-reel states reel {})))))
