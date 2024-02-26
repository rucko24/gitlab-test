package com.example.testgitlab.gitlabexceptions;

import com.example.testgitlab.model.AdditionalData;
import com.example.testgitlab.model.CreateGitlabRepository400Response;
import com.example.testgitlab.model.CreateGitlabRepository500Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Slf4j
@RestControllerAdvice
public class GitlabRepositoryControllerAdvice {


    @ExceptionHandler(GitlabRepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CreateGitlabRepository400Response> handleOnError(final Exception exception) {
        log.info(exception.getMessage());
        final CreateGitlabRepository400Response errorResponse = new CreateGitlabRepository400Response();
        errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTitle("Bad request");
        errorResponse.setDetail(exception.getMessage());
        errorResponse.setAdditionalData(this.buildAdditionalData());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(GitlabRepositoryTechnicalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CreateGitlabRepository500Response> handle500OnError(final Exception exception) {
        log.info(exception.getMessage());
        final CreateGitlabRepository500Response errorResponse = new CreateGitlabRepository500Response();
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setTitle("Internal Server Error");
        errorResponse.setDetail(exception.getMessage());
        errorResponse.setAdditionalData(this.buildAdditionalData());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private AdditionalData buildAdditionalData() {
        return new AdditionalData().timestamp(getTimestamp());
    }

    private String getTimestamp() {
        final var clock = Clock.systemDefaultZone();
        return ZonedDateTime.ofInstant(Instant.now()
                        .truncatedTo(ChronoUnit.MILLIS), clock.getZone())
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

}
