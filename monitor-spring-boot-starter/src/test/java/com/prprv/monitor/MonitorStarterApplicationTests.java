package com.prprv.monitor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = MonitorAutoConfiguration.class)
class MonitorStarterApplicationTests {

    @Test
    void contextLoads() {
        String info = IpAddressUtil.getInfo("127.0.0.0");
        System.out.println(info);
    }

}
