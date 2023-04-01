package com.prprv.monitor;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Yoooum
 */
@ConfigurationProperties(prefix = "monitor")
public class MonitorProperties {
    /**
     * 是否启用
     */
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
