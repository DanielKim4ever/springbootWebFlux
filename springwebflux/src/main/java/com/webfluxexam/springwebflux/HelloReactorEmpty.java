package com.webfluxexam.springwebflux;

import com.webfluxexam.springwebflux.utils.Logger;

import reactor.core.publisher.Mono;

public class HelloReactorEmpty {
    public static void main(String[] args){
        Mono.empty()
        .subscribe(
                  data -> Logger.info( "# emitted data: {}",data),
                  error -> {},
                  () -> Logger.info("# emitted onComplete signal")
                );

       
    }

}
