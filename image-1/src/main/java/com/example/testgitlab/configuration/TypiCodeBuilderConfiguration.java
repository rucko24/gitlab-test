package com.example.testgitlab.configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.Connection;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

/**
 * WebClient builder with typicode baseurl
 */
@Configuration
public class TypiCodeBuilderConfiguration {

    @Bean
    public WebClient callGetTodoTypiCode(final WebClient.Builder webClient) {

        final HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .doOnConnected((Connection connection) ->
                        connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        return webClient
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
}