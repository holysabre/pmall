package com.pange.pmall.portal.service.impl;

import com.pange.pmall.mapper.UmsMemberMapper;
import com.pange.pmall.model.UmsMember;
import com.pange.pmall.model.UmsMemberExample;
import com.pange.pmall.portal.domain.MemberDetail;
import com.pange.pmall.portal.service.UmsMemberService;
import com.pange.pmall.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther Pange
 * @description
 * @date {2024/3/19}
 */
@Service
@Slf4j
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadByUsername(String username) {
        UmsMember umsMember = getByUsername(username);
        if(umsMember != null){
            return new MemberDetail(umsMember);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public UmsMember getByUsername(String username) {
        UmsMemberExample memberExample = new UmsMemberExample();
        memberExample.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(memberExample);
        if(memberList.size() > 0){
            return memberList.get(0);
        }
        return null;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try{
            UserDetails userDetails = loadByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);

        }catch (Exception e){
            log.warn("登录异常, {}",e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }


}
