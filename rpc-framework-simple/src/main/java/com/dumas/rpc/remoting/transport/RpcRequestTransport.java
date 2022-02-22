package com.dumas.rpc.remoting.transport;

import com.dumas.rpc.extension.SPI;
import com.dumas.rpc.remoting.dto.RpcRequest;

/**
 * send RpcRequest.
 *
 * @author dumas
 * @date 2022/02/21 2:05 PM
 */
@SPI
public interface RpcRequestTransport {
    /**
     * send rpc request to server and get result
     *
     * @param rpcRequest message body
     * @return data from server
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}
