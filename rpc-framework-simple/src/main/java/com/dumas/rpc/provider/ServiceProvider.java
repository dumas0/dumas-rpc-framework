package com.dumas.rpc.provider;

import com.dumas.rpc.config.RpcServiceConfig;

/**
 * store and provide service object.
 *
 * @author dumas
 * @date 2022/02/21 3:37 PM
 */
public interface ServiceProvider {
    /**
     * @param rpcServiceConfig rpc service related attributes
     */
    void addService(RpcServiceConfig rpcServiceConfig);

    /**
     * @param rpcServiceName rpc service name
     * @return service object
     */
    Object getService(String rpcServiceName);

    /**
     * @param rpcServiceConfig rpc service related attributes
     */
    void publishService(RpcServiceConfig rpcServiceConfig);
}
