package com.pange.pmall.security.config;

import com.pange.pmall.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @auther Pange
 * @description
 * @date {2024/3/18}
 */
@Configuration
@EnableCaching
public class RedisConfig extends BaseRedisConfig {
}
