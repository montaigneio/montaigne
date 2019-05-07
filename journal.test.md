# @index

description: My tips

### template

```clojure
    (montaigne.fns/html [:div "hello"])
```

# data

airports: `(:airports (montaigne.fns/get-json-private "https://cdn.rawgit.com/konsalex/Airport-Autocomplete-JS/3dbde72e/src/airports.json"))`

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
@itinerary.duration: `(:date %prev)`
@itinerary.carbon: `(montaigne.fns/calc-carbon (:distance %))`
@visited-cities: `(map-indexed (fn [idx row] {:city (:city-from row) :country (:country-from row) :days (montaigne.fns/duration-in-days (:date (get (->> % :itinerary :value) (dec idx))) (:date row))})(->> % :itinerary :value))`
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
        [:div
          [:p "start"]
          [:p (str (->> % :visited-cities :value))]
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
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "country from"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "city from"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "country to"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "city to"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "layover"]
                  [:th {:class "fw6 bb b--black-20 tl pb3 pr3 bg-white"} "duration"]
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
                      [:td (->> row :country-from)]
                      [:td (->> row :city-from)]
                      [:td (->> row :country-to)]
                      [:td (->> row :city-to)]
                      [:td (->> row :layover)]
                      [:td (->> row :duration)]])
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

