package com.prprv.monitor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
public class TestController {
    @IpAddr
    @GetMapping("/test")
    public void test() {
        System.out.println("test");
    }
}
