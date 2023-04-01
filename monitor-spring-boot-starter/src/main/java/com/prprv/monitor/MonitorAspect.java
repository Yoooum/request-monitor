package com.prprv.monitor;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Yoooum
 */
@Aspect
@Component
public class MonitorAspect {
    private static final String UNKNOWN = "unknown";
    private final MonitorService monitorService;

    public MonitorAspect(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @Pointcut("@annotation(com.prprv.monitor.IpAddr)")
    public void pointcut() {}

    @AfterReturning("pointcut()")
    public String afterReturning(JoinPoint point) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) return null;
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String ip = getIpAddr(request);
        String method = request.getMethod();
        String path = request.getRequestURI();
        return monitorService.report(ip, method, path);
    }

    // 获取真实IP
     public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
