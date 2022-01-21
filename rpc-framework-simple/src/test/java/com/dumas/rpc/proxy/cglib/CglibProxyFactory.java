package com.dumas.rpc.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author dumas
 * @date 2022/01/21 3:07 PM
 */
public class CglibProxyFactory implements MethodInterceptor {

    // 维护目标对象
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象创建一个代理对象
    public Object getProxyInstance(){
        // 1、工具类
        Enhancer enhancer = new Enhancer();
        // 2、设置父类
        enhancer.setSuperclass(target.getClass());
        // 3、设置回调函数
        enhancer.setCallback(this);
        // 4、创建子类（代理对象）
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始...");
        // 执行目标对象的方法
        Object returnValue = method.invoke(target, objects);
        System.out.println("提交...");
        return returnValue;
    }
}
