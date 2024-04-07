package com.pange.pmall.portal.service.impl;

import com.pange.pmall.common.service.RedisService;
import com.pange.pmall.portal.service.UmsMemberCacheService;
import com.pange.pmall.security.annotation.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @auther Pange
 * @description
 * @date {2024/3/30}
 */
@Service
public class UmsMemberCacheServiceImpl implements UmsMemberCacheService {

    @Autowired
    private RedisService redisService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.expire.authCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_AUTH_CODE;
    @Value("${redis.key.orderId}")
    private String REDIS_KEY_ORDER_ID;
    @Value("${redis.key.member}")
    private String REDIS_KEY_MEMBER;

    @CacheException
    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE+":"+telephone;
        redisService.set(key,authCode,REDIS_EXPIRE);
    }

    @CacheException
    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE+":"+telephone;
        return (String)redisService.get(key);
    }
}
