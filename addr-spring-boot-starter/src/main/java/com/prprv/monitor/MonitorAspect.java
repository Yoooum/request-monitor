package com.prprv.monitor;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(MonitorAspect.class);

    @Pointcut("@annotation(com.prprv.monitor.Ip)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) return null;
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String ip = IpAddressUtil.getIp(request);
        String method = request.getMethod();
        String path = request.getRequestURI();
        log.info("ip: {} {}, method: {}, path: {}", ip,IpAddressUtil.getAddr(ip), method, path);
        return point.proceed();
    }
}
