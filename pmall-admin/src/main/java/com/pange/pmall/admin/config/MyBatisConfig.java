package com.pange.pmall.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @auther Pange
 * @description
 * @date {2024/3/20}
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.pange.pmall.mapper","com.pange.pmall.dao"})
public class MyBatisConfig {
}
