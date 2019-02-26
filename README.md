# Montaigne

This is a prototype.

Montaigne is a document system developed for personal journaling and keeping personal wikis.


## Dev

Run locally:

```
    clj -m cljs.main --repl-env node -m montaigne.parser PATH_TO_FILE
```

Compile

```
    clj -m cljs.main -t node -O simple -o main.js -c montaigne.parser
```

and run `node main.js`
