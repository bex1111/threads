package org.github.thread.exam;

import java.util.concurrent.CompletableFuture;

public class AsyncStoreSolution extends AsyncStoreExam {

    public AsyncStoreSolution(SlowDatabase slowDatabase) {
        super(slowDatabase);
    }

    public void store(int value) {
        CompletableFuture.runAsync(() -> {
            slowDatabase.storeInteger(value);
        });
    }
}
