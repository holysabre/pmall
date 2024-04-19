package com.pange.pmall.service;

import com.pange.pmall.dto.PmsProductAttributeParam;
import com.pange.pmall.dto.ProductAttrInfo;
import com.pange.pmall.model.PmsProductAttribute;

import java.util.List;

public interface PmsProductAttributeService {

    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    public int create(PmsProductAttributeParam pmsProductAttributeParam);

    public int update(Long id,PmsProductAttributeParam pmsProductAttributeParam);

    public PmsProductAttribute getItem(Long id);

    public int delete(List<Long> ids);

    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}
