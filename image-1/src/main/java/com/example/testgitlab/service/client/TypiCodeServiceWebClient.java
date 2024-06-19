package com.example.testgitlab.service.client;

import com.example.testgitlab.gitlabexceptions.GitlabRepositoryException;
import com.example.testgitlab.gitlabexceptions.GitlabRepositoryTechnicalException;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import com.example.testgitlab.model.TestRecordResponse;
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
 * Call to typicode server
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class TypiCodeServiceWebClient {

    private final WebClient callGetTodoTypiCode;

    public Mono<TestRecordResponse> createCallTypiCode() {
        return callGetTodoTypiCode.get()
                .uri("/todos/2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.value() == 400,
                        clientResponse -> Mono.error(new RuntimeException("Request contains incorrect syntax or cannot be processed")))
                .onStatus(httpStatusCode -> httpStatusCode.value() == 500,
                        clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(TestRecordResponse.class)
                .doOnNext(onNext -> log.info("Ok to get todos [id: {}]", onNext.completed()));
    }

}
