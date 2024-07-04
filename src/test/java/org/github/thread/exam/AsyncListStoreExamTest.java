package org.github.thread.exam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class AsyncListStoreExamTest {


    private static final int LENGTH = 10;
    private AsyncListStoreExam asyncListStoreExam;

    @Test
    void storeLotOfItemSuccess() {
        SlowDatabase slowDatabase = new SlowDatabase();
        asyncListStoreExam = new AsyncListStoreExam(slowDatabase);

        assertThatProcessCanRunBelowTheTimeLimit();
        awaitAllThread(slowDatabase);
        assertThatIntegersStoredProperly(slowDatabase);
    }

    private void assertThatIntegersStoredProperly(SlowDatabase slowDatabase) {
        Assertions.assertEquals(IntStream.range(0, LENGTH).sum() * LENGTH,
                slowDatabase
                        .getIntegers()
                        .stream()
                        .reduce(0, Integer::sum));
    }

    private void awaitAllThread(SlowDatabase slowDatabase) {
        await().pollDelay(1, SECONDS)
                .atMost(5, SECONDS)
                .until(() -> slowDatabase.getIntegers().size() == LENGTH * LENGTH);
    }

    private void assertThatProcessCanRunBelowTheTimeLimit() {
        Assertions.assertTimeout(Duration.ofSeconds(5L), () -> {
            for (int i = 0; i < LENGTH; i++) {
                asyncListStoreExam.storeList(IntStream.range(0, LENGTH).boxed().toList());
            }
        });
    }
}