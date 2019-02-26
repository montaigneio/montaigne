# Montaigne

This is a prototype.

Montaigne is a document system developed for keeping personal journals and wikis.

## TODO

 - [ ] support for date types
 - [ ] support for people, places, tags types
 - [ ] support for reference types `&Name`( or maybe &Name or maybe `Some Name`)
 - [ ] support for multiline code
 - [ ] define dynamic columns
 - [ ] eval `templates`
 - [ ] define cli commands

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


## Inspirtaions

 - https://wiki.xxiivv.com/#oscean
 - https://orgmode.org/