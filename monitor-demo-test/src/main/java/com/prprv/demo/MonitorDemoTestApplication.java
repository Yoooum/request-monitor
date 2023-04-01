package com.prprv.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.prprv.monitor")
@SpringBootApplication
public class MonitorDemoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorDemoTestApplication.class, args);
    }

}
