# Montaigne

This is a _prototype_.

Montaigne is a document system developed for keeping personal journals and wikis.

It comes with document format, format parser and CLI tool to generate web site from markdown documents.

## TODO

 - [ ] support for date types
 - [x] support for people, places, tags types
 - [ ] support for reference types `&Name`( or maybe &Name or maybe `Some Name`)
 - [ ] define dynamic columns
 - [ ] define cli commands
 - [ ] `parse` should only parse, `eval` should eval code blocks
 - [ ] move from collection/entity to entity/children. Collections are basically just entities with few more predefined props
 - [ ] multiline support for template properties. Only one works now

## Dev

Run locally:

```
    clj -m cljs.main --repl-env node -m montaigne.parser PATH_TO_FILE
```

or 

```
    clj scripts/repl.clj
    
    (require 'montaigne.parser)
    (montaigne.parser/parse "$PATH_TO_FILE")
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
 - https://johno.com/txt