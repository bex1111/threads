package org.github.thread.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AsyncMoneyStorageTest {

    private AsyncMoneyStorage asyncMoneyStorage;

    @BeforeEach
    void setUp() {
        asyncMoneyStorage = new AsyncMoneyStorageSolution();
    }

    @Test
    void storeMoney() {
        asyncMoneyStorage.storeMoney(100);
        assertEquals(100, asyncMoneyStorage.getMoney());
    }

    @Test
    void getMoney() {
        assertEquals(0, asyncMoneyStorage.getMoney());
    }

    @Test
    void storeMoneyAsync() {
        IntStream.range(1, 500).parallel().forEach(asyncMoneyStorage::storeMoney);
        assertEquals(IntStream.range(1, 500).sum(), asyncMoneyStorage.getMoney());
    }

}