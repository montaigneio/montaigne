# @index

description: Personal site, journal, wiki

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
        [:div {:class "dtc v-mid ph3"}
          [:h1 {:class "mt0 mb0 baskerville fw1 f4"} "Anton Podviaznikov"]
          [:h2 {:class "gray mt1 mb0 fw4 f6"} "observer; no answers, only questions"]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f3.athelas (:name %)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
          [:li.mb3
            [:a.link.f6.b.mb1 {:href "/readings"} "Readings"]]
          [:li.mb3  
            [:a.link.f6.b.mb1 {:href "/trips"} "Trips"]]
          [:li.mb3  
            [:a.link.f6.b.mb1 {:href "/activities"} "Activities"]]
          [:li.mb3  
            [:a.link.f6.b.mb1 {:href "/quotes"} "Quotes"]]
          [:li.mb3  
            [:a.link.f6.b.mb1 {:href "/now"} "Now"]]
            ]
      ]
    ]])
```


# data

author: `"Anton Podviaznikov"`
airports: `(:airports (montaigne.fns/http-get-json "https://cdn.rawgit.com/konsalex/Airport-Autocomplete-JS/3dbde72e/src/airports.json"))`


# readings

description: Books I've read

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
        [:div {:class "dtc v-mid ph3"}
          [:h1 {:class "mt0 mb0 baskerville fw1 f4"} "Anton Podviaznikov"]
          [:h2 {:class "gray mt1 mb0 fw4 f6"} "observer; no answers, only questions"]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f3.athelas (:name %)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (->> entity :id :value)} 
                [:span (->> entity :title :value)]
                [:span " by "]
                [:span (clojure.string/join ", " (->> entity :authors :value))]
              ]
              [:div.mt1.mb0.mh0
                [:small.f7.ml0.mb0.mr1.gray (->> entity :stars :value)]
                (if (not (nil? (->> entity :days :value)))
                  [:small.f7.ml0.mb0.mr1.gray (->> entity :days :value) " days"])
                (if (not (nil? (->> entity :pages :value)))
                  [:small.f7.ml0.mb0.mr1.gray (->> entity :pages :value) " pages"])
              ]
            ])
          (reverse %))]
      ]
    ]])
```

@id: `(montaigne.fns/slug (:name %))`  
@readings.duration: `(montaigne.fns/duration-in-days (->> % :started) (->> % :finished))`
@readings.year: `(montaigne.fns/get-year (->> % :finished))`
@stars: `(apply str (repeat (->> % :rating :value) "★&nbsp;"))`
@started: `(:started (last (->> % :readings :value)))`  
@finished: `(:finished (last (->> % :readings :value)))`  
@days: `(montaigne.fns/duration-in-days (->> % :started :value) (->> % :finished :value))`  

### @template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f4.athelas (->> % :title :value)]
        [:header
          [:div.pv2
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Authors:"]
              [:dd {:class "dib ml1"} (clojure.string/join ", " (->> % :authors :value))]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "ISBN:"]
              [:dd {:class "dib ml1"} (->> % :isbn :value)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Dates:"]
              [:dd {:class "dib ml1"} (str "from "(->> % :readings :value first :started) " to " (->> % :readings :value first :finished))]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Year:"]
              [:dd {:class "dib ml1"} (->> % :year :value)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Pages:"]
              [:dd {:class "dib ml1"} (->> % :pages :value)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Rating:"]
              [:dd {:class "dib ml1"} (->> % :rating :value)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Review date:"]
              [:dd {:class "dib ml1"} (->> % :reviewdate :value)]
            ]
          ]]
        [:section
          [:h2.f6 "readings"]
          [:article.lh-copy.measure
            [:table {:class "f6 w-100 mw8 center" :cellspacing "0"}
              [:thead
                [:tr
                  (map (fn [column]
                    [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} column])
                (->> % :readings :columns))]
              ]
              [:tbody {:class "lh-copy"}
                (map 
                  (fn [row]
                    [:tr
                      (map 
                        (fn [column-name]
                          [:td (get row (keyword column-name))]
                        )
                      (->> % :readings :columns)
                      )
                    ])
                  (->> % :readings :value)
                )
              ]
            ]
          ]]
        (if (not (nil? (->> % :review :value)))
          [:section
            [:h2.f6 "review"]
            [:article.lh-copy.measure
              (->> % :review :value)]])
      ]
    ]])
```

## Zero to One

title: Zero to One  
subtitle: Notes on Startups, or How to Build the Future  
authors: @{Peter Thiel, Blake Masters}
isnb: 9780804139298  
year: 2014  
pages: 195  
rating: 4
tags: #{startups}
reviewdate: 2019-04-11

### readings

started    | finished   | locations
---------- | ---------- | -------------
2014-09-23 | 2014-10-01 | San Francisco

### review

Peter Thiel of course in controversial figure but the book is great. The best part for me was to learn about optimism of people in the 60s and how that changed few generations later. 


## A Guide to the Good Life

title: A Guide to the Good Life  
subtitle: The Ancient Art of Stoic Joy  
authors: @{William Irvine}
isnb: 9780195374612  
year: 2008  
pages: 326  
rating: 4
tags: #{stoicism, wisdwom}
reviewdate: 2019-04-11

### readings

started    | finished   | locations
---------- | ---------- | -------------
2014-11-10 | 2014-12-01 | San Francisco

### review

Great book. Recommended by a friend. Enoyed it very much. It was my intro into stoicism.


## Gory govoriat

title: Гори говорять  
authors: @{Улас Самчук}  
isbn: 9667112004  
year: 1934  
pages: 272
rating: 4  
tags: #{Ukraine}  
reviewdate: 2019-04-11

### readings

started    | finished   | locations
---------- | ---------- | -------------
2015-01-01 | 2015-01-15 | San Francisco

### review

One of the best Ukrainian novels of XX century. I like Samchuk as an author.


## Volyn

title: Волинь  
authors: @{Улас Самчук}  
isbn: 9667631834  
year: 1937  
pages: 632
rating: 4  
tags: #{Ukraine}  
reviewdate: 2019-04-11

### readings

started    | finished   | locations
---------- | ---------- | -------------
2015-01-15 | 2015-01-22 | San Francisco

### review

My second novel from Samchuk. Very engaging.


## Istanbul by Pamuk

title: Istanbul  
subtitle: Memories and the City  
authors: @{Orhan Pamuk}  
isbn: 978-0-307-38648-9  
year: 2003  
pages: 356
rating: 4  
tags: #{Istanbul, Turkey, memoir}  
reviewdate: 2017-03-04

### readings

started    | finished   | locations
---------- | ---------- | -------------
2015-08-02 | 2015-10-04 | San Francisco

### review

Great book that helps to understand tranformation for Istanbul and Turkey through the XX century. It's interesting in the context of continuous globalization and westernization of the developing countries around the world.


## Dear Professor Einstein

title: Dear Professor Einstein  
subtitle: Albert Einstein's Letters to and from Children  
authors: @{Albert Einstein}  
isbn: 1-59102-015-8  
year: 2002  
pages: 200
rating: 4  
tags: #{letters, children}  
reviewdate: 2017-03-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2015-12-15 | 2016-01-01 | San Francisco

### review

Good book that shows how humane Einstein was.


## Sapiens

title: Sapiens
subtitle: A Brief History of Humankind    
authors: @{Yuval Noah Harari}  
asin: B00ICN066A  
year: 2015  
pages: 415  
rating: 5  
tags: #{philosophy, wisdom, religion}  
reviewdate: 2017-02-28

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-02-01 | 2016-02-23 | San Francisco 

### review

With this book I finally got hooked into non-fiction.


## Where Does The World Goes

title: Куди рухається світ
authors: @{Ярослав Грицак}  
isbn: 9789664653999  
year: 2015  
pages: 192  
rating: 3  
tags: #{history, sociedty, modernity}  
reviewdate: 2017-02-28

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-02-23 | 2016-02-23 | San Francisco 

### review

One day read. Good book for Ukrainian market but not a break through global-wise.


## Siddhartha

title: Siddhartha  
authors: @{Hermann Hesse}  
isbn: 978-1-62793-640-8  
year: 1922  
pages: 136  
rating: 5  
tags: #{philosophy, wisdom, religion}  
reviewdate: 2019-04-10

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-03-11 | 2016-03-13 | San Francisco 

### review

Great book that I'm planning to re-read from time to time.


## Bruce Lee Striking Thoughts

title: Bruce Lee Striking Thoughts  
subtitle: Bruce Lee's Wisdom for Daily Living  
authors: @{Bruce Lee}  
isbn: 978-1-4629-1792-1  
year: 1973  
pages: 250  
rating: 5  
tags: #{wisdom, diaries}
reviewdate: 2017-03-05

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-03-14 | 2016-03-19 | San Francisco 

### review

Outstanding book with a lot of wisdowm and personal discoveries. 
Bruce Lee connects East and West.

Reminds me of Meditations by Marcus Aurelius. 
It's both a diary and collecion of insights that can be open and read any day.


## The Stranger

title: The Stranger  
authors: @{Albert Camus}  
isbn: 978-1-4629-1792-1  
year: 1942  
pages: 123  
rating: 3  
tags: #{novel, meditations}
reviewdate: 2019-04-10

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-03-19 | 2016-03-21 | San Francisco 

### review

Very unsusual book on unsual topic. I guess reminds other works from Camus.


## The Prophet

title: The Prophet  
authors: @{Kahlil Gibran}  
isbn: 9780001000391  
year: 1923  
pages: 127  
rating: 5  
tags: #{wisdom}
reviewdate: 2019-04-10

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-03-22 | 2016-03-22 | San Francisco 

### review

Beautiful book. The one to read multiple times during the life.


## Bertrand Russell's Best

title: Bertrand Russell's Best  
authors: @{Bertrand Russell}  
isbn: 978-0-415-47358-3  
year: 1958
pages: 128  
rating: 5
tags: #{wisdom, philosophy}
reviewdate: 2019-04-09

### readings

started    | finished   | locations
---------- | ---------- | ------------- 
2016-05-19 | 2016-05-30 | San Francisco 

### review

I've only discovered Bertrand Russell in 2016.
He immediatley became and inspiration and a role model.


## The Course of Love

title: The Course of Love  
authors: @{Alain de Botton}  
isbn: 978-1-5011-3425-8  
year: 2016
pages: 226  
rating: 4
tags: #{relationships, love, adultery}
reviewdate: 2019-04-09

### readings

started    | finished   | locations
---------- | ---------- | ------------- 
2016-06-20 | 2016-06-28 | San Francisco 

### review

Novel about modern relationships, with all the real challenges and every day issues.


## Reinventing Organizations

title: Reinventing Organizations  
subtitle: A Guide to Creating Organizations Inspired by the Next Stage of Human Consciousness
authors: @{Frederic Laloux}  
isbn: 978-2-960133-53-0  
year: 2014
pages: 378  
rating: 4
tags: #{organizations}
reviewdate: 2017-03-04

### readings

started    | finished   | locations
---------- | ---------- | ------------- 
2016-06-14 | 2016-08-29 | San Francisco 

### review

Great book that briefly explains history of organizations and shows us how organizations of the future would look like.

It describes requirenments for the organizations of the future and some practical advices how to set the up.

However, I found book a bit longer than needed. It had double focus: both theory and a lot of practical case studies, and that seemed too much for me.


## Ill Fares the Land

title: Ill Fares the Land  
authors: @{Tony Judt}  
isbn: 9781101223703  
year: 2010  
pages: 237  
rating: 5  
tags: #{history}  
reviewdate: 2017-03-03

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-08-07 | 2016-08-13 | San Francisco 

### review

Very good book with some recipes for society. Will going to reread it in the future.

Also I really liked the amount of quotes in this book.


## The Power of Habit

title: The Power of Habit  
subtitle: Why We Do What We Do in Life and Business  
authors: @{Charles Duhigg}  
isbn: 978-0-679-60385-6  
year: 212  
pages: 286  
rating: 3  
tags: #{habit}
reviewdate: 2017-03-04

### readings

started    | finished   | locations
---------- | ---------- | ------------- 
2016-09-10 | 2016-09-14 | San Francisco 

### review

Book describes habits of individuals, organizations and societies. 
It was interesting to read, but at the end I didn't get many insights that would remain with me for a long time.

The book easily could have been shorter.


## The Black Swan

title: The Black Swan  
subtitle: The Impact of the Highly Improbable  
authors: @{Nassim Nicholas Taleb}
isbn: 978-0-679-60418-1  
year: 2010  
pages: 400
rating: 3
tags: #{statistics}
reviewdate: 2017-03-05

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-03-06 | 2016-09-06 | San Francisco 

### review

Nassim Taleb seems like a very smart person but:

- book was exremely long, especially to present one main idea
- the style is quite arrogant, which I didn't enjoy at all

I liked that he quoted a lot of people in his book, but because of 2 reasons stated above I'm not looking forward to read his other books. 
It was very painful experience and took me half a year to finish it.


## Chaos Monkeys

title: Chaos Monkeys  
subtitle: Obscene Fortune and Random Failure in Silicon Valley  
authors: @{Antonio García Martínez}
isbn: 978-0-06-245819-3  
year: 2016  
pages: 533  
rating: 3  
tags: #{startups, Silicon Valley}
reviewdate: 2017-03-05  

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-08-01 | 2016-08-21 | San Francisco 

### review

Good read if you want to have insight about life of startup founder, product manager/engineer in the Silicon Valley. Has a lot of pretty intimate details and a lot of humor (but not the type I like).

The book itself is too long. Easily can be done in 10 blog posts or shorter stories.


## Brave New World

title: Brave New World  
authors: @{Aldous Huxley}  
isbn: 978-0-06-085052-4  
year: 1932  
pages: 152  
rating: 5  
tags: #{dystopia, anti-utopia}
reviewdate: 2017-03-02

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-09-17 | 2016-09-27 | San Francisco 

### review

Great distopia written in early XX century. Suprisingly the world in the last 30 years become very close to the one described by Huxley.

The best book that explains Brave New World is Amusing Ourlselves to Death by Neil Postman.


## Fluent Forever

title: Fluent Forever
subtitle: How to Learn Any Language Fast and Never Forget It
authors: @{Gabriel Wyner}
isbn: 978-0-385-34810-2  
year: 2014  
pages: 326
rating: 4
tags: #{languages, learning}
reviewdate: 2017-03-05

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-09-01 | 2016-09-03 | Hong Kong

### review

Good book that lays out one of the methods to te language learning.

Both style and method made sense to me when I was reading it.

Also, it was first time that I properly read and was introduced to Spaced repetition.
I wish I knew about this system when I was in the university.


## Building a Bridge to the 18th Century

title: Building a Bridge to the 18th Century  
subtitle: How the Past Can Improve Our Future  
authors: @{Neil Postman}  
isbn: 978-0-375-70127-6  
year: 1999  
pages: 224  
rating: 5  
tags: #{philosophy, history, media, education}
reviewdate: 2016-10-25

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-10-19 | 2016-10-24 | San Francisco

### review

Manifest of humanity for humanity from Neil Postman. He shares his few big with ideas us.

The first one is the idea of introspection and reflection; that is the idea of being mindful when evaluating life, decisions, tools, words, behaviors of ours and others.

The second idea is the idea of finding the new narrative; that is a meta-purpose to our lives and the existence of humankind. 
He hints that idea of continuity of human race, which can be achieved through understanding of oneness between not only human beings but between humans and Nature, may be the answer. 
Idea of continuity should transcend one's life (everyone should become \"smaller\") and time (we should be fill continuity with the greats of the past and future).

The third big idea is the idea of importance of history. Postman demonstrates by example how history might have not only origins for our problems but also ideas how to solve them. History is a tool that scales down time, that is making us smaller, and allows us to see bigger picture and have bigger ideas.

The fourth big idea and hope from Postman is to show us that we can change things. He shows that ideas were created and implemented by real people. 
He suggest that we shouldn't blindly follow the flow of history, because there is no such flow. We should alter it (at the capacities available to humans) based on the values and idea we believe in.

The final idea is the idea of choosing correct tools and frameworks. He suggest Scientific method to us. In his book he tries to provide us with good tools that help us evaluate everything else. 
Neil Postman's book is a work of ultimate importance in the post-modern world. 
The absence of the Narrative, forgetfulness of smaller good ideas and lost of ideals causing some lack of belief in the bright future. 
Postman, as a true philosophe, tries to use his intellect, following the tradition of Voltaire, Rousseau, Locke, Hume, Jefferson, and others, to give us practical examples how we can change things for the better.


## Amusing Ourselves to Death

title: Amusing Ourselves to Death  
subtitle: Public Discourse in the Age of Show Business  
authors: @{Neil Postman}  
isbn: 9780143036531  
year: 1985  
pages: 208  
rating: 5  
tags: #{media, entertainment, television, technology}  
reviewdate: 2017-02-25

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-10-03 | 2016-10-11 | San Francisco

### review

Outstanding analysis of American culture of 1980s dominated by entertainment.

Makes a lot of sense to me since I witnessed myself similar changes to the culture growing up in Ukraine during the 1990s and 2000s.

This book is even more actual now in the age of Internet, mobile phones, social networks, instant deliveries etc.


## What I Talk About When I Talk About Running

title: What I Talk About When I Talk About Running  
authors: @{Haruki Murakami}  
isbn: 978-0-307-38983-1  
year: 2008  
pages: 194  
rating: 4  
tags: @{running}  
reviewdate: 2016-10-17

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-10-17 | 2016-10-17 | San Francisco

### review

Very personal memoir by Haruki Murakami that covers 30 something years of his life: after he became a writer and started to run.

Few interesting things that I noticed:

 - Murakami shows his struggles with getting older.
 Aging is such an important topic that touches everyone often not talked about. 
 Seeing your result getting worth with age is a good way to be aware of time in life. 
 It keeps you focused on the crucial thing and helped you appreciate life.
 
 - I appreciated how he put the duration of human life in a better perspective.
 He became a writer after being 33 years old. He sold his bar, quit smoking, changed his diet, started writing and running. 
 The best part of the life for him began after he became true to himself in the mid-30s.
 
 - Murakami emphasizes how for him life is the process of knowing himself better.
 Knowing what he likes, what is crucial for him. 
 His focus is on internal matters and not external things. 
 He decides to spend as much time with people he likes and avoid people he dislikes. 
 He is not competitive because for him competitiveness is about looking outward not inward.

 - Another valuable lesson: everything worthy in life requires a big effort.
 He shows how setting up routine and rules for himself was paramount.


## Creative Schools

title: Creative Schools  
subtitle: The Grassroots Revolution That's Transforming Education
authors: #{Ken Robinson, Lou Aronica}  
isbn: 978-0-14-31080601
year: 2016
pages: 320  
rating: 5
tags: #{education}
reviewdate: 2016-11-23

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-11-17 | 2016-11-24 | San Francisco

### review

Reading this book I deeply felt that it is not about the revolution in education.
It's a book about any complex adaptive human system. It's a book about how to _change_ such systems. We live in the age when it's easy to see that a lot of systems surrounding us are broken in some ways and often do more harm than good. We see that such systems need change.

Broken systems vary depending on the country you live in but quite often they include not only education but health care, media, government, judiciary etc.

It's a nice to have a framework how to think about systems and change. How to see problems with existing ones, how to came up (or find) with better ideas for the future and how to introduce changes in the different levels.


## Native Realm

title: Native Realm  
subtitle: A Search for Self-Definition  
authors: #{Czesław Miłosz}
isbn: 978-0-374-52830-0  
year: 1968
pages: 300
rating: 5
tags: #{Eastern Europe, wisdom, philosophy}
reviewdate: 2016-12-03

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-11-24 | 2016-11-26 | San Francisco

### review

Fascinating book because it's both autobiography, but also book on philosophy.

Miłosz shares his story of life and formation of his personality and outlook. His account is extremely valuable since he was born in the Polish family in the Lithuanian part of the Russian empire, saw WWI, grew up during independent Poland, experienced WWII as a poet in Nazi-occupied Warsaw. He spent a lot of time in the West (eventually migrating to the US) and in the communist Poland.

His life is a great example how a human being can live in different systems (imperialism, fascism, communism and capitalism) but still hold to the sacred values. In fact, he shows us that is possible to transcend horrors of our human-made systems.

His story is both about losing and finding faith in humanity. His philosophy is about searching of new explanations for the world and good, seeking new narratives that would reconcile scientific outlook and world religions.

Being Eastern European and living through different systems and cultures his shares his insights on Russia, America, and France. He gives glimpses of hope to other Eastern European and I believe any developing country. In his mind, historical tragedy and past events are not the final verdicts. Those are merely challenges that help us grow because it's only in the time of turbulence and change that you can really grow.


## Revolution by Russell Brand

title: Revolution
authors: #{Russell Brand}
isbn: 978-1-101-88291-7  
year: 2014
pages: 320
rating: 4
tags: #{revolution, wisdom}
reviewdate: 2016-12-08

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-11-27 | 2016-12-04 | San Francisco

### review

Serious book written in the funny style. Russell Brand basically shares with us things he believes in, mostly concerning how the modern world should look like and how we can get there.

His book is a good account of meaningful quotes (and their simple interpretations) of wise people of our time and from the past. But it is also an account of ideas and implementation policies that are available right now. He collected those ideas from different spheres of life - politics, economy, ecology, culture, religion - and presented them in easy to grasp form.

What this book does is show us that better future is possible and that so many people working on it and believe in it and that it will happen soon.


## Conscientious Objections

title: Conscientious Objections  
subtitle: Stirring Up Trouble About Language, Technology and Education  
authors: @{Neil Postman}  
isbn: 978-0-679-73421-5  
year: 1988  
pages: 201  
rating: 5  
tags: @{technology, medium}  
reviewdate: 2016-12-25

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-12-04 | 2016-12-10 | San Francisco

### review

Collection of essays on different topics from education, childhood, politics, technology, television etc. 
Postman, as always, provides insights into our culture (mostly American) and suggests some practical ideas how to prevent negative ongoing trends. As always he uses wisdom of sages, thinkers, writers, and philosophers from the past to help us understand current social issues.

His essays and observations draw a picture of quite sad future. A lot of this predications are already fulfilled, so Postman is unfortunately was very accurate in them which he poses as warnings for us.

However, what I liked about this book is that he tries to stay optimistic. He reminds us that civilized society is very vulnerable and we should pay attention to dangers. And he tries to provide us with a mindset how to do just that.


## The Animal Farm

title: The Animal Farm  
authors: @{George Orwell}  
isbn: 978-1-943138-42-5  
year: 1945  
pages: 122
rating: 5  
tags: #{socialism, society, revolution}
reviewdate: 2016-12-26

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-12-10 | 2016-12-10 | San Francisco

### review

A comprehensive history of Russian revolution in a short, simple book that anyone can understand. 
I think in this fact lies the beauty of Orwell’s work. 
People of all ages can appreciate and visualize even the most complex social changes.

It seems that Orwell did describe Russian revolution very closely with fall of the capitalist empire, communist revolution, Marx, collectivization, fight between Trotsky and Stalin, dictatorship and full counter-revolution and come back to the similar state as it was before 1917 but with a different name.

Also, it seems, that even if it accounts for the Russian revolution, the pattern is the same for all them.
This book is a reminder to us how events usually unfold and what happens at the end. 
And what kind of people typically end-up on top in any system regarding of the title.


## Myths to Live By

title: Myths to Live By  
authors: #{Joseph Campbell}  
isbn: 978-0-14-019461-6
year: 1972
pages: 276
rating: 5
tags: #{myths, narrative, religion}
reviewdate: 2017-02-20

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-12-10 | 2016-12-22 | San Francisco

### review

Great collection of essays on various topics united by the idea of myths.

Joseph Campbell explains why we need myths, what role they played in different cultures from East to West and why we would need new ones for the future.


## Letting Go by Hawkins

title: Letting Go  
subtitle: The Pathway of Surrender
authors: #{David Hawkins}
isbn: 978-1-4019-4501-5
year: 2013
pages: 368
rating: 5
tags: #{psychology, Self}
reviewdate: 2017-02-19  

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-01-16 | 2017-02-01 | San Francisco

### review

The part that I liked the most is the description of stages of spiritual development and their connection to chakras though corresponding energy levels.


## Homage to Catalonia

title: Homage to Catalonia  
authors: @{George Orwell}  
isbn: 978-1-84902-597-3  
year: 1938 
pages: 175 
rating: 3  
tags: #{socialism, propaganda}
reviewdate: 2017-02-25

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-12-23 | 2017-02-25 | San Francisco

### review

Personal account of Spanish war by George Orwell.

Couple of insights:

- Stalinist tactics used in Spain were as terrible as in USSR
- Press is extremely important in influencing events

What was interesting is that Orwell few times pointed out that his account is not fully objective and that reader should not believe his words without verification. 
This is great example of professionalism. 
Orwell recognizes both his subjectivity but also his duty to the public. 
Seems like not many people back then and now do this.

Also, I really like that Orwell was optimistic even in the end. 
He was wounded, needed to leave country, but at the end he still believed in human nature and better future.


## To the Castle and Back

title: To the Castle and Back  
authors: @{Václav Havel}  
isbn: 978-0-307-38845-2  
year: 2006  
pages: 383  
rating: 5  
tags: @{Eastern Europe, politics}  
reviewdate: 2017-05-28

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-02-25 | 2017-03-09 | San Francisco

### review

Havel is a role model.
He shows us what a public figure, a politician and a citizen can be.
He is definitely not afraid to think deeper and to express himself.
He is not afraid to formulate unpopular opinions.
He tries to understand pluses and minuses of each system.
E.x. he is for continous political integration, but he thinks globalization is a problem.
He recognized the pitfalls of both Soviet regime and consumerism society that both causes normalization and standartization of people.

It was interesting for me to read his book for several reasons: 

- to learn his story
- to learn about his thoughts
- to take a look at his diary

The diary of the President was an interesting read on itself.
It contained his agenda for a lot of days and it showed him as a real simple very likable human, which is quite opposite of the politicians of the day.

Havel wanted to be a role model.
He wanted to remind his fellow citizens of better and bigger ideals, he wanted to inspire them to improve.
Politicians of today mostly move in opposite direction.
They just represent the worst parts of our society and our people.
If society is corrupt, politicians are corrupt.
If society is uneducated, politicians are profane.


## The Medium is the Massage

title: The Medium is the Massage  
subtitle: An Inventory of Effects 
authors: @{Marshall McLuhan, Quentin Fiore}
year: 1967  
isbn: 978-1-58423-070-0  
pages: 159  
rating: 4  
tags: #{media} 
reviewdate: 2017-04-17

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-03-16 | 2017-03-18 | San Francisco

### review

Tiny book which is basically collection of quotes and small passages about mediums.
Some insights are quite good, but majority are very difficult to decompress and unbundle.


## Buddhism The Religion of No-Religion

title: Buddhism The Religion of No-Religion  
subtitle: The Edited Transcripts  
authors: @{Alan Watts}  
isbn: 978-0-8048-3203-8
year: 1996
pages: 98  
rating: 4  
tags: #{Buddhism, religion, Hinduism}
reviewdate: 2017-04-17

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-03-27 | 2017-04-01 | Gili Trawangan, Indonesia

### review

Introduction to Buddhism and especially Zen Buddhism. Very easy explanation of the core concepts.

I really like the idea that Buddhism is Hinduism for export, that is Hinduism that stripped from culture and can be used as religion by people of different cultures.


## The Magic Mountain

title: The Magic Mountain
authors: @{Thomas Mann}
isbn: 978-0-679-7728703
year: 1924  
pages: 700  
rating: 4 
tags: #{society, philosophy, fiction}
reviewdate: 2017-05-27

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-03-25 | 2017-04-14 | Gili, Indonesia and San Francisco, USA

### review

First book of Thomas Mann for me.

I discovered for myself new type of novel reading this book.
The novel is fictional and tells us a story. 
But what is more interesting is that Thomas Mann exists in the book himself.
He comments on events and characters, he expresses his own sibjective judgements in parallel to objectively narrating the story. 
Author spends a lot of time on side topics: like philosophy, chemistry etc.

The bigger topic that crosses the whole book is Mann's philosophical reflections on the topic if time and space.

Magic Mountain reminded me of The Dead Souls by Gogol.
It is a story with a characters that were mostly unlikable to me, especially at the beginning.
Those characters seem to be not the roel models, they had problems and some not impressive features. However, book had a lot of different characters, and maybe it was the Mann's point to show us, that no one is ideal in reality.


## The Little Book of Hygge

title: The Little Book of Hygge  
subtitle: The Danish Way to Live Well  
authors: @{Meik Wiking}  
isbn: 978-0-241-28391-2  
year: 2016  
pages: 288   
rating: 3  
tags: #{Denmark, lifestyle}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-04-13 | 2017-04-21 | San Francisco

### review

Not much to say about this one. Too simple.


## Technopoly

title: Technopoly  
subtitle: The Surrender of Culture to Technology  
authors: @{Neil Postman}
isbn: 978-0-679-74540-2  
year: 1992  
pages: 222  
rating: 5
tags: #{techonlogy, culture}
reviewdate: 2019-04-09

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-04-25 | 2017-04-30 | San Francisco

### review

One of the books that I'll want to reread. Very relevant nowadays.


## The Book by Alan Watts

title: The Book  
subtitle: On the Taboo Against Knowing Who You Are
authors: @{Alan Watts}
isbn: 978-0-679-72300-4  
year: 1966  
pages: 160  
rating: 4
tags: #{Self, religion}
reviewdate: 2019-04-09

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-05-09 | 2017-05-13 | San Francisco

### review

One of the great books from Alan Watts. Very human and approachable style.


## The Art of Loving

title: The Art of Loving  
authors: @{Erich Fromm}
isbn: 978-0-06-112973-5  
year: 1956  
pages: 124  
rating: 5
tags: #{love, relationship, parents}
reviewdate: 2019-04-09

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-05-09 | 2017-05-13 | San Francisco

### review

Fromm explains different types of love in easy to understand language.
I loved this book when I've read it. So small but very dense.


## The Little Prince

title: The Little Prince  
authors: @{Antoine de Saint-Exupéry}  
isbn: 978-0-15-601219-5  
year: 1943  
pages: 86
rating: 5  
tags: #{adulthood, life, wisdom}
reviewdate: 2017-05-28

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-05-13 | 2017-05-13 | San Francisco

### review

One of the most beautiful and simple books about life, love, death; 
about being a child and being an adult; about friendship, people and happiness.

When I was in 10th grade at school, I remember literature teacher was very enthusiastic about this book. 
She was telling us about it all the time. 
I didn't find it interesting back then. 
I didn't understand the story about little prince, talking foxes, flowers and snakes. 
Form didn't appeal to me and I probably had nothing to learn from it at that time. 
This time was different. I would recommend this book to everyone. 
It's a pure beauty. Just in few pages it shows what is important in life and what is not.


## The Unbearable Lightness of Being

title: The Unbearable Lightness of Being  
authors: @{Milan Kundera}  
isbn: 978-0-06-093213-8  
year: 1984  
pages: 314
rating: 5  
tags: #{fiction, relationship, sex, adultdery}
reviewdate: 2019-04-09

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-05-16 | 2017-05-26 | San Francisco

### review

First time I've read Kundera. Very engaging read. Storyline is interesting.
I enjoyed historical context too. Questions raised in the book were hard.
I also liked Kundera's philosophical meditations, that reminded me of Thomas Mann style.


## China's Disruptors

title: China's Disruptors  
subtitle: How Alibaba, Xiaomi, Tencent and Others Companies are Changing the Rules of Business  
authors: @{Edward Tse}  
isbn: 978-0-241-24039-7  
year: 2015  
pages: 256  
rating: 4  
tags: #{China, technology, business}  
reviewdate: 2017-06-08

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2017-04-15 | 2017-04-24 | San Francisco 

### review

Great book on the technological developments in China. 
Learned a lot about Chinese tech companies, the way they started and the way they do business.


## On Tyranny

title: On Tyranny  
subtitle: Twenty Lessons from the Twentieth Century  
authors: @{Tymothy Snyder}  
isbn: 978-0-8041-9011-4  
year: 2017  
pages: 126  
rating: 2  
tags: #{politics}  
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-04-30 | 2017-04-30 | San Francisco

### review

Too simple and too tied to the things that were going on in the USA during 2016.
My expectations were much higher, especially given authors background as a historian.


## Four Futures

title: Four Futures  
subtitle: Life After Capitalism 
authors: @{Peter Frase}  
isbn: 978-1-78168-813-7  
year: 2016
pages: 150  
rating: 4
tags: #{future, capitalism, utopia}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | ------------- 
2017-06-14 | 2017-06-18 | San Francisco 

### review

Suprisingly good book. Clever idea to outline several possible future scenarios. 
Mad Max one was the one I didn't like for sure. This book made me watch a movie.


## Man's Search for Meaning

title: Man's Search for Meaning  
authors: @{Victor Frankl}
isbn: 978-0-8070-1429-5  
year: 1946  
pages: 165  
rating: 5
tags: #{meaning, life, purpose, senses}
reviewdate: 2019-04-09

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-07-04 | 2017-07-22 | Hong Kong, San Francisco

### review

Must read. Classic.


## Mating in Captivity

title: Mating in Captivity  
subtitle: Unlocking Erotic Intelligence 
authors: @{Esther Perel}  
isbn: 978-0-06-075364-1  
year: 2007
pages: 242  
rating: 4
tags: #{relationships, sex}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | ------------- 
2017-07-22 | 2017-09-30 | San Francisco 

### review

I like Perel's talks a lot. But not sure I really like style of the book. 
I think some of the ideas I didn't agree with. 
Also it took me a while to finish this book. Ironically I was going through breakup while reading this book.


## Love Style Life

title: Love Style Life  
authors: @{Garance Doré}  
isbn: 978-0-8129-9637-1  
year: 2015
pages: 256  
rating: 4
tags: #{biography}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | ------------- 
2017-09-30 | 2017-10-05 | San Francisco 

### review

New genre for me. Read a book about something I didn't pay attention too for a long time now. Book about fashion, style and juts life from famous French fashion person. Easy entertaing read. I like the most parts of her career progress and some remarks on relationships.


## The Disappearence of Childhood

title: The Disappearence of Childhood  
authors: @{Neil Postman}
isbn: 0-679-75166-1  
year: 1994
pages: 176  
rating: 5  
tags: #{childhood}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-11-18 | 2017-11-27 | San Francisco

### review

Great book on history of chilhood in general and how the notion of children changes with the environment.


## The Art of Learning

title: The Art of Learning  
subtitle: An Inner Journey to Optimal Performance  
authors: @{Josh Waitzkin}  
isbn: 978-0-7432-7745-7  
year: 2008  
pages: 264  
rating: 4  
tags: #{autobiography, chess}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-12-03 | 2017-12-24 | San Francisco

### review

I like it because it's an autobiography with a lot of personal details.
Was interesting to read on career in chess and martial arts.


## Economics User's Guide

title: Economics  
subtitle: The User's Guide
authors: @{Ha-Joon Chang}
isbn: 978-1-62040-812-4 
year: 2014
pages: 364  
rating: 5  
tags: #{economics}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-12-24 | 2018-01-07 | San Francisco

### review

The type of book I love. Good intro into economics.
Explains what economics are in the simple language and explains all popular theories starting from Adam Smith.


## The Antidote

title: The Antidote  
subtitle: Happiness for People Who Can't Stand Positive Thinking
authors: @{Oliver Burkeman}
isbn: 978-0-86547-801-5  
year: 2012
pages: 236  
rating: 4  
tags: #{happiness, culture}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2018-01-08 | 2018-01-13 | San Francisco

### review

Good book. The best part was to learn Dia de los Muertos.
A lot of other ideas I already was exsposed throuth The School of Life and stoicism.


## Seven Brief Lessons on Physics

title: Seven Brief Lessons on Physics  
authors: @{Carlo Rovelli}
isbn: 978-0-399-18441-3  
year: 2016
pages: 86  
rating: 4  
tags: #{science}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2018-01-14 | 2018-01-14 | San Francisco

### review

Tiny pocket book. I love those on topics I don't know much about. 
But unfortunately this type of book didn't teach me much. 
One year after reading it I can't recall anything.


## A Little History of Philosophy

title: A Little History of Philosophy  
authors: @{Nigel Warburton}
isbn: 978-0-300-15208-1  
year: 2012
pages: 252  
rating: 5  
tags: #{philosophy, ideas}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2018-01-16 | 2018-01-18 | San Francisco

### review

Great book as intro into philosophy.
I think these types of books work for me very well.


## Answered Prayers

title: Answered Prayers  
authors: @{Truman Capote}
isbn: 978-0-679-75182-3  
year: 1987
pages: 150  
rating: 4  
tags: #{fiction}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2018-02-10 | 2018-02-15 | San Francisco

### review

I used to love Capote a lot. I've read a lot of him in my early twenties. 
It's nice to find something from him that I haven't read.


## The Elephant in the Brain

title: The Elephant in the Brain  
subtitle: Hidden Motives in Everyday Life  
authors: @{Kevin Simler, Robin Hanson}
isbn: 978-0-19-049599-2  
year: 2018
pages: 396  
rating: 4  
tags: #{brain}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2018-02-19 | 2018-02-25 | San Francisco

### review

Books covers very popular topic nowadays. 
It's an easy enjoyable read. 
However again, one year later I don't remember anything from it.


## 12 Rules for Life

title: 12 Rules for Life  
subtitle: An Antidote to Chaos
authors: @{Jordan B. Peterson}
isbn: 978-0-345-81602-3
year: 2018
pages: 408  
rating: 4  
tags: #{life}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2018-01-23 | 2018-03-20 | San Francisco 

### review

Jordan B. Peterson has a bad reputation these days. 
I'm not sure I like him as a person, but I found it interesting to listen to his lectures or read this book. 
Book is too long however, can be twice as short.


## So Good They Cant Ignore You

title: So Good They Can't Ignore You  
subtitle: Why Skills Trump Passion in the Quest for Work You Love
authors: @{Cal Newport}
isbn: 978-1-45550-912-6
year: 2012
pages: 268  
rating: 3  
tags: #{work}
reviewdate: 2019-04-06

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2017-10-01 | 2018-05-14 | San Francisco 

### review

This shouldn't be a book. It should be a blog post. 
Too long to tell the simple possible idea. 
It has catchy title, but this is mostly marketing.


## Great Thinkers

title: Great Thinkers  
authors: @{The School of Life}
isbn: 978-0-9935387-0-4
year: 2016
pages: 476  
rating: 5  
tags: #{philosophy, history, ideas}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | ------------------
2018-04-12 | 2018-05-01 | Kyiv, Lviv, Berlin

### review

Once again the type of book I love. Simple language, interesting topic. This is the type of book I would want to reread.


## The School of Life Dictionary

title: The School of Life Dictionary  
authors: @{The School of Life}
isbn: 978-0-9957535-9-4
year: 2017
pages: 278  
rating: 4  
tags: #{dictionary}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | ---------------------------------
2018-04-01 | 2018-05-13 | Kyiv, Lviv, Berlin, San Francisco 

### review

I want to see more of books like this. Modern definition for words that are important in our everyday lives.


## Relationships by The School of Life

title: Relationships  
authors: @{The School of Life}
isbn: 978-0-9935387-4-2  
year: 2016
pages: 116  
rating: 4  
tags: #{relationships}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-05-01 | 2018-05-06 | San Francisco 

### review

Love idea behind books like this. Important topic, simple language. 


## The Pocket Dalai Lama 

title: The Pocket Dalai Lama  
authors: @{Dalai Lama}
isbn: 978-1-61180-441-6
year: 2017
pages: 94  
rating: 4  
tags: #{wisdom}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-05-14 | 2018-05-15 | San Francisco 

### review

Simple pocket book. Easy read.


## How to Reform Capitalism 

title: How to Reform Capitalism  
authors: @{The School of Life}
isbn: 978-0-9957535-7-0
year: 2017
pages: 107  
rating: 4  
tags: #{capitalism, ideas}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-05-15 | 2018-05-16 | San Francisco 

### review

I like the idea behind this book, however one year after reading it I don't remeber anything specific about it.


## We Are All Weird 

title: We Are All Weird  
subtitle: The Rise of Tribes and the End of Normal  
authors: @{Seth Godin}
isbn: 978-1-59184-824-0  
year: 2015
pages: 102  
rating: 3  
tags: #{ideas}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-05-18 | 2018-05-22 | San Francisco 

### review

I like Seth, but again, this is blog post type of book: easy and disposabled reading.


## The Drama of the Gifted Child

title: The Drama of the Gifted Child  
subtitle: The Search for the True Self  
authors: @{Alice Miller}
isbn: 978-0-465-01690-7
year: 2007
pages: 130  
rating: 4  
tags: #{therapy, childhood, psychology}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-05-22 | 2018-05-26 | San Francisco 

### review

Recommended by my therapist. Needed to read it and I feel that it was ok, 
but as very common with some types of books one year later I can't tell anything about this one.


## Calm by The School Of Life

title: Calm  
authors: @{The School of Life}
isbn: 978-0-9935387-2-8  
year: 2016
pages: 134  
rating: 3  
tags: #{mental health}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-05-25 | 2018-06-02 | San Francisco, Los Angeles 

### review

Love idea behind books like this. Important topic, simple language.


## Island by Huxley

title: Island  
authors: @{Aldous Huxley}
isbn: 978-0-06-156179-5  
year: 1972
pages: 354  
rating: 5  
tags: #{utopia, society, ideas}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-06-03 | 2018-06-18 | San Francisco, Los Angeles 

### review

Beautiful novel. So many ideas inside to think over. 
No need to agree with all of them, but it's great to think and evaluate them based on your own experience.


## A World of Three Zeros

title: A World of Three Zeros  
subtitle: The New Economics of Zero Poverty, Zero Unemployment, and Zero Net Carbon Emissions 
authors: @{Muhammad Yunus}
isbn: 978-1-61039-757-5  
year: 2017
pages: 288  
rating: 5  
tags: #{sex}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2018-06-23 | 2018-07-04 | San Francisco 

### review

Somehow I just learned about Muhammad Yunus. He is a true inspiration now.
I can't belive he was focusing on poverty, unmployment and environment for so many years before it became mainstream. And it seems like he developed new models for enterprises before anyone else.


## Sex by The School of Life

title: Sex  
authors: @{The School of Life}
isbn: 978-0-9935387-6-6  
year: 2017
pages: 126  
rating: 4  
tags: #{sex}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-07-05 | 2018-07-08 | San Francisco, Los Angeles 

### review

Love idea behind books like this. Important topic, simple language.


## Žižek Jokes

title: Žižek's Jokes  
authors: @{Slavoj Žižek}
isbn: 978-0-262-02671-0    
year: 2012
pages: 148  
rating: 3  
tags: #{novel}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-07-08 | 2018-07-09 | Los Angeles 

### review

Good idea for a pocket book. Just a collection of jokes for an easy weekend read.


## Gratitude by Sacks

title: Gratitude  
authors: @{Oliver Sacks}
isbn: 978-0-451-49293-7    
year: 2017
pages: 45  
rating: 3  
tags: #{pocket}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-07-21 | 2018-07-21 | San Francisco 

### review

It felt artificial. I didn't get a feeling of author's actual gratitude while reading it. 


## The Art of Communicating

title: The Art of Communicating  
authors: @{Thich Nhat Nahn}
isbn: 978-0-06-222466-8
year: 2013
pages: 166  
rating: 4  
tags: #{communication}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-10-12 | 2018-10-28 | San Francisco 

### review

Six months later don't remember anything. 
I think it is a book that restates common knowledge.
Nothing revolutionary.


## A Man Without a Country

title: A Man Without a Country  
authors: @{Kurt Vonnegut}
isbn: 978-0-8129-7736-3  
year: 2005
pages: 142  
rating: 4  
tags: #{novel}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | --------------
2018-07-15 | 2018-07-15 | San Francisco, Los Angeles 

### review

My first book from Vonnegut. I might read more of his novels now. I like the style.


## Anarchy in the Ukr

title: Anarchy in the Ukr  
authors: @{Serhii Zhadan}  
isbn: 978-966-14-7841-0  
year: 2016  
pages: 232  
rating: 3  
tags: #{Ukraine}
reviewdate: 2019-04-06

### readings

started    | finished   | locations
---------- | ---------- | -------------
2019-01-19 | 2019-02-10 | San Francisco

### review

My first book from Zhadan. I like his poetry and songs, but I didn't like novel at all. 

Maybe I didn't understand it, but for me a lot of scenes from this book were too painful to read but it seemed like Zhadan romanticised his experiences and stories. Things that I wanted to forget from my past - those are the things that he cherished. As I said, maybe I misunderstood him, but book didn't meet my expectations.


## Treatise on Modern Stimulants

title: Treatise on Modern Stimulants  
authors: @{Honoré de Balzac}  
isbn: 978-1-939663-38-2  
year: 1839  
pages: 80  
rating: 3  
reviewdate: 2019-03-20

### readings

started    | finished   | locations
---------- | ---------- | -------------
2019-02-10 | 2019-03-03 | San Francisco

### review

Very short and simple book. Didn't find a lot of interesting things when read.
However, I think it's an interesting book to be written at 1839.
Also the topic is as relevant as always and insights are true and common knowledge now.


## Sea People

title: Sea People  
subtitle: The Puzzle of Polynesia  
authors: @{Christina Thompson}  
isbn: 978-0-06-206087-7  
year: 2019  
pages: 364  
rating: 5  
tags: #{Polynesia, history, travel, ocean, culture}
reviewdate: 2019-04-01

### readings

started    | finished   | locations
---------- | ---------- | -------------
2019-03-24 | 2019-03-31 | San Francisco

### review

Engaging book. Learned a lot about region I knew so little. 
The book laid out the history of the region and history scientific inqury into it. 
I've read it just in few days and couldn't stop reading.


## Daring Greatly

title: Daring Greatly  
subtitle: How the Courage to Be Vulnerable Transforms the Way We Live, Love, Parent and Lead  
authors: @{Brené Brown}  
isbn: 978-1-592-40841-2  
year: 2015  
pages: 302  
rating: 5  
tags: #{vulnerability, shame, guilt, parenting, psychology}
reviewdate: 2019-04-07

### readings

started    | finished   | locations
---------- | ---------- | -------------
2018-07-27 | 2019-04-07 | San Francisco

### review

I've read all of it except the last chapter in 2018, but for some reason didn't finish the book. Despite this fact I think it's very important, well written book with a lot of great insights and tons fo stories. Vulnerability was the topic I spent a lot thinking in 2018, I think Brené Brown thought it over much more than me. A lot of fresh and clear and simple ideas there.


# trips

description: My trips  

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}]) 
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]
      [:script {:src "https://api.mapbox.com/mapbox-gl-js/v0.44.2/mapbox-gl.js"}]
      [:link {:rel "stylesheet" :type "text/css" :href "https://api.mapbox.com/mapbox-gl-js/v0.44.2/mapbox-gl.css"}]
      [:script {:src "https://unpkg.com/mapbox@1.0.0-beta10/dist/mapbox-sdk.min.js"}]
      ]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
        [:div {:class "dtc v-mid ph3"}
          [:h1 {:class "mt0 mb0 baskerville fw1 f4"} "Anton Podviaznikov"]
          [:h2 {:class "gray mt1 mb0 fw4 f6"} "observer; no answers, only questions"]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f3.athelas (:name %)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (->> entity :id :value)} (:name entity)]
              [:div.mt1.mb0.mh0
                [:span.f7.ml0.mb0.mr0 "from " (->> entity :started :value) " to " (->> entity :finished :value)]
                [:span.f7.ml0.mb0.mr0 "; travelled " (->> entity :distance :value) " kms"]
                [:span.f7.ml0.mb0.mr0 "; emission " (montaigne.fns/format-float  (->> entity :carbon :value) 2) " tons of CO2"]
              ]
              ])
          %)]
        [:div#map {:class "w-50-ns w-40 h-100 dib ma0" :style "position:fixed;top:0;right:0;"}]  
      ]
      [:script
        (str 
        "
        mapboxgl.accessToken = 'pk.eyJ1IjoiYW50b24tcG9kdmlhem5pa292IiwiYSI6ImNqaGJmYnZxNTAxcWQzN3FvYzd3aHR0YzYifQ.wS4Vf6XVQSUJ9ZME1M6rLw';
        var map = new mapboxgl.Map({
          container: 'map',
          style: 'mapbox://styles/mapbox/light-v9',
          center: [-56, 37.8],
          zoom: 1.0, // starting zoom
          attributionControl: false
        });
        map.addControl(new mapboxgl.FullscreenControl());
        map.on('load', function() {"
          (apply str (map-indexed 
            (fn [idx entity]
              (str
              "map.addLayer(
                    {
                      'id': '" 
                      idx
                      "',
                      'type': 'circle',
                      'source': {
                        'type': 'geojson',
                        'data': {
                            'type': 'FeatureCollection',
                            'features': [
                              {
                                'type': 'Feature',
                                'geometry': {
                                  'type': 'Point',
                                  'coordinates': ["
                                    (->> entity :itinerary :value first :airport-to-lon)
                                    ","
                                    (->> entity :itinerary :value first :airport-to-lat)
                                  "]
                                }
                              }
                            ]}},
                      'paint': {
                          'circle-radius': 6,
                          'circle-color': '#B42222'
                      },
                      'filter': ['==', '$type', 'Point'],
                  });"
                )
            )
          %))
          
        "});"
        )
      ]
    ]])
```

@id: `(montaigne.fns/slug (:name %))`  
@itinerary.airport-from: `(first (filter (fn [row] (= (:from %) (:IATA row))) (->> %airports)))`
@itinerary.airport-to: `(first (filter (fn [row] (= (:to %) (:IATA row))) (->> %airports)))`
@itinerary.country-from: `(:country (:airport-from %))`
@itinerary.city-from: `(:city (:airport-from %))`
@itinerary.country-to: `(:country (:airport-to %))`
@itinerary.city-to: `(:city (:airport-to %))`
@itinerary.airport-from-lon: `(:lon (:airport-from %))`
@itinerary.airport-from-lat: `(:lat (:airport-from %))`
@itinerary.airport-to-lon: `(:lon (:airport-to %))`
@itinerary.airport-to-lat: `(:lat (:airport-to %))`
@itinerary.distance: `(montaigne.fns/calc-distance (:airport-from-lat %) (:airport-from-lon %) (:airport-to-lat %) (:airport-to-lon %))`
@visited-cities: `(into [] (map-indexed (fn [idx row] {:city (:city-from row) :country (:country-from row) :days (montaigne.fns/duration-in-days (:date (get (->> % :itinerary :value) (dec idx))) (:date row))})(->> % :itinerary :value)))`
@itinerary.carbon: `(montaigne.fns/calc-carbon (:distance %))`
@distance: `(apply + (map :distance (->> % :itinerary :value)))`
@carbon: `(apply + (map :carbon (->> % :itinerary :value)))`
@started: `(:date (first (->> % :itinerary :value)))`  
@finished: `(:date (last (->> % :itinerary :value)))`  
@days: `(montaigne.fns/duration-in-days (->> % :started :value) (->> % :finished :value))`  
@year: `(montaigne.fns/get-year (->> % :started :value))`

### @template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}]
          ]
        ]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f4.athelas (:name %)]
        [:header
          [:div.pv2
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Started:"]
              [:dd {:class "dib ml1"} (->> % :started :value)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Finished:"]
              [:dd {:class "dib ml1"} (->> % :finished :value)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Days:"]
              [:dd {:class "dib ml1"} (->> % :days :value)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Distance:"]
              [:dd {:class "dib ml1"} (->> % :distance :value) " km"]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Carbon:"]
              [:dd {:class "dib ml1"} (montaigne.fns/format-float (->> % :carbon :value) 2) " tons of CO2"]
            ]]]
        [:section
          [:h2.f6 "visited cities"]
          [:article.lh-copy.measure
            (map (fn [row]
              [:dl {:class "f6 lh-title mv2"}
                  [:dt {:class "dib gray"} (:city row)]
                  [:dd {:class "dib ml1"} (:days row) " days"]
                ]
            )(->> % :visited-cities :value))
]

        ]
        [:section
          [:h2.f6 "itinerary"]
          [:article.lh-copy.measure
            [:table {:class "f6 w-100 mw8 center" :cellspacing "0"}
              [:thead
                [:tr
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "from"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "to"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "date"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "type"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "flight"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "aircraft"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "distance"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "carbon"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "layover"]
]
              ]
              [:tbody {:class "lh-copy"}
                (map
                  (fn [row]
                    [:tr
                      [:td (->> row :from)]
                      [:td (->> row :to)]
                      [:td (->> row :date)]
                      [:td (->> row :type)]
                      [:td (->> row :flight)]
                      [:td (->> row :aircraft)]
                      [:td (->> row :distance)]
                      [:td (->> row :carbon)]
                      [:td (->> row :layover)]])
                  (->> % :itinerary :value)
                )
              ]
            ]
          ]
        ]
      ]
    ]
  ])
```


## Temecula Feb 2019

type: work  

### itinerary

from | to   | date       | type   | flight | aircraft 
---- | ---- | ---------- | ------ | ------ | -------- 
SFO  | SAN  | 2019-02-24 | flight | AS1956 | A318/321 
SAN  | SFO  | 2019-03-01 | flight | AS1969 | A318/321 


### summary

First time in this part of SoCal. Didn't do much since it was work event.


## Vail Feb 2019

type: friends  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | --------  
SFO  | DEN | 2019-02-14 | flight | UA1013 | 777-200 
DEN  | SFO | 2019-02-18 | flight | UA571  | 757-200 

### summary

First time in Colorado. Great weather, great slops - exactly as I like.


## Buenos Aires and Paris

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft       | city-to  | layover 
---- | --- | ---------- | ------ | ------ | -------------- | -------- | -------- 
SFO  | EWR | 2018-11-15 | flight | UA535  | 757-200        | New York |  
EWR  | EZE | 2018-11-17 | flight | UA979  | 767-400        |          |  
EZE  | MAD | 2018-12-23 | flight | IB6856 | A340-600       |          | true 
MAD  | ORY | 2018-12-24 | flight | IB3436 | A320 SHARKLETS |          |  
CDG  | OAK | 2019-01-05 | flight | DY7079 | 787-9          |          |  


## Tijuana 2018 Last Trip

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | SAN | 2018-10-12 | flight | UA361  | 737-900  
SAN  | SFO | 2018-11-15 | flight | UA334  | 737-900  


## Mexico City and Tijuana 2018

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | MEX | 2018-10-05 | flight | UA412  | A320  
MEX  | TIJ | 2018-10-08 | flight | Y4813  | A320  
SAN  | SFO | 2018-10-09 | flight | UA662  | 737-800  


## Europe Summer 2018

type: family  

### itinerary

from      | to        | date       | type   | flight | aircraft 
----------| --------- | ---------- | ------ | ------ | -------------- 
SFO       | MUC       | 2018-08-10 | flight | LH459  | 
MUC       | KBP       | 2018-08-11 | flight | LH2546 | 
KBP       | BCN       | 2018-08-17 | flight | PS991  | 
BCN       | ATH       | 2018-08-24 | flight | VY8100 | 
Athens    | Santorini | 2018-08-24 | boat   |        | 
Santorini | Athens    | 2018-08-30 | boat   |        | 
ATH       | ARN       | 2018-09-04 | flight | A3760  | 
ARN       | CPH       | 2018-09-04 | flight | SK1423 | 
CPH       | KEF       | 2018-09-05 | flight | WW903  | 
KEF       | SFO       | 2018-09-05 | flight | WW161  | 


## Ukraine First Trip in Years

type: family  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | EWR | 2017-11-16 | flight | UA1796 | 777-200  
JFK  | MUC | 2017-11-17 | flight | LH411  | A340-600  
MUC  | KBP | 2017-11-18 | flight | LH2544 | A320  
KBP  | FRA | 2017-11-27 | flight | LH1493 | A321  
FRA  | SFO | 2017-11-27 | flight | LH9052 | 777-300  


### summary

Add train to Kharkiv


## Tijuna and San Diego First Trip 2018

type: friends and work  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | SAN | 2018-02-02 | flight | VX1958 | 
SAN  | SFO |  | flight |   | 


## Hong Kong Birthday Trip

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | HKG | 2017-06-29 | flight | UA869  | 777-300  
HKG  | SFO | 2017-07-10 | flight | UA862  | 777-300  


## New York City October 2017

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | JFK | 2017-10-05 | flight | AA18   | 
JFK  | SFO | 2017-10-08 | flight | AA177  | 


## Phoenix May 2017

purpose: wedding

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | PHX | 2017-05-19 | flight | AA649  | 
PHX  | SFO | 2017-05-22 | flight | AA1642 | 


## New York City May 2017

type: friends  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | JFK | 2017-05-04 | flight | DL1144 | 717-200   
JFK  | SFO | 2017-05-11 | flight | DL426  | A320  


## Gili and Bali 2017

type: tourism  

### summary

Add boat ride to Gili

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | SIN | 2017-03-24 | flight | SQ1    | 777-300   
SIN  | DPS | 2017-03-25 | flight | SQ946  | A330-300  
DPS  | SIN | 2017-04-09 | flight | SQ943  | A330-300   
SIN  | SFO | 2017-04-09 | flight | SQ2    | 777-300  


## Hong Kong Second Trip

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | HKG | 2016-10-29 | flight | CX0879 | 777-300  
HKG  | SFO | 2016-11-07 | flight | CX0870 | 777-300  


## Hong Kong First Trip 2016

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | HKG | 2016-08-24 | flight | CX0873 | 777-300  
HKG  | SFO | 2016-09-06 | flight | CX0892 | A350-900  


## London First Trip 2016

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | LHR | 2016-05-06 | flight | OS7856 | 777-200  
LHR  | SFO | 2016-05-15 | flight | OS7857 | 777-200  


## STL 2015

purpose: StrangeLoop conference  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | DEN | 2015-09-23 | flight | UA1736 | 737-900  
DEN  | STL | 2015-09-23 | flight | UA3395 | ERJ-145  
STL  | SFO | 2015-09-28 | flight | UA6421 | ERJ-170  


## Istanbul Family Trip 2015

purpose: family trip  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | IST | 2015-08-15 | flight | TK0080 | 
IST  | SFO | 2015-09-01 | flight | TK0079 | 


## Portland First Trip 2015

purpose: Clojure/West conference  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | PDX | 2015-04-18 | flight | UA464  | A320  
PDX  | SFO | 2015-04-22 | flight | UA995  | 737-800  


## Las Vegas First Trip 2015

purpose: Microconf  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | LAS | 2015-04-12 | flight | UA1662 | 737-900  
LAS  | SFO | 2015-04-15 | flight | UA1528 | 737-900  


## Provo 2015

purpose: React Week  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
OAK  | SLC | 2015-03-07 | flight | DL1082 | 
SLC  | OAK | 2015-03-15 | flight | DL1082 | 


## Cancun and Playa del Carmen Second Time 2014

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | ---------
SFO  | LAX | 2014-12-04 | flight | UA478  | A319
LAX  | CUN | 2014-12-04 | flight | UA1276 | 737-900 
CUN  | SFO | 2014-12-07 | flight | UA1118 | 737-900


### summary

Add Playa del Carmen bus


## Macedonia 2014

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | ---------
SAW  | SAW | 2014-03-23 | flight | PC711  | 


## Istanbul 2014

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | ---------
HRK  | SAW | 2014-01-26 | flight | PC751  | 


## Washington DC First Trip 2013

purpose: Clojure/conj  

### itinerary

from          | to            | date       | type  
------------- | --------------| ---------- | ------
New York      | Washington DC | 2013-11-13 | bus   
Washington DC | New York      | 2013-11-17 | bus   


## NYC First Trip 2012

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | JFK | 2012-06-15 | flight | DL2340 |   


## Leaving Montenegro

type: tourism  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
TGD  | BUD | 2011-12-28 | flight | MA495  | 737-600  
BUD  | KBP | 2011-12-29 | flight | MA110  | 737-800  


## Berlin First Trip 2011

purpose: Google Dev Conf  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
BEG  | VIE | 2011-11-11 | flight | OS772  |   
VIE  | TXL | 2011-11-11 | flight | OS291  |   
TXL  | VIE | 2011-11-20 | flight | OS272  |   
VIE  | BEG | 2011-11-20 | flight | OS735  |   


### summary

Add bus ride to Belgrade.


## Munich and Prague 2011

type: tourism  

### summary

Add bus ride to Belgrade.


### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
BEG  | FMM | 2011-08-23 | flight | W64105 |   
FMM  | BEG | 2011-08-30 | flight | W64106 |   


# quotes

description: My collection of quotes

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
        [:div {:class "dtc v-mid ph3"}
          [:h1 {:class "mt0 mb0 baskerville fw1 f4"} "Anton Podviaznikov"]
          [:h2 {:class "gray mt1 mb0 fw4 f6"} "observer; no answers, only questions"]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f3.athelas (:name %)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (->> entity :id :value)} (:name entity)]])
          %)]
      ]
    ]])
```

@id: `(montaigne.fns/slug (:name %))`  

### @template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f4.athelas (:name %)]
        [:blockquote {:class "athelas ml0 mt0 pl4 black-90 bl bw2 b--blue"}
          [:p {:class "f5 f4-m f3-l lh-copy measure mt0"}
            (->> % :quote :value)
          ]
          [:cite {:class "f6 ttu tracked fs-normal"}
            (clojure.string/join ", " (->> % :authors :value))
          ]
        ]
      ]
    ]])
```


## Havel on critizing

authors: @{Václav Havel}  
tags: #{politics, criticizing}  

### sources

book                   | pages | chapter
---------------------- | ----- | ---------
To the Castle and Back | 5     | Chapter 1 

### quote

People told me exactly what I would later often say to others when tring to draw them into politics: 
you can't spend your whole life criticizing something and then, 
when you have the chance to do it better, refuse to go near it.


## Postman on promotion of fast technological solutions

authors: @{Neil Postman}  
tags: #{technology, television commercials, utopia}  

### quote

Television screens saturated with commercials to promote the Utopian and childish idea that all problems have fast, simple, and technological solutions.


## Balzac on consumption

authors: @{Honoré de Balzac}  
tags: #{consumption, coffee, alchohol, drugs}

### sources

book                           | pages | chapter
------------------------------ | ----  | ---------------------
Treatise on Modern Stimulants  | 6     | I The Subject at Hand

### quote

Excessive consumption of tobacco, coffee, opium and spirits produces grave disorders and drives one to an early death. The organs, endlessly irritated, endlessly nourished, become hypertrophied; they become abnormally enlarged, damanged and corrupt the machine, which succumbs.


## Brown on daring greatly

authors: @{Brené Brown}  
tags: #{vulnerability, courage}

### sources

book                           | pages | chapter
------------------------------ | ----  | ---------------------
Daring Greatly                 | 2     | What It Means to Dare Greatly

### quote

*Perfect* and *bulletproof* are seductive, but they don't exist in the human experience. We must walk into the arena, whatever it may be - a new relationship, an important meeting, our creative process, or a difficult family conversation - with courage and willingness to engage.
Rather than sitting on the sidelines and hurling judgement and advice, **we must dare to show up and let ourselves been seen**. 
This is vulnerability. This is *dearing greatly*.


# now

description: What am I'm doing now

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
        [:div {:class "dtc v-mid ph3"}
          [:h1 {:class "mt0 mb0 baskerville fw1 f4"} "Anton Podviaznikov"]
          [:h2 {:class "gray mt1 mb0 fw4 f6"} "observer; no answers, only questions"]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f3.athelas (:name %)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (->> entity :id :value)} (:name entity)]
              [:div.mt1.mb0.mh0
                [:span.f7.ml0.mb0.mr1 (->> entity :location :value first) ",&nbsp;" (->> entity :date :value)]
              ]
              ])
          (reverse %))]
      ]
    ]])
```

@id: `(montaigne.fns/slug (:name %))`  

### @template

```clojure
(montaigne.fns/html 
 [:html {:itemscope "" :itemtype "http://schema.org/WebPage"}
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:meta {:itemprop "author" :name "author" :content %author}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"
              :itemscope ""
              :itemprop "mainEntity"
              :itemtype "http://schema.org/BlogPosting"}
        [:h1.lh-title.f4.athelas {:itemprop "name"} (:name %)]
        [:article.lh-copy.measure {:itemprop "articleBody"}
          (->> % :content :value)]
        [:footer {:class "mt4 cf lh-copy measure f6"}
          [:p.i.fr
            [:span.i {:itemprop "locationCreated"} (->> % :location :value first)]
            [:span "&nbsp;&nbsp;"]
            [:span.i {:itemprop "datePublished"} (->> % :date :value)]
          ]
        ]
      ]
    ]])
```


## Update from Sept 29, 2018

date: 2018-09-29  
location: @{San Francisco} 

### content

It has been over 4 years since I moved to San Francisco. It’s the longest stretch of time I’ve spent in a single city outside of my home country.

I’m here by myself for the first time in my adult life.

I do have more free time than usual which I spend thinking, reading, programming, trying find new hobbies.

The most important part is that I’m trying to reflect on my readings, experiences, and ideas.
The best day for me is the one when I have a buzz of various ideas and thoughts in my head.

I’m thinking a lot nowadays about influence of technology on our lives, what the future will bring us and what changes I want to see in myself and society. 
I am also thinking a lot about Ukraine, its past and future.


## Update from April 7, 2019

date: 2019-04-07  
location: @{San Francisco}

### content

It has been almost 5 years since I moved to San Francisco. 
It’s the longest stretch of time I’ve spent in a single city outside of my home country.

I write some poetry as therapeutic exercise.
I write some longer essays that might look like serious things, but they are not. 
I read books. Travel a bit. 
Trying to find people and causes I can support.


# activities

description: Log of my activities

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
        [:div {:class "dtc v-mid ph3"}
          [:h1 {:class "mt0 mb0 baskerville fw1 f4"} "Anton Podviaznikov"]
          [:h2 {:class "gray mt1 mb0 fw4 f6"} "observer; no answers, only questions"]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f3.athelas (:name %)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (->> entity :id :value)} (:name entity)]
              [:div.mt1.mb0.mh0
                [:span.f7.ml0.mb0.mr1 "from " (->> entity :start :value) " to " (->> entity :end :value)]
                [:span.f7.ml0.mb0.mr1 "activities " (->> entity :activities-count :value)]
              ]
              ])
          %)]
      ]
    ]])
```

@id: `(montaigne.fns/slug (:name %))`  
@activities.count: `(count (filter (fn [col-pair] (= "✓" (clojure.string/trim (second col-pair)))) %))`
@activities-count: `(apply + (map :count (->> % :activities :value)))`

### @template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> % :description :value)))
        [:meta {:name "description" :content (->> % :description :value)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:header {:class "ph3 ph5-ns pt1 dt"}
        [:div {:class "dtc v-mid pt0"}
          [:a.link {:href "/"}
            [:img {:width "44px" :height "44px" :src "https:/podviaznikov.com/img/logo.svg"}
          ]
        ]]
      ]
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f4.athelas (:name %)]
        [:header
          [:div.pv2
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Start:"]
              [:dd {:class "dib ml1"} (->> % :start :value)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "End:"]
              [:dd {:class "dib ml1"} (->> % :end :value)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Activities:"]
              [:dd {:class "dib ml1"} (->> % :activities-count)]
            ]
          ]]
        [:section
          [:h2.f6 "activities"]
          [:article.lh-copy.measure
            [:table {:class "f6 w-100 mw8 center" :cellspacing "0"}
              [:thead
                [:tr
                  (map (fn [column]
                    [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} column]
                  )
                (->> % :activities :columns))]
              ]
              [:tbody {:class "lh-copy"}
                (map (fn [row]
                  [:tr
                    (map 
                      (fn [column-name]
                        [:td (get row (keyword column-name))]
                      )
                    (->> % :activities :columns)
                    )
                  ]
                  )
                  (->> % :activities :value)
                )
              ]
            ]
          ]]
        [:section
          [:h2.f6 "intake"]
          [:article.lh-copy.measure
          [:table {:class "f6 w-100 mw8 center" :cellspacing "0"}
              [:thead
                [:tr
                  (map (fn [column]
                    [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} column]
                  )
                (->> % :intake :columns))]
              ]
              [:tbody {:class "lh-copy"}
                (map (fn [row]
                  [:tr
                    (map 
                      (fn [column-name]
                        [:td (get row (keyword column-name))]
                      )
                    (->> % :intake :columns)
                    )
                  ]
                  )
                  (->> % :intake :value)
                )
              ]
            ]
          ]]  
      ]
    ]])
```


## week 1

start: 2019-03-03  
end: 2019-03-09  

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |  ✓  |     |     |  ✓  |     |     |  
football   |  ✓  |     |     |  ✓  |     |     |  
reading    |  ✓  |     |     |     |     |     |  
spanish    |     |     |     |     |  ✓  |     |  
pushups    |  ✓  |  ✓  |  ✓  |     |     |     |  
edu event  |     |     |     |     |     |  ✓  |  
ent event  |     |     |     |     |     |     |  ✓ 
cul event  |     |     |     |     |     |     |  
cycling    |     |     |     |     |     |     |  
tennis     |     |     |     |     |     |     |  

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |     |  ✓
no coffee  |     |     |     |     |     |     |  
no sugar   |     |     |     |     |     |     |  


## week 2

start: 2019-03-10  
end: 2019-03-16

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |  ✓  |     |     |  ✓  |     |     |  
football   |     |     |     |  ✓  |     |     |  
reading    |     |     |  ✓  |     |     |     |  ✓
spanish    |     |  ✓  |     |     |  ✓  |     |  
pushups    |     |     |     |     |     |     |  
edu event  |     |     |     |     |     |     |  
ent event  |     |     |     |     |     |     |  
cul event  |     |     |     |     |     |     |  
cycling    |     |     |     |  ✓  |     |     |  ✓
tennis     |     |     |     |  ✓  |     |     |  

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓ 
no coffee  |     |     |     |     |     |     |  
no sugar   |     |     |     |     |     |     |  


## week 3

start: 2019-03-17  
end: 2019-03-23

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |  ✓  |     |     |     |     |     |  
football   |     |  ✓  |     |     |     |     |  
reading    |     |  ✓  |     |     |     |     |  ✓
spanish    |     |     |     |     |  ✓  |     |  
pushups    |     |     |     |     |     |     |  ✓
edu event  |     |     |     |     |     |     |  
ent event  |  ✓  |     |     |     |     |     |  
cul event  |     |     |     |     |     |     |  
cycling    |     |     |     |     |     |     |  ✓
tennis     |     |     |     |     |     |     |  
ping-pong  |     |     |     |     |  ✓  |     |  

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓ 
no coffee  |     |     |     |     |     |     |  
no sugar   |     |     |     |     |     |     |  


## week 4

start: 2019-03-24  
end: 2019-03-30

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |  ✓  |     |     |     |     |     |  
football   |     |     |     |     |     |     |  
reading    |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓ 
spanish    |     |     |     |     |  ✓  |     |  
pushups    |  ✓  |  ✓  |     |     |     |     |  
edu event  |     |     |     |     |     |     |  
ent event  |     |     |     |     |     |     |  
cul event  |     |     |     |     |     |     |  
cycling    |     |     |     |     |     |     |  
tennis     |     |     |     |     |     |     |  
ping-pong  |     |  ✓  |     |     |     |     |  

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |  ✓  |     |  ✓  |  ✓  |  ✓  |    
no coffee  |     |     |     |     |     |     |  
no sugar   |     |     |     |     |     |     |  


## week 5

start: 2019-03-31  
end: 2019-04-06

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |  ✓  |     |     |     |     |     |  
football   |  ✓  |     |     |     |     |     |  
reading    |  ✓  |     |     |  ✓  |     |  ✓  |    
spanish    |     |     |     |     |  ✓  |     |  
pushups    |     |  ✓  |     |  ✓  |     |     |  ✓
edu event  |     |     |     |     |     |     |  
ent event  |     |     |     |     |     |     |  
cul event  |     |     |     |     |     |     |  
cycling    |     |     |     |     |     |     |  
tennis     |     |     |     |     |     |     |  
ping-pong  |     |     |     |     |     |     |  

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  
no coffee  |     |     |     |     |     |     |  
no sugar   |     |     |     |     |     |     |  


## week 6

start: 2019-04-07  
end: 2019-04-13

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |     |     |     |     |     |     |  
football   |     |     |     |     |     |     |  
reading    |  ✓  |     |  ✓  |     |     |     |    
spanish    |     |     |     |     |     |     |  
pushups    |  ✓  |     |     |     |     |     |  
edu event  |     |     |     |     |     |     |  
ent event  |     |     |     |     |     |     |  
cul event  |     |     |     |     |     |     |  
cycling    |  ✓  |     |     |     |     |     |  
tennis     |     |     |     |     |     |     |  
ping-pong  |     |  ✓  |     |     |     |     |  

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |  ✓  |     |     |     |     |    
no coffee  |     |     |     |     |     |     |  
no sugar   |     |     |     |     |     |     |    


## week 7

start: 2019-04-14  
end: 2019-04-20  

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |     |     |     |     |     |  ✓  |    
football   |     |     |     |     |     |     |    
reading    |     |     |     |     |     |     |    
spanish    |     |     |     |     |     |     |    
pushups    |     |     |     |     |     |     |    
edu event  |     |     |  ✓  |     |     |     |    
ent event  |  ✓  |     |     |  ✓  |  ✓  |  ✓  |  ✓ 
cul event  |     |  ✓  |     |     |     |     |    
cycling    |     |     |     |     |     |     |    
tennis     |     |     |     |     |     |     |    
ping-pong  |     |     |     |     |     |     |    

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |  ✓  |     |     |  ✓  |     |    
no coffee  |     |     |     |     |     |     |    
no sugar   |     |     |     |     |     |     |    


## week 8

start: 2019-04-21  
end: 2019-04-27  

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |     |     |     |     |     |     |    
football   |     |     |     |     |     |     |    
reading    |     |     |     |     |     |     |    
spanish    |     |     |     |     |     |     |    
pushups    |     |     |     |     |     |     |  ✓ 
edu event  |     |     |     |     |     |     |    
ent event  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓ 
cul event  |     |     |     |     |     |  ✓  |  ✓ 
cycling    |     |     |     |     |     |     |  ✓ 
tennis     |     |     |     |     |     |     |    
ping-pong  |     |     |     |     |     |     |    

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |     |     |     |  ✓  |  ✓  |    
no coffee  |     |     |     |     |     |     |    
no sugar   |     |     |     |     |     |     |    


## week 9

start: 2019-04-28  
end: 2019-05-04  

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |     |     |     |     |     |     |    
football   |     |     |     |     |  ✓  |     |    
reading    |     |     |  ✓  |  ✓  |  ✓  |  ✓  |  ✓ 
spanish    |     |     |     |     |  ✓  |     |    
pushups    |     |     |     |  ✓  |  ✓  |  ✓  |  ✓ 
edu event  |     |     |     |     |     |     |    
ent event  |  ✓  |  ✓  |  ✓  |     |     |     |    
cul event  |     |     |     |     |     |     |    
cycling    |     |     |     |     |  ✓  |     |  ✓ 
tennis     |     |     |     |     |     |     |    
ping-pong  |     |     |     |     |     |     |    

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |     |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓ 
no coffee  |     |     |     |     |     |     |    
no sugar   |     |     |     |     |     |     |    


## week 10

start: 2019-05-05  
end: 2019-05-11  

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |  ✓  |     |     |     |     |     |    
football   |  ✓  |     |     |     |     |     |    
reading    |  ✓  |     |     |     |     |     |    
spanish    |     |     |     |     |     |     |    
pushups    |  ✓  |     |     |     |     |     |    
edu event  |     |     |     |     |     |     |    
ent event  |     |     |     |     |     |     |    
cul event  |     |     |     |     |     |     |    
cycling    |     |     |     |     |     |     |    
tennis     |     |     |     |     |     |     |    
ping-pong  |     |     |     |     |     |     |    

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |     |     |     |     |     |    
no coffee  |     |     |     |     |     |     |    
no sugar   |     |     |     |     |     |     |    


## week 11

start: 2019-05-12  
end: 2019-05-16  

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |     |     |     |     |     |     |    
football   |     |     |     |     |     |     |    
reading    |     |     |     |     |     |     |    
spanish    |     |     |     |     |     |     |    
pushups    |     |     |     |     |     |     |    
edu event  |     |     |     |     |     |     |    
ent event  |     |     |     |     |     |     |    
cul event  |     |     |     |     |     |     |    
cycling    |     |     |     |     |     |     |    
tennis     |     |     |     |     |     |     |    
ping-pong  |     |     |     |     |     |     |    

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |     |     |     |     |     |     |    
no coffee  |     |     |     |     |     |     |    
no sugar   |     |     |     |     |     |     |    
