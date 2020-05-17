package com.example.concurrentdemo.queue;

public class MyArrayBlockingQueueTest {
    public static void main(String[] args) {

        MyArrayBlockingQueue queue = new MyArrayBlockingQueue(5);
        queue.add("11");
        queue.add("22");
        queue.add("33");
        queue.get();
        queue.get();
        queue.add("44");
        queue.get();
        queue.get();
    }
}
