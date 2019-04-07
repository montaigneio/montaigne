# Montaigne

This is a _prototype_.

Montaigne is a document system developed for keeping personal journals and wikis.

It comes with document format, format parser and CLI tool to generate web site from markdown document.

## TODO

 - [x] support for date types
 - [x] support for people, tags types
 - [ ] support for places type??
 - [x] multiline support for template properties. Only one works now
 - [ ] document evaluation order
 - [x] define dynamic columns
 - [x] render multiline as markdown
 - [x] define dynamic pages 
 - [ ] support for fully dynamic pages `# tags/{tag}`
 - [ ] define cli commands
 - [ ] `parse` should only parse, `eval` should eval code blocks
 - [ ] move from collection/entity to entity/children. Collections are basically just entities with few more predefined props
 - [ ] when we parse people, places and tags we need to store them as top level index with arrays of pathes to sources. Actually we only need it for places in order to batch them
 - [ ] normalize attributes interface. Some have {:value ""} and some are just plain
 - [ ] make executable
 
## Ideas

 - rename `template` props to `html`
 - color coded logs
 - collect all errors and print at the end
 - support for reference types `&Name`( or maybe &Name or maybe `Some Name`)
 - we can allow users to define validate functions that will be called on parse. Like validate that end date is bigger than start.
 - validate that collection/entity names are globally unique
 - pass args so all hardcoded string can be customized
 - multiple files support
 - markdown inside multiline attributes might also have code that we can evaulate.
 - define ttr function, number of characters function, number of words function into core
 - verify that function defined in the `data` section can be used below for evaluating custom things
 - see if https://github.com/hundredrabbits/Left can be customized with better support for Montaigne documents


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
 - https://github.com/brandur/blackswan