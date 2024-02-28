package com.example.testgitlab.service.client;

import com.example.testgitlab.gitlabexceptions.GitlabRepositoryException;
import com.example.testgitlab.gitlabexceptions.GitlabRepositoryTechnicalException;
import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Web client for gitlab that will allow you to create a repository
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class GitlabCreateRepositoryWebClient {

    private final WebClient gitlabWebClientRepositoryBuilder;

    public Mono<GitlabRepositoryResponse> createRepositoryInGitlab(Mono<GitlabRepositoryRequest> gitlabRequestBody) {
        return gitlabWebClientRepositoryBuilder.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gitlabRequestBody, GitlabRepositoryRequest.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.value() == 400,
                        clientResponse -> Mono.error(new GitlabRepositoryException("Request contains incorrect syntax or cannot be processed")))
                .onStatus(httpStatusCode -> httpStatusCode.value() == 500,
                        clientResponse -> Mono.error(new GitlabRepositoryTechnicalException("Internal Server Error")))
                .bodyToMono(GitlabRepositoryResponse.class)
                .doOnNext(onNext -> log.info("Repository successfully created [id: {}]", onNext.getId()));
    }

}
