package com.example.testgitlab;

import com.example.testgitlab.controller.CreateGitlabRepositoryController;
import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import com.example.testgitlab.service.client.GitlabService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

// @Disabled
//@ActiveProfiles("integration-testing")
@SpringBootTest
class GitlabBuildBaseTestClass {

    private static final String PATH_MOCK = "src/test/resources/mocksdir/";

    @LocalServerPort
    private int port;

    @Autowired
    private CreateGitlabRepositoryController createGitlabRepositoryController;

    @MockBean
    private GitlabService gitlabService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    @DisplayName("Esta funcionando pasando el controlador con el standaloneSetup")
    void setup() throws IOException {
        this.gitlabCreateRepository();

        RestAssuredWebTestClient.standaloneSetup(createGitlabRepositoryController);

    }

    void gitlabCreateRepository() throws IOException {

        final var repositoryRequest = this.objectMapper.readValue(new File(PATH_MOCK + "request-mocks/new_repository.json"),
                GitlabRepositoryRequest.class);

        GitlabRepositoryResponse gitlabRepositoryResponse = new GitlabRepositoryResponse();
        gitlabRepositoryResponse.setId("55196040");

        Mockito.when(this.gitlabService.createRepositoryWithDevelopBranchAndTagName(Mockito.any()))
                .thenReturn(Mono.just(gitlabRepositoryResponse));

    }


}
