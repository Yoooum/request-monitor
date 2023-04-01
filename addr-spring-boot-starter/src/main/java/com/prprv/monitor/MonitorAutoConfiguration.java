package com.prprv.monitor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author Yoooum
 */
@Configuration
@EnableAspectJAutoProxy
@EnableConfigurationProperties(MonitorProperties.class)
@Import(MonitorAspect.class)
public class MonitorAutoConfiguration {

}
