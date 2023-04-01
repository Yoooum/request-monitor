package com.prprv.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Yoooum
 */
@Service
public class MonitorService {
    private static final Logger log = LoggerFactory.getLogger(MonitorService.class);

    private final MonitorProperties monitorProperties;

    public MonitorService(MonitorProperties monitorProperties) {
        this.monitorProperties = monitorProperties;
    }

    public String report(String ip, String method, String path) {
        if (monitorProperties.isEnabled()) {
            String info = IpAddressUtil.getInfo(ip);
            System.out.println("ip: " + ip + ", method: " + method + ", path: " + path + ", addr: " + info);
            return info;
        }
        return null;
    }
}
