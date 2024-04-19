package com.pange.pmall.service;

import com.pange.pmall.dto.UmsAdminLoginParam;
import com.pange.pmall.dto.UmsAdminParam;
import com.pange.pmall.model.UmsAdmin;
import com.pange.pmall.model.UmsResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @auther Pange
 * @description
 * @date {2024/3/20}
 */
public interface UmsAdminService {
    /**
     * 获取用户信息
     */
    public UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名获取用户信息
     */
    public UmsAdmin getAdminByUsername(String username);

    /**
     * 根据用户id获取资源列表
     */
    public List<UmsResource> getResourceList(Long adminId);

    /**
     * 注册
     */
    public UmsAdmin register(UmsAdminParam adminParam);

    /**
     * 登录
     */
    public String login(UmsAdminLoginParam adminLoginParam);

    public UmsAdminCacheService getCacheService();

    public UmsAdmin getItem(Long adminId);
}
