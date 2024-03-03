package com.example.testgitlab.service.client;

import com.example.testgitlab.GitlabRepositoryArgumentsProvider;
import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class GitlabServiceTest {

    @InjectMocks
    private GitlabService gitlabService;

    @Mock
    private GitlabCreateRepositoryWebClient gitlabCreateRepositoryWebClient;

    @Mock
    private GitlabCreateBranchServiceWebClient gitlabCreateBranchServiceWebClient;

    @Mock
    private GitlabCreateTagServiceWebClient gitlabCreateTagServiceWebClient;

    @ParameterizedTest
    @ArgumentsSource(GitlabRepositoryArgumentsProvider.class)
    @DisplayName("The service must return an id with the value '55196040'.")
    void setupGitlabServiceMustReturnAnId(GitlabRepositoryRequest request, GitlabRepositoryResponse response,
                                          String repositoryId) throws IOException {

        Mono<GitlabRepositoryResponse> responseMono = Mono.just(response);
        Mono<GitlabRepositoryRequest> requestMono = Mono.just(request);

        Mockito.when(gitlabCreateRepositoryWebClient.createRepositoryInGitlab(requestMono)).thenReturn(responseMono);
        Mockito.when(gitlabCreateBranchServiceWebClient.createDevelopBranchInGitlab(repositoryId)).thenReturn(responseMono);
        Mockito.when(gitlabCreateTagServiceWebClient.createTagInGitlab(repositoryId)).thenReturn(responseMono);

        StepVerifier.create(gitlabService.createRepositoryWithDevelopBranchAndTagName(requestMono))
                .expectNextMatches(repositoryResponse -> repositoryResponse.getId().equals("55196040"))
                .verifyComplete();

        Mockito.verify(gitlabCreateRepositoryWebClient, times(1)).createRepositoryInGitlab(requestMono);
        Mockito.verify(gitlabCreateBranchServiceWebClient, times(1)).createDevelopBranchInGitlab(repositoryId);
        Mockito.verify(gitlabCreateTagServiceWebClient,times(1)).createTagInGitlab(repositoryId);

    }

}