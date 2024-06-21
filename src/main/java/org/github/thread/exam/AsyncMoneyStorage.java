package org.github.thread.exam;

public class AsyncMoneyStorage {

    private int money;

    public AsyncMoneyStorage() {
        this.money = 0;
    }

    public void storeMoney(int income) {
        this.money += income;
    }

    public int getMoney() {
        return this.money;
    }
}
