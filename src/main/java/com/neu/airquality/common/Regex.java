package com.neu.airquality.common;

public class Regex {



    /**
     * 密码 必须同时包含大小写字母 可以另外包含数字和特殊字符 长度8-20
     */
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d\\W]{8，20}$";

    /**
     * 11位电话号码
     */
    public static final String PHONE_REGEX = "^\\d{11}$";
    /**
     * 5-10位用户名
     */
    public static final String ACCOUNT_REGEX = "^[a-zA-Z0-9_]{5,10}$";
    /**
     * 10位字符(只能包含汉字，字母和圆点)
     */
    public static final String NAME_REGEX = "^[\\u4e00-\\u9fa5a-zA-Z·]{1,10}$";
}
