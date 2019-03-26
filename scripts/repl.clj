(require
  '[cljs.repl]
  '[cljs.repl.node])

(cljs.repl/repl
  (cljs.repl.node/repl-env)
  :output-dir "out"
  :install-deps true
  :npm-deps {
    "xregexp" "4.2.0"
    "@thi.ng/hiccup" "3.1.4"
  }
  :cache-analysis true)