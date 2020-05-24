package com.example.common.disign.structure;

public class AdapterTest {



    /**
     * 目标接口
     */
    interface Target {
        void sampleOperation1();

        void sampleOperation2();
    }

    static class Adaptee {
        public void sampleOperation1() {

        }
    }

    static class Adapter {
        private Adaptee adaptee;

        public Adapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        void sampleOperation1() {
            adaptee.sampleOperation1();
        }

        void sampleOperation2() {
            // 另外实现，以适配Target
        }

    }
}
