package com.dumas.rpc.proxy.statics;

/**
 * @author dumas
 * @date 2022/01/21 2:52 PM
 */
public class HelloStaticsProxyTest {
    public static void main(String[] args) {
        testHelloStaticsProxy();
    }

    public static void testHelloStaticsProxy() {
        HelloImpl target = new HelloImpl();
        Hello proxy = new HelloPrxoy(target);
        proxy.sayHello("普通静态代理模式");
    }
}
