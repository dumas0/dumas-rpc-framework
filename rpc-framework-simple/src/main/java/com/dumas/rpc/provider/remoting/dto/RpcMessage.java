package com.dumas.rpc.provider.remoting.dto;

import lombok.*;

/**
 * @author dumas
 * @date 2022/02/16 2:55 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcMessage {
    // rpc message type
    private byte messageType;
    // serialization type
    private byte codec;
    // compress type
    private byte compress;
    // request id
    private int requestId;
    // request data
    private Object data;
}
