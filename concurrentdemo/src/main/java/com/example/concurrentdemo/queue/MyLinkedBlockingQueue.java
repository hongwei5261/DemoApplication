package com.example.concurrentdemo.queue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyLinkedBlockingQueue {

    private int capacity;
    private AtomicInteger count = new AtomicInteger();

    private ReentrantLock takeLock;

    private ReentrantLock putLock;

    private Condition empty;
    private Condition full;

    private Node head;
    private Node last;


    public MyLinkedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.takeLock = new ReentrantLock();
        this.putLock = new ReentrantLock();
        this.empty = takeLock.newCondition();
        this.full = putLock.newCondition();
        head = last = new Node(null);
    }

    public void add(String item) {
        int c = -1;
        try {
            Node node = new Node(item);

            putLock.lockInterruptibly();
            while (count.get() == capacity) {
//                System.out.println("add await");
                full.await();
            }
//            System.out.println("add aaaaaa");
            last.next = node;
            last = node;

            System.out.println("add " + item);
            c = count.getAndIncrement();
            if (c + 1 < capacity) {
                full.signal();
            }


        } catch (Exception e) {

        } finally {
            putLock.unlock();
        }

        if (c == 0) {
            takeLock.lock();
            empty.signal();
            takeLock.unlock();
        }
    }

    public String get() {
        String temp = null;
        int c = -1;
        try {
            takeLock.lockInterruptibly();
            while (count.get() == 0) {
//                System.out.println("get await");
                empty.await();
            }
//            System.out.println("get gggg");


            Node first = head.next;
            temp = first.item;
            head = first;
            head.item = null;

            System.out.println("get " + temp);
            c = count.getAndDecrement();
            if (c > 1) {
                empty.signal();
            }
        } catch (Exception e) {

        } finally {
            takeLock.unlock();
        }

        if (c == capacity) {
            putLock.lock();
            empty.signal();
            putLock.unlock();
        }

        return temp;
    }

    private static class Node {
        private String item;
        private Node next;

        public Node(String item) {
            this.item = item;
        }
    }
}
