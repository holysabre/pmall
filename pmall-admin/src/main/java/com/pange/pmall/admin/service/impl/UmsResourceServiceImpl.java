package com.pange.pmall.admin.service.impl;

import com.pange.pmall.admin.service.UmsResourceService;
import com.pange.pmall.mapper.UmsResourceMapper;
import com.pange.pmall.model.UmsResource;
import com.pange.pmall.model.UmsResourceExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther Pange
 * @description
 * @date {2024/3/20}
 */
@Service
@Slf4j
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }
}
