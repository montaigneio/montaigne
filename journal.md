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
            [:a.link.f6.b.mb1 {:href "/writings"} "Writings"]
            [:p.pa0.ma0 "My essays"]]
          [:li.mb3
            [:a.link.f6.b.mb1 {:href "/readings"} "Readings"]
            [:p.pa0.ma0 "Books I've read in the last couple of years"]]
          [:li.mb3
            [:a.link.f6.b.mb1 {:href "/poems"} "Poems"]
            [:p.pa0.ma0 "Experiments with poetry"]]
          [:li.mb3
            [:a.link.f6.b.mb1 {:href "/haiku"} "Haiku"]
            [:p.pa0.ma0 "Wiring haiku"]]
          [:li.mb3
            [:a.link.f6.b.mb1 {:href "/talks"} "Розмови"]
            [:p.pa0.ma0 "Розмови з людиною"]]
          [:li.mb3  
            [:a.link.f6.b.mb1 {:href "/trips"} "Trips"]
            [:p.pa0.ma0 "My trips in the last 10 years"]]
          [:li.mb3  
            [:a.link.f6.b.mb1 {:href "/activities"} "Activities"]
            [:p.pa0.ma0 "Keeping track of things I do everyday"]]
          [:li.mb3  
            [:a.link.f6.b.mb1 {:href "/quotes"} "Quotes"]
            [:p.pa0.ma0 "Wiki with the quotes I found"]]
          [:li.mb3  
            [:a.link.f6.b.mb1 {:href "/now"} "Now"]
            [:p.pa0.ma0 "Some information about me"]]
            ]
      ]
    ]])
```


# data

author: `"Anton Podviaznikov"`  
airports: `(:airports (montaigne.fns/http-get-json "https://ohgodhelp.us/js/airports.json"))`  
flags: `(montaigne.fns/http-get-json "https://raw.githubusercontent.com/matiassingers/emoji-flags/master/data.json")`  

# writings

description: My essays

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %coll)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> %coll :description :value)))
        [:meta {:name "description" :content (->> %coll :description :value)}])
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
        [:h1.lh-title.f3.athelas (:name %coll)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (str "/" (->> entity :id :value))} 
                [:span (->> entity :name)]
              ]
              [:div.mt1.mb0.mh0
                (if (not (nil? (->> entity :date :value)))
                  [:small.f7.ml0.mb0.mr0.gray "written on "(->> entity :date :value) " "])
                (if (not (nil? (->> entity :locations :value)))
                  [:small.f7.ml0.mb0.mr0.gray "in " (->> entity :locations :value)])
              ]
            ])
          (reverse (sort-by (fn [ent] (->> ent :date :value)) %)))]
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
        [:h1.lh-title.f4.athelas (->> % :name)]
        (if (not (nil? (->> % :content :value)))
          [:section
            [:article.lh-copy.measure
              (->> % :content :value)]])
        [:footer {:class "mt4 cf lh-copy measure f6"}
          [:p.i.fr
            [:span.i {:itemprop "locationCreated"} (->> % :locations :value)]
            [:span "&nbsp;&nbsp;"]
            [:span.i {:itemprop "datePublished"} (->> % :date :value)]
          ]
        ]
      ]
    ]])
```

## Words

description: Personal story on how words can be misused  
date: 2018-12-21  
lastmod: 2018-12-21  
tags: Ukrainian, words, language, personal
locations: Buenos Aires  

### content

I like words. I usually pay attention to what other people are saying.
Sometimes it would be _easier_ if I didn't.
But easier doesn't mean better of course.

I know three different languages. And I know bits from few more.
I like to reflect on the nature of words.

I had a personal example of language misuse that haunted me for at least a year.

Once my ex-partner called me an _evil_ person. 
It was said in clear mind and repeated a lot of times during different occurrences afterwards.

That shocked me profoundly. I did know what _evil_ means.
I started to question myself a lot. Don't I know something about myself?
Am I lying to myself? Do other people see something in me that I can't see?
It was a lot of soul-searching.
I started to pay attention to all the references of _good_ and _evil_ in the literature.

I grew up with these concepts. They were everywhere: fairy tails, cartoons, myths, films.
I shivered every time I remembered this accusation. 
It was the opposite of what I believed in and valued.

Wikipedia gives this definition of _evil_:

> Evil, in a general sense, is the opposite or absence of good. It can be an extremely broad concept, though in everyday usage is often used more narrowly to denote profound wickedness.

For me, the simplest definition of _evil_ is "premeditated act of doing something wrong."

And I was reading through definitions and reflecting time and time again.
One day I confronted my ex-partner and explained what _evil_ means according to other people.
I asked if knowing this definition her accusation still holds true.
She switched it to another term. Which ironically was also incorrect, but that is for another story.

I was a bit relieved after this. But I still didn't understand how did that happen in the first place.
Why was I called _that_ initially? What was the thing person _really_ wanted to say?

In the last year, I believe I found answers to both of these questions.

The problem was that all that communication happened in the Ukrainian language.

In Ukrainian, the noun "evil" is "зло". And the adjective "evil" is "злий".
So I was called _злий_ meaning _evil person_. However, the problem that _angry_ in Ukrainian
is also _злий_. Ukrainian language simply doesn't have a separate word for _angry_.
So _feeling angry_ in Ukrainian is _being evil_. Those are two different things!

Ukrainian language doesn't have a separate word for one of the emotions or feelings
(however we define it nowadays).

What is even more disturbing that "злий" word (meaning angry) has a very negative spin to it in my native language.
When you have something that has negative connotation you want to get rid of it and dissociate yourself from it.
Which is the wrong way to view anger altogether. 
It has its own function and is quite important. 
And if you reflect on your anger you would understand a lot about yourself.

Anyway, it was an Aha moment when I realized that all that happened just because Ukrainian language lacked one word in it.

Two lessons that I learned from this situation. 

Listen to people, but make sure that you and they understand some basic words in the same way. 
A lot of miscommunication happens because people understand things differently.
Read common definitions. That is precisely why we have vocabularies - to establish common meanings.

Cross check words from different languages. Cross check definitions. 
Some words are extremely outdated. We can create new words if needed too. 
Without past baggage.
An environment in which we operate matters. 
Culture and language in which we are surrounded set defaults and context.


## Slavic Hospitality

description: Explaining Slavic Hospitality  
date: 2018-08-12  
lastmod: 2018-12-13  
tags: generalization, Slavic, hospitality
places: Kyiv, Ukraine, New York City, Montenegro
locations: Kyiv

### content

One of my favorite restaurants in the New York City is a place close to East Village that 
serves Balkan food. It is called _Kafana_.

I've spent 1.5 years living and working in Montenegro, traveled to most of the countries 
in the region multiple times. That region is my second home.
That is why _Kafana_ is a place I always try to attend while in NYC.

I like reading their menu, I love hearing Serbian or Croatian language at that place.

During one of my previous visits, I finally became aware of the following phenomena.

I was sitting in _Kafana_ and observing new guests coming.
Owner - middle-aged man from Balkans - would be very grumpy, not smiling to his foreign guests.
You'll get the vibe that they are not welcomed, and he actually doesn't care if they are here or not.
Or you might think that he is in a bad mood today.

But then suddenly came a group of people who he knew. Everything changed, he was welcoming, laughing,
spending time with them at their table, sharing news, etc.
He transformed in the most welcoming and happy person who really wants to feed these people and talk to them.


At that moment I realized - this is just generalization - that is how Slavic hospitality looks like.
You are grumpy and unwelcoming and uncaring towards strangers, and you are the most welcoming person 
towards _people you know_.

Since I became aware of this idea, it became more comfortable for me to travel to Eastern Europe myself.
I see the same thing happening in Ukraine over and over again.
As I said, my explanation is just a generalization, this thing happens not in all the cases.
From my Ukrainian experience, I can say that maybe in 50% of coffee shops I might experience
grumpiness, but this number shrinks rapidly every year.
People in the coffee shops and restaurants are becoming more and more welcoming and smiling. 
And this is the change I definitely welcome.


## Senseless smells

description: Smells of Ukraine  
date: 2018-05-01  
lastmod: 2018-12-21  
tags: smells, scents, Ukraine  
places: Ukraine
locations: Berlin-San Francisco

### content

On my recent trip to Ukraine I discovered how my senses can be 
constantly bombarded with new stimuli.
Stimuli distract us, take us from inside ourselves into the outer world.
Sometimes they also bring memories, make us more aware of surroundings.

__cigarettes__

I can smell them on the streets. Either from people I don't know 
or whom I hug.

__toilet air purifiers__

Being in a restaurant I would go to a bathroom and have a
flashback from the past. Still the same, highly chemical, direct, powerful, non-organic, unpleasant smell. 
Smell that is forever associated in my mind with Ukrainian toilets.

__petroleum__

I am in the taxi, making my way through the traffic. I open the window in the car.
Smell of the petroleum. It's my face. It's everywhere. It comes inside my cra.
Everyone still has this old non-environmental friendly car.
It's a smell of a poor country I grow up 20 years ago.

__laundry detergent__

I am staying at the city for a few days now. I want to do my laundry.
I buy local detergent. My clothes are clean now. But there is a problem.
They smell now. They covered with unpleasant, not natural artificial smell.
I try to get rid of this smell by rinsing my stuff over and over again.
It's impossible. It's stuck. Same scent as 2 decades ago.
Now I'm bound to wear it for the rest of my stay. I wear my memories now on me.

__beer__

Ukrainian beer is different. Bad taste, bad smell. I inhale it, it brought me back in time.
I remember now again why I don't like it and didn't miss it.

Scents are everywhere. They have a power to bring us back in time.
For the better or worse. They can make us nostalgic or happy for a moment.
But important part is that they cannot make us present in a sense.
They make us feel something but also to reflect on our tastes, our values and even lives.
They are tools of time travel, they trigger in us contact with something important, 
but if they are unpleasant they took us out from the flow, 
from this particular moment.


## The Age of Constructive Conversation

description: On the style of communication that is yet to come
date: 2018-02-25
lastmod: 2018-12-13
tags: post-mortems, reflections, failures, action items  
people: Kahil Gibran, Woody Allen, Aziz Ansari  
places:   
locations: San Francisco  

### content

A couple of weeks ago I was ill at home.
As I had some spare time, I spent some of it reading Twitter.
That was the week of few sexual scandals. One involved American comic Aziz Ansari and another one involved Woody Allen.
I couldn't read too much of the comments because it was too depressing.
But the general feeling I've got that audience split into two camps: the difference was only whose side each party supported.

Later I had the same type of conversation replicated in the offline setting.

Few things disturb me in what I've noticed:

- as I said, there are always _two_ camps. And discussed issue  used for polarization and delaminating line of the conflict
- the discussion is mostly emotional and not rational. It's usually about expressing emotions and fears, but not understanding what happened
- the discussion is mostly about individual people and blame. The blame would be assigned to the discussed individuals, and no effort would be made to identify underlying structural real reasons for the discussed situation
- the discussion is usually about past and not the future. I've heard a lot of arguments about why some person was wrong. But I never heard ideas or what _to do_ about the issue. What actions each one and all of us should take to solve the problem

There are different functions that conversation perform.
Informational exchange, emotional exchange, probing of what our peers think and into our "normality."
What lacks nowadays is the function of knowledge seeking. Engaging in the conversation to not exchange information but to seek knowledge and to generate it.

Wouldn't it be great if we approach conversation willing to learn something new, 
change our opinions, find underlying reasons for issues, 
find underlying relationships between different notions and at the end generate some ideas how to solve the problem.

Let's go back to the Aziz-Grace story.
Do we need really to blame _one person_ in the story?
Let's assume that you took an easy route and decided that the only person who made a mistake is Aziz.
You can spend all your energy (which would be mostly hatred - negative type of energy) explaining why he is wrong.
However, even with simple analysis, you might see that both parties had a bad experience
(to a different degree of course, but this situation is not a win-lose situation, both parties were losers).
A bit later, you realized, that _both parties_ had opportunities to stop the escalation of the situation into the problem and _both parties_ failed at that.
For a conflict between individuals to happen there should be two people involved.
That is a simple insight that gives some hint about how to solve the given problem.
A bit later, you might realize, that this story is not about Aziz and Grace at all and that this situation happens in all type of relationships
(and not exclusively romantic one).
From the higher level of view, it seems to be a problem of communication between two individuals in general.
Then you might think who is _really_ guilty in this situation.
Was this result of the pure failure of Aziz, or Grace, or both? But aren't they also the products of their societies and their culture.

Kahil Gibran wrote in The Prophet:

> Oftentimes have I heard you speak of one who commits a wrong as though he were not one of you, but a stranger unto you and an intruder upon your world.
> But I say that even as the holy and the righteous cannot rise beyond the highest which is in each one of you,
> So the wicked and the weak cannot fall lower than the lowest which is in you also.
> And as a single leaf turns not yellow but with the silent knowledge of the whole tree,
> So the wrong-doer cannot do wrong without the hidden will of you all.

And most importantly you might start thinking how to solve this problem for the majority of people. You want to maximize impact.
If you did recognize, that both parties are losers here,
if you did recognize, that both suffered,
if you did recognize that this failure was also systematic and cultural failure more than an individual one.

Then you might come up with a list of ideas/directions to work on:
- how about teaching kids of both sexes to communicate their boundaries better (and maybe even adults)
- how about teaching some skills of emotional intelligence, like body language
- how about improving how we teach sexual education
- how about making pop culture Hollywood movies on such topics, showing what is appropriate and what is not.
 Why do we have so many movies focusing on having sex on the first date, but no movies that show us how to communicate "no".

And when you realize, that the problem is systematic and that you are part of the problem you become more empathetic.
And empathy is positive energy.
The same goes for the producing list of ideas. You've created something new here.
Congratulation, you switched from the negative hatred to productive and positive creation.

I worked at the company called Runnable for almost three years.
Sometimes we had some significant incidents with our software when our customers wouldn't be able to use our service.
In this case, we had following procedures in place.
First, fix immediate problem and unblock customers.
Only after that call a post-mortem meeting where you try to analyze what happened on a broader level.
That meeting was never about whose personal mistake caused the outage.
We had blame-free culture, where we did recognize that outage is never the fault of one individual.
Someone might indeed push the wrong code. But that code was also reviewed by some else, and tested by tests (that were also reviewed) and several people. So in some sense, the fault was usually in the process we had.
And we tried to identify as a team what went wrong and came up with several action items that we could fix immediately.

I wish, we used similar procedures discussing our larger societal problems.
Acknowledge the problem, identify several root causes, create action items to fix them.


## The Best Game

description: Thinking about the nature of games  
date: 2018-02-09  
lastmod: 2019-01-21  
tags: games, football, competition, collaboration  
people: []  
places: São Paulo  
locations: San Diego  

### content

In January 2013 I was staying for a month in São Paulo, Brazil.
It was during my "digital nomad" stage of life where I traveled slowly and lightly.

While in the city, I decided to join YMCA and play football with local guys. 
I played every morning from 6.30-7.30AM five days per week. 
It was the best football experience of my life.

I'll try to decompose this a bit to understand what made it great.

I'm playing football for well over twenty years now. 
Quite often you are together on the team with the guy who thinks he is much better than everyone else and he would try to dribble past all the opponents and score goal all by himself. 
That would work one or two times per game, but that strategy is not reliable if the opposing team is any good. 
The well-organized side has much higher chances to beat a group of individuals who play all by themselves, even if individually each player on that team is better.

Brazilians played like a unit. A lot of them could easily beat opponents. 
But that is now what they chose. 
They understood the game on a deeper level. 
They knew that it's about collaboration. 
Scoring a goal because of the team effort is also remarkably fulfilling because it connects you with your team-mates.
Suddenly everyone becomes very important to the team. 
Everyone has a role to play and responsibility towards teammates. 
And if you make people _responsible_ for something - they would strive. 
They would try to improve; they would try to do their best and not let their team-mates down. 
They will be on their toes each minute, and they will try to play even better tomorrow. 

Usually, if less skillful people make one or two mistakes, no one would pass a ball to them until the end of the game. 
It is a terrible strategy that I saw too often during my life. 
When you make a mistake, and no one believes in you, then you lose confidence, and you lose motivation, and you more likely to make more mistakes. 
You lose sense and meaning of the game and the time. 
The best way to overcome that is by passing to the person who just made a mistake. 
They will feel trusted. 
And also since you involve them in the game they can improve their skills and make fewer mistakes. 
If you don't play with them - they would never become better. 
So the good teams have mechanisms for developing _all the players_, not just the best ones. 

In just a couple of weeks playing with those guys, 
I became much better player than I ever was. In addition to having more amount fun than ever.

What I learned from this is that:

- ego is a problem. People who play by themselves usually have a massive ego, and it's not fun to have them around.
- trust and responsibility are essential. Believe in your teammates and make them responsible for something. It's a win-win.
- victory as a result of collaboration (or team work) is much more satisfactory than any individual achievement
- there are different ways to play a game (or live life for that matter), not all of them are equal
- if fun and happiness are distributed - everyone wins 


## Joke is on Us

description: Effects of the comedy shows  
date: 2018-01-21  
lastmod: 2018-12-13  
acknowledgement: Thanks to Neil Postman for his ideas on connection between media and discourse
tags: comedy, politics, discourse, daily shows, modernity  
people: Stephen Colbert, John Oliver, Jon Steward, Donald Trump, George W. Bush, Neil Postman  
places: USA
locations: San Francisco

### content

Imagine two people. They are friends or colleagues or neighbors.
One person starts telling jokes about another, only criticizes her. 
All the time. 
Sometimes the jokes are offensive.
He says how stupid that person, how unreasonable all her thoughts are, how limited and second-class she is.

What would be the outcome of such behavior? 
At least it will be alienation, at most it will be rivalry and fight.

One person pumps his ego up at the expense of another person while destroying the possibility of the 
dialog, cooperation and any constructive type of relationship.
The second person might just either withdraw forever from such type of abusive relationship or will 
mirror the attitude towards her and start the fight.

And now think about "progressive" liberal media in the US. 
Starting from 1999 till today people like Jon Steward, Stephen Colbert, John Oliver
run highly popular daily/weekly shows where they laughed at people with whom they disagree.
The result of that is highly divided society each with each own media.

I've heard that such daily shows are a unique feature of American culture where it's allowed to make fun of anyone. 
I've been told that that is precisely the symbol of democracy. 
That might be a good point if such shows were merely additions to some serious media programs.
But as far as I can tell, at least for the young people, those programs are _the main_ source of political discourse.

Those programs divided people on "right"(pumping the egos up, because who don’t want to be "right" 
and it feels great to be on the "winning side") and "wrong".
It is unfortunate, because I saw this happened in Ukraine in the years between 1991 and 2014.
Once you have divided society it’s tough to bring it back.
Usually, some tragic events follow such situation.

On top of dividing society, those programs, and those comedians completely _trivialized_ discourse. 
Everything became a joke. There are different metaphors for life. 
I think John Oliver's metaphor is "life is a comedy… dark comedy".
When you make fun of Dalai Lama, Snowden, religions, elections - you create a world where there is no serious subject anymore. 
Every topic is a comedy, which is there only for our entertainment. 

In fact, the current President of the US is an entertainer and for a lot of people idea of him becoming a President was a joke.
But it’s the logical consequence of the world created by daily shows and comedians who became political commentators.
In the world of "old" political discourse, Donald Trump would never become a President. 
But in the "new" world of comedy, it's precisely him, who _should be_ a President.

Nowadays comedians also bring a lot of pure anger into the world. 
Please watch "Stephen Colbert at the 2006 White House Correspondents' Dinner" video and his commentary on it. 
He went there to make fun of President Bush, and he made him extremely angry by the end of the evening.
In what moral framework is it acceptable to _consciously_ make other people angry? 
He had no intentions for President to change his positions, opinions or actions.
Because if he had, he would approach the problem differently. 
I think all he cared about was his fame. 
He prioritized his ego and made the President of the United States angry.
When someone is angry, it's quite difficult to act and reason appropriately. 
You become less rational, more aggressive and bitter.
It doesn't matter if I agree with policies of President Bush, I think Stephen Colbert is a "bad guy" here. 
He is essentially a high-school bully.
Instead of being engaged in a constructive dialog he went for inappropriate behavior of the poorly developed teenager.

If you are liberal, why do you waste your energies blaming Trump, conservatives, Fox News, etc? 
What is the point in such blame?
_At least half_ of the responsibility lies on liberals. 
The only constructive way is to withdraw blame and hatred,
analyze own mistakes and follow up with some conclusions and lessons learned about own behavior.

We need to change ourselves first.


## Overcoming my prejudices

description: Learning my lessons. One lesson at a time  
date: 2018-01-17  
lastmod: 2018-12-13  
tags: life, life lessons, prejudice, universe  
people:   
places: Kyiv, Ukraine  
locations: San Francisco

### content

November 2017. I'm in the JFK airport, boarding a plane to Ukraine.
My place is in the last row; I notice my neighbor well in advance. 
She is senior American women in her late 70s. 
"Why I never get young and nice people as my neighbors on the plane so I can talk to them and make friends" - 
is the thought that pops into my head. Not very nice of me, I already made my judgments on a person I never met.

Of course, what happened next is precisely the thing you would expect from the universe. 
Simple lesson, straight to the point.
It was the best conversation on the plane I ever had. 
My neighbor shared the beautiful story of her life, her life in Italy and Tunis and then back to the US. 
I learned about her life choices, about her generation and her granddaughter's one. 
I learned about the 60s, how airplanes stayed pretty much the same. She was very curious about my destiny, my beliefs.

She felt very genuine, warm, wise - she was humane. 
I made a mistake of judging a person without knowing her and luckily was immediately corrected.
You'd think I learn my lesson. Far from it.

One week later. I'm in Kyiv. I want to do a manicure in the beauty salon. 
I have an appointment. 
I enter the room expecting to see a good-looking woman, of my age. 
I want to know what Ukrainian women of my generation think about. 
And of course, since I'm in the beauty salon, I expect everyone to be good-looking. 
Shame on me for such thoughts. My master shows up. 
She is in her late forties, slightly overweight women. 
I'm disappointed (shame on me again), my expectations failed me. 

But universe wouldn't give up and provide me the same lesson again. 
The woman was the best person I've met on that trip. She surprised me in so many aspects.
She got her first education in shipbuilding and worked at the factory; 
then she got a degree in economics. 
Eventually, she realized that she didn't like it and started doing nails for people. 
She worked at the beauty salon, and she loved her job a lot (very uncommon for a lot of Ukrainians that I know and who are older than 30). 
At her free time, she painted. 
And her dream was to open small gallery to support and promote young Ukrainian painters.
She was very optimistic, despite living through hard times. 
She was well-read and wise. 
She was very interested in sharing her thoughts and lessons and hearing mine. 
She was about doing what she liked, loving her family, helping people and enjoying her life. 
She felt complete and whole to me. One hour later we felt like good friends. 
She gave me a bit more belief in people, future, and my country, 
and I think I gave that to her too.

Where all these thoughts and judgments come from? 
Why I'm so close-minded at times, and why do I have those stupid beliefs and expectations? 
What are the reasons for it? Is it just me? How do I fix it?

Hopefully, I learned my lesson, and maybe I'll be a wiser person. 
At least in more situations than before.


## Life as a Dance

description: Dance as a metaphor of life
date: 2018-01-13
lastmod: 2018-12-13
tags: dance, life, metaphor, childhood  
people:  
places:   
locations: San Francisco


### content

Remember, when you were a kid, you could dance and enjoy it enormously even if you _didn't know how_ to dance?

After you grow out of childhood, you lose that bliss. 
You're starting to learn how to dance in the school. 
It's painful, and it takes time, and it might be not fun anymore. 
And those years are filled with effort, tears, setbacks, lost hope, varying levels of motivation, ups and downs, defeats and victories.

And then, maybe, if you're persistent and lucky, you might rediscover the joy from your childhood again. 
And that is the point where you know how to dance as professional, and you feel it and enjoy it as a kid. 
That is the integration point: your inner child integrated with an adult to form a mature creature. 

For me, that is the metaphor of life. 
You go from childhood - which most people enjoy - to sometimes painful period of adulthood where you need to learn how to live and how to bring that joy back, 
and when you're done - you are a mature person. 
The one, who has a deep understanding of life and know how to enjoy it and appreciate it. 

It is not about just rediscovering what was lost during adulthood. 
It's about bringing together all sides: mind and body and soul. 
It's this three-dimensionality that gives new depth to the joy. 
That is the whole point of growing up - to lose joy so you can find it again in a new broader sense and with new intensity.

Childhood is about not worrying about judgments from other people. 
You have no notion of shame, and you are yourself.
You lose it in the process of growing up but rediscover later if you are lucky. 
Maybe that was the meaning of Adam and Eve story. 
Becoming aware of themselves and ashamed of whom they are expelled those two from the paradise. 
They lost themselves. But the way back is also possible as the story tells us.


## Managers and Ministers

description: Ministers are managers  
date: 2018-01-08  
lastmod: 2018-12-13  
tags: Ukraine, politics, ideas  
people:  
places: Ukraine
locations: San Francisco

### content

In 2013 - before the revolution in Ukraine - I had this idea, 
that Prime Ministers and CEOs of large multinational corporation share mostly the same skills.

In 2013 it seemed to me that my home country had two principal problems: corruption and lack of talent. 
My idea was supposed to solve both of them.

Since Ukrainian politicians and government officials were poorly educated, 
corrupted and incapable of reforming the country in over 20 years, 
I thought that we need to invite professional managers from respected and successful foreign companies. 
Of course, to such professionals notions of corruption would be unthinkable. 
I was thinking about making offers for CEOs of Nokia, IKEA, Ericson, and the likes. 
It would work like that: pay competitive global salary, with 4 years contract and bonuses tied to the agreed KPIs.

I remember pitching this idea to few Ukrainian friends and, of course, they said that I was crazy.

There were two main arguments against it.
A foreigner can't run a country because she is not a patriot and her interests may collide.
My counterarguments are:

- Ukrainians stole much more money and made much more harm to Ukrainian state than any foreigner ever did. 
It is as simple as that. 
Most of the Ukrainian politicians and managers live at the bottom of the Maslow pyramid of needs - 
they don't invest in educating themselves, 
and they don't care about building a proper state, that is why they are least qualified to run the country.
- PM has nothing to do with international politics and national security. 
It's purely managerial role, and foreign policy and army are controlled by the President.

The second usual argument was that we can't afford to pay high salaries to PM and Ministers.
If you mention salary of 100K USD per year in Ukraine for the Prime Minister some people might go crazy. 
However, I think that is because they lack perspective. 
We live in the globalized world and in to compete for the top talent you need to pay premium salaries. 
Prime Minister of the country can't earn less than creative professional in the Silicon Valley. 
In fact, I think Ukrainian PM should earn around 1M USD per year.

According to [Wikipedia](https://en.wikipedia.org/wiki/List_of_salaries_of_heads_of_state_and_government) salaries of state leaders are following:

- Ukrainian President - 94K USD
- Russian President - 136K USD
- France PM - 200K USD
- Sweden PM - 220K USD
- Germany PM - 300K USD
- New Zealand - 325K USD
- Luxembourg - 340K USD 
- Switzerland PM - 495K USD
- Australia PM - 507K PM
- Singapore PM - 1631K USD

So why, I'm proposing to pay second biggest salary in the world to Ukrainian PM? 
I think, except Singaporean PM, those wages are unbalanced. 
Those are government salaries. Please, go look up salaries for CEOs of big companies. 
Most top companies pay well above 1M USD per year in base salary plus huge bonuses. 
Government salaries are lagging here, but that means they attract talent of lesser quality. 
If you want the best managers, who have an excellent education and excellent track record - pay appropriately.

If you still think 1M USD per year for PM is too much, please check out salaries for football coaches of national teams. 
In 2016 even Ukrainian National manager earned 170K Euro (which is higher than the wage of Ukrainian President). 
Top 9 European coaches earned between 1M Euro and 5M Euro per year. 
Fabio Capello, while coaching Russian National Team earned ~11M USD per year. 
Think about those numbers, and compare them to the salary of the President. 
Why did Capello receive salary well above the average? 
Because he is top manager coming into the average team and trying to make the difference.
Football coaches manage just 20-30 people, yet they receive salaries that are 20-100 times higher than people who manage the whole states. 
I'm not arguing that they salaries should be reduced, I'm arguing that even higher salaries should be paid for PMs of the countries which are serious about making the leap.

If you think about those numbers with a clear head, you will realize that proper professional government can deliver value much higher than the salaries they receive.

I want to acknowledge, that since the revolution Ukraine took a bit similar path to the one I described. 
In fact, decent amount of Ministers were foreign citizens. However, I think it's not enough. 
I think PM should be a successful international top manager. The same goes for Ministers. 
I think they should come from highly developed countries of Western Europe, Asia, etc. 
They should have the very best education in the world top schools.

I bet some of these successful managers would love to come to Ukraine. 
Because it will be a challenge for them - to modernize a country with 40M people. 
We talked about salaries a lot, but the challenge and the prospect of the big impact are what really drives these people. 
Salary is just a way of showing that you are serious about the project and believe in fair compensation.


## Eastern European Man

description: The Man from the Eastern Europe
date: 2018-01-02  
lastmod: 2018-12-13  
tags: Eastern Europe, ego, profanity, emotions, Ukraine  
people:  
places:  
locations: San Francisco

### content

Warning, unfair generalization and reversed sexism ahead.
This is just an exercise to describe one of the archetypes, don't take it too seriously.

Eastern European Man appears to be smart, tough, direct and confident.
He appears to be able to loudly disagree with a group of people. 
He is ready to put himself into the opposition/confrontation to the group of people of any size.
He appears to be also quite in most of the life situations. 
He looks like as if he knows something about life. 
He looks calm and it might seem like his calmness comes from inner security and integrity. 
Maybe he looks like he is at peace with himself and life.

However, underneath it is not like that at all. 
Eastern European Man is a child in a body of an adult.

His quietness and calmness come from the fact that he was never taught to express his emotions properly 
and integrate them into his life.

His directness in expressing unpopular opinion comes not from the fact that he is smart 
but from the fact that no one taught him how to behave in the groups of people. 
When Eastern European Man meets Westerner (and especially American), 
he sees a shady person who never speaks his mind. 
Immediately he feels superior. 
But he doesn't know that it is him who is shady in reality. 
You can't do much with direct, but aggressive and careless person. 
You can't build team or company, you can't build friendship or relationship, you can't build society and a country with such people.
Aggressive directness is toxic and counterproductive.

No one taught him that the best strategy for life is to be kind to other people, 
and no one taught him that there are different ways of expressing one's opinion. 
He only knows the way in which you isolate yourself, 
make other people feel bad (or stupid) and close possibility for the future dialog and development of relationships.

Have you been to programming conference or event? 
There is always this Eastern European Man in the audience who would ask some strange question 
to not help improve conversation or common understanding of a problem but to show off. 
Eastern European Man tries to show his intellectual superiority to everyone: 
his partner, colleagues, etc. 
It is acceptable to him that he would hurt every one of them in the process. 
But at least he is a "true man", he sticks to his principles and stays direct and "tough" at all time.

Eastern European Man always competes. He is never _for_ something, he is always _against_. 
He competes against everyone else. No one taught him that competition is good, 
but it should be in the balance with cooperation. 
The combination of cooperation and competition is the secret to every game, 
and to life and evolution itself.

Eastern European Man shouts at his colleagues and insults them. 
Just to show who is the boss, who is the smart one and the tough one. 
The tougher you are, the better you are. 
He would show that he does not care about you and despises you. 
Because he is independent and a loner. 
But, of course, it comes from the inner insecurity.

Eastern European Man swears a lot. To him, profanity is the symbol of adultness because only adults can do this.
But in fact it is opposite, real adults _know_ swear words 
and have the discipline and wisdom to not use it in every sentence.

Eastern European Man jokes at the expense of other people. 
He would mock and make fun of his friends. 
It should always be one-directional. Nothing like that should happen to him. 
For the Man, a joke is a tool to boost ego and to establish his superiority towards the other people.

In fact, Eastern European Man preoccupied with his ego all the time. 
Building it up makes him feel better. 
No one taught him that it is better to go the opposite direction with ego.

Eastern European Man doesn't know the difference between information, knowledge, and wisdom. 
He would think that he is smart just by knowing a lot of different facts (which is information). 
He doesn't know that there is something beyond that.

Eastern European Man looks grumpy. 
And you would think it's because he is so smart and read great literature 
and philosophy and he is preoccupied with understanding the world and life. 
But it's because no one told him that he can be happy and how to achieve that. 
He only knows how to be bitter about life.

Eastern European Man is of course nihilist. He believes in "Nothing Mattersness." 
Everything is someone else's fault, nothing can be done about it. 
Only he is enlightened but no one from these stupid people is smart enough to realize his greatness and start listening to him.

Eastern European Man is of course selfish. 
He doesn't believe in people, collaboration, and action. He is not preoccupied with self-transcendence.

Eastern European Man is, of course, unhappy person. 
But more than that he would be very willing to destroy fun for everyone else. 
If a group of people is having fun and the man can't participate, 
he would ruin the mood for everyone (by shouting or saying stupid things). 
If he can't have fun, no one should, and it's his right to make everyone feel in tune with him.

Eastern European Man wouldn't dance. He would explain it as a trait of a "real man," however, 
it seems like he couldn't integrate his body together with mind.

Eastern European Man has no feelings. 
He is a person of the intellect, and he is above emotions. 
But of course, it is because no one taught him how to integrate his emotions and feeling with his mind and body. 
Eastern European Man doesn't know that they are essential, he denies them, and is scared of them.

Eastern European Man is very romantic and believes in magic. 
For some reason, he thinks that other people can read his mind and read his feelings without him saying a word or expressing any emotions.


## Reduction

description: Reducing ourselves
date: 2017-12-29
lastmod: 2018-12-13
draft: false
tags: reduction, happiness, emotions, support  
people:   
places:  
locations: San Francisco

### content

We reduce people around us.
How often do we forget _all_ the good things about someone and focus on their "faults" at worst or few "positive" things at best?
When we forget to acknowledge good things about other people we create problems for ourselves and other people.

What kind of problems, you ask?

When you tell someone for long period (your child, your friend, your partner) 
that they are good at this _one_ thing they might start to doubt their wholeness 
- they might doubt that they have other "good" traits or even doubt that they are able to 
have them in the future.

When parents tell to their kid that she is good at e.g. "math" it will be engraved in the child's mind. 
The kid might never have courage or desire to approach art, or literature, or sport.

If you tell someone they are "this", that also might mean to them that they are not "that" and can never be anything except "this".

So such reduction, affects _your_ perception of another person, 
it might affect _self-perception_ of that person, and it will damage your relationships too. 
If you tell another person that she is "serious," you deny the existence of "funny" side. 
In such case, that person might never be "funny" with _you_.

In theory, we are all independent adult creatures, in practice, it is not like that at all.
Let's be careful with each other. Let's try to not project our insecurities on other people. 
Let's be explicit about all (even tiny) things we appreciate in each other. 
And also let's be conscious and mindful of those good things.

Maybe our kind words can change someone's life. 
Maybe we will bring a smile on someone face, or a tear of sadness followed by a tear of relief and happiness. 
Perhaps we will lift someone out of depression or difficult stage of life.

Let's not make assumptions about other people. Let's not think that our compliment will have no 
negative side-effects even if we said it with good intentions.
Let's be kind and gentle with each other. And let's be attentive. Let's acknowledge each other wholeness.


## Ukraine: Nov 2017

description: Looking through the new eyes
date: 2017-12-29
lastmod: 2018-12-13
tags: Ukraine, travelling, technology  
people: []  
places: Kyiv, Ukraine, Kharkiv  
locations: San Francisco

### content

In July 2015 I wrote a [post about Ukraine](https://podviaznikov.com/ukraine/) that defined two things 
that made me optimistic about Ukrainian future.

At that time for me, it was mostly theoretical exercise and even to some extent an example of wishful thinking.

In Nov 2017 after almost 4 years I visited Ukraine. What I saw _shocked_ me.
I was overwhelmed with emotions, thoughts, ideas.

What exactly did I see?

Downtowns of big cities (Kyiv and Kharkiv) looked completely different.
They were full of modern coffee shops, cafes, restaurants.
They were filled with people (both locals and foreigners).
Those places were modern in all senses:
 
- beautiful design - interior and brand - with attention to details.
- young, good-looking, English-speaking, _polite_ and _helpful_ personal. 
 This is a significant point, it wasn't like that before at all. 
 Only 30 years ago our service industry was full of angry, bitter ladies working for state-owned enterprises
- modern menu. Ok, when I saw browny,  merengue, marshmallows on the menu in Kharkiv - 
 I was both impressed but also started to really think about the speed of globalization. 
 That menu was no different from the one I see in San Francisco
- tourist-ready. As I said, personnel could speak English, all the menus had English translations.

Those coffee shops and cafes are examples of small businesses. 
As I argued in 2015, people are not afraid to start their own ventures. 
Small businesses are remarkably important. In my opinion, they drive change. 
They emerge from the bottom and not from the top. 
That is why I don't care which political party wins elections as long as people continue to take the initiative, start businesses and be active in all other spheres of communal and social life.
The best part that those small businesses look very modern (in the sense that I described above). 
By doing it the right way, they set an example for everyone else. 
The ones that would come after will build on this experience.

In 2015 I wrote, "we started to value intellectuals, it's becoming fashionable again to be smart and kind."
This thing happened! I'm so excited about it. When I left Ukraine in 2010, it was still cool to be “cool” (in the sense of “bad boys”).
While in Kyiv I saw a tremendous amount of small new libraries or coffee shops/libraries. 
Young people started to reading good modern books. 
I've met two software engineers, and how surprised I was when we realized that 3 of us read the same books 
(e.g., Peter Thiel's “Zero to One”). And I've got many good book recommendations.

At the same time, Kyiv is filled with trivia and quiz nights. I visited one music trivia Wednesday night.
There were like 20 teams with 6 people each trying to beat each other in the knowledge of music bands.
I was told that events of such type are happening almost every night. 
And there are music, history, sport trivia nights, etc.

In Ukraine 2010, it was cool to drink alcohol and gather with friends at one's apartment. 
Now it seems to be cool to do yoga, attend workshops, read books, visit jazz concerts, etc. 
Street fashion changed too! 
I've met British girl in Kyiv who lived there for 9 months, and she was complaining that Kyiv is even _too_ hipsterish for her. 
I'd take it as a good sign at this point in time.

_Following the leaders_. What I also noticed that people started looking abroad for ideas. 
Coffee shops with Western design are good examples of this point. There is no need to reinvent everything. 
Take what is working somewhere and use it. 
I saw the same in conversation with software engineers. 
They told me how their companies tried to improve themselves by studying books on hiring and managing at Google and other big US companies.

I also noticed how deeply _technology penetrated_ into society. 
Middle-aged Ukrainians are using Uber, asking about Bitcoin, buying drones, shopping online and using same-day delivery services.

For me, I practically saw no difference between creative class in San Francisco, Hong Kong, London, and Kyiv.
Forgetting about globalization issue, in general, I consider it to be a good thing for Ukraine at this particular moment in time.


## Issue with Tests

description: Issues I have with personality tests
date: 2017-05-29
lastmod: 2018-12-13
tags: self-knowledge, tests, personality  
people: []
places: []
locations: San Francisco

### content

I have this weird problem: I can't take personality tests. 
When I'm trying to do such tests, my brain starts working extremely hard.

How should I answer the question like "You often get so lost in thoughts that you ignore or forget your surroundings."? 
What do they mean by "often"? 
If I don't have a definitive answer, should I go through all my history and find in what percentage of cases I forgot about my surroundings? 
I find it impossible to give even a slightly precise answer to this question.

Or take another one: "You try to respond to your e-mails as soon as possible and cannot stand a messy inbox." 
How should I respond to that? It definitely depends on my motivation in that particular year, month or day. 
Sometimes I'm dedicated to cleaning up my inbox, sometimes I'm messy. 
I'm fast with replies for some people and not so fast with others. 
My response depends on so many factors that I can't just provide any meaningful answer to the posted question at all.

Whenever I tell someone I have this problem, they usually look at me with disbelief and say 
"What do you mean? Taking a test is easy, just don't think about it hard and quickly pick the answer that first comes to your mind". 
I heard this advice time and time again. 
It just isn't so easy with me. 
What is even more strange for me personally is that I am still to meet a person who has the same issue.

My mind couldn't accept the nature of the test: it's desire to reduce the complexity of our personalities to the numbers. 
I want it to provide as accurate model of me as possible, but I don't think tests designed to do that. 
They are designed to paint as broad picture as possible, and I have very little interest in that.

Recently a situation occurred that triggered even more scepticism towards the tests from me. 
A friend of mine asked a bunch of his and my friends to do Myers-Briggs personality test (there are 16 personality types). 
All the people besides him and me got ENFP personality type. 
So 6 people out of 8 got ENFP personality, but according to the global statistic, only 7% of the population has this personality type. 
Of course, 8 people are not enough to disprove global statistic, but it's enough to make one even more suspicious.

Here are the problems I see with personality tests:

- we don't know ourselves that well. It seems like personal questions should be the easiest. 
But I found it to be untrue. I'd go ahead and say that life to some extent is about learning about yourself. So if you don't know yourself perfectly well, you can't really assess yourself objectively.

- wishful thinking. People understand what features are "better" than others and then select them. 
Sometimes, we select option we would like to have, but not the one we have now.

- reduction. I'm ok being reduced to some type of personality, but it doesn't work. 
You can't fit individual into the model. It will be always to some extent wrong.

- behavior changes. Day to day. Based on moods, lessons, events, contexts, people.

- fluidity. Mind and soul are moving dots on the line, they are not fixed. 
Everything changes, and we are not even aware of all changes that happen to us.

- internalization. There is some desynchronized state between what we do and what we believe and know mentally. 

So we might behave differently than before, but we don't know why. 
And if someone asks you what do you do in the situation - you might provide an obsolete answer, 
just because your mind didn't fully catch up yet, and didn't internalize the situation.

- asserting your personality through the tests reminds me of the scientists trying to build mathematical models for the climate change. 
The world is too complex to comprehend and build an even slightly realistic model. 
The same applies to our personalities. 
The complexities are so high that I can't imagine a test that would be able to provide semi-accurate models of us.

Ok, so personality tests have a bunch of problems. Should you still take them? Absolutely. 
They can provide ideas, insights or rather questions for you to think about play with. 
They are just a tool to facilitate self-discovery, not to provide answers. 
So take them for what they are.


## Post-modernism

description: Thinking about defining features of the modern world  
date: 2017-05-19  
lastmod: 2018-12-13  
tags: post-modern world  
people: Adam Curtis  
places: []
locations: San Francisco

### content

I can't define what "post-modernism" is, however I realized that I can try to describe it, 
that is, describe life and trends of post-modern world.

Music of post-modern world is music without _meaning_, which means usually without words at all. It can be said about art probably too.

Movies of post-modern world are mostly _fantasy_ and futuristic action movies. 
Movies about existing reality and present times and present problems are not popular.

Virtual Reality will become more and more popular for the same reasons as fantasy movies.

_No dream_. John Lennon famously sang "The dream is over". 
That might be true in the context of individual but it's also true in the context of society. 
Society in the post-modern world has no shared communal dream (myth, narrative).

Political discourse either doesn't exist or exist in the form of the _comedy_. 
The most popular and famous political critics are comedians. 
This trend is true for various countries, from USA to Ukraine.

_Trivialization_ of everything: politics, environment, sex, media.

_HyperNormalization_ - majority people have same clothing (few brands and few styles), 
same phone (two providers), listen to the same music (few artists), 
watch same movies (made in the same place), have same opinions by there own choise.
Adam Curtius made a great [documentary](https://en.wikipedia.org/wiki/HyperNormalisation) about this topic.

_Facelessness_ - belief that everything is run by systems, not humans. E.g. market, or EU establishment, corporations - it's believed that there are no people behind it.

The rise of _profanity_. It's in media, television, offices, streets.

_Growing inequality_ - rich people getting richer across different countries from USA to Brazil.

_No alternative_ - general belief in the existing system, understanding it's flaws but refusal to believe in any alternatives. 
There is some belief that life is still getting better and that there is still some progress (at least small one). 
We an example have _wishfull thinking_ here on the global scale.

_Abundance of information_ - everyone can access information on the very complicated matters almost instantly. 
However, that changes nothing. Knowledge and wisdom is still as inaccessible as they were. 
All the available information provides no tips, no hope, no ideas.

_Communal schizophrenia_, by this I mean that we live in a world that doesn't seem real, 
but more like "Alice in Wonderland". 
E.g. chritistian conservative parties promote anti-Christian values, 
liberal politicians deploy drone attacks. 
Laws and legal acts get beautiful names that actually do opposite thing.

Continuing _globalization_ - archetypes of issues and politicians that we have in one country are very similar to the other countries.

_Unseriousness_ - people are not keen to talk on serious topics in larger groups (either politics, mental health or other topic). 
You are supposed to look happy and talk on fun topics in a group. 
When serious discussion, unpleasant news are brought up in a group - people look scared.

_Aging_ - aging seems like a scary thing for a lot of people. 
They will continue search for looking younger. 
20-35 years range is where majority of population is trying to visually remain as long as possible.

_Growing conservatism_ on both right and left, and general tendency for even bigger consolidation of right and left.

Protest becoming more _commoditized_. 
It's more about symbols and merchandise (t-shirts, hats etc) than substance and content.


## Soviet Union 2.0

description: On similarities between modern world and Soviet Union 30-40 years ag
date: 2017-03-05  
lastmod: 2018-12-13  
tags: modernity  
people: []
places: []
locations: San Francisco

### content

The modern globalized world looks surprisingly similar to the Soviet Union in 1970-1980s.

That might sound very contrarian, but I will try to explain what I mean using few examples.

The Soviet Union was pretty much _monocultural_ country. I mean that in two senses.

Firstly, it was Russian-language dominated culture. 
The rest of the nations and cultures were slowly moving into using the Russian language and melting with the Russian culture. 
I believe that the same is happening right now on the global scale. 
Afterward, it's the whole premise of globalization. 
All the countries and cultures are melting into the Western civilization and English as a language becomes more and more dominant.

Secondly, all the concepts of the shared culture were produced in few cultural centers and distributed in a centralized manner. 
The same is happening right now. 
We watch movies produced just in few places (mostly Hollywood).
We listen to the music produced by few producers. 
The culture is mostly English-speaking nowadays, and people across continents 
can relate to each other because of the same content they consume.

In the Soviet Union, all the _shops_ looked the same across the country. 
E.x. all the malls looked similar and were selling same goods, 
restaurants and cafes looked the same and had a similar menu, 
press kiosks looked the same and were selling the same newspapers and magazines across the country. 
The same thing is happening right now. 
You can find starbucks, mccdonalds, h&ms, zaras in the majority of the countries on this planet. 
You fly into some country, arrive at the airport that looks like any other airport and has all the same shops that 
sell the same brands and labels. 
Downtowns across different cities look the same with the same cinemas (that have same movies), 
same boutiques and mass-market clothing brands and same restaurants.

The modern globalized world has _limited political variety_. 
You may say that it's not true and that Soviet Union had only one party. 
But I would argue that there was always the opposition to the Communist Party. 
There were dissidents who provided another view/perspective. 
That is quite similar to the majority of the Western countries that had two parties for decades now. 
Two main parties that always win the election is not variety. 
Such setup doesn't provide plurality and ability to explain and solve problems in such a complex world we live in.

_Belief_. In the Soviet Union, there was a belief in planned, centrally controlled economy. 
Similar belief exists in the modern world, where everything can be explained and solved by the free market. 
Both systems believe in the human-made idea and bet heavily on it without any questioning.

_Normalization_. The Soviet Union tried to make everyone to be the same. 
People had similar clothing, similar haircuts, same education, same holidays. 
The same is happening now. 
People have same phones, play same games, watch same movies, get same news, 
wear few clothing labels and even have same haircuts (decided by what is particularly popular at this very moment). 
With all the theoretical variety capitalism provides, 
the system still produces very similar human beings (it might be two big groups in some rare cases, but that is still not a wide variety).

_No belief in the future_. In the Soviet Union, by the late 1970s, no one believed in the achievement of Communism 
that was promised in the 1960s. 
The dream became unreal and people saw that it's not achievable. I think, our modern world stuck with the same problem now. What is our current dream as a society or as a species? Do we have some common goal or story?

_Useless media_. Media was useless and untrustworthy in the Soviet Union, especially regarding international matters. 
However, the same is true regarding mainstream media in the modern world. 
You can't trust media because they represent private economic and political interests.

_Inequality_. The Soviet Union had two classes of people: the ruling class - "nomenklatura", 
and the working class - ordinary people. 
The difference in quality of life between those two classes was incredible. 
However, in the modern world this problem even more visible. 
The elites are getting unproportionally better of every day.

_National inequality_. The Soviet Union had the title nation, 
few "second-sort" nations and the rest was "third-sort" nations. 
The same is true for the globalized world. 
Some countries that are getting better in the existing system and countries that get exploited.

The _decline of spirituality_. The Soviet Union from the very beginning was against organized religion. 
However that also led to the decline of spirituality which is very different thing from the religion.
The same thing is happening now everywhere.

_Addictions_. The Soviet Union used to have epidemic of alcoholism 
(which state even tried to fight in the 1980s with the alcohol ban). 
But in the modern countries, we have epidemic of obesity and entertainment 
(in the form of television, Internet, games, etc). 
Both have the same reasons. 
Such epidemics happen when there is some disconnect between a human being and the world. 
And the result is the same: damaged body, mind, and soul.

_Architecture_. The Soviet Union is famous for its tasteless architecture style and buildings that look all the same. 
However, I do believe that it's true with modern architecture as well. 
As I mentioned before all airports look the same, skyscrapers are ugly and look the same, 
same is true for residential buildings. 
Probably 90% of new buildings are tasteless.

_Street look_. In the Soviet Union, a lot of propaganda posters were outside on the buildings and streets. 
They had no taste and no real value. 
Modern cities of today are covered with ads, which is a very similar thing - sell us ideas we didn't ask for.

_Imaginary enemies_. In the Soviet Union, there was the idea of "enemies,"  
that at that time were outsiders and some groups of people inside the country. 
Those "enemies" allowed the country to live in the constant threat. 
Same is true about the modern world. 
There are constant threats from "terrorist", new viruses, some "aggressive" nations, etc.

_Food_. There wasn't a wide variety of food in the Soviet Union. 
Even fruits and vegetables didn't vary that much (which can be explained by geography). 
However, I feel that the same is true with food in modern chains. 
All apples or bananas look and taste the same.

_Dogma_. In the Soviet Union, it was dangerous to "think" and to talk about ideas. 
In the modern society, it's not prohibited, but people don't do it anyway. 
People are afraid to explore ideas, they would rather stick to something they heard before, 
or they would be in a defensive mode to "protect" their preconceived ideas. 
Exploration mode is very rare these days.

_Incarceration_. The Soviet Union was famous for having prisons and putting large numbers of people there. 
However the same applies to some modern Western countries. 
Now almost 1% of US population is in the prisons.

_Antidemocracy_. Elections in the Soviet Union didn't depend on the popular vote in reality. 
People did vote, but results were predefined, because only one party were eligible for election. 
A lot of modern countries have same problems, the results of elections are also predetermined. 
They usually depend on the amount of funding for the poltical campaign. 
So formally, people do vote (as in the Soviet Union), but in reality, the results are known in advance.

_Power centers_. In the Soviet Union power belonged to the people who were not elected and got the mandate from the population. 
In the modern Western countries that it is also true. 
The real power belongs to the bankers, financiers, heads of corporations - people who didn't participate in any elections.

In my opinion, the lack of variability was one of the major critics of the Soviet Union.
However, I can say the same thing about the modern world. 
Sure, you can have variety, if you have enough financial resources, 
but the same was true in the Soviet Union, 
where the ruling class could get anything they wanted.

I tried to draw some analogies between the Soviet Union 30-40 years ago and the modern world. 
Maybe I made some factual mistakes, maybe my analogies are too stretched out, 
but it's up to you to decide. 
Anyway, I think it's a useful exercise to think what did happen to the world in the last 30-40 years, 
what changes were positive and what were negative.


## On space exploration

description: Exploring space exploration  
date: 2016-12-26  
lastmod: 2018-12-13  
tags: space, society, myths, narrative, Mars  
people: []
places: []
locations: San Francisco

### content

A lot of people get excited about advances in the space exploration in recent years. SpaceX, Blue Origin, NASA missions are all over the news. Discussing the space is fashionable again, space companies get a lot of highly-motivated engineers who want to work for them. All this is obviously not bad, but, in my opinion, space exploration is not the most difficult problem humanity faces, and definitely not the most important one.

Let me make a strong claim. In my opinion, working on space exploration right now causes more harm than provides benefits. It requires a lot of resources (human, financial, maybe energy), and we take them away from other more important areas by using these resources on space programs.

I have heard a lot of reasons why we need to work on space:

- we (humans) are explorers, and that is just what we do. To me it seems not enough to answer why we need to do it now.
- we can scientifically prove that religions are wrong. To me it seems that this was already done some time ago. World religions have been in decline for the last 500 years.
- we need to prove that we are not the only one civilization in the Universe. This requires two questions: 1) why do we need to have 100% scientific proof of it; 2) what will change of we have one? Can't we already assume with a very high probability that other civilizations exist given the size of the universe?
- we need to colonize another planet in case something happens to ours. This point seems valid to me. But this brings me to the initial point that space exploration is not a high priority task. What are the chances that humanity would disappear in the next 100 years because of some asteroid hit the Earth vs humanity would disappear because of terminal nuclear war or climate change - issues we are responsible for. It seems to me that latter is way more probable outcome and we should allocate our resources and work on these problems. In a nutshell, we need to make sure that this planet is in a good shape and all humans can live here for at least couple of centuries (if not millenniums) more.

Another point that I was making is that space exploration is not the most difficult problem we are facing. Why would I say that? There is a saying in which your compare simpler problems to a rocket science. "It is not a rocket science", they say. But to me it looks like rocket science isn’t the most difficult problem. It is resource-incentive, though - the more resources you put, the more results you get. At this point it scales pretty well linearly. In my opinion, it is moderately easy, because it is a "pure science". Scientific questions are well understood and are well isolated from complexities of other systems. Rocket science lives on the intersection of other "pure sciences", like physics, astronomy, math etc.

In my opinion the most difficult problems are those that require different bodies of knowledge to solve them, and such problems truly exist in a complex, highly tangled and interconnected systems.

One example of such problem is, of course, global warming. There is no pure technological or "scientific" solution to it. The real solution requires political, economic, cultural, and technological changes. Same is true for poverty, peace, inequality, education, unemployment etc. Those are the problems that are truly difficult and which are very hard to solve at the modern, that is, global scale. Those are the areas where we need research, human, financial and other resources.

To my mind space exploration is easy because it deals with data. What do missions do? They collect photos, samples of minerals - that is raw data. It is easy to work with data. True knowledge and wisdom are not required for it. It's like playing a game at the beginners level, while there are 5 extra exponentially more complex levels.

My final question is why do we need to make colonies now? We don't have a proper answer how to run them yet, what political, economic and social system to setup there? I assume we don't want to copy the model of the Western world with wild capitalism and extreme spending on defense. Why then bother going to Mars, unless we want to make interplanetary wars a reality very soon.

I think we should try to design new better systems here first. New planet settlement sounds great if we could have a healthy and happy society over there. But to build it there we first need to try to do it here, or at least try to develop ideas first.

Philosophy, psychology, sociology, politology, economics, ecology are not the rocket sciences. They are much more difficult because they do not work or exist in isolation. They exist in the complexity of our everyday life and of our societies. Truly difficult problems are here. And are we up to the real challenge?

P.S. I wrote this essay before I read Joseph Campbell's essay "The Moon Walk - the Outward Journey". I'm not incorporating any changes to my essay yet based on his work. I do like his view of the importance of space exploration to humanity. In his mind, space exploration would allow us to have new Myths, that we so deeply in need of. However, I still have to think about his opinion. It made sense to me, but he lived in a very different time from our own. He lived in a time of first trips to space, constant improvement, enrichment of middle class, huge positive social changes after the WWII. We live in a time when the level of optimism about future is quite different from that of 50 years ago.


## Non-believers

description: Refusal to believe in the systems' stability in the modern world  
date: 2016-11-24  
lastmod: 2018-12-13  
tags: liquid modernity, Eastern Europe  
people: Zygmunt Bauman
places:  
locations: San Francisco  

### content

Recently at work, we were discussing 401K - personal pension fund that you can contribute to during life and use after the age of 65. 
I wanted to be persuaded to use it and listened to all the arguments made by my colleagues, 
but they didn't work for me.

I tried to think why having 401K is a no-brainer for my American friends and why my perspective was so different.

The answer lies in my background and my comprehension of history, states, society, institutions, etc. 
It seems to me that my colleagues believe in the stability and immutability of the existing system they live in. 
Consciously or not they bet that in 40-60 years the system they live now would remain unchanged: 
 
- the United States would still exist as a country
- banks and financial institutions would not go bankrupt
- laws to protect savings of the regular people still would be in place
- the balance of social power would remain similar to the one we have today and middle class would still exist in similar 
proportion and have some political voice

It is a tough bet to make in my opinion, the probability of change in each system might not be high, 
but overall probability of general change is much higher - because it's multiplication of individual probabilities.

To be fair, I'm happy that my colleagues can hold such belief in the system's stability and predictable future. 
To some extent, I would like all of us to have this belief because it is much easier to live when uncertainties are low.

However, my background does not allow me to believe in such story even if I wanted to.

I think this belief in the unpredictability of the future is quite common among Eastern Europeans. 
And there are historical reasons for that.

Here are the facts.

I'm older than Ukrainian state.

My grandparents and parents grew up in the country that disappeared in 1991. 
All of them lost their savings. 
All of them experienced a radical change in the way of life, values, ideology, etc. 
They would never be able to anticipate coming changes few years in advance.

My grandparents lived through WWII as kids.

My great-grandparents were born in the Russian empire, lived through WWI as children, witnessed the collapse of the empire, 
revolution, civil war, and emergence of the new state of Soviet Union.

Even in my not long adult life I already experienced two revolutions in Ukraine and one war. 
I lived through the countless revolutions and wars across the globe, global economic crisis, 
banks going bankrupt and people losing their savings.

If I were born 100 years earlier - in 1887 instead of 1987 - I would experience following events:

- by the age of 27, I would see WWI starting
- by the age of 30, I would see Russian revolution, the collapse of Russian empire and emergence of the new states
in my late 30s and 40s, I would live through Stalin's repressions and genocide
- by the age of 52, I would see the start of WWII that would last six years
- by the age of 60, I would witness the start of the Cold War (and would probably die before it ends)

If I could live until the age of 104 years I would see the collapse of the Soviet Union
I think that it's valuable to map scale of one's life to the history. 
The speed of history is increasing with each new generation after all. 
Many changes may and will happen during the 80 years of person's life.

Exact changes are unpredictable and uncontrollable, but with high confidence, 
we can predict that extreme changes will happen in the next 40-60 years, and we should plan for this fact.

Another Eastern European - Zygmunt Bauman - describes our times as "liquid modernity." 
Those are the times of chaotic change. 
We change ourselves constantly; we flow through the life-changing places, jobs, life partners and even values. 
If we are changing ourselves, how could we expect large systems - states, banks, culture, etc - 
to remain stable and still exist in half a century?


## Implementation details

description: Working on the wrong level of abstraction
date: 2016-11-12  
lastmod: 2018-12-13  
tags: post-modern world, future, politics, capitalism  
people: [
places: []
locations: San Francisco

### content

We live in a post-modern world driven by the discussion of the wrong issues. 
It's an obvious thing to say when the year 2016 comes to an end.

"Discourse" is mainly centered these days around things we shouldn't even care about: 
tax breaks, income tax rates, problems of global terrorism, immigration, etc.

In my opinion, there is no need for us now to agree on specific policies and actions - those are implementation details. 
We don't even need to agree with some ideas either (e.x. the idea of the free market). 
Ideas are very vague, we all understand them differently, they can be easily misinterpreted. 
But the main problem with ideas is that they bundle too many things together. 
E.x. we couple both dreams and ways of implementing them - the idea of capitalism or democracy for some reason nowadays 
carries the assumption how it should be implemented.

What I think we should discuss more are values. 
Values usually remain unstated both in interpersonal dialogs and discussions on the higher levels. 
I think we should strive to change this situation and be very explicit in stating our values 
and enquiring about values of people and systems we deal with. 
Before we even start discussing why some idea or policy (implementation detail) is good or bad, 
we should clarify each others' values and rank them. We should try to find some consensus after such discussion.

The next step would be to reflect what are some problems with our existing values and dream up of 
some possible future values we would like to have. 
I think that political parties, social movements, people organizations should work on this level. 
They used to work on ideas and ideologies in the XX century, and on specific practical policies in the past few decades.

We should approach thinking about future in the following way:

1) let's identify our current values and their precedence

2) let's find some problems with our current values

3) let's dream up of what values we would like to have in society in 10, 20, 50, 100 years (and not what the income tax should be in 2018)

4) let's compile those values in a well-defined system and focus on talking about it

The rest is implementation details. 
Yes, it can be difficult to come up with the ideal solution in this complex and interconnected world, 
but some progress and solutions with positive impact are always possible.

For my engineering friends, I have a following metaphor for the issue I'm discussing.

We have a complicated system because we coupled interfaces and implementation details. 
Everything is so messy right now that it's hard to see what is where. In my opinion values are interfaces. 
They are high-level things that should be clearly defined and exposed. 
Interfaces are necessary because they allow communication between actors. 
Policies, actions, decisions, processes are implementation details. 
They should be implemented in the best rational way (given all the constraints) to comply with defined interfaces. 
E.x. the income tax is not a question of great importance in such approach. 
Pick the one that helps system to conform to the defined interface (achieve desired declared values).

Another great benefit of such clear separation is that it's easier to run the migration on a live system, that is, 
provide a real positive change. 
It's much easier to understand interfaces between system A and B and came up with an appropriate plan 
than to understand messy coupled beasts we have now.

Our branches of life: economy, technology, philosophy, education, science should be values-driven. 
They should be considered in the context of the future societal values and not some specific metrics they rely on now.

To reiterate, I think that _implementation detail_ < _idea_ < _values_ < _desired values_. 
Let's steer discussions and the whole discourse to the appropriate level of abstraction.

Also working on the proper level of abstraction can eliminate whole classes of problems automatically. 
E.x. if only we can rethink our values, a lot of issues we are discussing today would be eliminated: 
terrorism, an immigration crisis, tax issues and global warming. 
This sounds too idealistic and naive in 2016, but I think humanity did it before and can switch to the new way of thinking again.

I don't want to discuss now what one should do next after agreeing on values, that is, 
I don't want to discuss implementation details. 
I think 1) it's an easy problem to solve once you have a simple system design, 
2) it would contradict the whole message of this essay, which is _start from the most important issue first_.


## On obsession with numbers

description: We live in a world driven by numbers  
date: 2016-10-14  
lastmod: 2018-12-13  
tags: technology, numbers
people: []
places: []
locations: San Francisco

### content

We live in a world obsessed with numbers. 
They are everywhere, from Google Analytics, which is installed on the [majority of popular sites](https://en.wikipedia.org/wiki/Google_Analytics#Popularity), 
to numbers of followers, likes, tweets etc.

Do we really need to use and rely on the numbers in all places and situations? 
Let's dive deeper and discuss few examples.

While reading a book on Amazon Kindle it would highlight some of the most popular phrases bookmarked by other readers. 
I personally noticed that whenever I see such highlighted section I'd bookmark it too in the most of the cases (maybe ~80%). 
Only recently I realized this behavior of mine. 
It's an interesting use case and seems like a very dangerous side-effect because my actions are mostly automatic. 
I might like or agree with highlighted phrase not because it has value, but because there is a social proof for it. 
Whenever I encounter such section I would probably spend less time and energy thinking about it, forming my own opinion, because I would rely on conclusions of other people. 
The second reason why this may be dangerous is that it gives Amazon the possibility to manipulate peoples' opinions.

All Amazon needs to do is to highlight some sections of the book. I'm not suggesting that Amazon is doing this or going to do, all I'm saying that the possibility is there. 
It's a similar, although more subtle, situation with Google that has the power to manipulate public opinion, influence elections etc.

Let's move on to Google Analytics.

In theory, it's a tool that helps site owners to 1) collect data about visitors, 
2) make sense of the data and improve content on the site. 
But is it really that simple?

First of all, it's not only that site owner has access to the data but Google itself has such access. Putting so much data and trust into one company doesn't seem like a wise and reliable long-term solution.

Anyway, assume you've got your data. 
Let's say you run online blog/magazine or video hosting site. What are you going to do? 
You will find the most popular content (the one that attracts more views/interactions) and then you:

1) will try to promote it more. E.x. you would put top 10 most popular articles/videos/songs on your home page at the very top

2) will try to create more of the similar content

The main problem here is that there is no direct relation between quality (or value) of the content and it's popularity. 
Some Ph.D. work on physics is much more valuable to the society than the most viral action video, pop song, funny picture etc. 
So what are you going to do? If you blindly rely on analytics and few basic metrics then you are going to change/create your content in a way that maximizes your numbers, simply because you can't track the quality of the content by Google Analytics and such metrics don't exist right now in those tools.

There is a high chance that metrics suggested by Google Analytics would become your master and would drive future decisions about your product. 
There is a great talk by Joe Edelman who explores our obsession with maximizing numbers. 
He gives an example of YouTube. YouTube has no information on your personal goals while you interact with it. 
YouTube doesn't know that you want to learn to play guitar right now at this moment, 
but what YouTube knows is the most popular content slightly related to you. 
So if at any point of time you watched a music video, funny video or comedy on YouTube you will have similar recommendations while you will try to focus on learning how to play a guitar.

Why do we have a number of views below every YouTube video, or a number of retweets and likes for each post? 
Why do we need to know how many people liked a post on Facebook, or our photo on Instagram? 
If I want to watch a video why should I care how many other people watched it? 
Wouldn't it influence my opinion about it? 
Why can't we leave it up to the every user to decide for themselves if the content is worth something? 
People should be able to formulate their own opinions. 
Otherwise, it becomes a dogma when 1 billion views for pop song validates its worthiness.

The same goes for creators. 
I'm not sure having all those numbers available to the creator can influence her work positively. 
When you create something worthy it's not always possible for people to appreciate it at the moment. 
There are multiple examples of how now-famous artists died unknown and poor. 
Or how their work were heavily criticized by contemporaries (e.x. Huxley's Brave New World). Metrics are all about the current moment. 
They are not focused on the big picture or big idea, there is no notion of perspective in them. 
I'd argue that works of Shakespeare, Hemingway, Mozart would be totally different if they used tools available to us and cared about numbers. 
Because art is about expressing oneself and one's ideas and not about the anticipation of a public opinion. 
Moreover, to express a new idea you need to be free of numbers, and should not be influenced by public opinion.

I'll give you one more example that might be relevant. 
Paul Graham's website doesn't have any number of views, visitors or Google Analytics snippet installed. 
And that makes sense because a lot of his essays you can read years later.

As Neil Postman expressed in Amusing Ourselves to Death, 
in the modern times we are focused on the current moment which is dictated by our mediums. 
And our mediums are all about feeds nowadays. And feeds are about numbers. 
How many new items do you have today in your feed, how many unread items are there and so on. 
But again, those numbers are mostly focused on entertainment, not on a value that content provides. 
So numbers usually promote something entertaining. 
It's a way to keep something in a feed.

Another problem is that those numbers are becoming a part of the content itself. 
The published book would include appraisals by famous people or newspapers. 
The movie title would include credentials of the director and references to her other successes. We would know how many copies an album sold. 
We would remember how much money particular Hollywood movie gathered. 
So this information is a part of the movie/album/book now.

Another challenge with numbers is that they can be manipulated. 
You can buy views, you can make your content popular by spending money and promoting it. 
So if you have enough money, you can make something mediocre to become very important in the context of the current culture. Why do we need to have a system that can be gamed that easily with enough resources? 
Why do we need to have a system that prioritizes described distribution of the resources? 
Instead of spending time on value creation we are spending it on marketing in the bad sense of the word.

And final problem with the new mediums and our reliance on numbers is that they discriminate old content. 
Mozart doesn't have online identity, he has no Instagram, no Twitter, 
and he doesn't have authentic music videos on YouTube and can't even remotely be compared to Kanye West in the context of the current way of thinking (centered around numbers).

The extreme rise in the quantity of information and content in the last century led to the abundance of it. 
We feel lost now because there is a huge amount of books, songs, movies, articles etc. 
The time is a limited resources, and we need to be careful while choosing what to consume next.

How was this problem solved? 
It was solved with the help of the top/hot lists. We have top lists for everything now: Billboard Top 100, 40 before 40, Forbes 500, top stories on Medium, trending songs on Soundcloud, most popular tweets and Twitter Moments, most liked Facebook posts. 
This sounds like a noble approach - help us to navigate through all this information. But at what cost? It creates a mass culture. It makes people have similar tastes, opinions, fears, beliefs. It makes us lazy and dependent on some external entity.

So, giving all the information above, what can we do now? 
There are few simple ideas I was thinking about. 
Some include changes in your behavior:

- just keep in mind that not everything should be made easier for us. Easy things make us lazy and laziness leads to degradation.
- be mindful of your personal goals and reflect if some site/app helps you to achieve them or just distracts you.
- to cope with the abundance of information and content there is a simple solution - just ignore it. Opt out. 
 This idea was hinted by Alain de Botton when he said that the most important things happened not yesterday or today. 
 In fact, they probably happened decades and centuries ago. Pay attention to timeless things.

Some are technological ideas:

- remove Google Analytics and other similar tools (that is how I run this site).
- decouple numbers from content. E.x. don't show how many views/or comments your blog has.
- maybe have browser extension that cleans up sites and removes comments, numbers of views, suggestions etc.
- explore new ways of consuming content. Maybe as consumers we need more control on the content representation? Maybe browsers should be split into simpler tools?


## On side effects of technology

description: Evaluate new problems created by software technology  
date: 2016-10-13  
lastmod: 2018-12-13  
tags: side-effects, Brave New World, technology  
people: []
places: []
locations: San Francisco  

### content

Every technology, solution, and tool are about tradeoffs. 
I yet to come up with any example that brought only positive change to the world and our lives. 
Opposite examples are too easy: dynamite, cars, TV, Internet, phones etc.

Let's consider a tool like Yelp and focus on problems such technological solution created. 
Yelp influenced how we pick a restaurant or cafe. 
Right now you go to yelp.com, enter your query and you have a new place to visit. 
Sounds incredible, isn't it? What can be wrong with that?!

I'd argue that such flow creates a major problem influencing our lives and our happiness.

If you've picked a restaurant through Yelp you would mostly know what to expect from it. 
You know its ratings, reviews, best dishes, you saw photos of the food, 
you know what to expect from service, how the place looks inside. 
You formed your expectations of a place. 
In some sense you already experienced a portion of your emotions, 
if your imagination is vivid enough you probably lived through part of your intended experience.

I'd go further and suggest that after you actually visited restaurant you wouldn't be able to fully experience a place because:

1) you've already spent part of your emotional budget selecting the place.

2) you've eliminated the factor of surprise. 
Because your expectations were set before, you have only a small margin for the surprise. 
If you don't have expectations possibilities are much higher because your satisfaction/happiness level depends on a baseline. 
If you use Yelp you basically anchored your emotion to a particular level.

3) risk and reward. Using Yelp eliminates risk, however, it also eliminates reward. 
If you found few places wondering around some neighborhood you need to pick one and by picking one you are taking the risk. You might end up in a really terrible place, or amazing, or just "ok". 
Anyway, the potential emotional swing is much higher this way.

Yes, you may end up in a disappointing place, but why are we so afraid of it? Aldous Huxley in a Brave New World describes the society that focuses only on "happiness" and avoids any deviations as a plague. 
Aren't both happy and unhappy moments are integral parts of our life? 
Can one exist without another? 
Can you even experience happiness/satisfaction without occasionally feeling unhappy/unsatisfied? Why are we afraid negative emotions? Why we are not learning this skill? 
It's important to know how to handle bad situations, anxieties, worries, conflicts and not just avoid them.

What I'm arguing here is that Yelp not just eliminates some inconveniences from our lives. 
But it also eliminates to some extent two very important notions: surprise and risk. 
Life without surprise seems not only boring but what is worse predetermined. 
Giving existing google and yelp data it would be possible to pre-calculate all your restaurants till the end of your life. In this case, it's just about the food, but this can be extended to other areas of life as well.

I'm not arguing here that technological change is bad and we should stop it by all possible means. 
At the end of the day the change, as [Kevin Kelly](http://kk.org/books/the-inevitable/) put it, is inevitable. 
What I'm advocating here is awareness. 
Awareness (of both positive and negative effects of the technology) creates freedom and possibility for every individual to independently evaluate technology based on its true merits. Awareness is knowledge and knowledge brings freedom. After all aren't we trying to build a society of free and knowledgeable individuals?

P.S. Case for restaurant selection and using Yelp might sound too primitive. 
Exactly the same analysis can be applied to picking up travel destination, choosing a movie to see or a book to read or a person to go on a date with.

P.P.S. Idea of surprise is not just some abstract concept. 
It is what worked for me personally. 
E.x. my favorite restaurants are the ones I found randomly passing by. The same works for travel. The best trips were the ones I didn't plan in advance.


## On reading

description: My relationships with reading  
date: 2016-10-12  
lastmod: 2018-12-13  
tags: books, reading  
people: []
places: []
locations: San Francisco

### content

In this post I'll present my own relationships with reading, how it evolved over years, current challenges I'm facing with it and will think about possible solutions. 
Here we go.

Reading: first years
--------------------

Before school (up to 7 years) I was a very slow reader, I struggled a lot and my mom struggled to teach me. 
This problem continued well into the first half of the first year of school. 
My reading speed was one of the worst in the class. 
I vaguely remember what happened but I think during the winter break I picked up a popular Soviet children adventure book _The Adventures of Dunno_. 
I liked it a lot. 
I read it very slowly but after finishing I was hooked (and my reading speed improved dramatically) and continued with two other books from the series. Dunno/Neznaika, in my opinion, was like Harry Potter for Soviet and some post-Soviet kids like myself. 
I might have read at that time few more books in the same genre, like Gianni Rodari's _Chipolino_ and some others. 
The point is that by the end of my first school year I enjoyed reading a lot and my reading speed was one of the best in my class.

I think it is important to give a context to these first years. 
I grew up in a small western town in post-Soviet Ukraine. 
What was good about that world is that we didn't have much of now traditional media entertainment. 
We had only three TV channels, 
they didn't work during the day (so when I was back from school, there were not only no cartoons to watch but also no TV). 
Every weekday evening we didn't have electricity for two hours. 
So basically your TV entertainment was reduced to a tiny fraction of the time: we might watch either news or one TV show or one movie. 
We didn't have many cartoons either, 
it was usually 5–10 minutes cartoon during the evening program for the kids 
and one hour of some Disney cartoons on Saturday mornings. That was it.

Now imagine Ukrainian winter in the 90s. 
You can't go much outside (because of the weather) and the days are extremely short (it's getting dark around 4PM). 
So you are spending a lot of time inventing your own entertainment. 
Reading was usually the top choice. I also had few old video games on the Nintendo-like console.

I would spend summers in a village with my grandparents. 
My usual schedule was following: help grandma to look after chickens before the lunch (it is very boring activity so a book is a savior in that situation) 
and then play soccer in the evening when it's not that hot anymore. 
That is it. 
The rest of the time you have nothing to do. Once again you need to find your own entertainment, for me, it was cycling and reading. 
Reading was my favorite activity.

So during elementary school years, evenings without electricity, winters with short days and summers I read a lot. 
If was mostly adventure books. I didn't have any reading plan yet. I would pick up some books from local library. 
During this time I read about adventures of Hercules, _The Wonderfull Wizard of Oz_, _The Wonderful Adventures of Nils_, _Adventures of Tom Sawyer and Huckleberry Finn_, 
_Timur and His Squad_, I've read also some fantasy back then and I've read more children adventurous literature of the Soviet era.

Those were my first years reading. I think this stage was mostly about adventures 
(I read some others more serious books at the time, but I think they were rarer at this period). 
For me, it lasted from 1993 till 1997-98. I would call it a "Harry Potter" stage. 
However, I have never read or watched Harry Potter because the first book was published in 1997 and it came few years later to Ukraine. 
By that time I was mostly done with this genre.

Middle school
-------------

During this stage, I read most of the things we were reading for the school program but sometimes on my own schedule. 
A lot of works I read before the program because again I had a lot of free time on my hands and some I discovered outside of school (e.x. at my parent's or grandparents' library). In this period I read mostly classics of French, Ukrainian, and Russian literature (XVIII and XIX centuries), I read also some science fiction for the first time and I read a decent amount of books about WWII, about partisans, about resistance. I read also other historian adventures about cossack, pirates etc. Occasionally I would pick up some random books like Viktor Suvorov's explanations of Soviet and world history of the XX century.

The main theme during this stage was history (which I enjoyed a lot) and some adventures but by real people.

First transition
----------------

During my high school years I moved to the big city, it was the beginning of 00s years, the country was very different. We had a lot of TV channels, radio stations, cinemas. There were a lot of things one could do after school. The environment changed dramatically. I also lost some personal interest in books. I still read most of the works for the school program, but it was rare that I would find a book that I would read with the joy for few hours per day. Basically, my taste and interests changed but I wasn't able to find a replacement yet. School program was of no help. We read usually something I wasn't interested that much at the moment. Topics were unappealing to me.

I don't remember exactly when and what happened but I think at the end of my high school during 
my university years I discovered works of Ernest Hemingway and Erich Maria Remarque. 
I read at least 5 of books from each of them in those few years. 
It was the literature of XX's century, it was more familiar, easier to grasp and relate to. 
I think in retrospective it was also interesting to me because those books were about 
Lost Generation of Western Europeans who lived through and after the big shock of WWI. 
And it seems to me know that theme is very appropriate to Ukraine I know.

The collapse of Soviet Union probably had a similar effect on its former citizens like that to Europeans who witnessed WWI. 
Country collapsed. 
People didn't know what to do in life anymore, 
they didn't know what the world and life are becoming, 
some of their became were obsolete, other values were constantly attacked by the new order. 
I lived in Ukraine among the lost generation. 
Imagine growing up in one world for 30 years and then that world would disappear in one day. 
Not everyone was able to successfully adapt to this world. 
This transition is still in progress 25 years later.

I'm not sure I realized these parallels reading Hemingway and Remarque back then, but it probably doesn't matter. I was magnetized by these books anyway.

Second transition
-----------------

Around 2010 several things happened. 
First, I've read enough of Hemingway and Remarque and I didn't know what to read next. 
Second, I left Ukraine and my access to physical books in Ukrainian and Russian languages was limited. 
At that time I was reading technical books in English but I have never read a full fiction book in my non-native language. 
Next few years were periods of struggle with both finding books I would want to read and starting reading books in English. 
Only around 2014, I was comfortable enough with reading books in English. 
And only in 2016 I discovered a lot of books that I was excited to read.

In January 2016 I listened to 4 podcasts (2 with Naval Ravikant and 2 with Derek Sivers) on Tim Ferriss show that provided me with 
1) some ideas how I can change my reading habits, 2) list of books that were interesting to me.

Ideas
-----

Here is the list of tips from Naval that I tried and that worked for me:

- do not be afraid to quit book if you don't like it. Close it and start another one. Maybe you would be able to come back to it some time later (in a month, year, decade) or maybe it's just not your book. Don't feel guilty about not finishing it
- start several books simultaneously. Pick up 2-5 books at the same time and see which one you like the most and continue with it. Come back to other books later. This one works great with the first tip
- always have several books available to be started. Either maintain library at home with some pipeline of books or if you use Kindle buy several books even if you are not going to start them next week. Maintain pipeline. Tip 1 and 2 depend on this.

Derek Sivers shared his great idea of keeping down notes of books he has read. I thought that idea was great but too difficult and time-consuming. But we will come back to this idea later.

From my personal experience changing styles of books you are reading also helps. 
E.x. if you are reading 3 books in a row about spirituality that might become boring. 
Mix and match books, read a fiction book and then non-fiction, change topics etc. 
It happened to me at least once during the year one book reminded me so much of the previous one that I just stopped reading it (however I plan to come back to it later).

New problems
------------

This year is already one of the best in terms of reading for me personally. I don't think I've read that much since middle school. However 10 months after starting reading again I noticed several new problems.

I've read several books that I was super excited after finishing. 
Books like _Sapiens_, _Bruce Lee Striking Thoughts_, Bertrand Russell's Best, _Ill Fares the Land_ and few others impressed me a lot, they presented some new ideas I didn't know, some wisdom, approaches etc. They triggered a lot of thought. I was very enthusiastic after each of them but the problem is few month later I couldn't remember a single idea of any of those books. 
I don't know what I liked about _Sapiens_, I don't remember what Bruce Lee's insight I liked. 
The only thing I remember is whether I liked the book or not. 
I remember my feelings about the book, not the ideas presented in it.

I recently finished reading two books that go well together: _Brave New World_ by Aldous Huxley and _Amusing Ourselves to Death_ by Neil Postman. 
This is truly the most powerful pair of books I've read in years. 
After finishing those two books I've got scared that in 3 months I would forget what I liked about them and what were the ideas of those books. 
And I don't want to forget. 
Those books contain ideas of high importance. The same is true about books I enumerated above.

So what should I do?

New solutions
-------------

I started thinking in the last couple of days about the problem of forgetting. 
And I think there are at least several points to be made here.

Most of the worthy books were written not for entertainment. That stands true for both fiction and non-fiction. 
Everything worthy requires time, commitment and effort. It seems that effort just to read a book is not enough.

In a school for every worthy book, there was dedicated time for at least several lessons. 
During this time, you would have discussions about the book, discussion about the context in which the book was written (historical time and author biography e.x.), you would write some essay with critique about the book, you would have to test comprehension. You would basically think about the book and repeat it's message in different mediums on the different days. This reminds me of Spaced Repetition technique. 
You are repeating what you've learned over the period of time and you combine mediums too. At the end, a lot of information about the book is engraved in your brain. It would be much harder to forget those ideas, because you not only read them but discussed them, argued about them, wrote about them etc.

Alain de Botton once presented the idea of the importance of repetition. 
He argued that the brilliance of traditional religions is in the constant repetition of the truths and values. 
E.x. you go to church and listen to the same stories every week. I think that is what we are missing now. 
We should concentrate on the quality of knowledge/information/ideas/wisdom we consume.

It seems that fiction is much easier to remember than non-fiction. 
Fiction is more visual and easy to grasp. 
I can recall a plot of a lot of fiction books I've read. As I said I can't do that for non-fiction. 
The reason for that I think lies in the nature of non-fiction books. Non-fiction books are about ideas. 
Ideas are abstract concepts. They are not always directly tied to reality. 
In order to remember such book later, you need to understand it very well. 
In order to understand non-fiction book you've need to build mental model of this book in your head. 
Non-fiction authors have a mental model of the world in their heads when they write a book. 
Your goal is to rebuild that model in your head, to make it physical. 
Non-fiction sounds a little like programming. 
The code is an abstract expression of author ideas about the world. 
Your goal is to grasp it now and make it possible to recall in the future.

Thinking of this difference between fiction and non-fiction I realized that Alain de Botton's _The Course of Love_ written in a most brilliant way. It's written like a novel (fiction) but it has quotes of his main ideas in each chapter (non-fictional ideas in a non-fictional style). Those quotes are important there because sometimes it's not always easy for you to formulate idea nicely even after reading the story. 
I wonder if there are other books like that? 
That is an interesting approach for modern journalists, philosophers to take: pick your serious topic and present it in a fictional way but express and highlight your main ideas directly.

Another good idea is to read books in pairs. I mentioned before _Brave New World_ and _Amusing Ourselves to Death_. 
These two books work great together. 
It's much more difficult to extract true value of _Brave New World_ by yourself. 
E.x. for me world described by Huxley was scaring but I couldn't formulate all the reasons why it was that way. 
Postman gives you good explanations why. 
He gives also real examples from moderns times that you can grasp more easily.

So it seems that there are possible solutions to the problem of forgetting. 
It seems that sometimes we can make a process of remembering easier. But I don't think that this is the main takeaway. 
I think the best thing we can do is to invest more time and effort into reading.

Personally I plan to alter my approach to reading in the following way:

- use a physical notebook and keep notes while reading a book (we are finally back to the Derek Sivers advice from above). 
 This will slow down the process a lot and will shift focus from reading as entertainment to reading as something more serious (this was one of the important questions by Neil Postman in Amusing Ourselves to Death, should education be entertaining? It wasn't before)
- after finishing book manually digitize your notes by typing them
- pick up some question and write an essay about the book (not a short review)
- find a book club where you can discuss your book and discuss it with people. Giving 1 min summary of a book for people who never read it might be very useful for you

Theoretically, there can be also the technological solution for this problem. 
It might be something like an online non-traditional book club that would be similar to a school classroom I described above. 
You will become part of the classroom with other people who are reading the same book right now and willing to join. 
The classroom would include several discussion sessions and writing essays and tests.

However, I'm not super optimistic about such technological solution at this point of time. 
There are a lot of open questions. 
Is learning more social or individual activity (does the answer depend on a type of personality)? 
Is it better to have a solution that satisfies fewer requirements but offline (think book club) or online one that is theoretically more feature-complete?

So I'm sticking with my experimental ideas presented above. 
At the end, I would definitely read fewer books but hopefully, I would understand and remember each of them better.


## On abundance and moderation

description: World of everything and nothing
date: 2016-10-10  
lastmod: 2018-12-13  
tags: abundance, moderation, consumption, consumerism  
people: Goethe, Neil Postman  
places: []
locations: Hong Kong

### content

Goethe wrote, "One should, each day, try to hear a little song, read a good poem, see a fine picture, and, if possible, speak a few reasonable words." 
We can take few good points from this wisdom but let focus on one for now. 
What is interesting here to me is that Goethe didn't advice us on reading several poems per day, he didn't advise us on listening to the whole music album or talking a lot.

Almost every major world religion I know has the concept of self-restraint. 
Restricting ourselves during some periods for food consumption, sex, sleep and other sources of physical pleasure.

There are also a lot of proverbs on this topic in each culture. In my country, we have a saying that "what is a lot is not good." 
Neil Postman also said "if we eat too much food ... we turn a means of survival into its opposite." 
This wisdom existed for thousands of years. 
Do we forget it now? If we are, why is that?

Why do we need to restrict ourselves? Why can't we have it all and at once?

Maybe because we should be able to control our wishes and our vices. 
Otherwise, they will become our masters and drive our decisions, actions, thoughts, identity, and destiny at the end?

Or maybe moderation allows us to enjoy something better? If we have unlimited access to some resource can we truly be able to fully appreciate it? 
Isn't the abundance of something destroys its value? This sounds very similar to the law of open markets. 
Can we still truly enjoy our ice-cream if we eat it every day? 
Or can we truly appreciate watching some sport game if we can see it every day instead of once per week as it was only a few decades ago? 
Those examples are countless.

Or maybe moderation provides us with variety? If we can't spend all our time on one thing we, will try something else. 
And maybe this variety is a key to our development and health.

Or maybe abstaining from something makes us better? It allows us to better understand our true nature and true desires? 
Maybe it makes us more resilient to difficulties?

Or maybe restriction of some sort allows us to focus and turn on from one category of needs (e.x. physical) to another (e.x. intellectual, spiritual etc)?

Or maybe the idea of artificial restraint presents us with the change in our routine which is essential on recognizing 
when we do something on the autopilot? 
Maybe it helps us to fight boredom? 
It allows us to revisit our habits and default behaviors? 
E.x. instead of falling back to eating something when we are bored we can try to discover a new source of joy or even find some new meaning in our actions?

Or maybe it will allow us to be more careful with our choices. If you can listen to only one song today, which one it will be? 
You'll probably try hard to make it count and make a maximum of it.

Or maybe restriction allows us to have more unclaimed free time that we can spend on our own, reflecting on who we really are and what is really important to us?

No matter what was Goethe primary reason for writing the phrase mentioned above I think we should try to take his advice to heart. 
Let us see if we can limit ourselves in our world driven by consumption. 
Are we even able anymore to listen to one song per day, do one physical exercise, read one story/essay, 
eat one nutritional (and tasty) meal?

How would such change reshape our personalities and tastes? 
What skills would we acquire? 
Would we be less anxious or more? What would we do with our newly acquired free time? 
How would we spend it? Learn a new language, meet our friends, walk more and think more, do some community work, learn to play some instrument, invent a new game? 
I don't know, but it definitely puts you in control, it makes you design your life. 
Are we up to the challenge, are we capable of doing this?

It's clear nowadays that abundance of food leads to obesity, diabetes and other health issues. 
Those changes are physical and easy to see. But what about the abundance of media, technology, entertainment? 
What impact huge quantities of those things have on us: on our bodies, minds, souls, consciousness, attention, etc? 
Are we even aware of changes in those areas?


## People I am afraid of

description: On people that on my opinion are the most dangerous for the society
date: 2016-02-24  
lastmod: 2018-12-13  
tags: society, modernity  
people: []
places: []
locations: San Francisco

### content

There are two types of people that I am afraid of: 

- people who have one dimension of their personality or skill set and want or already have power 
- people who are "successful" in one field but don't have feedback loops

There is an intersection between those two categories which we should be aware of. 
Let me explain whom I consider being such people categories and why I am afraid of them.

In the last couple of years, I met a decent amount of people that lead me to write this post. 
For me, those were usually "entrepreneurs" (from various countries) that had 1 goal (become rich and powerful). 
Usually, they also do some projects that bring either ~0 value to people and societies or in some cases creates harm 
(e.x. products that play on weaknesses of people, products that make people worth and not better human beings).

I call these people one-dimensional humans. There is nothing more to their personality than I outlined in the single paragraph above. 
They have a single goal, they are not aware of more complex systems we live in, 
they don't have appropriate knowledge (or education) and interest in the humanitarian sciences. 
The real danger comes not from the fact that those people are one-dimensional 
(because there are a lot of people like that, but they are not dangerous but useful to the societies) 
but from the fact that those types of people have a high chance for "success" in the Western societies. 
And this "success" means money and power. So the real problem arises when people with no real perspective, with a narrow operational mindset, get power. 
Such situation puts the rest of the society in danger, because of the extremely poor choices such people are making.

My dream for us is to consider such individuals not as "successes" but as failures of the society. 
They are products of our past mistakes. Such people shouldn't be celebrated in any way, and they should be kept as far as possible from power.

Those people can be brilliant in their one thing, but they are usually sneaky and easily recognizable within few minutes of the conversation.

Another highly related group of people that I noticed recently are "smart" opinionated people with no feedback loops. 
I like when a person is opinionated, I think it's a good trait, it means to me that person thought about that particular problem. 
What I don't like when a person has an opinion but has basically no mechanism to change that opinion though communication with another person. 
Those people can look smart but to be honest, I wouldn't call any person without feedback mechanism smart (hence the quotes "smart" above). 
Again, there is no real danger until such person achieves some "success" and gets some money and some power. 
After that happens real problems arise. Recently I saw a lot of people who _have all the answers to all the problems). 
I noticed a lot of arrogance and unwillingness to listen, to explain, to discuss, to communicate and to find solutions together. 
Journalists are "experts" on every topic, politicians are "experts," 
regular people are "experts," "entrepreneurs" are experts on the politics, peace, economy, happiness, etc. 
The list goes on and on. A lot of people right now have opinions that hard to change.


Are we living in the meritocratic society? 
If the answer is "yes", then, in my opinion, we need to change the merits we evaluate "success" on. 
In the long run (for humanity) it's a better decision to not put "bad" people on top. 
People with limited perspective and people with no ability to improve their own point of view causing us a lot of harm. 
Let's evaluate people not by the size of their bank account or how much power they have, 
let's celebrate people who contribute most to the society now and in the long term in proportion to their original resources. 
As I said some people become rich and powerful creating the negative impact on the societies, nature, and humanity. 
And some people are just working hard, making the difference every day in their local communities. 
Let's celebrate the second group of people and let's be aware of the first one. 
Let's shame them, let's not become like them and let's fix the problem that put such people in charge.


## Phoneless

description: On living a life without a smart phone in the modern society  
date: 2015-09-27  
lastmod: 2018-12-13  
tags: phones, consumerism, anxiety  
people: Alain de Bottom
places: []
locations: St. Louis, USA

### content

I often get asked why I don't have a smartphone. 
Interestingly, it's not that easy to answer this question in one sentence. 
There are multiple reasons, and they change over time. 

I got my first and only smartphone in January 2012 when I first came to the US. 
I used it every day: checked the news, emails, Twitter and navigated using Google Maps. 
In June 2012, I left Bay Area and started traveling around Balkans, South and Central America. 
Suddenly, having that phone became a chore. I think it was locked and I couldn't use it properly in different countries. 
But that wasn't a point. Frankly, I don't think I needed it that much. 
So I didn't have smartphone ever since summer of 2012 (I've got a regular basic phone without the Internet in July 2015 for work). 
I don't think it was conscious decision back in 2012, 
however not getting a smartphone ever since was a conscious one since I thought about it a lot in the next years.

I remember in 2012 I noticed that I lost my ability to listen to a person for more than 3-5 minutes. 
I wasn't able to concentrate on one topic and one person. 
While listening to the person, I was thinking about something completely different in the background. 
That was awkward and embarrassing.
I wasn't able to follow up with questions to continue a conversation. 
If you are not interested in what person is saying to you, I doubt she will be interested in listening to you. 
That was the new issue to me that appeared while I was using a smartphone and was online all the time. 
I didn't like those changes in my mind and my personality.
I do believe that technology should assist us in becoming better versions of ourselves, and opposite direction is not appropriate.

The Internet is the source of knowledge but also the source of distraction. 
I do feel that our generation is probably the most consumerist of all of them. 
It became too easy to consume news, shows, movies, music videos, stuck in different forums, chats, etc. 
We often made fun of our parents who constantly consume TV with stupid shows and one-sided news. 
But I think newer generations are worst in this respect. 
Everyone is connected to the Internet 24 hours every day. 
Nowadays consumption happens not only during newspapers reads and evening news shows, 
but it also happens every 10 minutes when we refresh our facebook/twitter feeds, check our instagrams and snapchats. 
I think that consumption is the biggest enemy of production and creativity. 
It's like a path of the least resistance. 
It's very tempting to consume something, and it's much harder to create. 
The most worrying thing for me is the constant consumption of thoughts and opinions. 
I feel if you delegate function to get thoughts and opinions to the somebody/something you lose the ability to create them yourself. 
Our brains need practice!

If you are working in any creative field getting into "the flow" is your ultimate goal. 
It's something almost mythical that you can achieve only rarely and something you don't understand until you experience it. 
In my opinion, one of the preconditions for getting into the flow is getting long spans of time without interactions. 
E.x. you can't be in the flow if someone would come to your table every 5 minutes while you working. 
For doing creative work we want quite spaces. 
But I don't think it's possible to avoid interruptions if you connected to your phone, your smartwatch, etc. 
You are bombarded by new emails, private message, public chat messages, notifications from apps, etc. 
I don't think most of us have even 30 straight quite minutes during our work days.

I also think that having 24-hours access to the Internet raises our anxiety levels. 
Our parents watch this depressing local and international news and worry a lot about things they cannot influence. 
We however are also worried about news. 
But those are news about our Facebook friends and some events that are happening now. 
And we have strong fears and anxieties to miss these events. 
The problem is that we not worried to miss something once per week or per day, but every 10 minutes. 
We want to know about those events in the real-time. 
So basically every minute of our life we are anxious about some issues. 
Not a great place to be. I strongly recommend watching Alain de Bottom talks on news and anxiety.

Personally, it was much harder for me to be at the moment when I had a smartphone. 
It was extremely difficult to have a meaningful conversation.
It was hard to keep any conversation with someone at all because any second me or person 
I was talking with would pull up a phone and check something.

Camera in our mobile devices has another weird side-effect on our comprehension of the world. 
If you have a camera always with you, then you'll try to use it. Suddenly everyone became a photographer. 
Instead of just enjoying the beauty of the nature or vivid energy of some music band people would take photos or videos. 
Instead of looking at the world through the open eyes they start looking into it through the phone screen. 
Some people enjoy photography, and I understand when they are taking photos, but not all of us are photographers, right?

I'm not against smartphones or other Internet-connected devices. They provide us with some useful functionality. 
Everyone has their reasons for having a phone. My goal is to understand the trade-offs - 
what I'm personally getting and losing owning a smartphone. 
And my only wish is that everyone was able to evaluate personal trade-offs themselves and made an educated decision.

For me, the decision was down to evaluating whether constant interruptions and higher anxiety were worth the convenience of 
getting access to Maps and Wikipedia all the time.

As a software engineer, I'm connected to the Internet probably 14 hours every day. 
I consume large amounts of information during those hours. 
Not having a smartphone gives me the ability to be offline for the short periods when I'm commuting, or doing shopping or walking, or having a meal in the restaurant. 
I think we should strive for the balance and to have some time for ourselves. 
It can be scary at the beginning and maybe even boring. 
But that is the only way to know who you are and who you want to be. 
Permanent consumption prevents us not only from creating products but from knowing and improving ourselves.


## Ukraine

description: My take on changes in Ukraine in the last 5 years (2010-2015) and it's future
date: 2015-07-31  
lastmod: 2018-12-13  
tags: Ukraine  
places: Ukraine  
locations: San Francisco  

### content

Intro
-----

5 years ago I left Ukraine. 
What is interesting that after 5 years outside of my home country I can't answer a question "So how is life in Ukraine?" anymore. 
I mean I'm following news and can tell you what is happening. 
But it became much harder (it requires more thinking) for me to explain what are young Ukrainians up to. 
What are the problems, what are the challenges, what are the trends and what will happen next?

5 years ago I was part of the up-and-coming generation that was going to define Ukrainian future. 
I knew what my peers were thinking, doing, dreaming, etc (and to be honest I wasn't impressed). 
But right now there is an even younger generation of people in the early 20s, 
and they definitely have some differences (as any generation) with the previous generation.

I don't have good connections and knowledge about these younger Ukrainians. 
If I come to Ukraine now, I wouldn't exactly know what to expect. 
I have some ideas though about my expectations, and you can read them below.

Events
------

A lot of things happened in Ukraine in the past 5 years. 
First we had Victor Yanukovych regime, 
then we had the Revolution of Dignity connected with tragedy but huge hopes. 
Now we have a war against Russia. 
My country has a lot of problems now: war, corruption, slow speed of reforms, economic crises etc.

But I see 2 signs of future success of Ukraine. I have a good feeling about my home country. Bear with me to know why.

Music & art
-----------

When I was younger, we had a handful of the good Ukrainian music bands. 
To become successful, they needed some money/connections to appear on popular radio stations and TV-channel rotations. 
Now we live in the Internet age. 
To deliver your art (music, movies, etc) you don't need money or connections. 
All you need to do is your work of art and Internet. 
If you are a music band, you can release your music independently on the Soundcloud. 
You can keep in touch with fans on Twitter, you can organize events on Facebook. 
This is a completely new thing that is happening globally and also in Ukraine.

Basically, there is a general trend right now which I would call "without their permission" (see [Alexis Ohanian's book](http://withouttheirpermission.com/)). 
You don't need to ask anyone for "permission" if you want to start a company, political party or music band. 
All you need to focus on is your idea and execution. 
And if you are able to create something worthy people would pay attention to it. 
In a way "without their permission" phrase can be applied to the whole country. 
Ukraine used to be for quite a few centuries Russian colony.
 As a colony, we were looking towards Moscow and "asking permission" what to do, what to think. 
 We don't need to do it anymore. We can define our country, our ideals, our goals and directions ourselves.

I definitely like it. 
It enables people around the world to make a difference, 
do what they love and feel that they are working on something important. 
Priorities changed. 
If you are an artist, you don't need to be globally successful to be able to pay your bills. 
What you can do is be successful in a small niche. 
Previously it was difficult to deliver your work to that niche because of the geographic restrictions. 
That is not the case anymore. 
If you're a band from Ukraine making music in some specific genre/style, 
your fans/listeners no longer need to be in the same city, or even country, they can be around the world.

In the last 50 years for a lot of Ukrainian artists' success meant going to Moscow (which was a center of the bigger market for Soviet and post-Soviet countries). 
The focus changed completely recently. Currently, a lot of young Ukrainian bands do high-quality music oriented on the Western and global market.

This is the thing that excites me the most. 
I feel that culture/art and music is an extremely important indicator of change. 
It shows where country and society want really to go. 
Alain de Botton said that art balances us, it tries to compensate for what we are missing in our lives. 
And that is an important observation. 
Because producing high-quality western-like music shows that the new generation of Ukrainians wants to be better integrated with the Western world and there is high demand for quality too. 
It also shows that artists are not afraid to try anymore. 
An artist in some way also sends a right message to the rest of the society: 
if I am not afraid to try and do what we love - you can and should too.

New class
---------

In the 90s, most of the population in Ukraine became suddenly extremely poor after the collapse of Soviet Union. 
Early days of capitalism in my country were pretty wild. 
Some people became rich quickly. 
Most people who achieved high status and "success" were not good people. 
The majority earned their money doing some illegal things. 
Most of our rich people also became corrupt politicians, 
they don't have proper education and more importantly good moral values by any means. 
What even worth that several generations didn't have good roles models. 
"Bad guys" had better chances of success, which created negative dynamics in the society and probably some hopelessness.

In the last 10 years in Ukraine, we started to have some tiny layer of middle-class people 
that were able to earn enough money for the comfortable living. 
It's hugely important to have these people. 
Because the truth is after some income level more money doesn't bring you more happiness. See [WSJ article](http://blogs.wsj.com/wealth/2010/09/07/the-perfect-salary-for-happiness-75000-a-year/) which says:

> It turns out there is a specific dollar number, or income plateau, after which more money has no measurable effect on day-to-day contentment.

I hope that people who reach this level/income plateau would fill the need to do something else to be happier. 
There are not a lot of more fulfilling things than giving back to the community: through donations, 
volunteering work, building social enterprises or even building regular enterprises to help fellow citizens to live a decent life.

Also, some good role models (for future generations) are emerging. 
We started to have good, socially active artists, we started to value intellectuals, 
it's becoming fashionable again to be smart and kind.

Conclusion
----------

You can be pessimistic or optimistic about Ukrainian future. 
It's hard to predict what would happen next. 
There are great potential and a lot of opportunities, some are described in [Ukraine is open for U video](https://www.youtube.com/watch?v=jdSQuanI8Z8).

But I think that reforms from our government are not as important as two things I talked about above. 
There is a big demand among progressive young people for living (and building) modern Western state. 
There are artists who can show to the rest of us what we want and need and there is middle-class 
that would be able to execute these changes. 
None of this people is going to ask for the permissions from our government or from other countries. 
And that is the most significant fundamental change that happened. 
People did realize that they are in charge of everything and there is a way to achieve what they want.


## Is History Harmful?

description: Is history harmful and teaching it causes some serious problems in the long run?  
date: 2014-05-11  
lastmod: 2018-12-13  
tags: history, education
people: []
places: []
locations: Ohrid, Macedonia

### content

I love history, it’s fun, it’s interesting, it has a lot of "answers" to a lot of questions. 
You can pick any today's problem, find something similar in history and propose solution or explanation or even predict the future. 
History is a powerful tool. But it is also a dangerous tool (especially in the hands of people that try to use it for manipulation).

But there are at least several problems with it.

History gives context (often harmful one) to each nation. 
When you meet a  new person of another nationality sometimes by default, you might assign some features from the past (decades and centuries back) to that person. 
This context depends on your country of origin and the way you personally were taught history.

History plays a particularly bad joke with countries that have the history full of "glory." Let take e.x. Europe.
It had a lot of empires/kingdoms in the last 2-3 thousand years. 
Ancient Greece and Rome, Holy Roman Empire, Austro-Hungarian empire, Rzeczpospolita, the Russian empire, Ottoman empire, British empire, Napoleon empire, 
different Slavic kingdoms (Kingdom of Bohemia, Serbian empire, Kievan Rus) and so on. 
This is an incomplete list of "big" countries with overlapping borders across different centuries.

Even today when you meet an average European person they might tell you that their country was once big, 
and maybe the part of your home country was under their rule 1000 years ago or so. 
There is a significant chance that modern European nations were one country a few centuries ago or had several wars between them. 
Does it make an impact on some people today? Unfortunately yes. 
After traveling a lot in the past 2 years and talking to people of different nationalities, I can say that sometimes it's still a problem.

Can we pause for a second and think why all these borders, kingdoms that disappeared centuries ago still meter for the average person? 
Maybe it is because everyone studies history in school.

I do have a feeling that countries that either don’t have glory moments in their history or at least don’t put a lot of stress on it during history program in school have much more liberal citizens. 
I remember meeting a person from Finland. 
He was studying political science, and he told me that Finnish people have very low national self-esteem because they don’t have glory history. 
What is interesting that for me on average Finnish people are more intelligent, smart, pleasant to talk to than average person from "big" country. 
At the end of the day Finland has:

- one of the best education systems in the world
- great quality of life
- technology/Internet penetration is extraordinary

Finnish people have much more reasons to be proud of themselves because of their achievements in the last generations 
than people who are proud of achievements decades and centuries ago.

Those are the questions that pop up periodically in my mind:

- Why do people still so obsessed with countries, sizes, borders, power?
- Why do people celebrate war heroes and not great scientists?
- Why do people celebrate people from the past but not people who are working on improving our lives now? Don’t you think that Elon Musk is doing more positive things to humanity than some crazy dictator from the huge country in Eurasia?
- Why do people divide humankind between "us" and "them"? What makes "us" good and "them" bad?
- Why do people admire "strong" and "firm" leaders but not people with the intellect, with the vision for the future of the humanity?
- Why don't we celebrate intellectual victories and scientific breakthroughs, but we celebrate military "victories days"?
- Why people who are responsible for so many deaths are more famous and respected in history than people who saved millions of lives?
- Can it be because of history and the way it was/is taught in our schools? In my opinion, when we study history accents are made on the wrong details. We shouldn't admire war heroes, we should learn and realize why the war happened in the first place, what mistakes were made. We can't live in the past, we need to live in the today and think about the future. World today is very different from what it was even century ago, so many things, ideas, idols, ideals from the past are obsolete now.

Can we imagine what will happen if we just "forget" all the history? Wars between the nations, slavery, genocides - all of that. 
How we will treat every new person we meet? Will his nationality, origin, skin color matter to us?

Does the history protect us from repeating mistakes of the past or does it influence our attitude to each other?
History is important, but quite often it is used for propaganda and forming prejudices.


# poems

description: My poems

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %coll)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> %coll :description :value)))
        [:meta {:name "description" :content (->> %coll :description :value)}])
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
        [:h1.lh-title.f3.athelas (:name %coll)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (str "/" (->> entity :id :value))} 
                [:span (->> entity :name)]
              ]
              [:div.mt1.mb0.mh0
                (if (not (nil? (->> entity :date :value)))
                  [:small.f7.ml0.mb0.mr0.gray "written on "(->> entity :date :value) " "])
                (if (not (nil? (->> entity :locations :value)))
                  [:small.f7.ml0.mb0.mr0.gray "in " (->> entity :locations :value)])
              ]
            ])
          (reverse (sort-by (fn [ent] (->> ent :date :value)) %)))]
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
        [:h1.lh-title.f4.athelas (->> % :name)]
        (if (not (nil? (->> % :poem :value)))
          [:section
            [:article.lh-copy.measure
              (->> % :poem :value)]])
        [:footer {:class "mt4 cf lh-copy measure f6"}
          [:p.i.fr
            [:span.i {:itemprop "locationCreated"} (->> % :locations :value)]
            [:span "&nbsp;&nbsp;"]
            [:span.i {:itemprop "datePublished"} (->> % :date :value)]
          ]
        ]
      ]
    ]])
```

## A Little Prayer

description: A modern prayer  
date: 2018-01-29
lastmod: 2018-02-23
locations: San Francisco

### poem

In the senseless world  
of bigotry and desire  
we follow leaders  
we do not admire.  

We follow people  
we didn’t elect  
they abuse our brothers  
that we cannot protect.  

We do not pray  
since there is no one to pray to  
we do not obey  
since there are no rules too.  

But please, do say a little prayer  
make a small did  
shed a little tear  
you’ll be happier a bit.  

Please do avoid distractions  
and all angry news  
do not you live in abstractions  
and your anxiety will be reduced.  

Do not try to impress others  
you’ll only lose yourself  
and if you do not do that dear  
there would be no one left.  

Please always seek the Truth  
be the best person you can  
and after each mistake  
stand up and be a Man.  

Do not follow trends  
they will come and go  
try to make new friends  
with them, you’ll grow.  

When using your mind  
don’t forget about heart  
try being kind  
and not hit people hard.  

Pray, pray, pray dear  
pray for yourself  
it all depends on your dear  
ability to discover sense.  


## Farewell

description: A short farewell  
date: 2018-02-21
lastmod: 2018-03-14
locations: San Francisco

### poem

Hey, why did you do it that way?  
Why did you have need to betray?  
You wanted out, and you wanted to be free  
Why not just ask, why needed you to flee?  

Why did you blame me for all the things?  
Why did you cut your own wings?  
Why didn’t you just stand by me?  
Why did you kill a kid in me?  


You wanted drugs, and sex, and love,  
You wanted all those things above  
And what about trust my dear?  
It is indeed is super rare.

You hide your wishes by the lie  
And no, no, no - you can’t deny  
You didn’t give a single try  
You made me cry, you made me cry.  

How can you be so ruthless, dear?  
How did forget you all that fear?  
That was denying you to live  
I stood by you, I was your peer.  

I trust you made correct decision, 
You liberated self, I wish you well  
I will not be sad, and I will not dwell  
The life will not stop for me as well.  

Enjoy, my dear, your new freedom.  
Enjoy it, cause I paid the price.  
Avoid the sadness and the boredom.  
I’ve paid for it exactly twice.  

Of course, of course, you are not a devil,  
But you did hit me in the middle of the heart,  
Of course, that wasn’t purely evil  
But you did tear myself apart.  

There is not much left to say,  
All I can do is pray, pray, pray.  
Pray for my heart, my strength, my life,  
Of course, because I’ve lost my wife.


## Reminder

description: A short reminder
date: 2018-12-23
lastmod: 2018-12-24
locations: Buenos Aires-Paris

### poem

Neither a rich person,  
nor a poor.

Neither an artist,  
nor an engineer.

Neither a mother of a child,  
nor a single man.

Neither a senior adult,  
nor a teenager.

Neither a party girl,  
nor a bookworm.

Neither a people person,  
nor a hermited introvert.

Neither a successful business owner,  
nor a homeless hippie.

Neither a professor,  
nor a thug.

Neither a casanova,  
nor a virgin.

Neither a monk,  
nor a city lover.

Neither a globe trotter,  
nor a villager.

Neither a President,  
nor a cleaner.

No one really knows.


## The City

description: The City of Anger and the City of Love
date: 2017-04-16
lastmod: 2018-01-30
locations: San Francisco

### poem

In the City of Anger  
I’ll build City of Love,  
I’ll never surrender  
And I’ll build my enclave.

Among all that despair,  
Among hatred and fear  
I’ll do all repairs,  
And I’ll plant a new tree.

I’ll nourish respect,  
I’ll escape all anxiety  
And with my core intact  
I’ll build new reality.

I’ll grow people of peace  
From the seeds of love,  
And I’ll ban all injustice  
From this godly ashram.

And that City of Anger  
is, of course, being me,  
And that City of Love  
is the one I want to be.


## Trust

description: On trust  
date: 2018-10-01  
lastmod: 2018-10-02
locations: San Francisco

### poem

Why don’t you trust me?  
Why is your default assumption betrayal?  
If someone betrayed you doesn’t mean you should stop trusting the world or people.  
Distrust blocks power and possibility connection and closeness.  
It’s a border that should be crossed.  
It builds the wall which should be broken later.  
It all requires energy that we could spend elsewhere.  
Why do we start from there?

Why can’t you pick another default?

When I see that you don’t trust me, I start questioning my trust in you.  
I become suspicious.  
I start questioning your decision-making and your values.  
If betraying someone is so easy in your mind, I really start to worry that you can do it.

And it drives us further away.

Stop doing it.  
Why are you so angry?  
I thought that pain will make you kind and expose to love.  
Why do you bite?  
What did I do to you?

I don’t want to play this catchup game gain.  
I don’t want to prove my intentions.  
It is boring. And words don’t mean a lot in those conversations.  
Actions are much more important.

Please change your defaults. I beg you.

All this anger. I’m getting scared.  
I know the reasons for it, of course.  
I know those are just emotions.  
And that they are temporary.  
But they still have an impact on me.  
I heard you wanted me to suffer  
just because you felt in danger.

This worries me. You instinct reaction was to hurt me,  
But the source of your pain had nothing to do with me.  
It was all inside you and your past.

I’m afraid. I can see this fire off in the future when I’m not ready.  
I see how you could act immediately under the emotions.  
How you would hit me in my weakest spot.

This escalates quickly.  
Trust breeds trust.  
Distrust breeds distrust.  
It’s hard to build anything valuable on such foundation.  
I’ve been there.  
I don’t want to be afraid to speak my mind.  
I don’t want to censor myself to prevent your emotions and  
Actions that follow from that.

I want safety. I want to know that it is safe for me to speak.  
That my words will not cause you to act and make me suffer.

I want to talk to you the same way I talk to myself.  
That is my ultimate goal. That is what I’m looking for.  
This is ultimate intimacy.


## Truth

description: Catching Truth  
date: 2018-04-30
lastmod: 2018-10-30
locations: Berlin

## poem

Foolish me.  
I thought I knew a thing.  
Of course that arrogance comes with age and ego.

Foolish me.  
I thought I knew what life is.  
Of course, I didn’t.  
How could I?  
Living in a head  
Would not provide an answer.  
The same is right for living in a world.  
The Truth is always in the balance.

It’s not here, and it’s not there.  
It hides every time you try to grasp it.  
It’s like a quark,  
It’s opposite of what you just learned.  
It jumps around the corner,  
It hides from you,  
And you always try to seek it.  
It’s a phantom,  
It’s a ghost that can’t bee seen.  
The moment you grab it evaporates.  
And you need to start from scratch.

The Truth can’t be described,  
It can’t be said in words.  
It’s bigger than that.  
It’s like a God, or better like water  
That you want to take with your bare hands.  
You feel it, you are on the grasp of it,  
And then it’s gone.  
It might become ice or become a cloud.  
It will be the source of life for flower or a tree,  
for fox or for a bee.

But you would never be able to grasp the whole complexity of this.  
How can water do this?  
It will always be a speechless miracle.  
Water can be calm or powerful,  
The same is true for the Truth.  
It holds every thing.  
It holds polarities.  
It contains Universe.  
Sadness and happiness.  
Joy and sorrow.  
The Truth even contains falseness and lie.

Catching the Truth is like catching a bird by the tail.


## Where are you?

description: How to find strength
date: 2018-05-01
lastmod: 2018-05-01
locations: Berlin-San Francisco

### poem

Where are you Neil?  
Where are you Vaclav?  
If you couldn’t enlighten everyone then  
Who would do it now?

Who is even concerned nowadays with humanity?  
Professors, presidents, directors.  
They have no vision and no wisdom.  
They do not seek knowledge  
And do not love people.  
They do not enjoy life  
And don’t believe everyone should either.

Who would guide us?  
We have your books and we have memories.  
But we are weak and often distracted.  
What is the source for our strength?  
How do we start reading again?  
Instead of repressing our anxieties and fears  
with alcohol and media?

How did you find the strength?  
What brought your love?  
How do we find unity?  
How do we find motivation?

We are good only for consumption these days.  
It sucks everything out of us.  
It dumbs us and we are too weak and lazy to resist.

Please, send us your strength.  
How did you go against ignorance and systems?  
Where did you get that energy?  
Who feeded your dreams?  
We don’t have any of them anymore.  
Weak, dreamless, anxious, isolated creatures.  
We only have vague memories of the great heroes.

We are in pain.  
Maybe this will unite us?  
Maybe our pain will create collective love?  
And love would give us energy.  
And with energy we will seek wisdom.


## Women

description: About women
date: 2018-09-10
lastmod: 2018-09-29
locations: San Francisco 

### poem

Woman,  
Why did you do that?  
I’ve been by your side all my life.

I saw your tears.  
I wanted to protect you from men.

How stupid was I.  
I despised men and adored you.

First as a mother.  
As a friend,  
As a partner,  
As a lover.

But every time you betrayed me, woman.  
You didn’t need my protection.  
You didn’t care about me or value me.  
You betrayed me and hurt me every single time.  
And when you came back it was all too late.  
Part of me was dead.

As a mother you abandoned me.  
I needed your protection in those rare moments.  
But you didn’t care.  
As a friend and lover you always went somewhere else.  
You thought that I’m unbreakable and that you’ll always be able to return.

Woman, why can’t you see?  
I have feelings. As any other man.  
It’s you who made me tough and emotionally inaccessible.  
I became tough not because of men, but because of you.  
You forced me to close my heart more and more with each year.  
You blamed masculine nature for inaccessibility. But wasn’t you the one with whom I grew up?  
The one I went to school with and spend my adolescence and first adult years.

You were the mother.  
You were the friend.  
Your were the partner.  
You were the lover.

There are no one to blame for that.  
Not a mystical nature of men.  
We didn’t grow up on trees.  
We were abandoned and forced to close our hearts.  
You numbed us.

And only deep understanding of life itself can help to revitalize me.

Woman, I know what life is. I’ll revive my heart and show you real love.  
Love that comes from knowing what is pain. The love that is non-judgmental and unconditional.  
Woman, you’ll see. Past is the fuel for the love. The one who was really hurt can’t hurt no one.

Woman, you are like Mother Nature.  
Loving and deep and encompassing.  
But you are destructive force too.  
You are a hurricane, you are plague, you are tsunami.  
You give life but you can take it too.

This is my pledge. I finally see the duality of life. I see the need for both sexes.  
I see the duality of each sex. I see all of that inside myself.


# talks

description: Розмови з людиною

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %coll)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> %coll :description :value)))
        [:meta {:name "description" :content (->> %coll :description :value)}])
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
        [:h1.lh-title.f3.athelas (:name %coll)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (str "/" (->> entity :id :value))} 
                [:span (->> entity :title :value)]
              ]
              [:div.mt1.mb0.mh0
                (if (not (nil? (->> entity :date :value)))
                  [:small.f7.ml0.mb0.mr0.gray "written on "(->> entity :date :value) " "])
                (if (not (nil? (->> entity :locations :value)))
                  [:small.f7.ml0.mb0.mr0.gray "in " (->> entity :locations :value)])
              ]
            ])
          (reverse (sort-by (fn [ent] (->> ent :date :value)) %)))]
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
        [:h1.lh-title.f4.athelas (->> % :title :value)]
        (if (not (nil? (->> % :poem :value)))
          [:section
            [:article.lh-copy.measure
              (->> % :poem :value)]])
        [:footer {:class "mt4 cf lh-copy measure f6"}
          [:p.i.fr
            [:span.i {:itemprop "locationCreated"} (->> % :locations :value)]
            [:span "&nbsp;&nbsp;"]
            [:span.i {:itemprop "datePublished"} (->> % :date :value)]
          ]
        ]
      ]
    ]])
```

## Ne Vtikai

title: Не втікай  
description: Про віру  
date: 2018-03-25  
lastmod: 2018-09-29  
locations: San Francisco  

### poem

Не втікай,  
Я теж боюсь.  
Не біжи,  
Я теж соромлюсь.

Зупинись. Подивись.

В твоєму страсі я бачу свій страх.  
В твоєму обличчі я бачу себе.  
В твоїх проблемах я бачу свої проблеми.  
В твоєму соромі я бачу свій сором.  
В твоєму болі я бачу свій біль.

Не біжи,  
Я теж боюсь.  
Зупинись. Подивись.

Я хочу обняти тебе.  
Я хочу щоб ти обняла мене.  
Я хочу багато сказати тобі.  
Але я соромлюсь себе.  
І боюсь не сподобатись тобі.  
Я боюсь не бути тим, ким ти мене бачиш.  
Я боюсь бути не гідним тебе.  
Мені страшно, так само як і тобі.

Мені болить, що тобі болить.  
В твоїх очах мої очі.  
В твоїй сумній усмішці - я.  
Твоя ображена дитина - це також я.

Вдвох не так страшно - повір мені.  
Ми не ідеальні, у нас багато проблем,  
Тому що ми - живі,  
Ми - люди. Живі люди.

Ми можемо сміятись,  
Але ми сумуємо.  
Ми можемо виглядати сильними і дорослими,  
Але ми слабкі, беззахисні, ображені діти.

Не біжи. Благаю, не поспішай.  
Ми завжди це встигнемо.  
Якщо є 1 відсоток шансу, то треба пробувати.  
Це коштує всього болю та всіх сліз.

Ми далеко, але не поспішай, зупинись.


## My

title: Ми  
description: Про нас  
date: 2018-03-26  
lastmod: 2018-04-23  
locations: San Francisco  

### poem

Ти не гірше. 
Ти не краще.  
Навіщо ці порівняння?  
Ми всі рівні.  
Ми всі брати.  
Ми всі сестри.

Чому ми такі жорстокі?  
Де поділась наша людяність?  
Ти думаєш справа у світі?  
У справедливості?

Але так було завджи,  
І так буде завжди.  
І люди були людьми в часи голокосту та воєн.  
Чому ми не можемо так?

Ми можемо.  
Ми нащадки справжніх людей.  
Ми їх продовження.  
І хтось буде продовженням нас.

Ми в цьому разом.  
Ми ланка єдиного ланцюгу.  
Ми поєднуємо покоління.

Без них не було минулого.  
Без нас не буде майбутнього.

Чому ми жаліємось?  
Про що нам жалітись?  
Якщо вони змогли, то зможемо і ми.  
Чи не задля цього нам треба жити?

Хіба усмішка дитини,  
Хіба сльоза друга  
Не варті наших зусиль?  
Хіба вони не варті подолання нашого страху?

Ми маленькі частинки людства та всесвіту  
Але ми є їх невідємними частинами  
Без нас нічого не буде.  
Наша хоробрість, наша любов врятують все.


## Liudyno vir

title: Людино, вір  
description: Про віру  
date: 2018-03-26  
lastmod: 2018-04-23  
locations: San Francisco  

### poem

Людино, вір.  
Щастя в тобі.  
Ніхто не знає,  
ніхто не вміє.  
Всім болить.

Ти - це він.  
Ти - це вона.  
Ти - такий як всі.  
Всі - як ти.

Людино, посміхнись.  
Ми всі божевільні.  
Ми всі біжемо,  
але не вміємо зупинятись.

Людино, відкрийся.  
Ти - це мить.  
Більше нічого не буде.  
Ти прийшла нізвідки, і підеш в нікуди.  
Ти - ніщо, і ти - все.  
Тебе ніколи не було,  
І ти була завжди.  
Тому що ми однакові.

Ми однакові,  
В болю.  
В помилках.  
В божевіллі.

Людино, відкрийся.  
Розслабся, люби.  
Людино, оживи,  
і живи.


## Vrazlyvist

title: Вразливість  
description: Про вразливість  
date: 2018-04-22  
lastmod: 2018-04-23  
locations: Kyiv-Lviv  

### poem

Будь вразливим.  
Виглядати сильним може кожен.  
Але сила розділяє нас.  
Будь вразливим.  
Це об’єднує.

У всіх є проблеми,  
Страхи, недоліки,  
Дитячі травми,  
Образи, трагедії та помилки дорослого життя.

Сила ізолює тебе.  
Залишає тебе наодинці з усім цим.  
Будь вразливим.  
Тобі буде легше.

Показуй свою слабкість людям.  
Показуй свіх біль. Показуй свої сльози.  
Цe змінить усе.  
Прийде легкісь та теплота.

Ми розуміємо один одного  
Не через досягнення.  
Ми розуміємо один одного  
Через страхи та біль.  
Ділись цим  
І все відкриється.  
З’являться усмішки добра  
Та сльози легкості.

Будь вразливим.  
Це і є бути людиною.  
Ми не машини, ми не роботи.  
Ми зламані діти  
Зі шрамами від дорослого життя.  
Але в цьому наша чарівність, наша людяність.


## Ty

title: Ти  
description: Ти і слова, слова і ти  
date: 2019-01-05  
lastmod: 2019-02-01  
locations: Paris, San Francisco  

### poem

Ти не знаєш що слово значить  
Але використовуєш його все одно  
Ти не знаєш що слово ранить  
Опускає людину на дно 

Ти не думаєш про наслідки  
Для тебе слова, як і дії, пусті  
Ти не віриш що інші страждають  
Ти не знаєш що серця крихкі 

Ти говориш великими фразами  
Ти думаєш що розумніша за всіх  
Як же сильно ти помиляєшся  
Ти несеш страждання для них 

---

Ти не вмієш творити любов  
Хоча багато про це говориш  
Ти не можеш прийняти себе  
Тому іншу людину довбиш 

Ти не можеш знайти спокій  
Бо ти обманула себе  
Лише насолода без цінностей  
Ніколи не звільнить тебе 

Ти не вмієш вибачатись  
Тому що ти не любиш себе  
А поки себе не полюбиш  
Не зможуть любити тебе 

Ти не вмієш співчувати  
Тому що ти заблокувала біль  
Перестань себе аналізувати  
І любов прийде звідусіль 

---

Пробач себе  
Позбудься жалю  
Позбудься відчуття вини  
Люби себе  
Тебе пробачать  
І зможеш далі ти іти 

Борися з егом - ти звичайна  
Не розумніша ти, повір  
Повіриш, зможеш ти збагнути  
І жити знову як колись 

Слова не варті нічого  
Вчинки визначають все  
Слова можуть вбити  
Вчинки зцілити тебе 

Забудь що ти хотіла сказати  
Не варто це витрати сил  
Зроби що повинна зробити   
Відчуй любові в серці прилив 


# haiku

description: My haiku

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %coll)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> %coll :description :value)))
        [:meta {:name "description" :content (->> %coll :description :value)}])
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
        [:h1.lh-title.f3.athelas (:name %coll)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (str "/" (->> entity :id :value))} 
                [:span (->> entity :name)]
              ]
              [:div.lh-copy
                (->> entity :poem :value)
              ]
            ])
          (reverse (sort-by (fn [ent] (->> ent :date :value)) %)))]
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
        [:h1.lh-title.f4.athelas (->> % :name)]
        (if (not (nil? (->> % :poem :value)))
          [:section
            [:article.lh-copy.measure
              (->> % :poem :value)]])
        [:footer {:class "mt4 cf lh-copy measure f6"}
          [:p.i.fr
            [:span.i {:itemprop "locationCreated"} (->> % :locations :value)]
            [:span "&nbsp;&nbsp;"]
            [:span.i {:itemprop "datePublished"} (->> % :date :value)]
          ]
        ]
      ]
    ]])
```

## Phone

date: 2018-05-01
locations: Frankfurt

### poem

Phone.  
News.  
Anxious and foolish.


## Question

date: 2018-05-01
locations: Frankfurt

### poem

Going to the homecountry.  
Not telling anyone.  
Guilty or an adult?


## Food photo

date: 2018-04-30
locations: Berlin

### poem

Date.  
Restaurant.  
Food photo.


## Instagram

date: 2018-04-30
locations: Berlin

### poem

Yoga, meditation.  
Selfie.  
Instagram.


## Memory

date: 2018-04-30
locations: Berlin

### poem

Parties with cocaine.  
Sex with alcohol.  
No one wants to remember.


## Skype

date: 2018-04-30
locations: Berlin

### poem

Skype.  
Mom and dad.  
Complaints.


## Society

date: 2018-04-30
locations: Berlin

### poem

Traditional boring villages.
Drunken liberal cities.
No one is happy.


## Swear

date: 2018-04-30
locations: Berlin

### poem

Dressy beautiful people.  
Swear, swear, swear.  
Damaged broken hearts.


## Tesla

date: 2018-04-30
locations: Berlin

### poem

Tesla.  
Bitcoin.  
Loneliness.


## Tinder

date: 2018-04-30
locations: Berlin

### poem

Lonely.  
Tinder.  
Swipe.


## Youtube

date: 2018-04-30
locations: Berlin

### poem

Alone.  
Thoughts.  
Youtube.


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
      [:title (:name %coll)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> %coll :description :value)))
        [:meta {:name "description" :content (->> %coll :description :value)}])
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
        [:h1.lh-title.f3.athelas (:name %coll)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (str "/" (->> entity :id :value))} 
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

I've read all of it except the last chapter in 2018, but for some reason didn't finish the book. Despite this fact I think it's very important, well written book with a lot of great insights and tons fo stories. Vulnerability was the topic I spent a lot of time thinking in 2018, I think Brené Brown thought it over much more than me. A lot of fresh and clear and simple ideas there.


# trips

description: My trips  

### cities

```clojure
  (let [cities (into [] (flatten (map (fn [trip] (->> trip :visited-cities :value)) %)))]
      cities)
```

### tripssummary

```clojure
  (into [] 
    (sort-by :year
      (map 
        (fn [[year trips]] {
          :year year 
          :trips (count trips) 
          :cities (into [] (flatten (map :cities trips)))
          :countries (into #{} (flatten (map (fn [city]{:country (:country city) :flag (:country-flag city)}) (into [] (flatten (map :cities trips))))))
          :distance (apply + (map :distance trips))
          :days (apply + (map :days trips)) })
        (group-by 
          (fn [trip](->> trip :year)) 
            (into [] 
              (map 
                (fn [trip] 
                  {:year (->> trip :year :value) 
                   :distance (->> trip :distance :value)
                   :days (->> trip :days :value)
                   :cities (into [] (->> trip :visited-cities :value))}) 
              %))
      ))))
```

### template

```clojure
(montaigne.fns/html 
 [:html
    [:head
      [:meta {:charset "UTF-8"}]
      [:meta {:width "device-width, initial-scale=1.0, user-scalable=no" :name "viewport"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
      [:title (:name %coll)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> %coll :description :value)))
        [:meta {:name "description" :content (->> %coll :description :value)}]) 
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
        [:h1.lh-title.f3.athelas (:name %coll)]
        [:article.lh-copy.measure
          [:table {:class "f6 w-100 mw8 center" :cellspacing "0"}
            [:thead
              [:tr
                [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "year"]
                [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "trips"]
                [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "days"]
                [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "countries"]]
            ]
            [:tbody {:class "lh-copy"}
              (map
                (fn [row]
                  [:tr
                    [:td.nowrap (->> row :year)]
                    [:td.nowrap (->> row :trips)]
                    [:td.nowrap (->> row :days)]
                    [:td.nowrap 
                      (map 
                        (fn [country]
                          [:span {:title (:country country)} (:flag country)])
                        (:countries row))]
                    ])
                (->> %coll :tripssummary :value)
                )]]]


        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (str "/" (->> entity :id :value))} (:name entity)]
              [:div.mt1.mb0.mh0
                [:span.f7.ml0.mb0.mr0 "from " (->> entity :started :value) " to " (->> entity :finished :value)]
                [:span.f7.ml0.mb0.mr0 "; days " (->> entity :days :value)]
                [:span.f7.ml0.mb0.mr0 "; travelled " (->> entity :distance :value) " km"]
                [:span.f7.ml0.mb0.mr0 "; emission " (montaigne.fns/format-float  (->> entity :carbon :value) 2) " tons of CO" [:sub "2"]]
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
          "\nmap.addLayer(
                {
                  'id': 'places',
                  'type': 'circle',
                  'source': {
                    'type': 'geojson',
                    'data': {
                        'type': 'FeatureCollection',
                        'features': ["
                        (clojure.string/join ", " 
                          (remove nil? (map 
                            (fn [city]
                              (if (and 
                                (not (nil? (:lon city)))
                                (not (nil? (:lat city)))
                                )
                              (str "{
                                  'type': 'Feature',
                                  'properties': {
                                    'description':'"  (:city city) "'
                                  },
                                  'geometry': {
                                    'type': 'Point',
                                    'coordinates': ["
                                      (:lon city)
                                      ","
                                      (:lat city)
                                    "]
                                  }
                                }"))
                            ) (->> %coll :cities :value))))
                          
                        "]}},
                  'paint': {
                      'circle-radius': 6,
                      'circle-color': '#ffa3d7'
                  },
                  'filter': ['==', '$type', 'Point'],
              });"
          
        "
        
        map.on('click', 'places', function (e) {
          var coordinates = e.features[0].geometry.coordinates.slice();
          var description = e.features[0].properties.description;
          
          // Ensure that if the map is zoomed out such that multiple
          // copies of the feature are visible, the popup appears
          // over the copy being pointed to.
          while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
          coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
          }
          
          new mapboxgl.Popup()
          .setLngLat(coordinates)
          .setHTML(description)
          .addTo(map);
        });
        
        // Change the cursor to a pointer when the mouse is over the places layer.
        map.on('mouseenter', 'places', function () {
          map.getCanvas().style.cursor = 'pointer';
        });
        
        // Change it back to a pointer when it leaves.
        map.on('mouseleave', 'places', function () {
          map.getCanvas().style.cursor = '';
        });
        
        });"
        )
      ]
    ]])
```

@id: `(montaigne.fns/slug (:name %))`  
@itinerary.airport-from: `(first (filter (fn [row] (= (:from %) (:IATA row))) (->> %airports)))`
@itinerary.airport-to: `(first (filter (fn [row] (= (:to %) (:IATA row))) (->> %airports)))`
@itinerary.country-from: `(or (->> % :airport-from :country) (clojure.string/trim (last (clojure.string/split (:from %) "," ))))`
@itinerary.country-from-flag: `(->> (filter (fn [flag] (= (:name flag) (:country-from %)) ) %flags) first :emoji)`
@itinerary.city-from: `(or (->> % :airport-from :city) (first (clojure.string/split (:from %) ",")))`
@itinerary.country-to: `(->> % :airport-to :country)`
@itinerary.city-to: `(->> % :airport-to :city)`
@itinerary.airport-from-lon: `(->> % :airport-from :lon)`
@itinerary.airport-from-lat: `(->> % :airport-from :lat)`
@itinerary.airport-to-lon: `(->> % :airport-to :lon)`
@itinerary.airport-to-lat: `(->> % :airport-to :lat)`
@itinerary.distance: `(montaigne.fns/calc-distance (:airport-from-lat %) (:airport-from-lon %) (:airport-to-lat %) (:airport-to-lon %))`
@visited-cities-with-layover: `(into [] (drop 1 (map-indexed (fn [idx row] {:city (:city-from row) :country (:country-from row) :country-flag (:country-from-flag row) :lat (:airport-from-lat row) :lon (:airport-from-lon row) :layover (:layover row) :days (montaigne.fns/duration-in-days (:date (get (->> % :itinerary :value) (dec idx))) (:date row)) :date-from (:date (get (->> % :itinerary :value) (dec idx))) :date-to (:date row)})(->> % :itinerary :value))))`
@visited-cities: `(into [] (filter (fn [row] (clojure.string/blank? (:layover row)) ) (->> % :visited-cities-with-layover :value)))`
@itinerary.carbon: `(montaigne.fns/calc-carbon (:distance %))`
@distance: `(apply + (map :distance (->> % :itinerary :value)))`
@carbon: `(apply + (map :carbon (->> % :itinerary :value)))`
@started: `(->> % :itinerary :value first :date)`  
@finished: `(->> % :itinerary :value last :date)`  
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
                  [:dt {:class "dib gray"} (:city row) ", " (:country row)]
                  [:dd {:class "dib ml1"} (:days row) " days; from " (:date-from row) " to " (:date-to row)]
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
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "distance"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "carbon"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "data"]

                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "layover"]]
              ]
              [:tbody {:class "lh-copy"}
                (map
                  (fn [row]
                    [:tr
                      [:td.nowrap (->> row :from)]
                      [:td.nowrap (->> row :to)]
                      [:td.nowrap (->> row :date)]
                      [:td.nowrap (->> row :type)]
                      [:td.nowrap (->> row :distance)]
                      [:td.nowrap (->> row :carbon)]
                      [:td.nowrap (->> row :data)]
                      [:td.nowrap (->> row :layover)]])
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


## Volunteering in Ukraine 2019

type: work  

### itinerary

from                | to                  | date       | type   | data   | layover 
------------------- | ------------------- | ---------- | ------ | ------ | -------- 
SFO                 | IST                 | 2019-04-13 | flight | TK80   | 
IST                 | KBP                 | 2019-04-15 | flight | TK459  | 
Kyiv, Ukraine       | Kramatorsk, Ukraine | 2019-04-18 | train  |        | 
Kramatorsk, Ukraine | Kyiv, Ukraine       | 2019-04-18 | train  |        |  
Kyiv, Ukraine       | Lviv, Ukraine       | 2019-04-26 | car    |        | 
Lviv, Ukraine       | Pylypets, Ukraine   | 2019-04-27 | car    |        | 
Pylypets, Ukraine   | Lviv, Ukraine       | 2019-04-29 | car    |        | 
Lviv, Ukraine       | Kyiv, Ukraine       | 2019-04-30 | car    |        | 
KBP                 | IST                 | 2019-04-30 | flight | TK460  | ✓
IST                 | SFO                 | 2019-05-01 | flight | TK79   | 

### summary

Volunteering trip.


## Temecula Feb 2019

type: work  

### itinerary

from                     | to                       | date       | type   | data              | layover
------------------------ | ------------------------ | ---------- | ------ | ----------------- | ---------
SFO                      | SAN                      | 2019-02-24 | flight | AS1956, A318/321  | ✓ 
San Diego, United States | Temecula, United States  | 2019-02-24 | car    |                   | 
Temecula, United States  | San Diego, United States | 2019-03-01 | car    |                   | 
SAN                      | SFO                      | 2019-03-01 | flight | AS1969, A318/321  | ✓ 


### summary

First time in this part of SoCal. Didn't do much since it was work event.


## Vail Feb 2019

type: friends  

### itinerary

from                  | to                    | date       | type   | data           | layover 
--------------------- | --------------------- | ---------- | ------ | -------------- | -------  
SFO                   | DEN                   | 2019-02-14 | flight | UA1013, 777-200|  
Denver, United States | Vail, United States   | 2019-02-15 | car    |                | 
Vail, United States   | Denver, United States | 2019-02-18 | car    |                | 
DEN                   | SFO                   | 2019-02-18 | flight | UA571, 757-200 | ✓

### summary

First time in Colorado. Great weather, great slops - exactly as I like.


## Buenos Aires and Paris

type: tourism  

### itinerary

from                    | to                      | date       | type   | data                   | layover 
----------------------- | ----------------------- | ---------- | ------ | ---------------------- | ---------
SFO                     | EWR                     | 2018-11-15 | flight | UA535, 757-200         | 
Newark, United States   | New York, United States | 2018-11-16 | car    |                        | ✓
New York, United States | Newark, United States   | 2018-11-17 | car    |                        | 
EWR                     | EZE                     | 2018-11-17 | flight | UA979, 767-400         | ✓
EZE                     | MAD                     | 2018-12-23 | flight | IB6856, A340-600       | 
MAD                     | ORY                     | 2018-12-24 | flight | IB3436, A320 SHARKLETS | ✓ 
CDG                     | OAK                     | 2019-01-05 | flight | DY7079, 787-9          | 


## Tijuana 2018 Last Trip

type: tourism  

### itinerary

from                     | to                      | date       | type   | data           | layover 
------------------------ | ----------------------- | ---------- | ------ | -------------- | ---------
SFO                      | SAN                     | 2018-10-12 | flight | UA361, 737-900 | 
San Diego, United States | Tijuana, Mexico         | 2018-10-12 | car    |                | ✓
Tijuana, Mexico          | San Diego, United States| 2018-10-12 | car    |                | 
SAN                      | SFO                     | 2018-10-15 | flight | UA334, 737-900 | ✓


## Mexico City and Tijuana 2018

type: tourism  

### itinerary

from            | to                       | date       | type   | data           | ✓ 
--------------- | ------------------------ | ---------- | ------ | -------------- | --------
SFO             | MEX                      | 2018-10-05 | flight | UA412, A320    | 
MEX             | TIJ                      | 2018-10-08 | flight | Y4813, A320    | 
Tijuana, Mexico | San Diego, United States | 2018-10-09 | car    |                | true
SAN             | SFO                      | 2018-10-09 | flight | UA662, 737-800 | 


## Europe Summer 2018

type: family  

### itinerary

from              | to                | date       | type   | data   | layover 
------------------| ----------------- | ---------- | ------ | ------ | --------
SFO               | MUC               | 2018-08-10 | flight | LH459  |  
MUC               | KBP               | 2018-08-11 | flight | LH2546 |  
KBP               | BCN               | 2018-08-17 | flight | PS991  |  
BCN               | ATH               | 2018-08-24 | flight | VY8100 |  
Athens, Greece    | Akrotiri, Greece  | 2018-08-24 | boat   |        | ✓
Akrotiri, Greece  | Athens, Greece    | 2018-08-30 | boat   |        |  
ATH               | ARN               | 2018-09-04 | flight | A3760  | 
ARN               | CPH               | 2018-09-04 | flight | SK1423 | ✓ 
CPH               | KEF               | 2018-09-05 | flight | WW903  |  
KEF               | SFO               | 2018-09-05 | flight | WW161  | ✓ 


## Ukraine First Trip in Years

type: family  

### itinerary

from | to  | date       | type   | data             | layover 
---- | --- | ---------- | ------ | ---------------- | -------- 
SFO  | EWR | 2017-11-16 | flight | UA1796, 777-200  | 
JFK  | MUC | 2017-11-17 | flight | LH411, A340-600  | 
MUC  | KBP | 2017-11-18 | flight | LH2544, A320     | ✓
KBP  | FRA | 2017-11-27 | flight | LH1493, A321     | 
FRA  | SFO | 2017-11-27 | flight | LH9052, 777-300  | ✓


### summary

Add train to Kharkiv


## Tijuna and San Diego First Trip 2018

type: friends and work  

### itinerary

from | to  | date       | type   | data 
---- | --- | ---------- | ------ | ------------  
SFO  | SAN | 2018-02-02 | flight | VX1958, A320  
SAN  | SFO | 2018-02-10 | flight | VX1979, A320  


## Hong Kong Birthday Trip

type: tourism  

### itinerary

from | to  | date       | type   | data
---- | --- | ---------- | ------ | --------------
SFO  | HKG | 2017-06-29 | flight | UA869, 777-300  
HKG  | SFO | 2017-07-10 | flight | UA862, 777-300  


## New York City October 2017

type: tourism  

### itinerary

from | to  | date       | type   | data  
---- | --- | ---------- | ------ | ------
SFO  | JFK | 2017-10-05 | flight | AA18  
JFK  | SFO | 2017-10-08 | flight | AA177 


## Phoenix May 2017

purpose: wedding

### itinerary

from | to  | date       | type   | data
---- | --- | ---------- | ------ | ------
SFO  | PHX | 2017-05-19 | flight | AA649 
PHX  | SFO | 2017-05-22 | flight | AA1642 


## New York City May 2017

type: friends  

### itinerary

from | to  | date       | type   | data  
---- | --- | ---------- | ------ | ---------------
SFO  | JFK | 2017-05-04 | flight | DL1144, 717-200   
JFK  | SFO | 2017-05-11 | flight | DL426, A320  


## Gili and Bali 2017

type: tourism  

### summary

Add boat ride to Gili

### itinerary

from                | to                  | date       | type   | data            | layover 
------------------- | ------------------- | ---------- | ------ | --------------- | -------------- 
SFO                 | SIN                 | 2017-03-24 | flight | SQ1, 777-300    | 
SIN                 | DPS                 | 2017-03-25 | flight | SQ946, A330-300 | ✓
Denpasar, Indonesia | Gili, Indonesia     | 2017-03-26 | boat   |                 | 
Gili, Indonesia     | Denpasar, Indonesia | 2017-04-07 | boat   |                 | 
DPS                 | SIN                 | 2017-04-09 | flight | SQ943, A330-300 |  
SIN                 | SFO                 | 2017-04-09 | flight | SQ2, 777-300    | ✓


## Hong Kong Second Trip

type: tourism  

### itinerary

from | to  | date       | type   | data 
---- | --- | ---------- | ------ | ---------------- 
SFO  | HKG | 2016-10-29 | flight | CX0879, 777-300  
HKG  | SFO | 2016-11-07 | flight | CX0870, 777-300  


## Hong Kong First Trip 2016

type: tourism  

### itinerary

from | to  | date       | type   | data 
---- | --- | ---------- | ------ | ----------------- 
SFO  | HKG | 2016-08-24 | flight | CX0873, 777-300  
HKG  | SFO | 2016-09-06 | flight | CX0892, A350-900  


## London First Trip 2016

type: tourism  

### itinerary

from | to  | date       | type   | data 
---- | --- | ---------- | ------ | ---------------- 
SFO  | LHR | 2016-05-06 | flight | OS7856, 777-200  
LHR  | SFO | 2016-05-15 | flight | OS7857, 777-200  


## STL 2015

purpose: StrangeLoop conference  

### itinerary

from | to  | date       | type   | data            | layover
---- | --- | ---------- | ------ | --------------- | --------
SFO  | DEN | 2015-09-23 | flight | UA1736, 737-900 | 
DEN  | STL | 2015-09-23 | flight | UA3395, ERJ-145 | ✓
STL  | SFO | 2015-09-28 | flight | UA6421, ERJ-170 | 


## Istanbul Family Trip 2015

purpose: family trip  

### itinerary

from | to  | date       | type   | data 
---- | --- | ---------- | ------ | ------ 
SFO  | IST | 2015-08-15 | flight | TK0080 
IST  | SFO | 2015-09-01 | flight | TK0079  


## Portland First Trip 2015

purpose: Clojure/West conference  

### itinerary

from | to  | date       | type   | data 
---- | --- | ---------- | ------ | ---------------
SFO  | PDX | 2015-04-18 | flight | UA464, A320  
PDX  | SFO | 2015-04-22 | flight | UA995, 737-800 


## Las Vegas First Trip 2015

purpose: Microconf  

### itinerary

from | to  | date       | type   | data 
---- | --- | ---------- | ------ | ---------------- 
SFO  | LAS | 2015-04-12 | flight | UA1662, 737-900 
LAS  | SFO | 2015-04-15 | flight | UA1528, 737-900 


## Provo 2015

purpose: React Week  

### itinerary

from                          | to                            | date       | type   | data 
----------------------------- | ----------------------------- | ---------- | ------ | ------
OAK                           | SLC                           | 2015-03-07 | flight | DL1082 
Salt Lake City, United States | Provo, United States          | 2015-03-07 | train  | 
Provo, United States          | Salt Lake City, United States | 2015-03-15 | train  | 
SLC                           | OAK                           | 2015-03-15 | flight | DL1082  


## Cancun and Playa del Carmen Second Time 2014

type: tourism  

### itinerary

from                     | to                       | date       | type   | data            | layover 
------------------------ | ------------------------ | ---------- | ------ | --------------- | --------
SFO                      | LAX                      | 2014-12-04 | flight | UA478, A319     | 
LAX                      | CUN                      | 2014-12-04 | flight | UA1276, 737-900 | ✓ 
Cancun, Mexico           | Playa del Carmen, Mexico | 2014-12-04 | bus    |                 | 
Playa del Carmen, Mexico | Cancun, Mexico           | 2014-12-07 | bus    |                 | 
CUN                      | SFO                      | 2014-12-07 | flight | UA1118, 737-900 | ✓


### summary

Add Playa del Carmen bus


## Istanbul and Macedonia 2014

type: tourism  

### itinerary

from              | to                | date       | type   | data 
----------------- | ----------------- | ---------- | ------ | ------ 
HRK               | SAW               | 2014-01-26 | flight | PC751 
SAW               | SKP               | 2014-03-23 | flight | PC711  
Skopje, Macedonia | Ohrid, Macedonia  | 2014-04-23 | bus    | 
Ohrid, Macedonia  | Skopje, Macedonia | 2014-04-30 | bus    | 


## Washington DC First Trip 2013

purpose: Clojure/conj  

### itinerary

from                         | to                           | date       | type  
---------------------------- | -----------------------------| ---------- | ------
New York, United States      | Washington DC, United States | 2013-11-13 | bus   
Washington DC, United States | New York, United States      | 2013-11-17 | bus   


## South America 2012-2013

type: tourism  

### itinerary

from                      | to                        | date       | type   | data   | layover
------------------------- | ------------------------- | ---------- | ------ | ------ | -----
KBP                       | FRA                       | 2012-11-17 | flight | LH1493 | 
FRA                       | GIG                       | 2012-11-17 | flight | DE6080 | ✓
Rio de Janeiro, Brazil    | Sao Paulo, Brazil         | 2012-12-14 | bus    |        | 
GRU                       | PTY                       | 2013-01-30 | flight | CM724  | 
PTY                       | MEX                       | 2013-01-30 | flight | CM136  | 
MEX                       | CUN                       | 2013-02-06 | flight | VOI712 | 
Cancun, Mexico            | Playa del Carmen, Mexico  | 2013-02-06 | bus    |        | ✓
Playa del Carmen, Mexico   | Cancun, Mexico           | 2013-05-04 | bus    |        | 
CUN                       | MEX                       | 2013-05-04 | flight | 4O2317 | ✓
MEX                       | GUA                       | 2013-05-04 | flight | 4O2912 | ✓
Guatemala City, Guatemala | San Salvador, El Salvador | 2013-06-11 | bus    |        | 
San Salvador, El Salvador | Tegucigalpa, Honduras     | 2013-06-22 | bus    |        | 
Tegucigalpa, Honduras     | León, Honduras            | 2013-07-07 | bus    |        | 
León, Honduras            | San Jose, Costa Rica      | 2013-07-21 | bus    |        | 
San Jose, Costa Rica      | Panama City, Panama       | 2013-07-22 | bus    |        | 
PTY                       | GYE                       | 2013-08-05 | flight | EQ505  | 
GYE                       | UIO                       | 2013-08-05 | flight | EQ314  | ✓ 
Quito, Ecuador            | Baños, Ecuador            | 2013-08-25 | bus    | DL2340 |  
Baños, Ecuador            | Quito, Ecuador            | 2013-09-01 | bus    | DL2340 | 
GRU                       | PTY                       | 2013-09-27 | flight | CM758  | 
PTY                       | JFK                       | 2013-09-27 | flight | CM830  | ✓


## Berlin 2012

type: tourism  

### summary

October 14-Nov5

### itinerary

from                     | to                       | date       | type   | data   | layover
------------------------ | ------------------------ | ---------- | ------ | ------ | -----
KBP      | TXL           | 2012-10-14 | flight |  | 
TXL      | KBP           | 2012-11-05 | flight |  | 


## Balkans 2012

type: tourism  

### itinerary

from                     | to                       | date       | type   | data   | layover
------------------------ | ------------------------ | ---------- | ------ | ------ | -----
Zagreb, Croatia      | Zadar, Croatia           | 2012-08-03 | bus |  | 
Zadar, Croatia      | Split, Croatia           | 2012-08-06 | bus |  | 
Split, Croatia      | Zagreb, Croatia           | 2012-08-14 | train |  | 
Zagreb, Croatia      | Sarajevo, Bosnia           | 2012-08-20 | bus |  | 
Sarajevo, Bosnia      | Podgorica, Montenegro           | 2012-09-03 | bus |  | 


## New York City First Trip 2012

type: tourism  

### itinerary

from | to  | date       | type   | data 
---- | --- | ---------- | ------ | ------ 
SFO  | JFK | 2012-06-15 | flight | DL2340    


## Leaving Montenegro

type: tourism  

### itinerary

from | to  | date       | type   | data           | layover
---- | --- | ---------- | ------ | -------------- | --------
TGD  | BUD | 2011-12-28 | flight | MA495, 737-600 |  
BUD  | KBP | 2011-12-29 | flight | MA110, 737-800 | ✓


## Berlin First Trip 2011

purpose: Google Dev Conf  

### itinerary

from                  | to                    | date       | type   | data  | layover
--------------------- | --------------------- | ---------- | ------ | ----- | --------
Podgorica, Montenegro | Belgrade, Serbia      | 2011-11-11 | bus    |       | 
BEG                   | VIE                   | 2011-11-11 | flight | OS772 | ✓
VIE                   | TXL                   | 2011-11-11 | flight | OS291 | ✓ 
TXL                   | VIE                   | 2011-11-20 | flight | OS272 |   
VIE                   | BEG                   | 2011-11-20 | flight | OS735 | ✓ 
Belgrade, Serbia      | Podgorica, Montenegro | 2011-11-20 | bus    |       | ✓


## Munich and Prague 2011

type: tourism  

### itinerary

from                   | to                     | date       | type   | data   | layover 
---------------------- | ---------------------- | ---------- | ------ | ------ | ---------
Podgorica, Montenegro  | Belgrade, Serbia       | 2011-08-22  | bus   |        | ✓
BEG                    | FMM                    | 2011-08-23 | flight | W64105 | ✓
Munich, Germany        | Prague, Czech Republic | 2011-08-24 | bus    |        | 
Prague, Czech Republic | Munich, Germany        | 2011-08-27 | bus    |        | 
FMM                    | BEG                    | 2011-08-30 | flight | W64106 | ✓
Belgrade, Serbia       | Podgorica, Montenegro  | 2011-08-30 | bus    |        | ✓


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
      [:title (:name %coll)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> %coll :description :value)))
        [:meta {:name "description" :content (->> %coll :description :value)}])
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
              [:a.link.f6.b.mb1 {:href (str "/" (->> entity :id :value))} (:name entity)]])
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
        [:blockquote {:class "athelas ml0 mt0 pl4 black-90 bl bw2 b--blue measure"}
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
      [:title (:name %coll)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> %coll :description :value)))
        [:meta {:name "description" :content (->> %coll :description :value)}])
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
        [:h1.lh-title.f3.athelas (:name %coll)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (str "/" (->> entity :id :value))} (:name entity)]
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
I read books. Travel a bit. Trying to find people and causes I can support.


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
      [:title (:name %coll)]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/favicon.png" :type "image/png"}]
      [:link {:rel "icon" :role "shortcut icon" :href "https://podviaznikov.com/img/logo.svg" :type "image/svg+xml"}]
      (if (not (nil? (->> %coll :description :value)))
        [:meta {:name "description" :content (->> %coll :description :value)}])
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
        [:h1.lh-title.f3.athelas (:name %coll)]
        [:ul {:class "ph0 pv4 mt0 list measure"}
        (map 
          (fn [entity]
            [:li.mb3
              [:a.link.f6.b.mb1 {:href (str "/" (->> entity :id :value))} (:name entity)]
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
              [:dd {:class "dib ml1"} (->> % :activities-count :value)]
            ]
          ]]
        [:section
          [:h2.f6 "activities"]
          [:article.lh-copy.measure
            [:table {:class "f6 w-100 mw8 center" :cellspacing "0"}
              [:thead
                [:tr
                  (map (fn [column]
                    [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white nowrap"} column]
                  )
                (->> % :activities :columns))]
              ]
              [:tbody {:class "lh-copy"}
                (map 
                  (fn [row]
                    [:tr
                      (map 
                        (fn [column-name]
                          [:td.nowrap (get row (keyword column-name))])
                      (->> % :activities :columns))])
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
                  (map 
                    (fn [column]
                      [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white nowrap"} column])
                  (->> % :intake :columns))]
              ]
              [:tbody {:class "lh-copy"}
                (map (fn [row]
                  [:tr
                    (map 
                      (fn [column-name]
                        [:td.nowrap (get row (keyword column-name))])
                      (->> % :intake :columns))])
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
football   |  ✓  |     |     |     |  ✓  |     |    
reading    |  ✓  |  ✓  |     |  ✓  |  ✓  |  ✓  |  ✓ 
spanish    |     |     |     |     |  ✓  |     |    
pushups    |  ✓  |  ✓  |     |     |     |     |    
edu event  |     |     |     |     |     |     |    
ent event  |     |     |     |     |     |  ✓  |    
cul event  |     |     |     |     |     |  ✓  |    
cycling    |     |     |     |     |     |     |    
tennis     |     |     |     |     |     |     |    
ping-pong  |     |     |     |     |     |     |    

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓  |  ✓ 
no coffee  |     |     |     |     |     |     |    
no sugar   |     |     |     |     |     |     |    


## week 11

start: 2019-05-12  
end: 2019-05-18  

### activities

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
dance      |     |     |     |     |     |     |    
football   |     |     |     |     |     |     |    
reading    |  ✓  |     |     |     |     |     |    
spanish    |     |     |     |     |     |     |    
pushups    |     |     |     |     |     |     |    
edu event  |     |     |     |     |     |     |    
ent event  |     |     |     |     |     |     |    
cul event  |  ✓  |     |     |     |     |     |    
cycling    |     |     |     |     |     |     |    
tennis     |     |     |     |     |     |     |    
ping-pong  |     |     |     |     |     |     |    

### intake

Activity   | Sun | Mon | Tue | Wed | Thu | Fri | Sat
-----------|-----|-----|-----|-----|-----|-----|-----
no alcohol |  ✓  |     |     |     |     |     |    
no coffee  |     |     |     |     |     |     |    
no sugar   |     |     |     |     |     |     |    


## week 12

start: 2019-05-19  
end: 2019-05-25  

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
