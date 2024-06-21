package org.github.thread.type;

import java.util.stream.IntStream;

public class KeyWordPresenter {


    volatile Integer volatileInteger = 0;
     Integer integer = 0;
    final Object locker = new Object();


    public static void main(String[] args) {
        KeyWordPresenter presenter = new KeyWordPresenter();
        presenter.presentWithVolatile();
        presenter.presentWithoutVolatile();
    }

    private void presentWithVolatile() {
        IntStream.range(1, 101).parallel().forEach(x -> volatileInteger++);
        System.out.printf("%s %s%n", "volatileInteger", volatileInteger);
        volatileInteger = 0;
        IntStream.range(1, 101).parallel().forEach(x -> {
            synchronized (locker) {
                volatileInteger++;
            }
        });
        System.out.printf("%s %s%n",  "volatileInteger", volatileInteger);
    }

    private void presentWithoutVolatile() {
        IntStream.range(1, 101).parallel().forEach(x -> integer++);
        System.out.printf("%s %s%n", integer.getClass().getSimpleName(), integer);
        integer = 0;
        IntStream.range(1, 101).parallel().forEach(x -> {
            synchronized (locker) {
                integer++;
            }
        });
        System.out.printf("%s %s%n", integer.getClass().getSimpleName(), integer);
    }
}
