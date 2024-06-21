package org.github.thread;

import java.security.SecureRandom;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class StreamThreadRider {

    private final SecureRandom secureRandom;

    public StreamThreadRider() {
        this.secureRandom = new SecureRandom();
    }

    public static void main(String[] args) {
        StreamThreadRider streamThreadRider = new StreamThreadRider();
        streamThreadRider.ride();
    }

    public void ride() {
        IntStream.range(1, 30).parallel().forEach(this::printThread);
    }

    private void printThread(int number) {
        System.out.printf("Thread start working on %s task. Thread name: %s%n", number, Thread.currentThread().getName());
        sleepThread(secureRandom.nextInt(1000));
        System.out.printf("Thread finish working on %s task. Thread name: %s%n", number, Thread.currentThread().getName());
    }

    private void sleepThread(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }
}
