package com.alibaba.tmf3.core;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM6:53
 */
public abstract class BizInstance {

    private String bizCode;

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizCode() {
        return bizCode;
    }
}
