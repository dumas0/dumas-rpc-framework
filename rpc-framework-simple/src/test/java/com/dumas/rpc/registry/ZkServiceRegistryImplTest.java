package com.dumas.rpc.registry;

import com.dumas.rpc.DemoRpcServiceImpl;
import com.dumas.rpc.config.RpcServiceConfig;
import com.dumas.rpc.remoting.dto.RpcRequest;
import com.dumas.rpc.registry.zk.ZkServiceDiscoveryImpl;
import com.dumas.rpc.registry.zk.ZkServiceRegistryImpl;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author dumas
 * @date 2022/02/21 11:26 AM
 */
public class ZkServiceRegistryImplTest {

    @Test
    public void testZkServiceRegistryAndLookup() {
        ZkServiceRegistryImpl zkServiceRegistry = new ZkServiceRegistryImpl();
        InetSocketAddress givenInetSocketAddress = new InetSocketAddress("127.0.0.1", 9333);
        DemoRpcServiceImpl demoRpcService = new DemoRpcServiceImpl();
        RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                .group("test3").version("version3").service(demoRpcService).build();
        zkServiceRegistry.registerService(rpcServiceConfig.getRpcServiceName(), givenInetSocketAddress);
        ZkServiceDiscoveryImpl zkServiceDiscovery = new ZkServiceDiscoveryImpl();
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(rpcServiceConfig.getServiceName())
                .requestId(UUID.randomUUID().toString())
                .group(rpcServiceConfig.getGroup())
                .version(rpcServiceConfig.getVersion())
                .build();
        InetSocketAddress acquiredInetSocketAddress = zkServiceDiscovery.lookupService(rpcRequest);
        assertEquals(givenInetSocketAddress.toString(), acquiredInetSocketAddress.toString());
    }
}
