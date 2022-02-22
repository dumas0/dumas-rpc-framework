package com.dumas.rpc;

import com.dumas.rpc.annotation.RpcScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author dumas
 * @date 2022/02/22 10:16 AM
 */
@RpcScan(basePackage = {"com.dumas.rpc"})
public class NettyClientMain {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyClientMain.class);
        HelloController helloController = (HelloController) applicationContext.getBean("helloController");
        helloController.test();
    }
}
