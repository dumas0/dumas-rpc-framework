package com.dumas.rpc.provider.remoting.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author dumas
 * @date 2022/02/16 2:58 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -8250936148481452205L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;

    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }
}
