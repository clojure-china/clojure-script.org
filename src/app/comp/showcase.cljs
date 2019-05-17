
(ns app.comp.showcase
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.core
             :refer
             [defcomp cursor-> action-> mutation-> <> div button textarea span a]]
            [respo.comp.space :refer [=<]]
            [respo-md.comp.md :refer [comp-md comp-md-block]]
            [app.config :refer [dev?]]
            [app.util :refer [highlighter]]
            [shadow.resource :refer [inline]]))

(def showcases
  {0 {:code (inline "thread-macro.cljs"),
      :text "Thread macros",
      :url "https://clojure.org/guides/threading_macros"},
   1 {:code (inline "destructuring.cljs"),
      :text "Destructuring",
      :url "https://gist.github.com/john2x/e1dca953548bfdfb9844"},
   2 {:code (inline "inter-op.cljs"),
      :text "InterOp",
      :url "http://www.spacjer.com/blog/2014/09/12/clojurescript-javascript-interop/"},
   3 {:code (inline "transducer.cljs"),
      :text "Transducers",
      :url "https://stackoverflow.com/a/26322910/883571"},
   4 {:code (inline "reagent.cljs"),
      :text "Reagent",
      :url "http://reagent-project.github.io/"},
   5 {:code (inline "js-deps.cljs"),
      :text "JS Dependencies",
      :url "https://code.thheller.com/blog/shadow-cljs/2017/11/10/js-dependencies-in-practice.html"},
   6 {:code (inline "nodejs.cljs"),
      :text "Node.js Server",
      :url "https://gist.github.com/semperos/83696c0ac168eb03ab1d"},
   7 {:code (inline "data.cljs"),
      :text "Data manipulation",
      :url "https://stackoverflow.com/a/38754874/883571"},
   8 {:code (inline "atom.cljs"),
      :text "Atom",
      :url "https://clojuredocs.org/clojure.core/atom"},
   9 {:code (inline "edn.cljs"), :text "EDN", :url "https://learnxinyminutes.com/docs/edn/"}})

(defcomp
 comp-showcase
 (case-idx)
 (let [showcase (get showcases case-idx)]
   (div
    {:class-name "showcase",
     :style (merge ui/center {:padding 16, :min-height 320, :overflow :auto})}
    (div
     {}
     (<> (:text showcase) {:color :white, :font-size 16})
     (=< 16 nil)
     (a
      {:href (:url showcase),
       :target "_blank",
       :style {:color (hsl 240 80 80), :text-decoration :none, :font-family ui/font-fancy},
       :inner-text "Link"}))
    (comp-md-block
     (str "```clojure" "\n" (:code showcase) "\n" "```")
     {:style {:background-color :transparent,
              :font-family ui/font-code,
              :color :white,
              :padding 8,
              :margin :auto},
      :highlight highlighter}))))
