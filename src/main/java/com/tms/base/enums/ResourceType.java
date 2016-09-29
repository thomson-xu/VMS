package com.tms.base.enums;

/**
 * Created by Administrator on 2016/8/8.
 */
public enum ResourceType {
    URL("URL"),
    METHOD("METHOD");

    private String value;

    private ResourceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

