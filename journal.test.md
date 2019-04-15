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

@id: `(montaigne.fns/slug (:name %))`
@itinerary.airport: `(count %data)`


## Temecula Feb

from: San Francisco  
to: Temecula

### itinerary

from | to
-----|----
SFO  | LAX
LAX  | SFO 


### summary

Great trip.
What can one say?

Amazing stuff.


## Vail Feb

from: San Francisco  
to: Vail  
type: friends  


## Buenos Aires And Paris

from: San Francisco  
to: *{Buenos Aires, New York, Paris}

