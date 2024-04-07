package com.pange.pmall.admin.dto;

import com.pange.pmall.admin.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @auther Pange
 * @description
 * @date {2024/3/30}
 */
@Data
public class PmsBrandParam {
    @ApiModelProperty(value = "品牌名称",required = true)
    @NotEmpty(message = "名称不能为空")
    private String name;
    @ApiModelProperty(value = "品牌首字母")
    private String firstLetter;
    @ApiModelProperty(value = "排序")
    @Min(value = 0,message = "排序最小为0")
    private Integer sort;
    @ApiModelProperty(value = "是否为厂家制造商")
    @FlagValidator(value = {"0","1"}, message = "厂家状态不正确")
    private Integer factoryStatus;
    @ApiModelProperty(value = "是否显示")
    @FlagValidator(value = {"0","1"}, message = "显示状态不正确")
    private Integer showStatus;
    @ApiModelProperty(value = "品牌logo", required = true)
    private String logo;
    @ApiModelProperty(value = "品牌大图")
    private String bidPic;
    @ApiModelProperty(value = "是否故事")
    private String brandStory;
}