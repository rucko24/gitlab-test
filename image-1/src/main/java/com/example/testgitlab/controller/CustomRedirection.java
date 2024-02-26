package com.example.testgitlab.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class CustomRedirection {

    @Hidden
    @GetMapping("/")
    public Mono<Void> customRedirectionToSwaggerDoc(final ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().setLocation(URI.create("/webjars/swagger-ui/index.html#"));
        return response.setComplete();
    }
}
