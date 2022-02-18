package com.dumas.rpc;

import com.dumas.rpc.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dumas
 * @date 2022/02/18 3:20 PM
 */
@Slf4j
@RpcService(group = "test1", version = "version1")
public class DemoRpcServiceImpl implements DemoRpcService {
    @Override
    public String hello() {
        return "hello";
    }
}
