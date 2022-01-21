package com.dumas.rpc.proxy.statics;

/**
 * @author dumas
 * @date 2022/01/21 2:46 PM
 */
public class HelloImpl implements Hello{
    @Override
    public void sayHello(String str) {
        System.out.println("hello " + str);
    }
}
