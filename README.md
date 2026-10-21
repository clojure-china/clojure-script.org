
ClojureScript, beginners' home page
----

[clojure-script.org](http://clojure-script.org)

> No offending. I'm just trying to explore if such a page is meaningful for beginners.

### Workflow

使用者影响：应用使用精确配套正式 Calcit / `@calcit/procs` **0.30.1**，以及
Respo 0.16.124、Reel 0.6.41、Markdown 0.4.59、UI 0.8.0、js-ffi 0.2.5
（传递 Router 0.8.37），Node.js 24 / Yarn 4.18.0。升级后完整刷新，UI 0.8
基础样式可能改变外观。`content/*.cljs` 是展示用 ClojureScript 示例，不是待迁移的 Calcit 应用源码。

原导航、展示内容、高亮、Map 存档字段与启动时随机展示选择保持不变。
启动先读取并解码原存档，再渲染及注册保存监听；解析或解码失败时停止启动，不先注册
可能覆盖原值的 `beforeunload`。不吞异常、不添加默认存档，也不把正常刷新验收称为
全部损坏存档场景证明。Logo 继续使用原 HTTPS 地址。

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
旧 alpha.5 曾完成真实浏览器渲染、高亮、展示切换与刷新验收；这不替代本轮正式图的
CI 和实际浏览器证据。保留已有两项 updater/hydration 测试，不新增验证脚本或预算。

Yarn 恢复默认 Git 审批与包等待期，只预批准精确 `@calcit/procs@0.30.1`，不再
通配批准任意 Git 仓库或关闭包等待期。

Main/master deployment uploads frontend resources through
`worktools/cos-upload-action` with built-in public checksum verification. The
original server destination is unchanged. Repository deployment requires
`COS_BUCKET`, `COS_SECRET_ID`, `COS_SECRET_KEY`, and `rsync_private_key`;
PR checks build without deployment credentials, using relative paths in the
existing frontend artifact for local acceptance; production uses the original
COS base. No secret availability or production success is inferred from PR CI.
Generated `js-out/` and `dist/` stay untracked.

### License

MIT
