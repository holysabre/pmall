package com.pange.pmall.portal.service;

public interface UmsMemberCacheService {

    /**
     * 设置验证码缓存
     */
    public void setAuthCode(String telephone, String authCode);

    /**
     * 获取验证码缓存
     */
    public String getAuthCode(String telephone);
}
