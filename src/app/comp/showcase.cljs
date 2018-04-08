
(ns app.comp.showcase
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.macros
             :refer
             [defcomp cursor-> action-> mutation-> <> div button textarea span a]]
            [verbosely.core :refer [verbosely!]]
            [respo.comp.space :refer [=<]]
            [respo-md.comp.md :refer [comp-md comp-md-block]]
            [app.schema :refer [dev?]]
            [app.util :refer [highlighter]]))

(def showcases
  {0 {:code "\n```clojure\n(defn transform* [person]\n   (-> person\n      (assoc :hair-color :gray)\n      (update :age inc)))\n\n(defn calculate* []\n   (->> (range 10)\n        (filter odd? ,,,)\n        (map #(* % %) ,,,)\n        (reduce + ,,,)))\n\n(as-> [:foo :bar] v\n  (map name v)\n  (first v)\n  (.substring v 1))\n```",
      :text "Thread macros",
      :url "https://clojure.org/guides/threading_macros"},
   1 {:code "\n```clojure\n(let [[a b & the-rest] [:a :b :c :d]]\n  (println a b the-rest))\n;; => :a :b (:c :d)\n\n(let [{a :a d :d} {:a \"A\" :b \"B\" :c \"C\" :d \"D\"}]\n  (println a d))\n;; => A D\n\n(let [{:keys [a b], :as all} {:a \"A\" :b \"B\" :c \"C\" :d \"D\"}]\n  (println a b all))\n;; => A B {:a A :b B :c C :d D}\n```",
      :text "Destructuring",
      :url "https://gist.github.com/john2x/e1dca953548bfdfb9844"},
   2 {:code "\n```clojure\n(def m2 (js/Microsoft.Maps.Themes.BingTheme.))\n\n(def my-object (js-obj \"a\" 1 \"b\" true \"c\" nil))\n\n(def js-object #js {:a 1 :b 2})\n\n(def my-array (js->clj (.-globalArray js/window)))\n(def first-item (get my-array 0)) ;; 1\n\n(def my-obj (js->clj (.-globalObject js/window)))\n(def a (get my-obj \"a\")) ;; 1\n```",
      :text "InterOp",
      :url "http://www.spacjer.com/blog/2014/09/12/clojurescript-javascript-interop/"},
   3 {:code "\n```clojure\n(def xform\n  (comp\n    (map #(+ 2 %))\n    (filter odd?)))\n\n(transduce xform + (range 0 10))\n```",
      :text "Transducers",
      :url "https://stackoverflow.com/a/26322910/883571"},
   4 {:code "\n```clojure\n(ns example\n  (:require [reagent.core :as r]))\n(defn simple-component []\n  [:div\n   [:p \"I am a component!\"]\n   [:p.someclass\n    \"I have \" [:strong \"bold\"]\n    [:span {:style {:color \"red\"}} \" and red \"]\n    \"text.\"]])\n\n(defn render-simple []\n  (r/render [simple-component]\n    (.-body js/document)))\n\n```",
      :text "Reagent",
      :url "http://reagent-project.github.io/"},
   5 {:code "\n```clojure\n(:require [\"module-name\" :default defaultExport])\n(:require [\"module-name\" :as name])\n(:require [\"module-name\" :refer (export)])\n(:require [\"module-name\" :rename {export alias}])\n(:require [\"module-name\" :refer (export1 export2)])\n(:require [\"module-name\" :refer (export1) :rename {export2 alias2}])\n(:require [\"module-name\" :refer (export) :default defaultExport])\n(:require [\"module-name\" :as name :default defaultExport])\n(:require [\"module-name\"])\n```",
      :text "JS Dependencies",
      :url "https://code.thheller.com/blog/shadow-cljs/2017/11/10/js-dependencies-in-practice.html"},
   6 {:code "\n```clojure\n(def http (js/require \"http\"))\n;=> a lot of stuff you don't want to see\n(def head (clj->js {\"Content-Type\" \"text/plain\"}))\n;=> #js {:Content-Type \"text/plain\"}\n(def server\n  (.createServer http\n    (fn [req resp]\n      (.writeHead resp 200 head)\n      (.end resp \"Hello, World\\n\"))))\n;=> #<[object Object]>\n(.listen server 1337 \"127.0.0.1\")\n```",
      :text "Node.js Server",
      :url "https://gist.github.com/semperos/83696c0ac168eb03ab1d"},
   7 {:code "\n```clojure\n(->> data\n     (partition-by (comp boolean :high))\n     (partition 2 1)\n     (mapcat (fn [[lbounds rbounds]]\n               (let [left-bound (last lbounds)\n                     left-val (hi-or-lo left-bound)]\n                 (map #(let [right-val (hi-or-lo %)\n                             diff (Math/abs (- right-val left-val))]\n                         {:extremes [left-bound %]\n                          :price-range diff\n                          :midpoint (+ (min right-val left-val)\n                                       (/ diff 2))})\n                      rbounds))))\n     (clojure.pprint/pprint))\n```",
      :text "Data manipulation",
      :url "https://stackoverflow.com/a/38754874/883571"},
   8 {:code "\n```clojure\n(def car\n  (atom {:make \"Audi\"\n         :model \"Q3\"}))\n\n; @car\n;; {:make \"Audi\", :model \"Q3\"}\n\n(swap!\n car\n assoc :model \"Q5\")\n;; {:make \"Audi\", :model \"Q5\"}\n\n(reset! car {:make \"\" :model \"\"})\n;; {:make \"\", :model \"\"}\n```",
      :text "Atom",
      :url "https://clojuredocs.org/clojure.core/atom"},
   9 {:code "\n```clojure\n; Lists are sequences of values\n(:bun :beef-patty 9 \"yum!\")\n\n; Vectors allow random access\n[:gelato 1 2 -2]\n\n; Maps are associative data structures that\n; associate the key with its value\n{:eggs        2\n :lemon-juice 3.5\n :butter      1}\n```",
      :text "EDN",
      :url "https://learnxinyminutes.com/docs/edn/"}})

(defcomp
 comp-showcase
 (case-idx)
 (let [showcase (get showcases case-idx)]
   (div
    {:class-name "showcase",
     :style (merge ui/center {:padding 16, :min-height 360, :overflow :auto})}
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
     (:code showcase)
     {:style {:background-color :transparent,
              :font-family ui/font-code,
              :color :white,
              :padding 8},
      :highlight highlighter}))))
