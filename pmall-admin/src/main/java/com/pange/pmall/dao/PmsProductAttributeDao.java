package com.pange.pmall.dao;

import com.pange.pmall.dto.ProductAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductAttributeDao {
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
