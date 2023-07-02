package com.neu.airquality.req;

import lombok.Data;

@Data
public class UserReq {
    private String account;
    private String password;
    private String name;
    private String phone;
    private Long district;
}
