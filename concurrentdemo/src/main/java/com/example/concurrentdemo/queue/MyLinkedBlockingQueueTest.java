package com.example.concurrentdemo.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyLinkedBlockingQueueTest {

    private static MyLinkedBlockingQueue myLinkedBlockingQueue;

    public static void main(String[] args) {

        myLinkedBlockingQueue = new MyLinkedBlockingQueue(4);


        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {
                    myLinkedBlockingQueue.get();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    myLinkedBlockingQueue.add((int) (i + 1) + "");
                }
            }
        }).start();
    }
}
