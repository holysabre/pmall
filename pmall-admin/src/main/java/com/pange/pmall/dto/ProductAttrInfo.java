package com.pange.pmall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ProductAttrInfo {
    @ApiModelProperty("商品属性id")
    private Long attributeId;
    @ApiModelProperty("商品分类id")
    private Long attributeCategoryId;

}
