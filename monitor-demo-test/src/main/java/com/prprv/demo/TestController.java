package com.prprv.demo;

import com.prprv.monitor.Ip;
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
