# Montaigne

This is a prototype.

Montaigne is a document system developed for keeping personal journals and wikis.

## TODO

 - [ ] support for date types
 - [ ] support for people, places, tags types
 - [ ] support for reference types `&Name`( or maybe &Name or maybe `Some Name`)
 - [ ] define dynamic columns
 - [ ] define cli commands
 - [ ] `parse` should only parse, `eval` should eval code blocks
 - [ ] move from collection/entity to entity/children. Collections are basically just entities with few more predefined props

## Dev

Run locally:

```
    clj -m cljs.main --repl-env node -m montaigne.parser PATH_TO_FILE
```

or 

```
clj scripts/repl.clj
```

Compile

```
    clj -m cljs.main -t node -O simple -o main.js -c montaigne.parser
```

and run `node main.js`


## Inspirtaions

 - https://wiki.xxiivv.com/#oscean
 - https://orgmode.org/
 - https://github.com/renerocksai/sublime_zk