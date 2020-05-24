package com.example.common.disign.create;

public class SingleTonTest {

    /**
     * 懒汉式
     */
    private static class SingleTon1 {
        private static SingleTon1 instance;

        private SingleTon1() {
        }

        public static synchronized SingleTon1 getInstance() {
            if (instance == null) {
                instance = new SingleTon1();
            }
            return instance;
        }
    }

    /**
     * 饿汉式
     */
    private static class SingleTon2 {
        private static final SingleTon2 instance = new SingleTon2();

        private SingleTon2() {
        }

        public static SingleTon2 getInstance() {
            return instance;
        }
    }

    /**
     * 静态内部类式
     */
    private static class SingleTon3 {
        private SingleTon3() {
        }

        public static SingleTon3 getInstance() {
            return Holder.INSTANCE;
        }

        private static class Holder {
            private static final SingleTon3 INSTANCE = new SingleTon3();
        }
    }

    /**
     * 双重锁校验
     */
    private static class SingleTon4 {
        private volatile static SingleTon4 instance;

        private SingleTon4() {
        }

        public static SingleTon4 getInstance() {
            if (instance == null) {
                synchronized (SingleTon4.class) {
                    if (instance == null) {
                        instance = new SingleTon4();
                    }
                }
            }
            return instance;
        }
    }

}
