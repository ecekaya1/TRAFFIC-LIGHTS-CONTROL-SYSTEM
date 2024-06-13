package com.example.proje;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Kuyruk<T> {
    private LinkedList<T> queue = new LinkedList<>();


    public List<T> toList() {
        return new ArrayList<>(queue);
    }


    public void add(T element) {
        queue.addLast(element);
    }

}
