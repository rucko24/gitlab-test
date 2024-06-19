package com.example.testgitlab;

import com.example.testgitlab.model.TestRecordResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class WebClientSimpleTypiCodeTest {

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("getTestRecord")
    @DisplayName("A simple get to the endpoint of the free jsonplaceholder service typicode.com")
    void simpleGet(final TestRecordResponse expectedTestRecordResponse) {

        final CountDownLatch latch = new CountDownLatch(1);

        WebClient.create("https://jsonplaceholder.typicode.com")
                .get()
                .uri("/todos/2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TestRecordResponse.class)
                .log()
                .doOnTerminate(latch::countDown)
                .subscribe(actualTestRecordResponse -> {

                    assertThat(actualTestRecordResponse)
                            .isNotNull()
                            .usingRecursiveComparison()
                            .isEqualTo(expectedTestRecordResponse);

                });

        latch.await();

    }

    private static Stream<TestRecordResponse> getTestRecord() {

        final TestRecordResponse expectedTestRecordResponse = TestRecordResponse.builder()
                .id("2")
                .userId("1")
                .title("quis ut nam facilis et officia qui")
                .completed(false)
                .build();

        return Stream.of(expectedTestRecordResponse);
    }

}
