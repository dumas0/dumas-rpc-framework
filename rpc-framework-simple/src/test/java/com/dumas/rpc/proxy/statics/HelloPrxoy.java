package com.dumas.rpc.proxy.statics;

/**
 * @author dumas
 * @date 2022/01/21 2:47 PM
 */
public class HelloPrxoy implements Hello{

    /**
     * 普通代理模式、代理者和被代理者同时继承接口
     * 同时 代理者类拥有被代理者的对象
     * 代理操作
     */
    private Hello target = null;

    // 1、建立代理对象和真实对象的关系
    public HelloPrxoy(Hello target){
        this.target = target;
    }

    /**
     * 2、实现代理逻辑
     */
    @Override
    public void sayHello(String str) {
        System.out.println("before 代理逻辑");
        target.sayHello(str);
        System.out.println("after 代理逻辑");
    }
}
