package com.dumas.rpc;

import com.dumas.rpc.annotation.RpcReference;
import org.springframework.stereotype.Component;

/**
 * @author dumas
 * @date 2022/02/22 9:42 AM
 */
@Component
public class HelloController {

    @RpcReference(version = "version1", group = "test1")
    private HelloService helloService;

    public void test() throws InterruptedException {
        String hello = this.helloService.hello(new Hello("111", "222"));
        // 如需使用 assert 断言，需要在 VM options 添加参数: -ea
        assert "Hello description is 222".equals(hello);
        Thread.sleep(100);
        for (int i = 0; i < 10; i++) {
            System.out.println(helloService.hello(new Hello("111", "222")));
        }
    }
}
