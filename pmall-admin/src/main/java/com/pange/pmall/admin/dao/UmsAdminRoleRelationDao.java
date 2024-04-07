package com.pange.pmall.admin.dao;

import com.pange.pmall.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色关系管理自定义Dao
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
