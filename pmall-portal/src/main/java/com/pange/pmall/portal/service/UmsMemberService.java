package com.pange.pmall.portal.service;

import com.pange.pmall.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;

public interface UmsMemberService {
    UserDetails loadByUsername(String username);

    UmsMember getByUsername(String username);

    String login(String username,String password);

    String refreshToken(String token);
}
