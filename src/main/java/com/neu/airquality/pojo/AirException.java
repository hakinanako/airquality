package com.neu.airquality.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_air_exception")
@ApiModel(value="AirException对象")
public class AirException implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private Long id;

    @ApiModelProperty(value = "上报用户id/关联用户表")
    private Long user;

    @ApiModelProperty(value = "处理人id/关联用户表")
    private Long handlers;

    @ApiModelProperty(value = "区域id/关联区域表")
    private Long district;

    @ApiModelProperty(value = "详细地址")
    private String address;


    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "处理状态")
    private Integer status;

    @ApiModelProperty(value = "检测结果id/关联info表")
    private Long infoId;

    @ApiModelProperty(value = "逻辑删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "添加时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "乐观锁")
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

    @ApiModelProperty(value = "预估等级")
    private Integer level;

}