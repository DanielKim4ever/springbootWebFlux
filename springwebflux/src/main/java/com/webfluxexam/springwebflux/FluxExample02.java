package com.webfluxexam.springwebflux;

import com.webfluxexam.springwebflux.utils.Logger;

import reactor.core.publisher.Flux;

public class FluxExample02 {

    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{3,6,7,9})
        .filter(num->num > 6)
        .map(num -> num + 2)
        .subscribe(multiply->Logger.info("# multiple : {}", multiply));
    }

}
