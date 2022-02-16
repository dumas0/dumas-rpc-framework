package com.dumas.rpc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dumas
 * @date 2022/02/16 10:21 AM
 */
@AllArgsConstructor
@Getter
public enum CompressTypeEnum {

    GZIP((byte) 0x01, "gzip");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (CompressTypeEnum c : CompressTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
}
