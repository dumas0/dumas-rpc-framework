package com.dumas.rpc.serialize.kryo;

import com.dumas.rpc.remoting.dto.RpcRequest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author dumas
 * @date 2022/02/16 3:35 PM
 */
public class KryoSerializerTest {

    @Test
    public void kryoSerializerTest(){
        RpcRequest target = RpcRequest.builder().methodName("hello")
                .parameters(new Object[]{"sayHello", "sayHellosayHello"})
                .interfaceName("com.dumas.rpc.HelloService")
                .paramTypes(new Class<?>[]{String.class, String.class})
                .requestId(UUID.randomUUID().toString())
                .group("group1")
                .version("version1")
                .build();
        KryoSerializer kryoSerializer = new KryoSerializer();
        byte[] bytes = kryoSerializer.serialize(target);
        RpcRequest actual = kryoSerializer.deserialize(bytes, RpcRequest.class);
        assertEquals(target.getGroup(), actual.getGroup());
        assertEquals(target.getVersion(), actual.getVersion());
        assertEquals(target.getRequestId(), actual.getRequestId());
    }
}
