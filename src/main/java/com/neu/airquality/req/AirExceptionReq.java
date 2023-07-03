package com.neu.airquality.req;
import lombok.Data;

@Data
public class AirExceptionReq {

    private Long user;

    private Long district;

    private String address;


    private String picture;

    private String description;
}


