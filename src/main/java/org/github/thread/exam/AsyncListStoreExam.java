package org.github.thread.exam;

import java.util.List;

public class AsyncListStoreExam {

    protected final SlowDatabase slowDatabase;

    public AsyncListStoreExam(SlowDatabase slowDatabase) {
        this.slowDatabase = slowDatabase;
    }

    public void storeList(List<Integer> value) {
        value.forEach(slowDatabase::storeInteger);
    }
}
