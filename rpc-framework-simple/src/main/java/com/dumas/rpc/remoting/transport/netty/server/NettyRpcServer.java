package com.dumas.rpc.remoting.transport.netty.server;

import com.dumas.rpc.config.CustomShutdownHook;
import com.dumas.rpc.config.RpcServiceConfig;
import com.dumas.rpc.factory.SingletonFactory;
import com.dumas.rpc.provider.ServiceProvider;
import com.dumas.rpc.provider.impl.ZkServiceProviderImpl;
import com.dumas.rpc.remoting.transport.netty.codec.RpcMessageDecoder;
import com.dumas.rpc.remoting.transport.netty.codec.RpcMessageEncoder;
import com.dumas.rpc.utils.RuntimeUtil;
import com.dumas.rpc.utils.current.threadpool.ThreadPoolFactoryUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author dumas
 * @date 2022/02/21 3:54 PM
 */
@Slf4j
@Component
public class NettyRpcServer {

    public static final int PORT = 9998;

    private final ServiceProvider serviceProvider = SingletonFactory.getInstance(ZkServiceProviderImpl.class);

    public void registerService(RpcServiceConfig rpcServiceConfig) {
        serviceProvider.publishService(rpcServiceConfig);
    }

    @SneakyThrows
    public void start() {
        CustomShutdownHook.getCustomShutdownHook().clearAll();
        String host = InetAddress.getLocalHost().getHostAddress();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        DefaultEventExecutorGroup serviceHandlerGroup = new DefaultEventExecutorGroup(RuntimeUtil.cpus() * 2, ThreadPoolFactoryUtils.createThreadFactory("service-handler-group", false));

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                // TCP 默认开启了 Nagle 算法，该算法的作用是尽可能的发送大数据块，减少网络传输。TCP_NODELAY 承诺书的作用就是控制是否启用 Nagle 算法
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 是否开启 TCP 底层心跳机制
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 表示系统用于临时存放已完成三次握手的请求队列的最大长度，如果建立连接频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG, 128)
                .handler(new LoggingHandler(LogLevel.INFO))
                // 当客户端第一次进行请求的时候才会进行优化
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 30s 之内没有收到客户端请求的时候才会进行初始化
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
                        pipeline.addLast(new RpcMessageEncoder());
                        pipeline.addLast(new RpcMessageDecoder());
                        pipeline.addLast(serviceHandlerGroup, new NettyRpcServerHandler());
                    }
                });
        // 绑定端口，同步等待绑定成功
        ChannelFuture future = b.bind(host, PORT).sync();
        // 等待服务端监听端口关闭
        future.channel().closeFuture().sync();
    }
}
