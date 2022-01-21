package com.dumas.rpc.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author dumas
 * @date 2022/01/21 2:57 PM
 */
public class JdkDynamicProxy implements InvocationHandler {

    // 真实对象
    private Object target = null;

    /**
     * @param target 真实对象
     */
    public JdkDynamicProxy(Object target){
        this.target = target;
    }

    /**
     * 1、建立代理对象和真实对象的代理关系
     * @return
     */
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 2、代理方法逻辑
     * @param proxy 代理对象
     * @param method 当前调度方法
     * @param args 当前方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before 代理逻辑");
        // 相当于调用target.sayHello()
        method.invoke(target, args);
        System.out.println("after 代理逻辑");
        return null;
    }
}
