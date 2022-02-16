package com.dumas.rpc.compress;

import com.dumas.rpc.extension.SPI;

/**
 * @author dumas
 * @date 2022/02/16 2:16 PM
 */
@SPI
public interface Compress {

    /**
     * 压缩
     * @param bytes
     * @return
     */
    byte[] compress(byte[] bytes);

    /**
     * 解压缩
     * @param bytes
     * @return
     */
    byte[] decompress(byte[] bytes);
}
