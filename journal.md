# readings

description: Books I've read

### template

```clojure
(str "<ul>" 
    (clojure.string/join "" 
        (map 
            (fn [entity]
                (str "<li><a href='" (:id entity) "'>" (:name entity) "</a></li>")) %)) 
    "</ul>")
```

@id: `(montaigne.parser/slug (:name %))`

### @template

```clojure
    (str 
        "<html>"
            "<head>"
            "<meta charset='UTF-8'>"
            "<link rel='stylesheet' type='text/css' href='https://npmcdn.com/tachyons@4.11.1/css/tachyons.min.css'/>"
            "</head>"
            "<body>"
                "<main class='ph3 pb3 pt2 ph5-ns pb5-ns pt2-ns'>"
                    "<h1 class='lh-title f3 athelas'>" (:name %) "</h1>"
                    "<header><div class='pv2'>"
                    "<dl class='f6 lh-title mv2'>"
                        "<dt class='dib gray'>Authors:</dt>"
                        "<dd class='dib ml1'>" (clojure.string/join ", " (->> % :authors :value)) "</dd>"
                    "</dl>"
                    "<dl class='f6 lh-title mv2'>"
                        "<dt class='dib gray'>ISBN:</dt>"
                        "<dd class='dib ml1'>" (->> % :isbn :value) "</dd>"
                    "</dl>"
                    "<dl class='f6 lh-title mv2'>"
                        "<dt class='dib gray'>Year:</dt>"
                        "<dd class='dib ml1'>" (->> % :year :value) "</dd>"
                    "</dl>"
                    "<dl class='f6 lh-title mv2'>"
                        "<dt class='dib gray'>Rating:</dt>"
                        "<dd class='dib ml1'>" (->> % :rating :value) "</dd>"
                    "</dl>"
                    "<dl class='f6 lh-title mv2'>"
                        "<dt class='dib gray'>Dates:</dt>"
                        "<dd class='dib ml1'>" "from "(->> % :readings :value first :started) " to " (->> % :readings :value first :finished) "</dd>"
                    "</dl>"
                    "</div></header>"
                    "<section><h2>review</h1>"
                    "<article class='lh-copy measure f5'>"
                    (->> % :review :value)
                    "</article></section>"
                "</main>"   
            "</body>"
        "</html>")
            
    
```

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
2019-10-02| 2019-03-03 | San Francisco

### reviews

Very short and simple book. Didn't find a lot of interesting things when read.
However, I think it's an interesting book to be written at 1839.
Also the topic is as relevant as always and insights are true and common knowledge now.

# trips

description: My trips  

@id: `(montaigne.parser/slug (:name %))`

## Temecula Feb 2019

from: San Francisco  
to: Temecula, CA  

### itinerary

from | to   | date       | type
---- | ---- | ---------- | -----
SFO  | SAN  | 2019-02-24 | flight
SAN  | SFO  | 2019-03-01 | flight


## Vail Feb 2019

from: San Francisco  
to: Vail  

### itinerary

col1 | col2 | col3
---- | ---- | ----
v11  | v12  | v13
v21  | v22  | v23


## Buenos Aires And Paris 2018

from: San Francisco  
to: *{Buenos Aires, Paris}
type: friends  

### itinerary

type      | from | to
--------- | ---- | ---
airplane  | sfo  | dwr
airplane  | dwr  | sfo

## Summer 2018

from: San Francisco  
to: *{Kyiv, Barcelona, Santorini, Athens, Copenhaghen}  
type: family  

### itinerary

type      | from    | to
--------- | ------- | ---
airplane  | sfo     | dwr
airplane  | dwr     | sfo
boat      | athens  | santorini

## Los Angeles 2018

from: San Francisco
to: Los Angeles
type: solo

### itinerary

type      | from  | to
--------- | ----- | ---
airplane  | sfo   | lax
airplane  | lax   | sfo

# quotes

description: My collection of quotes

@id: `(montaigne.parser/slug (:name %))`

## Havel on critizing

authors: @{Václav Havel}  
tags: @{politics, criticizing}  

### sources

book                   | pages | chapter
---------------------- | ----- | ---------
To the Castle and Back | 5     | Chapter 1

### quote

People told me exactly what I would later often say to others when tring to draw them into politics: 
you can't spend your whole life criticizing something and then, when you have the chance to do it better, refuse to go near it.

## Postman on promotion of fast technological solutions

authors: @{Neil Postman}  
tags: @{technology, television commercials, utopia}  

### quote

Television screens saturated with commercials promote the Utopian and childish idea that all problems have fast, simple, and technological solutions.

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

description: My activities

@id: `(montaigne.parser/slug (:name %))`

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
pushups    |     |     |     |     |     |     |  
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