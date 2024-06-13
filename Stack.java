package com.example.proje;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Stack<T> {
    private LinkedList<T> stack = new LinkedList<>();


    public List<T> toList() {
        return new ArrayList<>(stack);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(T element) {
        stack.addFirst(element);
    }

    public T pop() {
        return stack.removeFirst();
    }

    public T peek() {
        return stack.getFirst();
    }
}
