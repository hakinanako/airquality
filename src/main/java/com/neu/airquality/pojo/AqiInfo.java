package com.neu.airquality.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_aqi_info")
@ApiModel(value="AqiInfo对象", description="")
public class AqiInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private Long id;

    @ApiModelProperty(value = "区域id/关联区域表")
    private Long district;

    @ApiModelProperty(value = "pm2.5")
    private BigDecimal pm25;

    @ApiModelProperty(value = "so2")
    private BigDecimal so2;

    @ApiModelProperty(value = "co")
    private BigDecimal co;

    @ApiModelProperty(value = "空气质量等级")
    private Integer level;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
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


}
