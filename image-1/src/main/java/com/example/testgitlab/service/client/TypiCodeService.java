package com.example.testgitlab.service.client;

import com.example.testgitlab.model.TestRecordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TypiCodeService {

    private final TypiCodeServiceWebClient createCallTypiCode;

    public Mono<TestRecordResponse> createCallTypiCode() {
        return createCallTypiCode.createCallTypiCode()
                .doOnSuccess(success -> log.info("Success {}", success));
    }

}
