package com.example.testgitlab.service.client;

import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Disabled
//@WebFluxTest(controllers = CreateGitlabRepositoryController.class)
class GitlabCreateRepositoryWebClientTest {

//    @Autowired
    private WebTestClient webTestClient;

//    @MockBean
    private GitlabService gitlabService;

    @Test
    @Disabled
    @DisplayName("Obtener un 200 cuando se llama al endpoint de gitlab, y se deberia retornar un id")
    void setup() {

        final var request = new GitlabRepositoryRequest();
        request.setName("test");
        request.setDescription("Example of project creation using gitlab api with java.");
        request.setPath("basic_project");
        request.setInitializeWithReadme("true");
        request.setTopics(List.of("0.0.1"));
        request.setVisibility("public");
        request.setDefaultBranch("master");

        final var response = new GitlabRepositoryResponse();
        response.setId("55196040");

        Mockito.when(gitlabService.createRepositoryWithDevelopBranch(Mockito.any()))
                .thenReturn(Mono.just(response));

        this.webTestClient.post()
                .uri("/api/v1/gitlab-repository")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), GitlabRepositoryRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("55196040");
    }

}