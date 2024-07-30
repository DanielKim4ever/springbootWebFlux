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
import org.springframework.web.util.UriComponentsBuilder;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.webfluxexam.springwebflux.utils.Logger;

import reactor.core.publisher.Flux;
/**
 * Mono 활용 예제
 *  - worldtimeapi.org Open API를 이용해서 서울의 현재 시간을 조회한다.
 */
public class WebFluxExamRest {
    public static void main(String[] args) {
        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        URI worldTimeUri1 = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Europe/London")
        .build()
        .encode()
        .toUri();


        URI worldTimeUri2 = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/America/Argentina/Salta")
        .build()
        .encode()
        .toUri();

        URI worldTimeUri3 = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Asia/Qatar")
        .build()
        .encode()
        .toUri();

        URI worldTimeUri4 = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Australia/Melbourne")
        .build()
        .encode()
        .toUri();

        URI worldTimeUri5 = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Asia/Oral")
        .build()
        .encode()
        .toUri();

        URI worldTimeUri6 = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Asia/Singapore")
        .build()
        .encode()
        .toUri();

        URI worldTimeUri7 = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Asia/Taipei")
        .build()
        .encode()
        .toUri();

        URI worldTimeUri8 = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/America/Rio_Branco")
        .build()
        .encode()
        .toUri();

        URI worldTimeUri9 = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/America/Scoresbysund")
        .build()
        .encode()
        .toUri();



        Instant start = Instant.now();
        Flux.just(
                restTemplate.exchange(worldTimeUri, HttpMethod.GET, new HttpEntity<String>(headers), String.class),
                restTemplate.exchange(worldTimeUri1, HttpMethod.GET, new HttpEntity<String>(headers), String.class),
                restTemplate.exchange(worldTimeUri2, HttpMethod.GET, new HttpEntity<String>(headers), String.class),
                restTemplate.exchange(worldTimeUri3, HttpMethod.GET, new HttpEntity<String>(headers), String.class),
                restTemplate.exchange(worldTimeUri4, HttpMethod.GET, new HttpEntity<String>(headers), String.class),
                restTemplate.exchange(worldTimeUri5, HttpMethod.GET, new HttpEntity<String>(headers), String.class),
                restTemplate.exchange(worldTimeUri6, HttpMethod.GET, new HttpEntity<String>(headers), String.class),
                restTemplate.exchange(worldTimeUri7, HttpMethod.GET, new HttpEntity<String>(headers), String.class),
                restTemplate.exchange(worldTimeUri8, HttpMethod.GET, new HttpEntity<String>(headers), String.class),
                restTemplate.exchange(worldTimeUri9, HttpMethod.GET, new HttpEntity<String>(headers), String.class)
        )
                .map(response -> {
                    DocumentContext jsonContext = JsonPath.parse(response.getBody());
                    String dateTime = jsonContext.read("$.datetime");
                    return dateTime;
                })
                .subscribe(
                        data -> Logger.info("# emitted data: " + data),
                        error -> {
                            Logger.onError(error);
                        },
                        () -> Logger.info("# emitted onComplete signal")
                );
                Instant end = Instant.now();
                System.out.println("수행 시간: " + Duration.between(start, end).toMillis() + " ms");

    }
}
