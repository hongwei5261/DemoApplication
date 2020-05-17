package com.example.concurrentdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WaitNotifyTest {
    final static String lock1 = new String();
    final static ReentrantLock lock2 = new ReentrantLock();
    final static Condition condition = lock2.newCondition();

    public static void main(String[] args) {
        t1();
        t2();
    }

    private static void t1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName() + " 11");
                        System.out.println(Thread.currentThread().getName() + " 22");
                        lock1.notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName() + " 11");
                        lock1.wait();
                        System.out.println(Thread.currentThread().getName() + " 22");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "t2").start();
    }

    private static void t2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName() + " 11");
                    System.out.println(Thread.currentThread().getName() + " 22");
                    condition.signal();
                    lock2.unlock();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "---t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock2.lock();
                    System.out.println(Thread.currentThread().getName() + " 11");
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + " 22");
                    lock2.unlock();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, "---t2").start();
    }
}
