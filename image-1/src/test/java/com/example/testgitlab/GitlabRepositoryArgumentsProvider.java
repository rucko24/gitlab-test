package com.example.testgitlab;

import com.example.testgitlab.model.GitlabRepositoryRequest;
import com.example.testgitlab.model.GitlabRepositoryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.nio.file.Path;
import java.util.stream.Stream;

public class GitlabRepositoryArgumentsProvider implements ArgumentsProvider {

    private static final Path PATH_MOCK_REQUEST  = Path.of("src/test/resources/mocksdir/request-mocks/new_repository.json");
    private static final Path PATH_MOCK_RESPONSE = Path.of("src/test/resources/mocksdir/response-mocks/repository-created.json");

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        final var request = objectMapper.readValue(PATH_MOCK_REQUEST.toFile(), GitlabRepositoryRequest.class);
        
        final var response = this.objectMapper.readValue(PATH_MOCK_RESPONSE.toFile(), GitlabRepositoryResponse.class);

        final String repositoryId = "55196040";

        return Stream.of(
                Arguments.of(request, response , repositoryId)
        );
    }
}
