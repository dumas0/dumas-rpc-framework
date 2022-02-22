package com.dumas.rpc.impl;

import com.dumas.rpc.Hello;
import com.dumas.rpc.HelloService;
import com.dumas.rpc.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dumas
 * @date 2022/02/21 3:29 PM
 */
@Slf4j
@RpcService(group = "test1", version = "version1")
public class HelloServiceImpl implements HelloService {

    static {
        System.out.println("HelloServiceImpl has been created.");
    }

    @Override
    public String hello(Hello hello) {
        log.info("HelloServiceImpl received:{}.", hello.getMessage());
        String result = "Hello description is " + hello.getDescription();
        log.info("HelloServiceImpl back:{}", result);
        return null;
    }
}
