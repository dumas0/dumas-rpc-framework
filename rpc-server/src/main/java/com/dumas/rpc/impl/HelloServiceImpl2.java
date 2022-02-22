package com.dumas.rpc.impl;

import com.dumas.rpc.Hello;
import com.dumas.rpc.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dumas
 * @date 2022/02/22 10:22 AM
 */
@Slf4j
public class HelloServiceImpl2 implements HelloService {
    static {
        System.out.println("HelloServiceImpl2 has been created.");
    }


    @Override
    public String hello(Hello hello) {
        log.info("HelloServiceImpl2 received: {}", hello.getMessage());
        String result = "Hello description is " + hello.getDescription();
        log.info("HelloServiceImpl2 send back: {}", result);
        return result;
    }
}
