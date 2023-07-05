package com.neu.airquality.req;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AirExceptionReq {
    @ApiModelProperty(value = "用户id")
    private Long user;
    @ApiModelProperty(value = "区域id")
    private Long district;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "图片")
    private String picture;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "预估等级")
    private Integer level;
}


