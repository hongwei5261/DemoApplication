package com.example.concurrentdemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CreateThreadTest {
    public static void main(String[] args) {
        t1();
        t2();
        t3();
    }

    private static void t1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, "t1").start();
    }

    private static void t2() {
        new Thread(new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println(Thread.currentThread().getName());
            }
        }, "t2").start();
    }

    private static void t3() {
        Callable callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                return 11;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);

        new Thread(futureTask, "t3").start();
    }
}
