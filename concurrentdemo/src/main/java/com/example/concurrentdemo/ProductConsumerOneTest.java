package com.example.concurrentdemo;

import android.text.TextUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductConsumerOneTest {
    static String value = null;
    static String lock = new String("");

    public static void main(String[] args) {
        t1();
    }

    private static void t1() {
        Product product = new Product(lock);
        Consumer consumer = new Consumer(lock);
        new ProductThread(product).start();
        new ConsumerThread(consumer).start();

    }

    private static class ProductThread extends Thread {
        private Product product;

        public ProductThread(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            while (true) {
                product.setValue();
            }
        }
    }

    private static class ConsumerThread extends Thread {
        private Consumer consumer;

        public ConsumerThread(Consumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                consumer.getValue();
            }
        }
    }


    private static class Product {
        private String lock;

        public Product(String lock) {
            this.lock = lock;
        }

        public void setValue() {
            try {
                synchronized (lock) {
                    if (value != null) {
                        System.out.println("P w");
                        lock.wait();
                    }

                    value = "" + System.currentTimeMillis() + "  " + System.nanoTime();
                    System.out.println("set " + value);
                    lock.notify();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class Consumer {
        private String lock;

        public Consumer(String lock) {
            this.lock = lock;
        }

        public void getValue() {
            try {
                synchronized (lock) {
                    if (value == null) {
                        System.out.println("C w");
                        lock.wait();
                    }
                    System.out.println("get " + value);
                    value = null;
                    lock.notify();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
