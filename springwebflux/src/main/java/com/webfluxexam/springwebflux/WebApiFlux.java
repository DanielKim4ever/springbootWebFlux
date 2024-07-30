package com.webfluxexam.springwebflux;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.reactive.function.client.WebClient;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.webfluxexam.springwebflux.utils.Logger;
import java.util.List;
import java.util.ArrayList;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * Mono 활용 예제
 *  - worldtimeapi.org Open API를 이용해서 서울의 현재 시간을 조회한다.
 */
public class WebApiFlux {
   
    private final WebClient webClient;

    public WebApiFlux() {
       this.webClient = WebClient.builder().baseUrl("http://worldtimeapi.org/api/timezone")
       .build();
    }

    public Flux<String> getMultipleData(List<String> endpoints) {
        Flux<String> response = Flux.fromIterable(endpoints)
                   .flatMap(endpoint -> webClient.get()
                                                 .uri(endpoint)
                                                 .retrieve()
                                                 .bodyToMono(String.class));
                                                 return response;
    }

    public static void main(String[] args) {
        List<String> endpoints = new ArrayList<String>();
        endpoints.add("/Asia/Seoul");
        endpoints.add("/Europe/London");
        endpoints.add("/America/Argentina/Salta");
        endpoints.add("/Asia/Qatar");
        endpoints.add("/Australia/Melbourne");
        endpoints.add("/Asia/Ora");
        endpoints.add("/Asia/Singapore");
        endpoints.add("/Asia/Taipei");
        endpoints.add("/America/Rio_Branco");
        endpoints.add("/America/Scoresbysund");

        WebApiFlux webapi = new WebApiFlux();
        Instant start = Instant.now();
        Flux<String> responses = webapi.getMultipleData(endpoints);
        responses.subscribe(System.out::println);
        Instant end = Instant.now();
        System.out.println("수행 시간: " + Duration.between(start, end).toMillis() + " ms");
    }
}
