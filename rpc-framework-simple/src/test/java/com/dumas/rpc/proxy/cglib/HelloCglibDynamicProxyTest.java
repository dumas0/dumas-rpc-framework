package com.dumas.rpc.proxy.cglib;


/**
 * @author dumas
 * @date 2022/01/21 2:52 PM
 */
public class HelloCglibDynamicProxyTest {
    public static void main(String[] args) {
        testHelloCglibDynamicProxy();
    }

    public static void testHelloCglibDynamicProxy() {
        // 被代理者
        HelloCglib target = new HelloCglib();
        CglibProxyFactory cglibPE = new CglibProxyFactory(target);
        // 被代理者
        HelloCglib proxy = (HelloCglib) cglibPE.getProxyInstance();
        /**
         * 在这一句的时候
         * 运行了intercept()函数（继承的）
         * 这里的proxy并不是简单的HelloCglib对象
         */
        proxy.sayHello("CGLIB动态代理");
    }
}
