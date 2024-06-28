package org.github.thread.exam;

import java.util.concurrent.atomic.AtomicInteger;

public class AsyncMoneyStorageSolution extends AsyncMoneyStorageExam {

    private final AtomicInteger money;

    public AsyncMoneyStorageSolution() {
        this.money = new AtomicInteger(0);
    }

    @Override
    public void storeMoney(int income) {
        this.money.accumulateAndGet(income, Integer::sum);
    }

    @Override
    public int getMoney() {
        return this.money.get();
    }
}
