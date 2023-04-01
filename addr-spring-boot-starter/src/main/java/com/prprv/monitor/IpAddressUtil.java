package com.prprv.monitor;

import jakarta.servlet.http.HttpServletRequest;
import org.lionsoul.ip2region.xdb.Searcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Yoooum
 */
public class IpAddressUtil {
    private static final Logger log = LoggerFactory.getLogger(IpAddressUtil.class);
    public static String getAddr(String ip) {
        // 读取 ip2region.db 文件路径
        String dbPath = Objects.requireNonNull(IpAddressUtil.class.getClassLoader().getResource("ip2region.xdb")).getPath();

        // 1、从 dbPath 中预先加载 VectorIndex 缓存，并且把这个得到的数据作为全局变量，后续反复使用。
        byte[] vIndex;
        try {
            vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        } catch (Exception e) {
            log.error("failed to load vector index from {}: {}", dbPath, e.getMessage());
            return null;
        }

        // 2、使用全局的 vIndex 创建带 VectorIndex 缓存的查询对象。
        Searcher searcher;
        try {
            searcher = Searcher.newWithVectorIndex(dbPath, vIndex);
        } catch (Exception e) {
            log.error("failed to create vectorIndex cached searcher with `{}`: {}", dbPath, e.getMessage());
            return null;
        }

        // 3、查询
        try {
            String region = searcher.search(ip);
            if (StringUtils.hasText(region)) {
                region = region.replace("|0", "");
                region = region.replace("0|", "");
            }
            return region;
        } catch (Exception e) {
            log.error("failed to search({}): {}", ip, e.getMessage());
        } finally {
            // 4、关闭资源
            try {
                searcher.close();
            } catch (IOException e) {
                log.error("failed to close searcher: {}", e.getMessage());
            }
        }
        // 备注：每个线程需要单独创建一个独立的 Searcher 对象，但是都共享全局的制度 vIndex 缓存。
        return null;
    }

    private static final String UNKNOWN = "unknown";

    public static String getIp(HttpServletRequest request) {
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
