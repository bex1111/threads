package org.github.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeadLockPresenter {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public static void main(String[] args) {
        DeadLockPresenter deadLockPresenter = new DeadLockPresenter();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread1 = new Thread(deadLockPresenter::method1);
            Thread thread2 = new Thread(deadLockPresenter::method2);
            thread1.start();
            thread2.start();
            threadList.add(thread1);
            threadList.add(thread2);
            System.out.println("Thread start");
        }
        System.out.println(threadList
                .stream()
                .map(Thread::isAlive)
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }

    private void method1() {
        synchronized (lock1) {
            System.out.println("method1 lock1");
            synchronized (lock2) {
                System.out.println("method1 lock2");
            }
        }
    }

    private void method2() {
        synchronized (lock2) {
            System.out.println("method2 lock2");
            synchronized (lock1) {
                System.out.println("method2 lock1");
            }
        }
    }
}
