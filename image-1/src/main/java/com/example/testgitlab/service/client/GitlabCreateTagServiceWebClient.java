package com.example.testgitlab.service.client;

import com.example.testgitlab.gitlabexceptions.GitlabRepositoryException;
import com.example.testgitlab.gitlabexceptions.GitlabRepositoryTechnicalException;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

/**
 * Service to create in the existing repository a tag 0.0.1
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class GitlabCreateTagServiceWebClient {

    private final WebClient gitlabWebClientRepositoryBuilder;

    public Mono<GitlabRepositoryResponse> createTagInGitlab(final String repositoryId) {
        return gitlabWebClientRepositoryBuilder.post()
                .uri(this.createUriBuilder(repositoryId))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.value() == 400,
                        clientResponse -> Mono.error(new GitlabRepositoryException("Request contains incorrect syntax or cannot be processed")))
                .onStatus(httpStatusCode -> httpStatusCode.value() == 500,
                        clientResponse -> Mono.error(new GitlabRepositoryTechnicalException("Internal Server Error")))
                .bodyToMono(GitlabRepositoryResponse.class)
                .map(gitlabRepositoryResponse -> {
                    final var response = new GitlabRepositoryResponse();
                    response.setId(repositoryId);
                    return response;
                })
                .doOnNext(onNext -> log.info("Tag successfully created [id: {}]", repositoryId));
    }

    private Function<UriBuilder, URI> createUriBuilder(final String id) {
        return uriBuilder -> uriBuilder.path("/"+ id + "/repository/tags")
                .queryParam("tag_name","0.0.1")
                .queryParam("ref","master")
                .build();
    }

}
