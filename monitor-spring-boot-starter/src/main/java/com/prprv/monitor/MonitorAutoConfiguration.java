package com.prprv.monitor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yoooum
 */
@Configuration
@EnableConfigurationProperties(MonitorProperties.class)
public class MonitorAutoConfiguration {
}
