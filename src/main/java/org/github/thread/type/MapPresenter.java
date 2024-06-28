package org.github.thread.type;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class MapPresenter {


    public static void main(String[] args) {
        MapPresenter presenter = new MapPresenter();
        presenter.demo(new HashMap<>());
        presenter.demo(new ConcurrentHashMap<>());
    }

    private void demo(Map<Integer, Integer> map) {
        map.put(0, 0);
        IntStream.range(1, 200)
                .parallel()
                .forEach(x -> map.computeIfPresent(0, (key, value) -> value + 1));
        System.out.printf("Map type: %s Value sum: %s Elements: %s%n",
                map.getClass().getSimpleName(),
                map.values().stream().reduce(0, Integer::sum),
                map);
    }
}
