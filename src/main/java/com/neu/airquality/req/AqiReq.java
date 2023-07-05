package com.neu.airquality.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AqiReq {
    @ApiModelProperty(value = "pm2.5")
    private Double pm25;
    @ApiModelProperty(value = "so2")
    private Double so2;
    @ApiModelProperty(value = "co")
    private Double co;
}
