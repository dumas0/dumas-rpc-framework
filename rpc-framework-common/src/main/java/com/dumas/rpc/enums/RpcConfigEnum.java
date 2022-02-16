package com.dumas.rpc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dumas
 * @date 2022/02/16 10:18 AM
 */
@AllArgsConstructor
@Getter
public enum RpcConfigEnum {
    RPC_CONFIG_PATH("rpc.properties"),
    ZK_ADDRESS("rpc.zookeeper.address");

    private final String propertyValue;
}
