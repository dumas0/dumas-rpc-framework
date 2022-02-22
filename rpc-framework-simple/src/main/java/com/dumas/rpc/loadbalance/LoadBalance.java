package com.dumas.rpc.loadbalance;

import com.dumas.rpc.extension.SPI;
import com.dumas.rpc.remoting.dto.RpcRequest;

import java.util.List;

/**
 * Interface to the load balancing policy
 *
 * @author dumas
 * @date 2022/02/18 2:34 PM
 */
@SPI
public interface LoadBalance {
    /**
     * Choose one from the list of existing service addresses list
     *
     * @param serviceAddress Service address list
     * @param rpcRequest     target service address
     * @return
     */
    String selectServiceAddress(List<String> serviceAddress, RpcRequest rpcRequest);
}
