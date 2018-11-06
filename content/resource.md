You may also try [Planck](http://planck-repl.org/), [boot-cljs](https://github.com/boot-clj/boot-cljs) or [lein-cljsbuild](https://github.com/emezeske/lein-cljsbuild/) based on your needs.

### Ecosystem

There are several libraries in ClojureScript that light up the community. They are famous:

* [Reagent](http://reagent-project.github.io/) - Reagent: Minimalistic React for ClojureScript
* [Rum](https://github.com/tonsky/rum) - Simple, decomplected, isomorphic HTML UI library for Clojure and ClojureScript
* [om](https://github.com/omcljs/om) - A powerful interface to React, makes use of its object oriented structures.
* [cljs-devtools](https://github.com/binaryage/cljs-devtools) - Better presentation of ClojureScript values in Chrome Devtools.
* [Datascript](https://github.com/tonsky/datascript) - An immutable in-memory database and Datalog query engine in ClojureScript.
* [re-frame](https://github.com/Day8/re-frame) - A Reagent Framework For Writing SPAs, in Clojurescript.
* [core.async](https://github.com/clojure/core.async) - Facilities for async programming and communication in Clojure.
* [sente](https://github.com/ptaoussanis/sente) - Realtime web comms for Clojure/Script.

### ClojureScript and Clojure

ClojureScript and Clojure share the same syntax but distinguish by `.cljs` extension name. The most different part is the difference in host platforms, like JavaScript is known as single-threaded and restricted by browser APIs.

Libraries of both sides release code on [Clojars](https://clojars.org/) in jar files.

### ClojureScript and npm

With JavaScript InterOp, you may call some JavaScript code from in Clojure syntax. ClojureScript is designed to use features from host platform. You are free to import npm modules in ClojureScript, most of them will work correctly, especially in shadow-cljs and Lumo.

### Immutable Data Structure

Clojure is a functional programming language. It provides the tools to avoid mutable state, provides functions as first-class objects, and emphasizes recursive iteration instead of side-effect based looping. Meanwhile immutable data structure happens to be a great tool React needs in reducing redundant virtual DOM renderings.

### Communities

Join us on:

* http://clojureverse.org/
* http://clojurians.slack.com/
* https://www.reddit.com/r/Clojure/
* https://discord.gg/X6yrEjc

Also cool if you use [Twitter](http://twitter.com/scriptclojure).
