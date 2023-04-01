## 启动
引入依赖
```groovy
implementation project(':monitor-spring-boot-starter')
```
在 resources 目录添加 ip2region.xdb IP数据库文件

配置文件开启访问IP地址监控

```yml
monitor:
  enabled: true
```

## 使用

在需要监控的方法上添加 @IpAddr 注解, 会自动打印访问IP地址
```java
@IpAddr
@GetMapping("/test")
public void test(){
    System.out.println("test");
}
```
