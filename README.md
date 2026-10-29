
ClojureScript, beginners' home page
----

[clojure-script.org](http://clojure-script.org)

> No offending. I'm just trying to explore if such a page is meaningful for beginners.

### Calcit 0.32.0 迁移候选：使用者影响

CLI/procs 配对正式 0.32.0，需重新安装完整图、生成 JS 并刷新。UI 模块暂为本地候选，
正式标签整图待验收。导航、展示内容、高亮、启动随机选择和存档恢复顺序保持；
`content/*.cljs` 仍是展示示例，不作为 Calcit 源码改写。

官方转换九处类型写法，32 个定义 schema 不变；原严格门禁、两项业务测试、
零弃用调用/动态方法检查保留，不增加强转或预算。Actions 使用已核对原 SHA 的
精确正式 tag；依赖隔离统一为一小时，COS 内置 verify 和部署路径不变。
本地完整 strict（diagnostics 为空）、全部 307 个定义、原两项测试、零动态方法/
弃用调用、构建及 immutable 安装通过。实际 Logo 展示切换（InterOp→EDN）、
高亮与刷新通过，浏览器 warn/error 为空；独立只读评审无待处理问题。
保留原大 bundle 提示，不调整阈值；未点击复制、访问外部工具或操作真实存档。
坏存档、HMR、移动端未在本轮重验，正式依赖整图和 PR/Linux CI 尚待验证。
下方 0.31 说明为历史记录。

### Workflow

使用者影响：应用使用精确配套正式 Calcit / `@calcit/procs` **0.31.0**，以及
Respo 0.17.0-alpha.4、Reel 0.7.0-alpha.4、Markdown 0.5.0-alpha.4、UI 0.9.0-alpha.4、js-ffi 0.2.7
（传递 Router 0.9.0-alpha.3），Node.js 24 / Yarn 4.18.0、Vite 8.3.3。模块图仍含已发布 alpha；重新安装完整依赖并刷新，UI 0.9
基础样式可能改变外观。`content/*.cljs` 是展示用 ClojureScript 示例，不是待迁移的 Calcit 应用源码。

本次补齐 Respo 的 DispatchOp 入口绑定，保留原 Dynamic→Enum 检查与业务/Reel 解码。
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

本轮迁移旧 Map merge（单参数恒等包装直接去掉）和等价静态 CSS 写法；
事件、watcher、EventHost 及原无声明的 render-app! 按真实生产者补全合同。
`register-language!` 的语言定义参数修正为实际 Fn，而非 JsObject；自定义调用者需传
Highlight.js LanguageFn 函数，不传普通对象。该函数边界仍开放，不伪造已完全闭合的回调类型。
原始 JS 导入在进入这个接口前先绑定为局部值，通过已有 `fn?` 检查确为函数，
保留同一函数值，不以 unsafe 或对象强转掩盖边界。
随机选择、展示示例、原 HUD、存档恢复及全部原测试保持，不增加强转、预算或验证脚本。

CI checks all admitted definitions and all application public definitions through all-defs, plus the full strict workflow,
runs the two attached updater/hydration tests, and builds the frontend.
旧 alpha.5 曾完成真实浏览器渲染、高亮、展示切换与刷新验收；这不替代本轮正式图的
CI 和实际浏览器证据。保留已有两项 updater/hydration 测试，不新增验证脚本或预算。

Yarn 使用默认 Git 审批与包等待期，不保留 procs 预批准、不通配批准任意 Git 仓库或关闭包等待期。

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
