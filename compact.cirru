
{} (:package |app)
  :configs $ {} (:init-fn |app.main/main!) (:reload-fn |app.main/reload!) (:version |0.0.1)
    :modules $ [] |respo.calcit/ |lilac/ |memof/ |respo-ui.calcit/ |respo-markdown.calcit/ |reel.calcit/
  :entries $ {}
  :files $ {}
    |app.comp.container $ {}
      :defs $ {}
        |comp-container $ quote
          defcomp comp-container (reel)
            let
                store $ :store reel
                states $ :states store
              div
                {} $ :style
                  merge ui/global $ {} (:background-color :white) (:font-size 16)
                div
                  {} $ :style
                    {} $ :background-image "\"linear-gradient(to right, #0f2242, #2452a1)"
                  comp-header
                  div
                    {} $ :style
                      {} (:color :white) (:font-weight 300) (:font-family ui/font-fancy) (:font-size 20) (:margin :auto) (:text-align :center) (:max-width 800) (:padding "\"0 16px")
                    comp-md-block (inline "\"clojure-is.md")
                      {} $ :class-name "\"content on-dark"
                  comp-showcase $ :case-idx store
                =< nil 32
                comp-tools
                comp-resources
                =< nil 16
                comp-footer
                when dev? $ comp-reel (>> states :reel) reel ({})
        |comp-footer $ quote
          defcomp comp-footer () $ div
            {} $ :style
              {} (:background-color "\"#292d2e") (:height 200) (:padding 16)
            div
              {} $ :style
                {} (:max-width 800) (:margin :auto)
              a
                {}
                  :style $ {}
                    :color $ hsl 240 80 80
                    :text-decoration :none
                  :href "\"https://github.com/clojure-china/clojure-script.org"
                  :target "\"_blank"
                <> "\"Site built with shadow-cljs & Respo."
        |comp-header $ quote
          defcomp comp-header () $ div
            {} $ :style
              {} $ :padding 16
            div
              {} $ :style
                merge ui/row-parted $ {} (:max-width 800) (:margin :auto)
              div
                {}
                  :style $ merge ui/row-center
                    {} $ :cursor :pointer
                  :on-click $ fn (e d!)
                    d! :pick-case $ rand-int 10
                div $ {}
                  :style $ {}
                    :background-image $ str "\"url(http://cdn.tiye.me/logo/cljs.png)"
                    :width 40
                    :height 40
                    :background-position "\"center center"
                    :background-size :cover
                =< 8 nil
                a $ {} (:href "\"https://clojurescript.org/") (:inner-text "\"(Unofficial)")
                  :style $ {}
                    :color $ hsl 240 80 80
                    :font-family ui/font-fancy
                    :text-decoration :none
              div $ {}
        |comp-resources $ quote
          defcomp comp-resources () $ div
            {} $ :style
              {} (:max-width 800) (:margin :auto) (:padding 16)
            comp-md-block (inline "\"resource.md")
              {} $ :class-name "\"content"
        |comp-tool-card $ quote
          defcomp comp-tool-card (info)
            div
              {} $ :style
                merge ui/row-parted $ {}
                  :border $ str "\"1px solid " (hsl 0 0 90)
                  :padding "\"8px 16px"
                  :border-radius "\"8px"
                  :margin-top 16
              div ({})
                div ({})
                  a
                    {}
                      :href $ :url info
                      :target "\"_blank"
                      :style $ {} (:text-decoration :none) (:font-size 16)
                        :color $ hsl 240 90 70
                        :line-height "\"1.4em"
                        :font-family ui/font-fancy
                    <> $ :name info
                div ({})
                  <> $ :description info
              if
                some? $ :logo info
                div $ {}
                  :style $ {}
                    :background-image $ str "\"url(" (:logo info) "\")"
                    :background-size :cover
                    :background-position :center
                    :width 40
                    :height 40
        |comp-tools $ quote
          defcomp comp-tools () $ div
            {} $ :style
              {} (:max-width 800) (:margin :auto) (:padding 16)
            comp-md-block (inline "\"quote.md")
              {} $ :class-name "\"content"
            div
              {} $ :style
                {} $ :margin-top 48
              <> |Learning $ {} (:font-size 24) (:font-weight 100) (:font-family ui/font-normal) (:vertical-align :middle)
              =< 16 nil
              a
                {}
                  :style $ merge ui/button
                    {} (:vertical-align :middle) (:border-radius "\"16px") (:padding "\"0 16px") (:font-family ui/font-fancy) (:font-size 20) (:text-decoration :none) (:line-height "\"32px") (:display :inline-block)
                  :href "\"http://clojurescript.io/"
                  :target "\"_blank"
                <> "\"Get an online REPL"
            comp-md-block (inline "\"learning.md")
              {} $ :class-name "\"content"
            div
              {} $ :style
                merge $ {} (:width :auto)
              comp-tool-card $ {} (:name "\"shadow-cljs") (:logo "\"http://cdn.tiye.me/logo/shadow-cljs.png") (:url "\"http://shadow-cljs.org") (:description "\"ClojureScript compilation made easy.")
              comp-tool-card $ {} (:name "\"figwheel-main") (:logo nil) (:url "\"https://figwheel.org") (:description "\"Figwheel Main builds your ClojureScript code and hot loads it as you are coding!")
      :ns $ quote
        ns app.comp.container $ :require
          [] respo-ui.core :refer $ [] hsl
          [] respo-ui.core :as ui
          [] respo.core :refer $ [] defcomp >> <> div button textarea span a
          [] respo.comp.space :refer $ [] =<
          [] reel.comp.reel :refer $ [] comp-reel
          [] respo-md.comp.md :refer $ [] comp-md comp-md-block
          [] app.config :refer $ [] dev?
          [] app.util :refer $ [] highlighter inline
          [] app.comp.showcase :refer $ [] comp-showcase
          "\"@calcit/std" :refer $ rand-int
    |app.comp.showcase $ {}
      :defs $ {}
        |comp-showcase $ quote
          defcomp comp-showcase (case-idx)
            let
                showcase $ get showcases case-idx
              div
                {} (:class-name "\"showcase")
                  :style $ merge ui/center
                    {} (:padding "\"16px") (:min-height 320) (:overflow :auto) (:padding 16)
                div ({})
                  <> (:text showcase)
                    {} (:color :white) (:font-size 16)
                  =< 16 nil
                  a $ {}
                    :href $ :url showcase
                    :target "\"_blank"
                    :style $ {}
                      :color $ hsl 240 80 80
                      :text-decoration :none
                      :font-family ui/font-fancy
                    :inner-text "\"Link"
                comp-md-block
                  str "\"```clojure" &newline (:code showcase) &newline "\"```"
                  {}
                    :style $ {} (:background-color :transparent) (:font-family ui/font-code) (:color :white) (:padding 8) (:margin :auto)
                    :highlight highlighter
        |showcases $ quote
          def showcases $ {}
            0 $ {}
              :code $ inline "\"thread-macro.cljs"
              :text "|Thread macros"
              :url "\"https://clojure.org/guides/threading_macros"
            1 $ {}
              :code $ inline "\"destructuring.cljs"
              :text "\"Destructuring"
              :url "\"https://gist.github.com/john2x/e1dca953548bfdfb9844"
            2 $ {}
              :code $ inline "\"inter-op.cljs"
              :text |InterOp
              :url "\"http://www.spacjer.com/blog/2014/09/12/clojurescript-javascript-interop/"
            3 $ {}
              :code $ inline "\"transducer.cljs"
              :text |Transducers
              :url "\"https://stackoverflow.com/a/26322910/883571"
            4 $ {}
              :code $ inline "\"reagent.cljs"
              :text |Reagent
              :url "\"http://reagent-project.github.io/"
            5 $ {}
              :code $ inline "\"js-deps.cljs"
              :text "|JS Dependencies"
              :url "\"https://code.thheller.com/blog/shadow-cljs/2017/11/10/js-dependencies-in-practice.html"
            6 $ {}
              :code $ inline "\"nodejs.cljs"
              :text "|Node.js Server"
              :url "\"https://gist.github.com/semperos/83696c0ac168eb03ab1d"
            7 $ {}
              :code $ inline "\"data.cljs"
              :text "|Data manipulation"
              :url "\"https://stackoverflow.com/a/38754874/883571"
            8 $ {}
              :code $ inline "\"atom.cljs"
              :text |Atom
              :url "\"https://clojuredocs.org/clojure.core/atom"
            9 $ {}
              :code $ inline "\"edn.cljs"
              :text |EDN
              :url "\"https://learnxinyminutes.com/docs/edn/"
      :ns $ quote
        ns app.comp.showcase $ :require
          [] respo-ui.core :refer $ [] hsl
          [] respo-ui.core :as ui
          [] respo.core :refer $ [] defcomp cursor-> action-> mutation-> <> div button textarea span a
          [] respo.comp.space :refer $ [] =<
          [] respo-md.comp.md :refer $ [] comp-md comp-md-block
          [] app.config :refer $ [] dev?
          [] app.util :refer $ [] highlighter inline
    |app.config $ {}
      :defs $ {}
        |dev? $ quote
          def dev? $ = "\"dev" (get-env "\"mode")
        |site $ quote
          def site $ {} (:dev-ui "\"http://localhost:8100/main.css") (:release-ui "\"http://cdn.tiye.me/favored-fonts/main.css") (:cdn-url "\"http://cdn.tiye.me/clojure-script-org/") (:title "\"ClojureScript, beginners' home page") (:icon "\"http://cdn.tiye.me/logo/cljs.png") (:storage-key "\"clojure-script.org")
      :ns $ quote (ns app.config)
    |app.main $ {}
      :defs $ {}
        |*reel $ quote
          defatom *reel $ -> reel-schema/reel (assoc :base schema/store) (assoc :store schema/store)
        |dispatch! $ quote
          defn dispatch! (op ? op-data)
            when
              and config/dev? $ not= op :states
              println |Dispatch: op
            if (list? op)
              recur $ : states op op-data
              if (tag? op)
                recur $ :: op op-data
                reset! *reel $ reel-updater updater @*reel op
        |main! $ quote
          defn main! ()
            println "\"Running mode:" $ if config/dev? "\"dev" "\"release"
            if config/dev? $ load-console-formatter!
            .!registerLanguage hljs "\"clojure" clojure-lang
            render-app!
            add-watch *reel :changes $ fn (r p) (render-app!)
            listen-devtools! |a dispatch!
            js/window.addEventListener |beforeunload $ fn (e)
              .setItem js/localStorage (:storage-key config/site)
                format-cirru-edn $ :store @*reel
            let
                raw $ js/localStorage.getItem (:storage-key config/site)
              when (some? raw)
                dispatch! :hydrate-storage $ parse-cirru-edn raw
            dispatch! :pick-case $ rand-int 10
            println "|App started."
        |mount-target $ quote
          def mount-target $ .querySelector js/document |.app
        |reload! $ quote
          defn reload! () $ if (nil? build-errors)
            do (remove-watch *reel :changes) (clear-cache!)
              add-watch *reel :changes $ fn (reel prev) (render-app!)
              reset! *reel $ refresh-reel @*reel schema/store updater
              hud! "\"ok~" "\"Ok"
            hud! "\"error" build-errors
        |render-app! $ quote
          defn render-app! () $ render! mount-target (comp-container @*reel) dispatch!
      :ns $ quote
        ns app.main $ :require
          [] respo.core :refer $ [] render! clear-cache! realize-ssr!
          [] app.comp.container :refer $ [] comp-container
          [] app.updater :refer $ [] updater
          [] app.schema :as schema
          [] app.config :as config
          [] reel.util :refer $ [] listen-devtools!
          [] reel.core :refer $ [] reel-updater refresh-reel
          [] reel.schema :as reel-schema
          [] "\"highlight.js" :default hljs
          [] "\"highlight.js/lib/languages/clojure" :default clojure-lang
          "\"./calcit.build-errors" :default build-errors
          "\"bottom-tip" :default hud!
          "\"@calcit/std" :refer $ rand-int
    |app.schema $ {}
      :defs $ {}
        |store $ quote
          def store $ {}
            :states $ {}
            :content |
            :case-idx 0
      :ns $ quote (ns app.schema)
    |app.updater $ {}
      :defs $ {}
        |updater $ quote
          defn updater (store op op-id op-time)
            tag-match op
                :states cursor s
                update-states store cursor s
              (:content c) (assoc store :content c)
              (:pick-case c) (assoc store :case-idx c)
              (:hydrate-storage d) d
              _ $ do (println "\"Unknown op:" op) store
      :ns $ quote
        ns app.updater $ :require
          [] respo.cursor :refer $ [] update-states
    |app.util $ {}
      :defs $ {}
        |highlighter $ quote
          defn highlighter (code lang)
            if (contains? supported-langs lang)
              .-value $ .!highlight hljs lang code
              escape-html code
        |inline $ quote
          defmacro inline (path)
            read-file $ str "\"content/" path
        |supported-langs $ quote
          def supported-langs $ &{} "\"clojure" "\"clojure"
      :ns $ quote
        ns app.util $ :require ([] "\"escape-html" :default escape-html) ([] "\"highlight.js" :default hljs)
