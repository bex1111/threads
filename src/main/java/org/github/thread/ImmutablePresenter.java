package org.github.thread;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ImmutablePresenter {


    private LocalDate localDate = LocalDate.now();
    private ImmutableCounter immutableCounter = new ImmutableCounter(0);
    private final MutableCounter mutableCounter = new MutableCounter(0);

    public static void main(String[] args) {
        ImmutablePresenter presenter = new ImmutablePresenter();
        presenter.demo(presenter::dateTimeDemo);
        System.out.println("=====================================");
        presenter.demo(presenter::dateImmutableCounterDemo);
        System.out.println("=====================================");
        presenter.demo(presenter::dateMutableCounterDemo);
    }

    private LocalDate dateTimeDemo(){
        this.localDate = localDate.plusDays(1);
        return this.localDate;
    }

    private ImmutableCounter dateImmutableCounterDemo(){
        this.immutableCounter = immutableCounter.increment();
        return this.immutableCounter;
    }

    private MutableCounter dateMutableCounterDemo(){
        this.mutableCounter.increment();
        return this.mutableCounter;
    }


    private void demo(Supplier<Object> demo) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        IntStream.range(1, 10_000).parallel().forEach(x -> map.put(Thread.currentThread().getName(), demo.get()));
        printResult(map);
    }

    private static void printResult(ConcurrentHashMap<String, Object> map) {
        System.out.println(map
                .entrySet()
                .stream()
                .map(entry -> "Thread: %s Hash: %s ToString: %s"
                        .formatted(entry.getKey(), entry.getValue().hashCode(), entry.getValue().toString()))
                .collect(Collectors.joining("\n")));
    }
}

class ImmutableCounter {
    private final int value;

    ImmutableCounter(int value) {
        this.value = value;
    }

    ImmutableCounter increment() {
        int incrementedValue = this.value + 1;
        return new ImmutableCounter(incrementedValue);
    }

    @Override
    public String toString() {
        return "Counter{" +
                "value=" + value +
                '}';
    }
}

class MutableCounter {
    private int value;

    MutableCounter(int value) {
        this.value = value;
    }

    void increment() {
        this.value++;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "value=" + value +
                '}';
    }
}
