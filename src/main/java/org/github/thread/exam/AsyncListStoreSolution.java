package org.github.thread.exam;

import java.util.List;

public class AsyncListStoreSolution extends AsyncListStoreExam {
    
    public AsyncListStoreSolution(SlowDatabase slowDatabase) {
     super(slowDatabase);
    }

    public void storeList(List<Integer> value) {
        value.stream().parallel().forEach(slowDatabase::storeInteger);
    }

}
