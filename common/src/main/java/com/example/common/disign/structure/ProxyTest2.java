package com.example.common.disign.structure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class ProxyTest2 {

    public static void main(String[] args) {
        A a = new B();
        ProxyH proxyH = new ProxyH(a);
        A h = (A) Proxy.newProxyInstance(proxyH.getClass().getClassLoader(), a.getClass().getInterfaces(), proxyH);
        h.test();
    }

    interface A{// 接口
        void test();
    }

    static class B implements A { // 被代理类

        @Override
        public void test() {
            System.out.println("BBBBBBB");
        }
    }

    static class ProxyH implements InvocationHandler{// 代理类
        A a;
        private ProxyH(A a) {
            this.a = a;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object o = method.invoke(a, args);
            return o;
        }
    }
}
