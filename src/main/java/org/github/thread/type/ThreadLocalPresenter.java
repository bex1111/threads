package org.github.thread.type;

import java.util.stream.IntStream;

public class ThreadLocalPresenter {

    public static void main(String[] args) {
        ThreadLocalPresenter presenter = new ThreadLocalPresenter();
        presenter.demo();
    }

    private void demo() {
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

        IntStream.range(1, 10_000).parallel().forEach(x -> threadLocal.set(threadLocal.get() + 1));
        IntStream.range(1, 15).parallel().forEach(x ->
                System.out.printf("Thread name: %s value:%s%n",
                        Thread.currentThread().getName(),
                        threadLocal.get()));
        threadLocal.remove();
        System.out.println("-----------------");
        IntStream.range(1, 15).parallel().forEach(x ->
                System.out.printf("Thread name: %s value:%s%n",
                        Thread.currentThread().getName(),
                        threadLocal.get()));

    }
}
