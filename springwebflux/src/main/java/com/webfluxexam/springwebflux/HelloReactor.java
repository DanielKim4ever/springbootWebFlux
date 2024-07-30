package com.webfluxexam.springwebflux;

import com.webfluxexam.springwebflux.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class HelloReactor {
    public static void main(String[] args){
        Mono.just("Hello Reactor")
        .subscribe(message -> System.out.println(message) );

        Flux<String> sequece = Flux.just("Hello","Reactor");
        sequece
               .map(data -> data.toLowerCase())
               .subscribe(data -> Logger.onNext(data));
    }

}
