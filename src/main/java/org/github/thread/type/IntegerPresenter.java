package org.github.thread.type;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class IntegerPresenter {

   private Integer boxedInteger = 0;
   private int primitiveInteger = 0;
   private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        IntegerPresenter presenter = new IntegerPresenter();
        presenter.demo();
    }

    private void demo() {
        IntStream.range(1, 101).parallel().forEach(x -> boxedInteger++);
        IntStream.range(1, 101).parallel().forEach(x -> primitiveInteger++);
        IntStream.range(1, 101).parallel().forEach(x -> atomicInteger.incrementAndGet());

        System.out.printf("%s %s%n", atomicInteger.getClass().getSimpleName(), atomicInteger.get());
        System.out.printf("%s %s%n", boxedInteger.getClass().getSimpleName(), boxedInteger);
        System.out.printf("%s %s%n", "primitiveInteger", primitiveInteger);
    }
}
