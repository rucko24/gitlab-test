package com.example.testgitlab.service.client;

import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
@RequiredArgsConstructor
public class GitlabService {

    private final GitlabCreateRepositoryWebClient gitlabCreateRepositoryWebClient;

    private final GitlabCreateBranchServiceWebClient gitlabCreateBranchServiceWebClient;

    public Mono<GitlabRepositoryResponse> createRepositoryWithDevelopBranch(Mono<GitlabRepositoryRequest> gitlabRespositoryRequest) {
        return gitlabCreateRepositoryWebClient.createRepositoryInGitlab(gitlabRespositoryRequest)
                .flatMap(this::handleMergeWithAnotherCall);
    }

    private Mono<GitlabRepositoryResponse> handleMergeWithAnotherCall(GitlabRepositoryResponse gitlabRespositoryResponse) {
        final var repositoryId = gitlabRespositoryResponse.getId();
        return this.gitlabCreateBranchServiceWebClient.createDevelopBranchInGitlab(repositoryId);
    }


}
