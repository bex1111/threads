package org.github.thread.type;

import java.util.stream.IntStream;

public class ThreadLocalPresenter {

    public static void main(String[] args) {
        ThreadLocalPresenter presenter = new ThreadLocalPresenter();
        presenter.presentThreadLocal();
    }

    private void presentThreadLocal() {
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

        IntStream.range(1, 101).parallel().forEach(x -> threadLocal.set(threadLocal.get() + 1));
        IntStream.range(1, 10).parallel().forEach(x ->
                System.out.printf("Thread name: %s value:%s%n",
                        Thread.currentThread().getName(),
                        threadLocal.get()));
        threadLocal.remove();
        IntStream.range(1, 10).parallel().forEach(x ->
                System.out.printf("Thread name: %s value:%s%n",
                        Thread.currentThread().getName(),
                        threadLocal.get()));

    }
}
