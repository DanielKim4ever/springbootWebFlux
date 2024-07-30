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
public class WebApiMono {
   
    private final WebClient webClient;

    public WebApiMono() {
       this.webClient = WebClient.builder().baseUrl("http://worldtimeapi.org/api/timezone")
       .build();
    }

    public String getData(String endpoint) {
        Mono<String> response = webClient.get()
                                         .uri(endpoint)
                                         .retrieve()
                                         .bodyToMono(String.class);
       return response.block();
    }

    public static void main(String[] args) {
        String endpoint = "/Asia/Seoul";
        WebApiMono webapi = new WebApiMono();
        Instant start = Instant.now();
        String data = webapi.getData(endpoint);
        Instant end = Instant.now();
        System.out.println("수행 시간: " + Duration.between(start, end).toMillis() + " ms");
        System.out.println(data);
    }
}
