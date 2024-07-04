package org.github.thread.exam;

public class AsyncStoreExam {

    protected final SlowDatabase slowDatabase;

    public AsyncStoreExam(SlowDatabase slowDatabase) {
        this.slowDatabase = slowDatabase;
    }

    public void store(int value) {
        slowDatabase.storeInteger(value);
    }
}
