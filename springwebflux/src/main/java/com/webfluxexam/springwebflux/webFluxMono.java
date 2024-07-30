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

import reactor.core.publisher.Mono;
/**
 * Mono 활용 예제
 *  - worldtimeapi.org Open API를 이용해서 서울의 현재 시간을 조회한다.
 */
public class webFluxMono {
    public static void main(String[] args) {

        Instant start = Instant.now();
        callMonoRest("worldtimeapi.org","/api/timezone/Asia/Seoul");
        callMonoRest("worldtimeapi.org","/api/timezone/Europe/London");
        callMonoRest("worldtimeapi.org","/api/timezone/America/Argentina/Salta");
        callMonoRest("worldtimeapi.org","/api/timezone/Asia/Qatar");
        callMonoRest("worldtimeapi.org","/api/timezone/Australia/Melbourne");
        callMonoRest("worldtimeapi.org","/api/timezone/Asia/Oral");
        callMonoRest("worldtimeapi.org","/api/timezone/Asia/Singapore");
        callMonoRest("worldtimeapi.org","/api/timezone/Asia/Taipei");
        callMonoRest("worldtimeapi.org","/api/timezone/America/Rio_Branco");
        callMonoRest("worldtimeapi.org","/api/timezone/America/Scoresbysund");
        Instant end = Instant.now();
        System.out.println("수행 시간: " + Duration.between(start, end).toMillis() + " ms");

    }

    private static void callMonoRest(String host, String path){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
                .host(host)
                .port(80)
                .path(path)
                .build()
                .encode()
                .toUri();
                Instant start = Instant.now();
        Mono.just(
                restTemplate.exchange(worldTimeUri, HttpMethod.GET, new HttpEntity<String>(headers), String.class)
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

                
                

    }
}
