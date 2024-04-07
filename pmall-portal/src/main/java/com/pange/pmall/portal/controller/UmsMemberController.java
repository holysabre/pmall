package com.pange.pmall.portal.controller;

import com.pange.pmall.common.api.CommonResult;
import com.pange.pmall.portal.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Pange
 * @description
 * @date {2024/3/19}
 */
@RestController
@RequestMapping("/sso")
@Api(tags = {"UmsMemberController", "会员登录注册管理"})
public class UmsMemberController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsMemberService memberService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public CommonResult login(@RequestParam String username,
                              @RequestParam String password){
        String token = memberService.login(username,password);
        if(token == null){
            return CommonResult.failed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }

    @PostMapping("/refresh")
    @ApiOperation("刷新token")
    public CommonResult refreshToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        String refreshToken = memberService.refreshToken(token);
        if(refreshToken == null){
            return CommonResult.failed("登录过期，请重新登录");
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",refreshToken);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }
}
