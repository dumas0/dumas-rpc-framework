package com.dumas.rpc.loadbalance.loadbalancer;

import com.dumas.rpc.DemoRpcServiceImpl;
import com.dumas.rpc.config.RpcServiceConfig;
import com.dumas.rpc.extension.ExtensionLoader;
import com.dumas.rpc.loadbalance.LoadBalance;
import com.dumas.rpc.provider.remoting.dto.RpcRequest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author dumas
 * @date 2022/02/18 2:33 PM
 */
public class ConsistentHashLoadBalanceTest {

    @Test
    public void testConsistentHashLoadBalance(){
        LoadBalance loadBalance = ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension("loadBalance");
        ArrayList<String> serviceUrlList = new ArrayList<>(Arrays.asList("127.0.0.1:9997", "127.0.0.1:9998", "127.0.0.1:9999"));

        DemoRpcServiceImpl demoRpcService = new DemoRpcServiceImpl();
        RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                .group("test2").version("version2").service(demoRpcService).build();

        RpcRequest rpcRequest = RpcRequest.builder()
                .parameters(demoRpcService.getClass().getTypeParameters())
                .interfaceName(rpcServiceConfig.getServiceName())
                .requestId(UUID.randomUUID().toString())
                .group(rpcServiceConfig.getGroup())
                .version(rpcServiceConfig.getVersion())
                .build();
        String userServiceAddress = loadBalance.selectServiceAddress(serviceUrlList, rpcRequest);
        assertEquals("127.0.0.1:9998", userServiceAddress);
    }
}
