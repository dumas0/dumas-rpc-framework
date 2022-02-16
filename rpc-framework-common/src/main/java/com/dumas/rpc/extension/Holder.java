package com.dumas.rpc.extension;

/**
 * @author dumas
 * @date 2022/02/16 10:30 AM
 */
public class Holder<T> {

    private volatile T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
