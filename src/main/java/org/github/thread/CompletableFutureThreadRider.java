package org.github.thread;

import java.security.SecureRandom;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class CompletableFutureThreadRider {

    private final SecureRandom secureRandom;

    public CompletableFutureThreadRider() {
        this.secureRandom = new SecureRandom();
    }

    public static void main(String[] args) {
        CompletableFutureThreadRider streamThreadRider = new CompletableFutureThreadRider();
        streamThreadRider.ride();
    }

    public void ride() {
        runThread(CompletableFuture.allOf(IntStream.range(1, 30)
                .mapToObj(this::printThread)
                .toArray(CompletableFuture<?>[]::new)));

    }

    private void runThread(CompletableFuture<Void> x) {
        try {
            x.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    private CompletableFuture<Void> printThread(int number) {
        return CompletableFuture.runAsync(() -> {
            System.out.printf("Thread start working on %s task. Thread name: %s%n", number, Thread.currentThread().getName());
            sleepThread(secureRandom.nextInt(1000));
            System.out.printf("Thread finish working on %s task. Thread name: %s%n", number, Thread.currentThread().getName());
        });
    }

    private void sleepThread(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }
}