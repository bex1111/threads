package org.github.thread.exam;

public class AsyncMoneyStorageExam {

    private int money;

    public AsyncMoneyStorageExam() {
        this.money = 0;
    }

    public void storeMoney(int income) {
        this.money += income;
    }

    public int getMoney() {
        return this.money;
    }
}
