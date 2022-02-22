package com.dumas.rpc.registry;

import com.dumas.rpc.extension.SPI;

import java.net.InetSocketAddress;

/**
 * service registration
 *
 * @author dumas
 * @date 2022/02/21 9:52 AM
 */
@SPI
public interface ServiceRegistry {
    /**
     * register service
     *
     * @param rpcServiceName    rpc service name
     * @param inetSocketAddress service address
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);
}
