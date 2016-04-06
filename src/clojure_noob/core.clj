(ns clojure-noob.core
)

(defn foo
  "I'm a little teapot."
  [& args]
  (println "I'm a little teapot"))

(+ 2 3 4)	
(str "It was the panda " "in the library " "with a dust blower")
(if true
	"abra cadabra"
	"hocus pocus"
)

(if true
	(do (println "abra cadabra")
		"it worked"
	)
	(do (println "hocus pocus")
		"it failed"
	)
)

(when true
	(println "abra cadabra")
	"it worked")

(def failed_protoganist_names
	["Larry Potter"
	 "Doreen the explorer"
	 "The Incredible Bulk" 	 
	])

(def severity :mild)
(def error_message "Oh god! its a disaster,")
(if (= severity :mild)
		(def error_message (str error_message "we're mildly inconvenianced"))
		(def error_message (str error_message "we are doomed"))
	)

(nil? 1)
(nil? nil)
(= 1 1)
(= "one" "one")
(= nil nil)
(def _name "Chewbecca")
(str " \"ugugugugugh\" - " _name)
(= 1 2)	
{:a 1 :b "boring example" :c [] "string-key" +}
{:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}}	
(get {:a 0 :b {:c "ho hum"}} :b)
(get {:a 0 :b 1} :c "UNICORNS")
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
({:a 0 :b {:c "ho hum"}} :b)

(:a {:a 0 :b {:c "ho hum"}})

(get [3 2 1] 0)
(vector "creepy" "full" "moon")	
(conj [1 2 3] 4)

(list 1 2 3 4)
'(1 3 3 7)

(nth '(1 1 2 3) 2)
(conj '(1 1 2 3) 0)

(println #{"hannah montana" "miley cyrus" 20 45})

(conj #{:a :b} :c)
(conj #{:a :b} :b)
(get #{:a :b} :a)
(:a #{ :a { :b "Eternal" :c "Sunshine"  :d ["of" "the" "spotles" "mind"] } } )
(set ["hanna" "anna" "hanna" "hannah"])
(set '("robert" "micheal" "marlon" "francis" "micheal" "robert")) 
(def galaxies ["Milky Way","Andromeda"])
(eval 'galaxies)
;eval is not working

(first ['failed_protoganist_names,'galaxies])
((or + -) 1 2 3)
;or returns the first truthy value while and returns the first falsy value or the last falsy value

((and (= 1 1) +) 1 2 3)

(map inc [0 1 2 3 4])
;;even though you passed it a vector map doesnot return a vector...
;;special forms cannot be passed to a function as arguments.
;;macros evaluate their arguments differently too but that's a discussion for another day.

(defn too-enthusiastic
	;;doc-string can be used to generate documentation
	"return a cheer that might be too enthusiastic" 
	[name]
	(str "oh my god " name " you are the best man slash woman for me,we should run away together")
)

(too-enthusiastic "zelda")

(doc too-enthusiastic)

;;clojure has the capability of defining multi-arity functions

(defn multi-arity 
	"this function can take upto three arguments"
	([firstArg secondArg thirdArg]
			(str firstArg secondArg thirdArg)
		)
	([firstArg secondArg] 
			(str firstArg secondArg)
		)
	([arg] 
			(str arg)
		)
)

(multi-arity "Me")

;;provide default parameters for a function
(defn x-chop
	"Describe the kind of chop you're inflicting on someone",
	([_name chop] 
			(str "I " chop " chop " _name "!" " Take " " that.")
		)
	([_name] 
			(x-chop _name "karate")
		)
	)

(x-chop "Kanye West" "slap")
(x-chop "Kanye West")

(defn codger-communication
	[whippersnapper]
		(str "get off my lawn ",whippersnapper)
	)

(defn codger
	[& whippersnappers] ;;the ampersand is used to indicate rest-params
		(map codger-communication whippersnappers)
	)

(defn favourite-things
	[name & things]
	(str "Hi " name "some of my favourite things are " 
			(clojure.string/join "," things)
		)
	)

(defn first-thing-fn
	[[first-thing]];;this understands that the argument is a vector and we need the first value in it
	first-thing
	)

(defn my-other-first 
	[collection]
	(first collection)
)

(first-thing-fn ["one" "two" "three"])
(my-other-first ["a" "b" "v"])

(defn chooser
	[[first-choice second-choice & other-choices]]
		(println (str "Your first choice is " first-choice))
		(println (str "Your second choice is " second-choice))
		(println (str "We are ignoring some choices,but if you insist," 
				(clojure.string/join "," other-choices)
			))
	)

(chooser ["Marmalade" "Handsome Jack" "Pigpen" "Aquaman"])

;;similarly you can destructure maps too

(defn announce-treasure-location 
	[{lat :lat lng :lng}]
	(println (str "Treasure lat " lat))
	(println (str "Treasure long " lng))	
		)

(announce-treasure-location {:lat 27.34 :lng 56.43})

;;this can be improved with syntax for map

(defn recieve-treasure-location 
	[{:keys [lat lng] :as treasure-location}]
		(println (str "Treasure lat " lat))
		(println (str "Treasure long " lng))
		(println (str "information is false" 
			(if (:incorrect treasure-location)
				" yes"
				" no"
				)
			)
		)
	)

(recieve-treasure-location {:lat 27.45 :lng 43.32 :incorrect true})

;;your function evaluates the last form returned from a function
;;you can create anonymous functions with the function fn
;;you can give your anonymous function a name
;;you can define your anonymous function using a # sign
;;when you do this,iyou pass arguments using %1 %2 etc
;;good for small functions
(map (fn [name] 
		(str "Hi " name)
	) ["Darth Vader" "Yoda"]
)

(map (fn doubler [value] 
		(* 2 value)

	)
[2,4,6,8])

(#(str "I am " %1 ",I work as a " %2) "Vamsi" "Web Developer")

(defn inc-maker 
	"Create a custom incrementer"
	[inc-by]
	#(+ % inc-by)
)

(def inc3 (inc-maker 3))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

;;strings with a hash in front of them are interpreted as regex
;;clojure adopts the regex style of the language it is using
(defn needs-matching-part? 
	[part];;re-find returns th first match within a string
	(re-find #"^left-" (:name part))
)

(defn make-matching-part [part]
		{;; clojure.string/replace replaces all matches with a string
			;;http://www.lispcast.com/clojure-regex
			:name (clojure.string/replace (:name part) #"^left-" "right-") 
			:size (:size part)
		}
	)

(defn symmetrize-body-parts
	"Expects a sequence of maps which have a :name and a :size"
	[asym-body-parts] ;
	(loop [remaining-asym-parts asym-body-parts final-body-parts []]
		(if (empty? remaining-asym-parts)
				final-body-parts
				(let [
					[part & remaining] remaining-asym-parts
					final-body-parts (conj final-body-parts part)
				]
				(if (needs-matching-part? part)
					(recur remaining (conj final-body-parts (make-matching-part part)))
					(recur remaining final-body-parts))
				)
		)
	)
)

(defn better-symmetrize-body-parts 
	"Expects a sequence of maps which have a :name and a :size"
	[asym-body-parts]
	(reduce (fn [final-body-parts part] 
			(let [final-body-parts (conj final-body-parts part)]
					(if (needs-matching-part? part)
							(conj final-body-parts (make-matching-part part))
							final-body-parts
						) 
				)
			)
		[] asym-body-parts)
	)	 

(better-symmetrize-body-parts asym-hobbit-body-parts)

(fn sayHello
  	"say hello to someone",
   [x]
  (println (str "Hello" x "!"))
 )
