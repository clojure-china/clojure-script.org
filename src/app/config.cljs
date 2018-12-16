
(ns app.config )

(def cdn?
  (cond
    (exists? js/window) false
    (exists? js/process) (= "true" js/process.env.cdn)
    :else false))

(def dev?
  (let [debug? (do ^boolean js/goog.DEBUG)]
    (cond
      (exists? js/window) debug?
      (exists? js/process) (not= "true" js/process.env.release)
      :else true)))

(def site
  {:dev-ui "http://localhost:8100/main.css",
   :release-ui "http://cdn.tiye.me/favored-fonts/main.css",
   :cdn-url "http://cdn.tiye.me/clojure-script-org/",
   :cdn-folder "tiye.me:cdn/clojure-script-org",
   :title "ClojureScript, beginners' home page",
   :icon "http://cdn.tiye.me/logo/cljs.png",
   :storage-key "clojure-script.org",
   :upload-folder "tiye.me:repo/clojure-china/clojure-script.org/"})