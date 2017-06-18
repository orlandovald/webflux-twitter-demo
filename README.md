# Spring 5 Webflux Demo

This is the code I used during my Spring Webflux presentation at the Charlotte Java User Group on 6/14/17.

## What is this again?

In short, this codes builds an application that connects to the [Twitter Streaming API](https://dev.twitter.com/streaming/reference/post/statuses/filter) to retrive a stream of Tweets,
the tweets are read using Spring's WebClient, stored in a MongoDB using Spring Data's new ReactiveCrudRepository and finally
exposed as a REST point that sends Server Sent Events as we get new Tweets real time, all of these using a Reactive programming model.


### Modules
##### twitter-auth-spring-boot-starter
Helps to create the Twitter Authorization signature, heavily based and dependent on Twitter's [Hosebird Client (hbc)](https://github.com/twitter/hbc).
Don't use this for any other purpose than this demo, is ugly, I just built this to save time during the live coding part of the presentation.
##### webflux-twitter-app
This is the actual application that does the work

### Dependencies

#### MongoDB
I'm using MongoDB for this example, so you'll need this up and running in your local machine, if you are on OSX, you can easily install
MongoDB using Homebrew

#### A Twitter App
You need to create a Twitter App, this is free, just go to [Twitter Apps](https://apps.twitter.com/)

### Set up
1. Create an application-secret.properties file under `webflux-twitter-app/src/main/resources` that looks like the below, 
use the values from your created Twitter application

```
twitter.consumer-key=<Your Twitter App's Consumer Key (API Key)>
twitter.consumer-secret=<Your Twitter App's Consumer Secret (API Secret)>
twitter.token=<Your Twitter App's Access Token>
twitter.secret=<Your Twitter App's Access Token Secret>
```

2. For exposing the endpoint as an infinite stream of Server Sent Events we need to create a tailable cursor to the Tweets collection, in order to 
 do that we need a Capped Collection in MongoDB, so we'll need to create this manually, you can create it using the below
 commands after connecting to the Mongo shell.
 
    1. Create and start using the cltjug database with the command `use cltjug`
    2. Create the collection with something like `db.createCollection("tweets", {capped:true, max:1500, size:1000000})`
    
3. Time to compile and build,

`mvn clean install`

4. Below an example of how to run the program, every parameter will be used as a filter track, if no arguments are present a
 default filter will be used
 
`java -jar webflux-twitter-app/target/webflux-twitter-app-0.0.1-SNAPSHOT.jar "#cltjug" "webflux"`

If everything goes well you will start seeing the Tweets in the console, you can curl the tweets enpoint at [http://localhost:8080/tweets](http://localhost:8080/tweets) 

A thymeleaf template is included with enough Javascript to connect to the endpoint and display the tweets, you can open it at [http://localhost:8080/](http://localhost:8080/)
