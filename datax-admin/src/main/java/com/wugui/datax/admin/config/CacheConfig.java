package com.wugui.datax.admin.config;

import com.wugui.datax.admin.exector.CacheWithScheduledExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cat
 * @createTime 2023-09-21 16:35
 * @description cache 配置
 */
@Configuration
public class CacheConfig {

    @Bean
    public CacheWithScheduledExecutor cacheWithScheduledExecutor() {
        // 创建并返回一个CacheWithScheduledExecutor实例
        return new CacheWithScheduledExecutor();
    }
}
