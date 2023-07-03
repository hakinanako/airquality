package com.neu.airquality.vo;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String account;
    private String name;
    private String phone;
    private String districtName;
    private int type;
}
