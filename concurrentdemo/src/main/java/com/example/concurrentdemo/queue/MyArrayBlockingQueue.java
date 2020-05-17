package com.example.concurrentdemo.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockingQueue {

    private String[] items;

    private int takeIndex;

    private int putIndex;

    private int count;

    private ReentrantLock lock;

    private Condition notEmpty;

    private Condition notFull;

    public MyArrayBlockingQueue(int capacity) {
        this.items = new String[capacity];
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public void add(String item) {
        try {
            lock.lockInterruptibly();

            while (count == items.length) {
                notEmpty.await();
            }
            items[putIndex] = item;
            inc();
            count++;
            notFull.signal();
            System.out.println("add " + item);
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        try {
            lock.lockInterruptibly();

            while (count == 0) {
                notFull.await();
            }
            String temp = items[takeIndex];
            items[takeIndex] = null;
            dec();
            count--;
            notEmpty.signal();
            System.out.println("get " + temp);
            return temp;


        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return null;
    }

    private void inc() {
        putIndex = putIndex + 1;
        if (putIndex >= items.length) {
            putIndex = 0;
        }
    }

    private void dec() {
        takeIndex = takeIndex + 1;
        if (takeIndex >= items.length) {
            takeIndex = 0;
        }
    }
}
