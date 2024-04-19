package com.pange.pmall.service.impl;

import com.pange.pmall.bo.AdminUserDetails;
import com.pange.pmall.dao.UmsAdminRoleRelationDao;
import com.pange.pmall.dto.UmsAdminLoginParam;
import com.pange.pmall.dto.UmsAdminParam;
import com.pange.pmall.service.UmsAdminCacheService;
import com.pange.pmall.service.UmsAdminService;
import com.pange.pmall.common.exception.Asserts;
import com.pange.pmall.mapper.UmsAdminMapper;
import com.pange.pmall.model.UmsAdmin;
import com.pange.pmall.model.UmsAdminExample;
import com.pange.pmall.model.UmsResource;
import com.pange.pmall.security.util.JwtTokenUtil;
import com.pange.pmall.security.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @auther Pange
 * @description
 * @date {2024/3/20}
 */
@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin umsAdmin = getAdminByUsername(username);
        if(umsAdmin != null) {
            List<UmsResource> resourceList = getResourceList(umsAdmin.getId());
            return new AdminUserDetails(umsAdmin,resourceList);
        }
        return null;
    }

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin umsAdmin = getCacheService().getAdmin(username);

        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(adminExample);
        if(!adminList.isEmpty()){
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return adminRoleRelationDao.getResourceList(adminId);
    }

    @Override
    public UmsAdmin register(UmsAdminParam adminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(adminParam,umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(adminExample);
        if(!adminList.isEmpty()){
            return null;
        }
        //将密码进行加密操作
        String encodedPassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodedPassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(UmsAdminLoginParam adminLoginParam) {
        String token = null;
        try{
            UserDetails userDetails = loadUserByUsername(adminLoginParam.getUsername());
            if(!passwordEncoder.matches(adminLoginParam.getPassword(), userDetails.getPassword())){
                Asserts.fail("密码错误");
            }
            if(!userDetails.isEnabled()){
                Asserts.fail("账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }catch (Exception e){
            log.warn("登录异常,{}",e.getMessage());
        }
        return token;
    }

    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }

    @Override
    public UmsAdmin getItem(Long adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }
}
