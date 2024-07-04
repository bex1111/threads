package org.github.thread.exam;

import java.util.concurrent.CopyOnWriteArrayList;

public class SlowDatabase {

    private  final CopyOnWriteArrayList<Integer> integers;

    public CopyOnWriteArrayList<Integer> getIntegers() {
        return integers;
    }

    public SlowDatabase() {
        integers = new CopyOnWriteArrayList<>();
    }

    public void storeInteger(int value) {
        try {
            Thread.sleep(100);
            integers.add(value);
        } catch (InterruptedException e) {
         throw new RuntimeException(e);
        }
    }
}
