package com.dumas.rpc.annotation;

import java.lang.annotation.*;

/**
 * RPC service annotatio, marked on the service implementation class
 *
 * @author dz.ma
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface RpcService {
    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";
}
