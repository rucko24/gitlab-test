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
import java.util.Objects;
import java.util.function.Function;

/**
 * Service to create in the existing repository a branch develop
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class GitlabCreateBranchServiceWebClient {

    private final WebClient gitlabWebClientRepositoryBuilder;

    public Mono<GitlabRepositoryResponse> createDevelopBranchInGitlab(final String repositoryId) {
        return gitlabWebClientRepositoryBuilder.post()
                .uri(this.createUriBuilder(repositoryId))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.value() == 400, clientResponse -> Mono.error(new GitlabRepositoryException("Request contains incorrect syntax or cannot be processed")))
                .onStatus(httpStatusCode -> httpStatusCode.value() == 500, clientResponse -> Mono.error(new GitlabRepositoryTechnicalException("Internal Server Error")))
                .bodyToMono(GitlabRepositoryResponse.class)
                .map(gitlabRepositoryResponse -> {
                    final var response = new GitlabRepositoryResponse();
                    response.setId(repositoryId);
                    return response;
                })
                .doOnNext(onNext -> log.info("Branch successfully created [id: {}]", repositoryId));
    }

    private Function<UriBuilder, URI> createUriBuilder(final String id) {
        return uriBuilder -> uriBuilder.path("/"+ id + "/repository/branches")
                .queryParam("branch","develop")
                .queryParam("ref","master")
                .build();
    }

}
