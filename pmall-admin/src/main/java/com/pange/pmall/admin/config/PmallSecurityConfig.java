package com.pange.pmall.admin.config;

import com.pange.pmall.admin.service.UmsAdminService;
import com.pange.pmall.admin.service.UmsResourceService;
import com.pange.pmall.model.UmsResource;
import com.pange.pmall.security.component.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther Pange
 * @description
 * @date {2024/3/20}
 */
@Configuration
public class PmallSecurityConfig {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> adminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService(){
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<UmsResource> resourceList = resourceService.listAll();
                for (UmsResource resource : resourceList) {
                    map.put(resource.getUrl(), new SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                return map;
            }
        };
    }
}
