package com.dumas.rpc;

import com.dumas.rpc.config.RpcServiceConfig;
import com.dumas.rpc.proxy.RpcClientProxy;
import com.dumas.rpc.remoting.transport.RpcRequestTransport;
import com.dumas.rpc.remoting.transport.socket.SocketRpcClient;

/**
 * @author dumas
 * @date 2022/02/15 5:22 PM
 */
public class SocketClientMain {
    public static void main(String[] args) {
        RpcRequestTransport rpcRequestTransport = new SocketRpcClient();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcRequestTransport, rpcServiceConfig);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        String hello = helloService.hello(new Hello("111", "222"));
        System.out.println(hello);
    }
}
