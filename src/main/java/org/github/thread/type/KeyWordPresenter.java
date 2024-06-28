package org.github.thread.type;

import java.util.stream.IntStream;

public class KeyWordPresenter {


    private volatile Integer volatileInteger = 0;
    private Integer integer = 0;
    private final Object locker = new Object();


    public static void main(String[] args) {
        KeyWordPresenter presenter = new KeyWordPresenter();
        presenter.demoWithVolatile();
        presenter.demoWithoutVolatile();
    }

    private void demoWithVolatile() {
        IntStream.range(1, 101).parallel().forEach(x -> volatileInteger++);
        System.out.printf("%s %s%n", "volatileInteger", volatileInteger);
        volatileInteger = 0;
        IntStream.range(1, 101).parallel().forEach(x -> {
            synchronized (locker) {
                volatileInteger++;
            }
        });
        System.out.printf("%s %s%n", "volatileInteger with synchronized", volatileInteger);
    }

    private void demoWithoutVolatile() {
        IntStream.range(1, 101).parallel().forEach(x -> integer++);
        System.out.printf("%s %s%n", integer.getClass().getSimpleName(), integer);
        integer = 0;
        IntStream.range(1, 101).parallel().forEach(x -> {
            synchronized (locker) {
                integer++;
            }
        });
        System.out.printf("%s %s %s%n", integer.getClass().getSimpleName(),"with synchronized", integer);
    }
}
