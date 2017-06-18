package com.orlandovald.webfluxtwitterdemo;

import com.orlandovald.webfluxtwitterdemo.model.Tweet;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ovaldez
 */
@Controller
public class TweetController {

    private ReactiveTweetRepository repo;

    public TweetController(ReactiveTweetRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    Mono<String> home() {
        return Mono.just("home");
    }

    @GetMapping(value = "/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    Flux<Tweet> tweets() {
        return repo.findWithTailableCursorBy();
    }

}
