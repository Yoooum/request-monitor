package com.prprv.monitor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
public class TestController {
    @Ip
    @GetMapping("/test")
    public String test() {
        System.out.println("test");
        return "test";
    }
}
