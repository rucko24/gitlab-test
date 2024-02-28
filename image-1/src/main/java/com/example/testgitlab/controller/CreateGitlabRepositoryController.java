package com.example.testgitlab.controller;

import com.example.testgitlab.api.GitlabApi;
import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import com.example.testgitlab.service.client.GitlabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CreateGitlabRepositoryController implements GitlabApi {

    private final GitlabService gitlabService;

    @Override
    public Mono<GitlabRepositoryResponse> createGitlabRepository(Mono<GitlabRepositoryRequest> gitlabRespositoryRequest, ServerWebExchange exchange) {
        return gitlabService.createRepositoryWithDevelopBranchAndTagName(gitlabRespositoryRequest);
    }


}
