package org.github.thread.type;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class ListPresenter {

    public static void main(String[] args) {
        ListPresenter presenter = new ListPresenter();
        presenter.demo(new ArrayList<>());
        presenter.demo(new CopyOnWriteArrayList<>());

    }

    private void demo(List<Integer> list) {
        IntStream.range(1, 101).parallel().forEach(list::add);
        System.out.printf("List type: %s Lenght: %s Elements: %s%n",
                list.getClass().getSimpleName(),
                list.size(),
                list);
    }
}
