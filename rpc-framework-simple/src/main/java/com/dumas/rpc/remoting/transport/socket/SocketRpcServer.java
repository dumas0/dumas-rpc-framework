package com.dumas.rpc.remoting.transport.socket;

import com.dumas.rpc.config.CustomShutdownHook;
import com.dumas.rpc.config.RpcServiceConfig;
import com.dumas.rpc.factory.SingletonFactory;
import com.dumas.rpc.provider.ServiceProvider;
import com.dumas.rpc.provider.impl.ZkServiceProviderImpl;
import com.dumas.rpc.utils.current.threadpool.ThreadPoolFactoryUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static com.dumas.rpc.remoting.transport.netty.server.NettyRpcServer.PORT;

/**
 * @author dumas
 * @date 2022/02/21 3:33 PM
 */
@Slf4j
public class SocketRpcServer {

    private final ExecutorService threadPool;
    private final ServiceProvider serviceProvider;

    public SocketRpcServer() {
        threadPool = ThreadPoolFactoryUtils.createCustomThreadPoolIfAbsent("socket-server-rpc-pool");
        serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);
    }

    public void registerService(RpcServiceConfig rpcServiceConfig) {
        serviceProvider.addService(rpcServiceConfig);
    }

    public void start() {
        try {
            ServerSocket server = new ServerSocket();
            String host = InetAddress.getLocalHost().getHostAddress();
            server.bind(new InetSocketAddress(host, PORT));
            CustomShutdownHook.getCustomShutdownHook().clearAll();
            Socket socket;
            while ((socket = server.accept()) != null) {
                log.info("client connected [{}]", socket.getInetAddress());
                threadPool.execute(new SocketRpcRequestHandlerRunnable(socket));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            log.error("occur IOException:", e);
        }
    }
}
