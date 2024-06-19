package com.example.testgitlab.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record TestRecordResponse (

        @Schema(name = "userId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String userId,

        @Schema(name = "id", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String id,

        @Schema(name = "title", example = "quis ut nam facilis et officia qui", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String title,

        @Schema(name = "completed", example = "false|true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean completed) {

}
