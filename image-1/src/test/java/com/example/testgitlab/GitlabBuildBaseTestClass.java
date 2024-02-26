package com.example.testgitlab;

import com.example.testgitlab.controller.CreateGitlabRepositoryController;
import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import com.example.testgitlab.service.client.GitlabService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
//@ActiveProfiles("local")
class GitlabBuildBaseTestClass {

    private static final String PATH_MOCK = "src/test/resources/mocksdir/";

    @Autowired
    private CreateGitlabRepositoryController controller;

    @MockBean
    private GitlabService gitlabService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    @DisplayName("Esta funcionando pasando el controlador con el standaloneSetup")
    void setup() throws IOException {
        RestAssuredWebTestClient.standaloneSetup(controller);

        this.gitlabCreateRepository();
    }

    void gitlabCreateRepository() throws IOException {

        final var repositoryRequest = this.objectMapper.readValue(new File(PATH_MOCK + "new_repository.json"),
                GitlabRepositoryRequest.class);

        GitlabRepositoryResponse gitlabRepositoryResponse = new GitlabRepositoryResponse();
        gitlabRepositoryResponse.setId("55196040");

        Mockito.when(this.gitlabService.createRepositoryWithDevelopBranch(Mono.just(repositoryRequest)))
                .thenReturn(Mono.just(gitlabRepositoryResponse));

    }


}
