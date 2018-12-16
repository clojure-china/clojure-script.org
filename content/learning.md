
If you want a REPL running on your machine, try [Lumo](http://lumo-cljs.org) or [Planck](http://planck-repl.org).

ClojureScript shares same syntax with Clojure but with different host APIs and environments. To Learn it:

* [ClojureScript Syntax in 15 minutes](https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes)
* [Learn X in Y minutes](https://learnxinyminutes.com/docs/clojure/)
* [ClojureScript: JavaScript Interop](http://www.spacjer.com/blog/2014/09/12/clojurescript-javascript-interop/), [Video](https://lambdaisland.com/episodes/clojurescript-interop)
* [ClojureScript Cheatsheet](http://cljs.info/cheatsheet/)
* [Clojure for the Brave and True: Do Things](https://www.braveclojure.com/do-things/)
* [Understanding Clojure's Persistent Vectors, pt. 1](https://hypirion.com/musings/understanding-persistent-vector-pt-1)
* [ClojureScript 入门指南(Chinese)](http://cljs-book.clj.im/)

### How to compile/build ClojureScript projects?

By following [official Quick Start](https://clojurescript.org/guides/quick-start) you can compile your project with [cljs.main](https://clojurescript.org/guides/quick-start). It's the core library of compiling the language.

However in real projects you will very likely to need hot code swapping and npm integrations. This site is built with [shadow-cljs](http://shadow-cljs.org) along npm modules. Besides shadow-cljs, there are several tools you can choose: