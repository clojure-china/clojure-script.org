
ClojureScript, beginners' home page
----

[clojure-script.org](http://clojure-script.org)

> No offending. I'm just trying to explore if such a page is meaningful for beginners.

### Workflow

The application uses paired Calcit / `@calcit/procs` 0.30.1-alpha.5, with stable
module releases pinned in `deps.cirru`. Node.js 24 and Yarn 4.18.0 are used for
the frontend build. `content/*.cljs` are displayed ClojureScript examples, not
Calcit application source.

This is a 0.30.1 candidate migration, not a completed stable 0.30.1 release:
the stable release currently has no CLI assets. Visitor navigation, the legacy
saved Map format and random showcase selection are preserved. The header logo
uses HTTPS at the same host/path, and site credits now name Calcit and Respo.

```sh
caps --strict --ci
yarn install --immutable
caps verify --toolchain
yarn check
calcit fix --workflow strict --verify
calcit test --require-match
yarn build
```

For development, run `yarn watch` and `yarn serve` in separate terminals. Open
the Vite URL with `?mode=dev` to enable the existing Reel tools.

The application Store, actions, showcases and tool cards have explicit nominal
types. The legacy Respo component-state payload and incoming dispatcher remain
open boundaries, so this is not a claim of zero Dynamic type debt.
Stored data retains the original Map field format and is decoded into Store
when restored. Startup still chooses a random showcase, as before.

CI checks all admitted definitions, the full strict workflow and all application public definitions,
runs the two attached updater/hydration tests, and builds the frontend.
Local acceptance also includes real browser rendering, code highlighting,
showcase clicks, and refresh/hydration without runtime errors.

Main/master deployment uploads frontend resources through
`worktools/cos-upload-action` with built-in public checksum verification. The
original server destination is unchanged. Repository deployment requires
`COS_BUCKET`, `COS_SECRET_ID`, `COS_SECRET_KEY`, and `rsync_private_key`;
no deployment secrets were listed during this migration. PR checks build
without deployment credentials. Generated `js-out/` and `dist/` stay untracked.

### License

MIT
