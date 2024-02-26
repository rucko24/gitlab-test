package com.example.testgitlab;

import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import com.example.testgitlab.service.client.GitlabCreateRepositoryWebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.File;
import java.io.IOException;
import java.util.List;

//@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureStubRunner(
       ids = StubRunnerGitlabCreateRepositoryClientTest.PRODUCER_ARTIFACT_ID, stubsMode = StubsMode.LOCAL)
class StubRunnerGitlabCreateRepositoryClientTest {

    public static final String PRODUCER_ARTIFACT_ID = "com.example.testgitlab:image-1:+:stubs:";

    private static final String PATH_MOCK = "src/test/resources/mocksdir/";

    @StubRunnerPort(StubRunnerGitlabCreateRepositoryClientTest.PRODUCER_ARTIFACT_ID)
    private int port;

    @Autowired
    private GitlabCreateRepositoryWebClient createRepositoryWebClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Testing stubrunner ")
    void test() throws IOException {

        final var repositoryRequest = this.objectMapper.readValue(new File(PATH_MOCK + "new_repository.json"), GitlabRepositoryRequest.class);

        GitlabRepositoryResponse gitlabRepositoryResponse = new GitlabRepositoryResponse();
        gitlabRepositoryResponse.setId("55196040");

        StepVerifier.create(this.createRepositoryWebClient.createRepositoryInGitlab(Mono.just(repositoryRequest)))
                .expectNextCount(1)
                .verifyComplete();

    }

}
