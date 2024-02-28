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
 * WebClient builder with gitlab baseurl
 */
@Configuration
public class GitlabWebClientRepositoryBuilderConfiguration {

    /**
     * <p>The gitlab base url for creating a basic repository with master</p>
     * <br>
     *  <p>The other uris used with this client are:</p>
     *  <p>
     *  <ul>
     *  <li>To create a tag: <strong>/:id/repository/tags?tag_name=0.0.1&ref=master</strong></li>
     *  <li>To create a new branch: <strong>/:id/repository/branches?branch=develop&ref=master</strong></li>
     *  </ul>
     *  </p>
     */
    @Value("${gitlab-base-url.project}")
    public String baseUrl;

    @Bean
    public WebClient gitlabWebClientRepositoryBuilder(final WebClient.Builder webClient) {

        final HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .doOnConnected((Connection connection) ->
                        connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        return webClient
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION,"")
                .build();
    }
}