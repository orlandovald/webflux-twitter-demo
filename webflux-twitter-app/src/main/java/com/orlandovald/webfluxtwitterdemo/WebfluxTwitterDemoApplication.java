package com.orlandovald.webfluxtwitterdemo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.orlandovald.twitter.TwitterApiEndpoint;
import com.orlandovald.twitter.TwitterOAuth;
import com.orlandovald.webfluxtwitterdemo.model.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@EnableReactiveMongoRepositories
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class WebfluxTwitterDemoApplication extends AbstractReactiveMongoConfiguration {

    private static Logger log = LoggerFactory.getLogger(WebfluxTwitterDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebfluxTwitterDemoApplication.class, args);
    }

    @Override
    protected String getDatabaseName() {
        return "cltjug";
    }

    @Override
    public MongoClient mongoClient() {
        // this assumes your MongoDB is running on the default port, i.e. 27017
        return MongoClients.create();
    }

    @Bean
    public CommandLineRunner tweetBot(TwitterOAuth twitterOAuth, ReactiveTweetRepository tweetRepo) {

        return args -> {
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

            String tracks = "#cltjug,#FathersDay";
            if (args.length > 0) {
                log.info("Using arguments as tracks");
                tracks = String.join(",", args);
            }

            log.info("Filtering tracks [{}]", tracks);
            body.add("track", tracks);

            WebClient webClient = WebClient.create()
                    .filter((currentRequest, next) ->
                            next.exchange(ClientRequest.from(currentRequest)
                                    .header(HttpHeaders.AUTHORIZATION, twitterOAuth.oAuth1Header(
                                            currentRequest.url(), currentRequest.method(), body.toSingleValueMap()))
                                    .build()));

            Flux<Tweet> tweets = webClient
                    .post()
                    .uri(TwitterApiEndpoint.TWITTER_STREAM_API_STATUS_FILTER_URL)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(body))
                    .exchange()
                    .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Tweet.class));

            tweetRepo.saveAll(tweets).subscribe(System.out::println);

        };
    }


}
