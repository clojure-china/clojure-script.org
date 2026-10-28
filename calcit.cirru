
{}
  :about "|Machine-generated snapshot. Do not edit directly — changes will be overwritten. Use `calcit query` to inspect and `calcit edit`/`calcit tree` to modify. Run `calcit docs agents --contract` before mutations; use `--full` for first orientation or changed contract digest. Manual edits must follow format and schema conventions, then run `calcit edit format`."
  :package |app
  :entries $ {} $ :default
    {} (:description |) (:init-fn 'app.main/main!) (:mode :js) (:reload-fn 'app.main/reload!) (:target :browser)
      :feature-policy $ {}
      :modules $ [] |respo.calcit/ |respo-ui.calcit/ |respo-markdown.calcit/ |reel.calcit/ |js-ffi/
      :type-slots $ {} $ :dispatch-op |app.schema/DispatchOp
  :files $ {}
    'app.comp.container $ %{} 'FileEntry
      :defs $ {}
        'comp-container $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defcomp comp-container (reel)
            let
                store $ :store reel
                states $ :states store
              div
                {} $ :style $ .merge ui/global
                  {} (:background-color |white) (:font-size |16px)
                div
                  {} $ :style $ {} (:background-image "|linear-gradient(to right, #0f2242, #2452a1)")
                  comp-header
                  div
                    {} $ :style $ {} (:color |white) (:font-weight |300) (:font-family ui/font-fancy) (:font-size |20px) (:margin |auto) (:text-align |center) (:max-width |800px) (:padding "|0 16px")
                    comp-md-block (inline |clojure-is.md)
                      {} $ :class-name "|content on-dark"
                  comp-showcase $ :case-idx store
                =< nil 32
                comp-tools
                comp-resources
                =< nil 16
                comp-footer
                when dev? $ comp-reel (>> states :reel) (reel-view/view-data reel) ({})
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'respo.schema/Component)
            :args $ [] $ :: 'reel.typed/State 'app.schema/Action 'app.schema/Store
        'comp-footer $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defcomp comp-footer ()
            div
              {} $ :style $ {} (:background-color |#292d2e) (:height |200px) (:padding |16px)
              div
                {} $ :style $ {} (:max-width |800px) (:margin |auto)
                a
                  {}
                    :style $ {}
                      :color $ hsl 240 80 80
                      :text-decoration |none
                    :href |https://github.com/clojure-china/clojure-script.org
                    :target |_blank
                  <> "|Site built with Calcit & Respo."
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'respo.schema/Component)
            :args $ []
        'comp-header $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defcomp comp-header ()
            div
              {} $ :style $ {} (:padding |16px)
              div
                {} $ :style $ .merge ui/row-parted
                  {} (:max-width |800px) (:margin |auto)
                div
                  {}
                    :style $ .merge ui/row-center $ {} (:cursor |pointer)
                    :on-click $ fn (e d!)
                      hint-fn $ {} (:return 'Unit)
                        :args $ [] (:: 'Map 'Tag 'Dynamic)
                          :: 'Fn $ {} (:rest 'Dynamic) (:return 'Unit)
                            :args $ [] 'Dynamic
                      d! $ :: :pick-case $ rand-int 10
                  div $ {} $ :style
                    {}
                      :background-image $ str "|url(https://cdn.tiye.me/logo/cljs.png)"
                      :width |40px
                      :height |40px
                      :background-position "|center center"
                      :background-size |cover
                  =< 8 nil
                  a $ {} (:href |https://clojurescript.org/) (:inner-text "|(Unofficial)")
                    :style $ {}
                      :color $ hsl 240 80 80
                      :font-family ui/font-fancy
                      :text-decoration |none
                div $ {}
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'respo.schema/Component)
            :args $ []
        'comp-resources $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defcomp comp-resources ()
            div
              {} $ :style $ {} (:max-width |800px) (:margin |auto) (:padding |16px)
              comp-md-block (inline |resource.md)
                {} $ :class-name |content
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'respo.schema/Component)
            :args $ []
        'comp-tool-card $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defcomp comp-tool-card (info)
            div
              {} $ :style $ .merge ui/row-parted
                {}
                  :border $ str "|1px solid " $ hsl 0 0 90
                  :padding "|8px 16px"
                  :border-radius |8px
                  :margin-top |16px
              div ({})
                div ({})
                  a
                    {}
                      :href $ :url info
                      :target |_blank
                      :style $ {} (:text-decoration |none) (:font-size |16px)
                        :color $ hsl 240 90 70
                        :line-height |1.4em
                        :font-family ui/font-fancy
                    <> $ :name info
                div ({})
                  <> $ :description info
              match (:logo info)
                (:some logo)
                  div $ {} $ :style
                    {}
                      :background-image $ str "|url(" logo "|)"
                      :background-size |cover
                      :background-position |center
                      :width |40px
                      :height |40px
                (:none) nil
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'respo.schema/Component)
            :args $ [] 'app.schema/ToolInfo
        'comp-tools $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defcomp comp-tools ()
            div
              {} $ :style $ {} (:max-width |800px) (:margin |auto) (:padding |16px)
              comp-md-block (inline |quote.md)
                {} $ :class-name |content
              div
                {} $ :style $ {} (:margin-top |48px)
                <> |Learning $ {} (:font-size |24px) (:font-weight |100) (:font-family ui/font-normal) (:vertical-align |middle)
                =< 16 nil
                a
                  {}
                    :style $ .merge ui/button $ {} (:vertical-align |middle) (:border-radius |16px) (:padding "|0 16px") (:font-family ui/font-fancy) (:font-size |20px) (:text-decoration |none) (:line-height |32px) (:display |inline-block)
                    :href |http://clojurescript.io/
                    :target |_blank
                  <> "|Get an online REPL"
              comp-md-block (inline |learning.md)
                {} $ :class-name |content
              div
                {} $ :style $ {} (:width |auto)
                comp-tool-card $ ToolInfo :name |shadow-cljs :logo (Option :some |http://cdn.tiye.me/logo/shadow-cljs.png) :url |http://shadow-cljs.org :description "|ClojureScript compilation made easy."
                comp-tool-card $ ToolInfo :name |figwheel-main :logo (Option :none) :url |https://figwheel.org :description "|Figwheel Main builds your ClojureScript code and hot loads it as you are coding!"
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'respo.schema/Component)
            :args $ []
      :ns $ %{} 'NsEntry (:doc |)
        :code $ quote $ ns app.comp.container
          :require
            respo-ui.core :refer $ hsl
            respo-ui.core :as ui
            respo.core :refer $ defcomp >> <> div button textarea span a
            respo.comp.space :refer $ =<
            reel.comp.reel :refer $ comp-reel
            respo-md.comp.md :refer $ comp-md comp-md-block
            app.config :refer $ dev?
            app.util :refer $ highlighter inline
            app.comp.showcase :refer $ comp-showcase
            |@calcit/std :refer $ rand-int
            app.schema :refer $ ToolInfo
            reel.typed-compat :as reel-view
    'app.comp.showcase $ %{} 'FileEntry
      :defs $ {}
        'comp-showcase $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defcomp comp-showcase (case-idx)
            let
                showcase $ match (showcases .get case-idx)
                  (:some item) item
                  (:none) (raise "|Unknown showcase index")
              div
                {} (:class-name |showcase)
                  :style $ .merge ui/center $ {} (:padding |16px) (:min-height |320px) (:overflow |auto) (:padding |16px)
                div ({})
                  <> (:text showcase)
                    {} (:color |white) (:font-size |16px)
                  =< 16 nil
                  a $ {}
                    :href $ :url showcase
                    :target |_blank
                    :style $ {}
                      :color $ hsl 240 80 80
                      :text-decoration |none
                      :font-family ui/font-fancy
                    :inner-text |Link
                comp-md-block
                  str "|```clojure" &newline (:code showcase) &newline "|```"
                  {}
                    :style $ {} (:background-color |transparent) (:font-family ui/font-code) (:color |white) (:padding |8px) (:margin |auto)
                    :highlight highlighter
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'respo.schema/Component)
            :args $ [] 'Number
        'showcases $ %{} 'CodeEntry (:doc |)
          :code $ quote $ def showcases
            {}
              0 $ Showcase :code (inline |thread-macro.cljs) :text "|Thread macros" :url |https://clojure.org/guides/threading_macros
              1 $ Showcase :code (inline |destructuring.cljs) :text |Destructuring :url |https://gist.github.com/john2x/e1dca953548bfdfb9844
              2 $ Showcase :code (inline |inter-op.cljs) :text |InterOp :url |http://www.spacjer.com/blog/2014/09/12/clojurescript-javascript-interop/
              3 $ Showcase :code (inline |transducer.cljs) :text |Transducers :url |https://stackoverflow.com/a/26322910/883571
              4 $ Showcase :code (inline |reagent.cljs) :text |Reagent :url |http://reagent-project.github.io/
              5 $ Showcase :code (inline |js-deps.cljs) :text "|JS Dependencies" :url |https://code.thheller.com/blog/shadow-cljs/2017/11/10/js-dependencies-in-practice.html
              6 $ Showcase :code (inline |nodejs.cljs) :text "|Node.js Server" :url |https://gist.github.com/semperos/83696c0ac168eb03ab1d
              7 $ Showcase :code (inline |data.cljs) :text "|Data manipulation" :url |https://stackoverflow.com/a/38754874/883571
              8 $ Showcase :code (inline |atom.cljs) :text |Atom :url |https://clojuredocs.org/clojure.core/atom
              9 $ Showcase :code (inline |edn.cljs) :text |EDN :url |https://learnxinyminutes.com/docs/edn/
          :examples $ []
          :schema $ :: 'Map 'Number 'app.schema/Showcase
      :ns $ %{} 'NsEntry (:doc |)
        :code $ quote $ ns app.comp.showcase
          :require
            respo-ui.core :refer $ hsl
            respo-ui.core :as ui
            respo.core :refer $ defcomp cursor-> action-> mutation-> <> div button textarea span a
            respo.comp.space :refer $ =<
            respo-md.comp.md :refer $ comp-md comp-md-block
            app.config :refer $ dev?
            app.util :refer $ highlighter inline
            app.schema :refer $ Showcase
    'app.config $ %{} 'FileEntry
      :defs $ {}
        'Site $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defstruct Site (:dev-ui 'String) (:release-ui 'String) (:cdn-url 'String) (:title 'String) (:icon 'String) (:storage-key 'String)
          :examples $ []
          :schema $ :: 'StructDef
        'dev? $ %{} 'CodeEntry (:doc |)
          :code $ quote $ def dev?
            = |dev $ .unwrap-or (get-env |mode) |release
          :examples $ []
          :schema $ :: 'Bool
        'site $ %{} 'CodeEntry (:doc |)
          :code $ quote $ def site
            Site :dev-ui |http://localhost:8100/main.css :release-ui |http://cdn.tiye.me/favored-fonts/main.css :cdn-url |http://cdn.tiye.me/clojure-script-org/ :title "|ClojureScript, beginners' home page" :icon |http://cdn.tiye.me/logo/cljs.png :storage-key |clojure-script.org
          :examples $ []
          :schema $ :: 'app.config/Site
      :ns $ %{} 'NsEntry (:doc |)
        :code $ quote $ ns app.config
    'app.main $ %{} 'FileEntry
      :defs $ {}
        '*reel $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defref *reel (typed-reel/new-reel schema/store)
          :examples $ []
          :schema $ :: 'Ref $ :: 'reel.typed/State 'app.schema/Action 'app.schema/Store
        'dispatch! $ %{} 'CodeEntry
          :doc "|One checked Respo event boundary: legacy anonymous enums normalize to typed Reel controls or nominal application Actions. Dynamic remains only because Respo render! owns an open dispatcher contract; business updater/store are concrete."
          :code $ quote $ defn dispatch! (input)
            if (enum? input)
              match (typed-reel/decode-control input)
                (:some control)
                  reset! *reel $ typed-reel/apply-control updater @*reel control
                (:none)
                  let
                      action $ decode-action input
                    reset! *reel $ typed-reel/record-op updater @*reel action (generate-id!) (host/now-ms)
              raise "|Expected one action enum"
            , &unit
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'Unit)
            :args $ [] 'Dynamic
        'main! $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defn main! ()
            println "|Running mode:" $ if config/dev? |dev |release
            if config/dev? $ load-console-formatter!
            let
                language clojure-lang
              if (fn? language) (register-language! |clojure language) (raise "|Expected Highlight.js LanguageFn")
            match
              browser/storage-get $ :storage-key config/site
              (:some raw)
                dispatch! $ :: :hydrate-storage $ parse-cirru-edn raw
              (:none) &unit
            render-app!
            add-watch! *reel :changes $ fn (r p)
              hint-fn $ {} (:return 'Unit)
                :args $ [] (:: 'reel.typed/State 'app.schema/Action 'app.schema/Store) (:: 'reel.typed/State 'app.schema/Action 'app.schema/Store)
              render-app!
            listen-devtools! |a dispatch!
            browser/add-event-listener! |beforeunload $ fn (event)
              hint-fn $ {} (:return 'Unit)
                :args $ [] 'js-ffi.browser/EventHost
              save-store!
            dispatch! $ :: :pick-case $ rand-int 10
            println "|App started."
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'Unit)
            :args $ []
            :features $ #{} :js-ffi
        'mount-target $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defn mount-target ()
            let
                document-host $ js-cast js/document 'respo.dom/DomDocument
              document-host .query-selector |.app
          :examples $ []
          :schema $ :: 'Fn $ {}
            :args $ []
            :features $ #{} :js-ffi
            :return $ :: 'JsNullish 'respo.dom/DomElement
        'reload! $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defn reload! ()
            if (js-nullish? build-errors)
              do (remove-watch! *reel :changes) (clear-cache!)
                add-watch! *reel :changes $ fn (reel prev)
                  hint-fn $ {} (:return 'Unit)
                    :args $ [] (:: 'reel.typed/State 'app.schema/Action 'app.schema/Store) (:: 'reel.typed/State 'app.schema/Action 'app.schema/Store)
                  render-app!
                reset! *reel $ typed-reel/refresh updater @*reel schema/store
                hud! |ok~ |Ok
              hud! |error build-errors
            , &unit
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'Unit)
            :args $ []
            :features $ #{} :js-ffi
        'render-app! $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defn render-app! ()
            render! (mount-target) (comp-container @*reel) dispatch!
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'Unit)
            :args $ []
            :features $ #{} :js-ffi
        'save-store! $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defn save-store! ()
            let
                store $ :store @*reel
              browser/storage-set! (:storage-key config/site)
                format-cirru-edn $ {}
                  :states $ :states store
                  :content $ :content store
                  :case-idx $ :case-idx store
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'Unit)
            :args $ []
      :ns $ %{} 'NsEntry (:doc |)
        :code $ quote $ ns app.main
          :require
            respo.core :refer $ render! clear-cache! realize-ssr!
            app.comp.container :refer $ comp-container
            app.updater :refer $ updater decode-action
            app.schema :as schema
            app.config :as config
            reel.util :refer $ listen-devtools!
            |highlight.js/lib/languages/clojure :default clojure-lang
            |./calcit.build-errors :default build-errors
            |bottom-tip :default hud!
            |@calcit/std :refer $ rand-int
            reel.typed :as typed-reel
            js-ffi.shared :as host
            app.util :refer $ register-language!
            js-ffi.browser :as browser
    'app.schema $ %{} 'FileEntry
      :defs $ {}
        'Action $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defenum Action
            :states (:: 'List 'Dynamic) 'Dynamic
            :content 'String
            :pick-case 'Number
            :hydrate-storage 'app.schema/Store
          :examples $ []
          :schema $ :: 'EnumDef
        'DispatchOp $ %{} 'CodeEntry (:doc "|Respo 入口事件合同，容纳应用和 Reel 控制事件；代表值不发送。")
          :code $ quote $ def DispatchOp (:: :unhandled)
          :examples $ []
          :schema $ :: 'Enum
        'Showcase $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defstruct Showcase (:code 'String) (:text 'String) (:url 'String)
          :examples $ []
          :schema $ :: 'StructDef
        'Store $ %{} 'CodeEntry
          :doc "|Application fields are concrete. Only the Respo component-state tree retains its existing heterogeneous payload contract; hydrate and updates must pass a runtime decoder."
          :code $ quote $ defstruct Store
            :states $ :: 'Map 'Tag 'Dynamic
            :content 'String
            :case-idx 'Number
          :examples $ []
          :schema $ :: 'StructDef
        'ToolInfo $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defstruct ToolInfo (:name 'String) (:url 'String) (:description 'String)
            :logo $ :: 'Option 'String
          :examples $ []
          :schema $ :: 'StructDef
        'store $ %{} 'CodeEntry (:doc |)
          :code $ quote $ def store
            Store :states ({}) :content | :case-idx 0
          :examples $ []
          :schema $ :: 'app.schema/Store
      :ns $ %{} 'NsEntry (:doc |)
        :code $ quote $ ns app.schema
    'app.updater $ %{} 'FileEntry
      :defs $ {}
        'decode-action $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defn decode-action (op)
            match op
              (:states cursor data)
                Action :states
                  decode-map-as cursor $ :: 'List 'Dynamic
                  , data
              (:content text)
                if (string? text) (Action :content text) (raise "|Expected content String")
              (:pick-case index)
                if (number? index) (Action :pick-case index) (raise "|Expected showcase Number")
              (:hydrate-storage data)
                Action :hydrate-storage $ decode-map-as data 'app.schema/Store
              _ $ raise "|Unknown application action"
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'app.schema/Action)
            :args $ [] 'Enum
          :tests $ [] $ %{} 'TestEntry (:name |restores-legacy-store-map)
            :code $ quote $ let
                action $ decode-action $ :: :hydrate-storage
                  {}
                    :states $ {}
                    :content |saved
                    :case-idx 3
              match action
                (:hydrate-storage restored)
                  do
                    assert= |saved $ :content restored
                    assert= 3 $ :case-idx restored
                _ $ raise "|Expected hydration action"
            :tags $ #{} :regression :unit
        'updater $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defn updater (store op op-id op-time)
            match op
              (:states cursor s)
                assoc store :states $ decode-map-as
                  update-state-tree (:states store) cursor s
                  :: 'Map 'Tag 'Dynamic
              (:content c) (assoc store :content c)
              (:pick-case c) (assoc store :case-idx c)
              (:hydrate-storage d) d
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'app.schema/Store)
            :args $ [] 'app.schema/Store 'app.schema/Action 'String 'Number
          :tests $ [] $ %{} 'TestEntry (:name |updates-concrete-store)
            :code $ quote $ let
                original $ Store :states ({}) :content | :case-idx 0
                edited $ updater original (Action :content |hello) |op-1 1
                picked $ updater edited (Action :pick-case 4) |op-2 2
              assert= | $ :content original
              assert= |hello $ :content picked
              assert= 4 $ :case-idx picked
              assert= 0 $ :case-idx original
            :tags $ #{} :regression :unit
      :ns $ %{} 'NsEntry (:doc |)
        :code $ quote $ ns app.updater
          :require
            respo.cursor :refer $ update-state-tree
            app.schema :refer $ Store Action
    'app.util $ %{} 'FileEntry
      :defs $ {}
        'HighlightHost $ %{} 'CodeEntry (:doc |)
          :code $ quote $ deftrait HighlightHost
            .highlight $ :: 'Fn $ {}
              :args $ [] 'app.util/HighlightHost 'String 'JsObject
              :return 'JsObject
            .register-language! $ :: 'Fn $ {}
              :args $ [] 'app.util/HighlightHost 'String 'Fn
              :return 'Unit
          :examples $ []
          :ffi $ {} (:backend :js) (:kind :external-object)
          :schema $ :: 'Trait
        'HighlightResult $ %{} 'CodeEntry (:doc |)
          :code $ quote $ deftrait HighlightResult (:value 'String)
          :examples $ []
          :ffi $ {} (:backend :js) (:kind :external-object)
          :schema $ :: 'Trait
        'highlighter $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defn highlighter (code lang)
            let
                html $ if (contains? supported-langs lang)
                  let
                      host $ js-cast hljs 'app.util/HighlightHost
                      result $ js-cast
                        host .highlight code $ to-js-data $ {} (:language lang)
                        , 'app.util/HighlightResult
                    .-value result
                  escape-html code
              if (string? html) html $ raise "|Markdown highlighter expected HTML String"
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'String)
            :args $ [] 'String 'String
            :features $ #{} :js-ffi
        'inline $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defmacro inline (path)
            read-file $ str |content/ path
          :examples $ []
          :schema $ :: 'Macro $ {}
            :capabilities $ #{} :fs-read
            :expansion $ :: 'Expr 'String
            :required $ [] 'Syntax
        'register-language! $ %{} 'CodeEntry (:doc |)
          :code $ quote $ defn register-language! (name language)
            let
                registry $ js-cast hljs 'app.util/HighlightHost
              registry .register-language! name language
            , &unit
          :examples $ []
          :schema $ :: 'Fn $ {} (:return 'Unit)
            :args $ [] 'String 'Fn
            :features $ #{} :js-ffi
        'supported-langs $ %{} 'CodeEntry (:doc |)
          :code $ quote $ def supported-langs (&{} |clojure |clojure)
          :examples $ []
          :schema $ :: 'Map 'String 'String
      :ns $ %{} 'NsEntry (:doc |)
        :code $ quote $ ns app.util
          :require (|escape-html :default escape-html) (|highlight.js :default hljs)
