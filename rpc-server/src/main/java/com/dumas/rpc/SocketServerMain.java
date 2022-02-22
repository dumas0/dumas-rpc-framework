package com.dumas.rpc;

import com.dumas.rpc.config.RpcServiceConfig;
import com.dumas.rpc.impl.HelloServiceImpl;
import com.dumas.rpc.remoting.transport.socket.SocketRpcServer;

/**
 * @author dumas
 * @date 2022/02/21 3:28 PM
 */
public class SocketServerMain {
    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setService(helloService);
        socketRpcServer.registerService(rpcServiceConfig);
        socketRpcServer.start();
    }
}
