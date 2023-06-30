package com.nue.airquality.common;

public enum ResultCode {

    /**
     * 响应体中的响应码和响应信息
     */
    OK(20000, "操作成功"),

    FAIL(50000, "操作失败"),

    ERROR(52000, "未知错误"),
    /**
     * 补充
     */
    XXX(00000, "补充");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}


