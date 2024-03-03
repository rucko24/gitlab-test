package com.example.testgitlab;

import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import com.example.testgitlab.service.client.GitlabCreateBranchServiceWebClient;
import com.example.testgitlab.service.client.GitlabCreateRepositoryWebClient;
import com.example.testgitlab.service.client.GitlabCreateTagServiceWebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@ActiveProfiles("integration-testing")
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureStubRunner(ids = StubRunnerGitlabCreateRepositoryClientTest.PRODUCER_ARTIFACT_ID,
        stubsMode = StubsMode.LOCAL)
@AutoConfigureWireMock(port = 9092)
class StubRunnerGitlabCreateRepositoryClientTest {

    public static final String PRODUCER_ARTIFACT_ID = "com.example.testgitlab:image-1:+:stubs:";

    @StubRunnerPort(StubRunnerGitlabCreateRepositoryClientTest.PRODUCER_ARTIFACT_ID)
    private int port;

    @Autowired
    private GitlabCreateRepositoryWebClient gitlabCreateRepositoryWebClient;

    @Autowired
    private GitlabCreateBranchServiceWebClient gitlabCreateBranchServiceWebClient;

    @Autowired
    private GitlabCreateTagServiceWebClient gitlabCreateTagServiceWebClient;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @ArgumentsSource(GitlabRepositoryArgumentsProvider.class)
    @DisplayName("create a basic project should return an id 55196040")
    void createRepository(GitlabRepositoryRequest request, GitlabRepositoryResponse response,
                          String repositoryId) throws IOException {

        WireMock.stubFor(WireMock
                .post(WireMock.urlEqualTo("/api/v4/projects"))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(response))));

        StepVerifier.create(this.gitlabCreateRepositoryWebClient.createRepositoryInGitlab(Mono.just(request)))
                .expectNextMatches(repositoryResponse -> repositoryResponse.getId().equals(repositoryId))
                .verifyComplete();

    }

    @ParameterizedTest
    @ArgumentsSource(GitlabRepositoryArgumentsProvider.class)
    @DisplayName("Cuando se envia una request con null id")
    void createRepositoryWithNullId(GitlabRepositoryRequest request, GitlabRepositoryResponse response,
                                    String repositoryId) throws IOException {

        WireMock.stubFor(WireMock
                .post(WireMock.urlEqualTo("/api/v4/projects"))
                .willReturn(aResponse().withStatus(404)
                        .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(null))));

        StepVerifier.create(this.gitlabCreateRepositoryWebClient.createRepositoryInGitlab(Mono.just(request)))
                .expectErrorMatches(error -> error.getMessage().contains("404 Not Found from POST http://localhost:9092/api/v4/projects"))
                .verify();

    }

    @ParameterizedTest
    @ArgumentsSource(GitlabRepositoryArgumentsProvider.class)
    @DisplayName("Create a develop branch and return an id 55196040")
    void createDeveloBranch(GitlabRepositoryRequest request, GitlabRepositoryResponse response,
                            String repositoryId) throws IOException {

        final String urlBranchGitlab = "/api/v4/projects/" + repositoryId + "/repository/branches?branch=develop&ref=master";

        WireMock.stubFor(WireMock
                .post(WireMock.urlEqualTo(urlBranchGitlab))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(response))));

        StepVerifier.create(this.gitlabCreateBranchServiceWebClient.createDevelopBranchInGitlab(repositoryId))
                .expectNextMatches(repositoryResponse -> repositoryResponse.getId().equals(repositoryId))
                .verifyComplete();

    }

    @ParameterizedTest
    @ArgumentsSource(GitlabRepositoryArgumentsProvider.class)
    @DisplayName("When sending the id with null it should return 404 not found")
    void createDeveloBranchWithNullId(GitlabRepositoryRequest request, GitlabRepositoryResponse response,
                                      String repositoryId) throws IOException {

        final String urlBranchGitlab = "/api/v4/projects/" + repositoryId + "/repository/branches?branch=develop&ref=master";

        WireMock.stubFor(WireMock
                .post(WireMock.urlEqualTo(urlBranchGitlab))
                .willReturn(aResponse().withStatus(404)
                        .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(null))));

        StepVerifier.create(this.gitlabCreateBranchServiceWebClient.createDevelopBranchInGitlab(null))
                .expectErrorMatches(error -> error.getMessage().contains("404 Not Found from POST"))
                .verify();

    }

    @ParameterizedTest
    @ArgumentsSource(GitlabRepositoryArgumentsProvider.class)
    @DisplayName("create a basic tag 0.0.1 an return an id 55196040")
    void createATag(GitlabRepositoryRequest request, GitlabRepositoryResponse response,
                    String repositoryId) throws IOException {

        String urlTagGitlab = "/api/v4/projects/" + repositoryId + "/repository/tags?tag_name=0.0.1&ref=master";

        WireMock.stubFor(WireMock
                .post(WireMock.urlEqualTo(urlTagGitlab))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(response))));


        StepVerifier.create(this.gitlabCreateTagServiceWebClient.createTagInGitlab(repositoryId))
                .expectNextMatches(repositoryResponse -> repositoryResponse.getId().equals(repositoryId))
                .verifyComplete();
    }


}
