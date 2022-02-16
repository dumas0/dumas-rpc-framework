package com.dumas.rpc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dumas
 * @date 2022/02/16 10:28 AM
 */
@AllArgsConstructor
@Getter
public enum RpcResponseCodeEnum {

    SUCCESS(200, "The remote call is successful"),
    FAIL(500, "The remote call is fail");

    private final int code;
    private final String message;
}
