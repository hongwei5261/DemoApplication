package com.example.concurrentdemo;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    static ReentrantLock lock = new ReentrantLock();
    static Object object = new Object();

    public static void main(String[] args) {
//        lockTest();
        synTest();
    }

    private static void lockTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t1 run");
                    lock.lock();

                    Thread.sleep(10 * 1000);

                } catch (Exception e) {
                } finally {
                    System.out.println("t1 unlock");
                    lock.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                    System.out.println("t2 run");
                    lock.lock();
                    System.out.println("t2 start");

                } catch (Exception e) {
                } finally {
                    lock.unlock();
                }

            }
        }).start();
    }

    private static void synTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
               synchronized (object) {
                   System.out.println("sss");
                   t();
               }
            }
        }).start();
    }

    private static void t() {
        synchronized (object) {
            System.out.println("ttt");
        }
    }
}
