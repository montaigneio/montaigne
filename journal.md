# @index

description: My wiki

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
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
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
            [:a.link.f6.b.mb1 {:href "/quotes"} "Quotes"]]]
      ]
    ]])
```

# data

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
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
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
              [:a.link.f6.b.mb1 {:href (:id entity)} 
                [:span (->> entity :title :value)]
                [:span " by "]
                [:span (clojure.string/join ", " (->> entity :authors :value))]
              ]
              [:div.mt1.mb0.mh0
                [:small.f7.ml0.mb0.mr1.gray (:stars entity)]
                (if (not (nil? (->> entity :days)))
                  [:small.f7.ml0.mb0.mr1.gray (:days entity) " days"])
                (if (not (nil? (->> entity :pages :value)))
                  [:small.f7.ml0.mb0.mr1.gray (->> entity :pages :value) " pages"])
              ]
              ])
          %)]
      ]
    ]])
```

@id: `(montaigne.fns/slug (:name %))`  
@readings.duration: `(montaigne.fns/duration-in-days (:started %) (:finished %))`
@readings.year: `(montaigne.fns/get-year (:finished %))`
@stars: `(apply str (repeat (->> % :rating :value) "★&nbsp;"))`
@started: `(:started (last (->> % :readings :value)))`  
@finished: `(:finished (last (->> % :readings :value)))`  
@days: `(montaigne.fns/duration-in-days (:started %) (:finished %))`  

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
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
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


## Dear Professor Einstein

title: Dear Professor Einstein  
subtitle: Albert Einstein's Letters to and from Children  
authors: @{Albert Einstein}  
isbn: 1-59102-015-8  
year: 2002  
pages: 200
rating: 4  
tags: #{letters, children}  

### readings

started    | finished   | locations
---------- | ---------- | -------------
2015-12-15 | 2016-01-01 | San Francisco

### review

Good book that shows how humane Einstein was.


## Building a Bridge to the 18th Century

title: Building a Bridge to the 18th Century  
subtitle: How the Past Can Improve Our Future  
authors: @{Neil Postman}  
isbn: 978-0-375-70127-6  
year: 1999  
pages: 224  
rating: 5  
tags: #{philosophy, history, media, education}

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
tags: #{media, entertainment}  

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
rating: 5  
tags: @{running}  

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


## Conscientious Objections

title: Conscientious Objections  
subtitle: Stirring Up Trouble About Language, Technology and Education  
authors: @{Neil Postman}  
isbn: 978-0-679-73421-5  
year: 1988  
pages: 201  
rating: 5  
tags: @{technology, medium}  

### readings

started    | finished   | locations
---------- | ---------- | -------------
2016-12-04 | 2016-12-10 | San Francisco

### review

Collection of essays on different topics from education, childhood, politics, technology, television etc. 
Postman, as always, provides insights into our culture (mostly American) and suggests some practical ideas how to prevent negative ongoing trends. As always he uses wisdom of sages, thinkers, writers, and philosophers from the past to help us understand current social issues.

His essays and observations draw a picture of quite sad future. A lot of this predications are already fulfilled, so Postman is unfortunately was very accurate in them which he poses as warnings for us.

However, what I liked about this book is that he tries to stay optimistic. He reminds us that civilized society is very vulnerable and we should pay attention to dangers. And he tries to provide us with a mindset how to do just that.


## Homage to Catalonia

title: Homage to Catalonia  
authors: @{George Orwell}  
isbn: 978-1-84902-597-3  
year: 1938  
rating: 4  

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


## The Animal Farm

title: The Animal Farm  
authors: @{George Orwell}  
isbn: 978-1-943138-42-5  
year: 1945  
rating: 5  

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


## To the Castle and Back

title: To the Castle and Back  
authors: @{Václav Havel}  
isbn: 978-0-307-38845-2  
year: 2006  
pages: 383  
rating: 5  
tags: @{Eastern Europe, politics}  

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


## Technopoly

title: Technopoly  
subtitle: The Surrender of Culture to Technology  
authors: @{Neil Postman}
isbn: 978-0-679-74540-2  
year: 1992  
pages: 222  
rating: 5
tags: #{techonlogy, culture}

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-04-25 | 2017-04-30 | San Francisco


## The Little Prince

title: The Little Prince  
authors: @{Antoine de Saint-Exupéry}  
isbn: 978-0-15-601219-5  
year: 1943  
rating: 5  

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


## The Little Book of Hygge

title: The Little Book of Hygge  
subtitle: The Danish Way to Live Well  
authors: @{Meik Wiking}  
isbn: 978-0-241-28391-2  
year: 2016  
pages: 288   
rating: 3  
tags: #{Denmark, lifestyle}

### review

Not much to say about this one. Too simple.


## The Black Swan

title: The Black Swan  
subtitle: The Impact of the Highly Improbable  
authors: @{Nassim Nicholas Taleb}
isbn: 978-0-679-60418-1  
year: 2010  
pages: 400
rating: 3
tags: #{statistics}

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-03-06 | 2016-09-06 | San Francisco 

### review

Nasim Taleb seems like a very smart person but:

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

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-08-01 | 2016-08-21 | San Francisco 

### review

Good read if you want to have insight about life of startup founder, product manager/engineer in the Silicon Valley. Has a lot of pretty intimate details and a lot of humor (but not the type I like).

The book itself is too long. Easily can be done in 10 blog posts or shorter stories.


## Siddhartha

title: Siddhartha  
authors: @{Hermann Hesse}  
isbn: 978-1-62793-640-8  
year: 1922  
pages: 136  
rating: 5  
tags: #{philosophy, wisdom, religion}  

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

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-03-14 | 2016-03-19 | San Francisco 

### review

Outstanding book with a lot of wisdowm and personal discoveries. 
Bruce Lee connects East and West.

Reminds me of Meditations by Marcus Aurelius. 
It's both a diary and collecion of insights that can be open and read any day.


## Reinventing Organizations

title: Reinventing Organizations  
subtitle: A Guide to Creating Organizations Inspired by the Next Stage of Human Consciousness  
authors: @{Frederic Laloux}  
isbn: 978-2-960133-53-0  
year: 2014
pages: 378  
rating: 4
tags: #{organizations}

### readings

started    | finished   | locations
---------- | ---------- | ------------- 
2016-06-14 | 2016-08-29 | San Francisco 

### review

Great book that briefly explains history of organizations and shows us how organizations of the future would look like.

It describes requirenments for the organizations of the future and some practical advices how to set the up.

However, I found book a bit longer than needed. It had double focus: both theory and a lot of practical case studies, and that seemed too much for me.


## The Power of Habit

title: The Power of Habit  
subtitle: Why We Do What We Do in Life and Business  
authors: @{Charles Duhigg}  
isbn: 978-0-679-60385-6  
year: 212  
pages: 286  
rating: 3  
tags: #{habit}

### readings

started    | finished   | locations
---------- | ---------- | ------------- 
2016-09-10 | 2016-09-14 | San Francisco 

### review

Book describes habits of individuals, organizations and societies. 
It was interesting to read, but at the end I didn't get many insights that would remain with me for a long time.

The book easily could have been shorter.


## Ill Fares the Land

title: Ill Fares the Land  
authors: @{Tony Judt}  
isbn: 9781101223703  
year: 2010  
pages: 237  
rating: 5  
tags: #{history}  

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-08-07 | 2016-08-13 | San Francisco 

### review

Very good book with some recipes for society. Will going to reread it in the future.

Also I really liked the amount of quotes in this book.


## Brave New World

title: Brave New World  
authors: @{Aldous Huxley}  
isbn: 978-0-06-085052-4  
year: 1932  
pages: 152  
rating: 5  
tags: #{dystopia, anti-utopia}

### readings

started    | finished   | locations 
---------- | ---------- | ------------- 
2016-09-17 | 2016-09-27 | San Francisco 

### review

Great distopia written in early XX century. Suprisingly the world in the last 30 years become very close to the one described by Huxley.

The best book that explains Brave New World is Amusing Ourlselves to Death by Neil Postman.


## The Medium is the Massage

title: The Medium is the Massage  
subtitle: An Inventory of Effects 
authors: @{Marshall McLuhan, Quentin Fiore}
year: 1967  
isbn: 978-1-58423-070-0  
pages: 159  
rating: 4  
tags: #{media} 

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

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-03-27 | 2017-04-01 | Gili Trawangan, Indonesia

### review

Introduction to Buddhism and especially Zen Buddhism. Very easy explanation of the core concepts.

I really like the idea that Buddhism is Hinduism for export, that is Hinduism that stripped from culture and can be used as religion by people of different cultures.


## China Disruptors

title: China's Disruptors  
subtitle: How Alibaba, Xiaomi, Tencent and Others Companies are Changing the Rules of Business  
authors: @{Edward Tse}  
isbn: 978-0-241-24039-7  
year: 2015  
pages: 256  
rating: 4  
tags: #{China, technology, business}  

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
rating: 3  
tags: #{politics}  

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-04-30 | 2017-04-30 | San Francisco

### review

Too simple and too tied to the things that were going on in the USA during 2016.
My expectations were much higher, especially given authors background as a historian.


## The Disappearence of Childhood

title: The Disappearence of Childhood  
authors: @{Neil Postman}
isbn: 0-679-75166-1  
year: 1994
pages: 176  
rating: 5  
tags: #{childhood}

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

### readings

started    | finished   | locations
---------- | ---------- | -------------
2017-12-03 | 2017-12-24 | San Francisco

### review

I like it because it's an autobiography with a lot of personal details. Was interesting to read on career in chess and martial arts.


## A Little History of Philosophy

title: A Little History of Philosophy  
authors: @{Nigel Warburton}
isbn: 978-0-300-15208-1  
year: 2012
pages: 252  
rating: 5  
tags: #{philosophy, ideas}

### readings

started    | finished   | locations
---------- | ---------- | -------------
2018-01-16 | 2018-01-18 | San Francisco

### review

Great book as intro into philosophy. I think these types of books work for me very well.


## Treatise on Modern Stimulants

title: Treatise on Modern Stimulants  
authors: @{Honoré de Balzac}  
isbn: 978-1-939663-38-2  
year: 1839  
pages: 80  
rating: 3  

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

### readings

started    | finished   | locations
---------- | ---------- | -------------
2019-03-24 | 2019-03-31 | San Francisco

### review

Engaging book. Learned a lot about region I knew so little. 
The book laid out the history of the region and history scientific inqury into it. 
I've read it just in few days and couldn't stop reading.


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
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}]) 
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
              [:a.link.f6.b.mb1 {:href (:id entity)} (:name entity)]
              [:div.mt1.mb0.mh0
                [:span.f7.ml0.mb0.mr0 "from " (:started entity) " to " (:finished entity)]
                [:span.f7.ml0.mb0.mr0 "; travelled " (:distance entity) " kms"]
                [:span.f7.ml0.mb0.mr0 "; emission " (montaigne.fns/format-float (:carbon entity) 2) " tons of CO2"]
              ]
              ])
          %)]
      ]
    ]])
```

@id: `(montaigne.fns/slug (:name %))`  
@itinerary.airport-from: `(first (filter (fn [row] (= (:from %) (:IATA row))) (->> %airports)))`
@itinerary.airport-to: `(first (filter (fn [row] (= (:to %) (:IATA row))) (->> %airports)))`
@itinerary.airport-from-lon: `(:lon (:airport-from %))`
@itinerary.airport-from-lat: `(:lat (:airport-from %))`
@itinerary.airport-to-lon: `(:lon (:airport-to %))`
@itinerary.airport-to-lat: `(:lat (:airport-to %))`
@itinerary.distance: `(montaigne.fns/calc-distance (:airport-from-lat %) (:airport-from-lon %) (:airport-to-lat %) (:airport-to-lon %))`
@itinerary.carbon: `(montaigne.fns/calc-carbon (:distance %))`
@distance: `(apply + (map :distance (->> % :itinerary :value)))`
@carbon: `(apply + (map :carbon (->> % :itinerary :value)))`
@started: `(:date (first (->> % :itinerary :value)))`  
@finished: `(:date (last (->> % :itinerary :value)))`  
@days: `(montaigne.fns/duration-in-days (:started %) (:finished %))`  
@year: `(montaigne.fns/get-year (:started %))`

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
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
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
              [:dt {:class "dib gray"} "Started:"]
              [:dd {:class "dib ml1"} (->> % :started)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Finished:"]
              [:dd {:class "dib ml1"} (->> % :finished)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Days:"]
              [:dd {:class "dib ml1"} (->> % :days)]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Distance:"]
              [:dd {:class "dib ml1"} (->> % :distance) " km"]
            ]
            [:dl {:class "f6 lh-title mv2"}
              [:dt {:class "dib gray"} "Carbon:"]
              [:dd {:class "dib ml1"} (->> % :carbon) " tons of CO2"]
            ]
            
            ]]
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
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "carbon"]]
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
                      [:td (->> row :carbon)]])
                  (->> % :itinerary :value)
                )
              ]
            ]
          ]]
      ]
    ]])
```


## Temecula Feb 2019

from: San Francisco  
to: Temecula

### itinerary

from | to   | date       | type   | flight | aircraft | emissions 
---- | ---- | ---------- | ------ | ------ | -------- | ---------- 
SFO  | SAN  | 2019-02-24 | flight | AS1956 | A318/321 | 192.2 lbs CO2 
SAN  | SFO  | 2019-03-01 | flight | AS1969 | A318/321 | 192.2 lbs CO2 


### summary

First time in this part of SoCal. Didn't do much since it was work event.


## Vail Feb 2019

from: San Francisco  
to: Vail  
type: friends  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | --------  
SFO  | DEN | 2019-02-14 | flight | UA1013 | 777-200 
DEN  | SFO | 2019-02-18 | flight | UA571  | 757-200 

### summary

First time in Colorado. Great weather, great slops - exactly as I like.


## Buenos Aires and Paris

from: *{San Francisco}$  
to: *{Buenos Aires, New York, Paris}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | EWR | 2018-11-15 | flight | UA535  | 757-200 
EWR  | EZE | 2018-11-17 | flight | UA979  | 767-400 
EZE  | MAD | 2018-12-23 | flight | IB6856 | A340-600 
MAD  | ORY | 2018-12-24 | flight | IB3436 | A320 SHARKLETS 
CDG  | OAK | 2019-01-05 | flight | DY7079 | 787-9 


## Tijuana 2018 Last Trip

from: *{San Francisco}  
to: *{San Diego, Tijuana}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | SAN | 2018-10-12 | flight | UA361  | 737-900  
SAN  | SFO | 2018-11-15 | flight | UA334  | 737-900  


## Mexico City and Tijuana 2018

from: *{San Francisco}  
to: *{Mexico City, Tijuana, San Diego}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | MEX | 2018-10-05 | flight | UA412  | A320  
MEX  | TIJ | 2018-10-08 | flight | Y4813  | A320  
SAN  | SFO | 2018-10-09 | flight | UA662  | 737-800  


## Ukraine First Trip in Years

from: @{San Francisco}  
to: @{Kyiv, Kharkiv}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | EWR | 2017-11-16 | flight | UA1796 | 777-200  
JFK  | MUC | 2017-11-17 | flight | LH411  | A340-600  
MUC  | KBP | 2017-11-18 | flight | LH2544 | A320  
KBP  | FRA | 2017-11-27 | flight | LH1493 | A321  
FRA  | SFO | 2017-11-27 | flight | LH9052 | 777-300  


## Hong Kong Birthday Trip

from: @{San Francisco}  
to: @{Hong Kong}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | HKG | 2017-06-29 | flight | UA869  | 777-300  
HKG  | SFO | 2017-07-10 | flight | UA862  | 777-300  


## New York City May 2017

from: @{San Francisco}  
to: @{New York}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | JFK | 2017-05-04 | flight | DL1144 | 717-200   
JFK  | SFO | 2017-05-11 | flight | DL426  | A320  


## Indonesia 2017

from: @{San Francisco}  
to: @{Singapore, Bali, Gili}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | SIN | 2017-03-24 | flight | SQ1    | 777-300   
SIN  | DPS | 2017-03-25 | flight | SQ946  | A330-300  
DPS  | SIN | 2017-04-09 | flight | SQ943  | A330-300   
SIN  | SFO | 2017-04-09 | flight | SQ2    | 777-300  


## Hong Kong Second Trip

from: @{San Francisco}  
to: @{Hong Kong}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | HKG | 2016-10-29 | flight | CX0879 | 777-300  
HKG  | SFO | 2016-11-07 | flight | CX0870 | 777-300  


## Hong Kong First Trip 2016

from: @{San Francisco}  
to: @{Hong Kong}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | HKG | 2016-08-24 | flight | CX0873 | 777-300  
HKG  | SFO | 2016-09-06 | flight | CX0892 | A350-900  


## London First Trip 2016

from: @{San Francisco}  
to: @{London}

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | LHR | 2016-05-06 | flight | OS7856 | 777-200  
LHR  | SFO | 2016-05-15 | flight | OS7857 | 777-200  


## STL 2015

from: @{San Francisco}  
to: @{St. Louis}  
purpose: StrangeLoop conference  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | DEN | 2015-09-23 | flight | UA1736 | 737-900  
DEN  | STL | 2015-09-23 | flight | UA3395 | ERJ-145  
STL  | SFO | 2015-09-28 | flight | UA6421 | ERJ-170  


## Portland First Trip 2015

from: @{San Francisco}  
to: @{Portland}  
purpose: Clojure/West conference  

### itinerary

from | to  | date       | type   | flight | aircraft 
---- | --- | ---------- | ------ | ------ | -------------- 
SFO  | PDX | 2015-04-18 | flight | UA464  | A320  
PDX  | SFO | 2015-04-22 | flight | UA995  | 737-800  


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
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
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
              [:a.link.f6.b.mb1 {:href (:id entity)} (:name entity)]])
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
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
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

Excessive consumption of tobacco, coffee, opium and spirits produces grave disorders and drives one to an early death. The organs, endlessly irritated, endlessly nourished, become hypertrophied; they become 
abnormally enlarged, damanged and corrupt the machine, which succumbs.


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
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
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
              [:a.link.f6.b.mb1 {:href (:id entity)} (:name entity)]
              [:div.mt1.mb0.mh0
                [:span.f7.ml0.mb0.mr1 "from " (:from entity) " to " (:to entity)]
              ]
              ])
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
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
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
reading    |  ✓  |     |     |  ✓  |     |     |    
spanish    |     |     |     |     |  ✓  |     |  
pushups    |     |  ✓  |     |  ✓  |     |     |  
edu event  |     |     |     |     |     |     |  
ent event  |     |     |     |     |     |     |  
cul event  |     |     |     |     |     |     |  
cycling    |     |     |     |     |     |     |  
tennis     |     |     |     |     |     |     |  
ping-pong  |     |     |     |     |     |     |  

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |     |    
no coffee  |     |     |     |     |     |     |  
no sugar   |     |     |     |     |     |     |  


## week 6

start: 2019-04-07  
end: 2019-04-14

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

