package com.dumas.rpc.utils;

/**
 * @author dumas
 * @date 2022/02/15 5:26 PM
 */
public class RuntimeUtil {

    /**
     * 获取CPU核心数
     *
     * @return cpu核心数
     */
    public static int cpus() {
        return Runtime.getRuntime().availableProcessors();
    }
}
