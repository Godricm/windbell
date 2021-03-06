package com.wupaas.boot.paas.im.controller.model.enums;

/**
 * @author by jueyue on 19-6-30.
 */
public enum AppIdEnum {


    ENTERPRISE(1000, "企业ID"),
    /**
     * 1*** 都是paas平台自带的应用
      */
    CONTACTS(1001, "通讯录"),
    APPROVAL(1010, "审批");

    AppIdEnum(int appId, String name) {
        this.appId = appId;
        this.name = name;
    }

    private int appId;

    private String name;

    public int getAppId() {
        return appId;
    }

    public String getName() {
        return name;
    }
}
