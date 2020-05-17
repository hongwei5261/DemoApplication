package com.example.concurrentdemo;

import java.util.concurrent.atomic.AtomicInteger;

public class ATest {
    static AtomicInteger atomicInteger = new AtomicInteger(3);
    public static void main(String[] args) {
        int a = atomicInteger.getAndIncrement();
        System.out.println(a);
        System.out.println(atomicInteger.get());

    }
}
