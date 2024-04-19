package com.pange.pmall.dto;

import com.pange.pmall.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@EqualsAndHashCode
public class PmsProductCategoryParam {
    @ApiModelProperty("父分类id")
    private Long parentId;

    @ApiModelProperty("名称")
    @NotEmpty
    private String name;

    @ApiModelProperty("单位")
    private String productUnit;

    @ApiModelProperty("是否显示在导航 0->不显示;1->显示")
    @FlagValidator({"0","1"})
    private Integer navStatus;

    @ApiModelProperty("是否显示 0->不显示;1->显示")
    @FlagValidator({"0","1"})
    private Integer showStatus;

    @ApiModelProperty("排序")
    @Min(0)
    private Integer sort;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("关键词")
    private String keywords;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("商品相关属性筛选集合")
    private List<Long> productAttributeIdList;
}
