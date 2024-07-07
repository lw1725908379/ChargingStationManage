package com.yjq.programmer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 17259
 * @create 2024-07-06 20:45
 * 扫描降级代码类的包
 */
@Configuration
@ComponentScan("com.lw.api.server.fallback")
public class InitConfig {
}
