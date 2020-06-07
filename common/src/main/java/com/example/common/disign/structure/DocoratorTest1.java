package com.example.common.disign.structure;

public class DocoratorTest1 {

    public static void main(String[] args) {
        Base a = new A();

        Base c = new C(new B(a));
        c.move();
    }

    /**
     * 基础接口
     */
    public interface Base{
        void move();
    }

    /**
     * 具体实现
     */
    public static class A implements  Base{

        @Override
        public void move() {
            System.out.println("AAAAA move");
        }
    }

    /**
     * 装饰基础类
     */
    public static class Base2 implements Base {
        Base base;
        public Base2(Base base) {
            this.base = base;
        }

        @Override
        public void move() {
            base.move();
        }
    }

    /**
     * 装饰B类
     */
    public static class B extends Base2{

        public B(Base base) {
            super(base);
        }

        @Override
        public void move() {
            System.out.println("BBBBBBB move");
        }
    }

    /**
     * 装饰C类
     */
    public static class C extends Base2 {

        public C(Base base) {
            super(base);
        }

        @Override
        public void move() {
            System.out.println("CCCCCCCC move");
        }
    }
}
