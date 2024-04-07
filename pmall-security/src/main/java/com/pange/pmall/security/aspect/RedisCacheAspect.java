package com.pange.pmall.security.aspect;

import com.pange.pmall.security.annotation.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @auther Pange
 * @description Redis缓存切面，防止Redis宕机影响正常业务逻辑
 * @date {2024/3/18}
 */

@Aspect
@Component
@Order(2)
@Slf4j
public class RedisCacheAspect {

    @Pointcut("execution(public * com.pange.pmall.portal.service.*CacheService.*(..)) || execution(com.pange.pmall.service.*CacheService.*(..))")
    public void cacheAspect(){}

    @Around("cacheAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object result = null;
        try{
            result = joinPoint.proceed();
        }catch (Throwable throwable){
            //有CacheException注解的地方需要抛出异常
            if(method.isAnnotationPresent(CacheException.class)){
                throw throwable;
            }
            log.error(throwable.getMessage());
        }
        return result;
    }
}
