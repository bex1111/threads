package org.github.thread.exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class AsyncStoreExamTest {

    private static final int LENGTH = 100;
    private AsyncStoreExam asyncStoreExam;

    @Test
    void storeLotOfItemSuccess() {
        SlowDatabase slowDatabase = new SlowDatabase();
        asyncStoreExam = new AsyncStoreExam(slowDatabase);

        assertThatProcessCanRunBelowTheTimeLimit();
        awaitAllThread(slowDatabase);
        assertThatIntegersStoredProperly(slowDatabase);
    }

    private void assertThatIntegersStoredProperly(SlowDatabase slowDatabase) {
        Assertions.assertEquals(IntStream.range(0, LENGTH).sum(),
                slowDatabase
                        .getIntegers()
                        .stream()
                        .reduce(0, Integer::sum));
    }

    private void awaitAllThread(SlowDatabase slowDatabase) {
        await().pollDelay(1, SECONDS)
                .atMost(5, SECONDS)
                .until(() -> slowDatabase.getIntegers().size() == LENGTH);
    }

    private void assertThatProcessCanRunBelowTheTimeLimit() {
        Assertions.assertTimeout(Duration.ofSeconds(5L), () -> {
            for (int i = 0; i < LENGTH; i++) {
                asyncStoreExam.store(i);
            }
        });
    }
}