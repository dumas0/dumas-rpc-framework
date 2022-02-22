package com.dumas.rpc;

import com.dumas.rpc.annotation.RpcScan;
import com.dumas.rpc.config.RpcServiceConfig;
import com.dumas.rpc.impl.HelloServiceImpl2;
import com.dumas.rpc.remoting.transport.netty.server.NettyRpcServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author dumas
 * @date 2022/02/22 10:20 AM
 */
@RpcScan(basePackage = {"com.dumas.rpc"})
public class NettyServerMain {
    public static void main(String[] args) {
        // Register service via annotation
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
        NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");
        // Register service manually
        HelloService helloService2 = new HelloServiceImpl2();
        RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                .group("test2")
                .version("version2")
                .service(helloService2)
                .build();
        nettyRpcServer.registerService(rpcServiceConfig);
        nettyRpcServer.start();
    }
}
