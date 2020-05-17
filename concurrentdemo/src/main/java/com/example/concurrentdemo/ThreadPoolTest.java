package com.example.concurrentdemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadPoolTest {
    public static void main(String[] args) {
        t1();
    }

    private static void t1() {
        ExecutorService e1 = Executors.newFixedThreadPool(2);
        e1.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        ExecutorService e2 = Executors.newCachedThreadPool();
        ExecutorService e3 = Executors.newScheduledThreadPool(2);
        ExecutorService e4 = Executors.newSingleThreadExecutor();
    }
}
