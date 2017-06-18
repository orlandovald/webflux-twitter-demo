package com.orlandovald.webfluxtwitterdemo;

import com.orlandovald.webfluxtwitterdemo.model.Tweet;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author ovaldez
 */
public interface ReactiveTweetRepository extends ReactiveCrudRepository<Tweet, String> {

    @Tailable
    Flux<Tweet> findWithTailableCursorBy();

}
