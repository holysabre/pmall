package com.pange.pmall.dto;

import com.pange.pmall.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode
public class PmsProductAttributeParam {

    @ApiModelProperty("属性分类id")
    private Long productAttributeCategoryId;

    @ApiModelProperty("名称")
    @NotEmpty
    private String name;

    @ApiModelProperty("选择类型：0->唯一；1->单选；2->多选")
    @FlagValidator({"0","1","2"})
    private Integer selectType;

    @ApiModelProperty("属性录入方式：0->手工录入；1->从列表中选取")
    @FlagValidator({"0","1"})
    private Integer inputType;

    @ApiModelProperty("可选值列表，以逗号隔开")
    private String inputList;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("分类筛选样式：0->普通；1->颜色")
    @FlagValidator({"0","1"})
    private Integer filterType;

    @ApiModelProperty("检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    @FlagValidator({"0","1","2"})
    private Integer searchType;

    @ApiModelProperty("相同属性产品是否关联；0->不关联；1->关联")
    @FlagValidator({"0","1"})
    private Integer relatedStatus;

    @ApiModelProperty("是否支持手动新增；0->不支持；1->支持")
    @FlagValidator({"0","1"})
    private Integer handAddStatus;

    @ApiModelProperty("属性的类型；0->规格；1->参数")
    @FlagValidator({"0","1"})
    private Integer type;
}
