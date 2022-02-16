package com.dumas.rpc.gzip;

import com.dumas.rpc.compress.gzip.GzipCompress;
import com.dumas.rpc.remoting.dto.RpcRequest;
import com.dumas.rpc.serialize.kryo.KryoSerializer;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author dumas
 * @date 2022/02/16 3:56 PM
 */
public class GzipCompressTest {

    @Test
    public void gzipCompressTest(){
        GzipCompress gzipCompress = new GzipCompress();
        RpcRequest rpcRequest = RpcRequest.builder().methodName("hello")
                .parameters(new Object[]{"sayHello", "sayHellosayHello"})
                .interfaceName("com.dumas.rpc.HelloService")
                .paramTypes(new Class<?>[]{String.class, String.class})
                .requestId(UUID.randomUUID().toString())
                .group("group1")
                .version("version1")
                .build();
        KryoSerializer kryoSerializer = new KryoSerializer();
        byte[] rpcRequestBytes = kryoSerializer.serialize(rpcRequest);
        byte[] compressRpcRequestBytes = gzipCompress.compress(rpcRequestBytes);
        byte[] decompressRpcRequestBytes = gzipCompress.decompress(compressRpcRequestBytes);
        assertEquals(rpcRequestBytes.length, decompressRpcRequestBytes.length);
    }
}
