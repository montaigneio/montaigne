# readings

description: Books I've read

### template

```clojure
(montaigne.parser/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f3.athelas (:name %)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (:id entity)} (->> entity :title :value)]])
          %)]
      ]
    ]])
```

@id: `(montaigne.parser/slug (:name %))`

### @template

```clojure
(montaigne.parser/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
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
          [:h2.f5 "review"]
          [:article.lh-copy.measure
          (->> % :review :value)]]
      ]
    ]])
```


## Building a Bridge to the 18th Century

title: Building a Bridge to the 18th Century  
subtitle: How the Past Can Improve Our Future  
authors: @{Neil Postman}  
isbn: 978-0-375-70127-6  
year: 1999  
pages: 224  
rating: 5
tags: #{philosophy, history, media, education,}

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

This book is even more actual now in the age of Internet, mobile phones, social networks, 
instant deliveries etc.  


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

Also, it seems, that even if it accounts for the Russian revolution, 
the pattern is the same for all them. 
This book is a reminder to us how events usually unfold and what happens at the end. 
And what kind of people typically end-up on top in any system regarding of the title.  


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

Havel is role model. He shows us waht a public figure, a politician and a citizen ca be.  
He is definitely not afraid to think deeper and to express himself. 
He is not afraid of expressing not popular opinions. 
He tries to understand pluses and minuses of each system. 
E.x. he is for continous political integration, but he thinks globalization is a problem. 
He recognized the pitfalls of both Soviet regime and consumerism society that both causes normalization and standartization of people.

It was interesting for me to read his book for several reasons: to learn his story; 
to learn about his thoughts; to take a look at his diary. 
The diary of the President was an interesting read on itself. 
It contained his agenda for a lot of days and it showed him as a real simple very likable human, 
which is quite opposite of the politicians of the day.

Havel wanted to be a role model. 
He wanted to remind his fellow citizens of better and bigger ideals, 
he wanted to inspire them to improve. 
Politicians of today mostly move in opposite direction. 
They just represent the worst parts of our society and our people. If society is corrupt, politicians are corrupt. 
If society is uneducated, politicians are profane.


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

started   | finished   | locations
----------| ---------- | -------------
2016-12-04| 2016-12-10 | San Francisco

### review

Collection of essays on different topics from education, childhood, politics, technology, television etc. 
Postman, as always, provides insights into our culture (mostly American) and suggests some practical ideas how to prevent negative ongoing trends. As always he uses wisdom of sages, thinkers, writers, and philosophers from the past to help us understand current social issues.

His essays and observations draw a picture of quite sad future. A lot of this predications are already fulfilled, so Postman is unfortunately was very accurate in them which he poses as warnings for us.

However, what I liked about this book is that he tries to stay optimistic. He reminds us that civilized society is very vulnerable and we should pay attention to dangers. And he tries to provide us with a mindset how to do just that.


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

started   | finished   | locations
----------| ---------- | -------------
2017-04-30| 2017-04-30 | San Francisco

### review

Too simple and too tied to the things that were going on in the USA during 2016.
My expectations were much higher, especially given authors background as a historian.


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

started   | finished   | locations
----------| ---------- | -------------
2017-04-15| 2017-04-24 | San Francisco

### review

Great book on the technological developments in China. 
Learned a lot about Chinese tech companies, the way they started and the way they do business.


## Treatise on Modern Stimulants

title: Treatise on Modern Stimulants  
authors: @{Honoré de Balzac}  
isbn: 978-1-939663-38-2  
year: 1839  
pages: 80  
rating: 3  

### readings

started   | finished   | locations
----------| ---------- | -------------
2019-02-10| 2019-03-03 | San Francisco

### reviews

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

### readings

started   | finished   | locations
----------| ---------- | -------------
2019-03-24| 2019-03-31 | San Francisco

### review

Engaging book. Learned a lot about region I knew so little. The book 
laid out the history of the region and history scientific inqury into it. 
I've read it just in few days and couldn't stop reading.


# trips

description: My trips  

### template

```clojure
(montaigne.parser/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}]) 
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
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

@id: `(montaigne.parser/slug (:name %))`

### @template

```clojure
(montaigne.parser/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
      [:main {:class "ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns"}
        [:h1.lh-title.f4.athelas (:name %)]
      ]
    ]])
```


## Temecula Feb 2019

from: San Francisco  
to: Temecula, CA  

### itinerary

from | to   | date       | type   | flight | aircraft | emissions 
---- | ---- | ---------- | ------ | ------ | -------- | ---------- 
SFO  | SAN  | 2019-02-24 | flight | AS1956 | A318/321 | 192.2 lbs CO2 
SAN  | SFO  | 2019-03-01 | flight | AS1969 | A318/321 | 192.2 lbs CO2 


## Vail Feb 2019

from: San Francisco  
to: Vail  
type: friends

### itinerary

from | to  | date       | type   | flight | aircraft  
---- | --- | ---------- | ------ | ------ | -------- 
SFO  | DEN | 2019-02-14 | flight | UA1013 | 777-200 
DEN  | SFO | 2019-02-18 | flight | UA571  | 757-200 


## Buenos Aires And Paris 2018

from: San Francisco  
to: *{Buenos Aires, New York, Paris}
  
### itinerary

from | to  | date       | type   | flight | aircraft
---- | --- | ---------- | ------ | ------ | --------------
SFO  | EWR | 2018-11-15 | flight | UA535  | 757-200 
EWR  | EZE | 2018-11-17 | flight | UA979  | 767-400 
EZE  | MAD | 2018-12-23 | flight | IB6856 | A340-600 
MAD  | ORY | 2018-12-24 | flight | IB3436 | A320 SHARKLETS 
CDG  | OAK | 2019-01-05 | flight | DY7079 | 787-9 


# quotes

description: My collection of quotes

### template

```clojure
(montaigne.parser/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
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

@id: `(montaigne.parser/slug (:name %))`

### @template

```clojure
(montaigne.parser/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
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
tags: @{politics, criticizing}  

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
tags: @{technology, television commercials, utopia}  

### quote

Television screens saturated with commercials to promote the Utopian and childish idea that all problems have fast, simple, and technological solutions.


## Balzac on consumption

authors: @{Honoré de Balzac}
tags: @{consumption, coffee, alchohol, drugs}

### sources

book                           | pages | chapter
------------------------------ | ----  | ---------------------
Treatise on Modern Stimulants  | 6     | I The Subject at Hand

### quote

Excessive consumption of tobacco, coffee, opium and spirits produces grave disorders and drives one to an 
early death. The organs, endlessly irritated, endlessly nourished, become hypertrophied; they become 
abnormally enlarged, damanged and corrupt the machine, which succumbs.


# activities

description: Log of my activities

### template

```clojure
(montaigne.parser/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
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

@id: `(montaigne.parser/slug (:name %))`

### @template

```clojure
(montaigne.parser/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %)]
      (if (not (nil? (:description %)))
        [:meta {:name "description" :content (:description %)}])
      [:link {:rel "stylesheet" :type "text/css" :href "https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css"}]]
    [:body
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
          [:h2.f5 "activities"]
          [:article.lh-copy.measure]]
        [:section
          [:h2.f5 "intake"]
          [:article.lh-copy.measure]]  
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
reading    |  ✓  |     |     |     |     |     |    
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
no alcohol |  ✓  |     |     |     |     |     |    
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

