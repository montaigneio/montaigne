# Intro

Montaigne is a superset of Markdown. 
That means that any Montaigne document is valid Markdown document,
however the reverse is not true.

# Data types

Montaigne is set of conventions over Markdown file.
There are several supported data types.

Top level data types are `collections` and `entities`.

E.g. you might want to document list of books you've read.
In this case you define collection `books`.

Collection are definted as Markdown level 1 headers with `# ` prefix.

Here is sample collection definition.

```markdown
## books

some content
```

Entities are items inside collection. So in our example `Amusing Ourselves to Death` will be and `entity` inside the `books` collection. Entities are defined as Markdown level 2 headers with `## ` prefix.

```markdown
# books

## Amusing Ourselves to Death

...

## Animal Farm

...
```

Both `collections` and `entities` support 

 - inline attributes
 - multine attributes
 - computable attributes

## Inline attributes

Let us define new `description` inline attribute for our `books` collection.

 ```markdown
# books

description: List of books I've read

...
```

In the same way we can define attributes for the entity:

 ```markdown
## Amusing Ourselves to Death

title: Amusing Ourselves to Death  
Authors: Neil Postman
ISBN: 9780143036531

...
```

## Multiline attributes

Multiline attributes can be defined using Markdown level 3 headers prefixed with `### `.

This is true for both `collections` and `entities`.

```markdown
# books

description: The list of books I've read

### into

The collection of books I've read over the years.
Starting from 2015.

## Amuzing Ourselves to Death

Authors: Neil Postman  

### review

Inspiring book. Insightful. Inspiring.
Wish I've read it earlier.

```

## Predefined attributes

There are predefined attributes:
 - `name` comes from `collection`/`entity` definition
 - `template` and `@template`. If `template` attribute is defined it will be used when rendering document to HTML. 

## Nested attributes

If you want to define computable attribute that will be applied to all entities you can prefix them with `@` symbol.

You can do inline or multiline nested attributes.

```markdown
# readings


@id: `(montaigne.parser/slug (:name %))`

### @template

\```clojure
    (html [:div "Hello world"])
\```

```

In this case every entity will have `id` and `template` attribute evaluated automatically.


# Core functions

List core functions: slug, duration, distance.

# Evaluation order