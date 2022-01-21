package com.dumas.rpc.proxy.dynamic;


/**
 * @author dumas
 * @date 2022/01/21 2:52 PM
 */
public class HelloJDKDynamicProxyTest {
    public static void main(String[] args) {
        testHelloJDKDynamicProxy();
    }

    public static void testHelloJDKDynamicProxy() {
        // 被代理者
        Hello target = new HelloImpl();
        JdkDynamicProxy jdkPE = new JdkDynamicProxy(target);
        // 代理者
        Hello proxy = (Hello)jdkPE.getProxyInstance();
        /**
         * 在这一句的时候
         * 运行了invoke()函数（继承的）
         * 这里的proxy，并不是简单的Hello接口实现
         */
        proxy.sayHello("JDK动态代理");
    }
}
