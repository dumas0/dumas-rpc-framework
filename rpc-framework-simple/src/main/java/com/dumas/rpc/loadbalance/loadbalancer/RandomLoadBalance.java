package com.dumas.rpc.loadbalance.loadbalancer;

import com.dumas.rpc.loadbalance.AbstractLoadBalance;
import com.dumas.rpc.provider.remoting.dto.RpcRequest;

import java.util.List;
import java.util.Random;

/**
 * Implementation of random load balancing strategy
 *
 * @author dumas
 * @date 2022/02/18 2:36 PM
 */
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    protected String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest) {
        Random random = new Random();
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));
    }
}
