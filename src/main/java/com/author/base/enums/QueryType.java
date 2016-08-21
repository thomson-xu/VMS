package com.author.base.enums;

/**
 * Created by Administrator on 2016/8/8.
 */
public enum QueryType {
    LIKE("like"),
    FRONT_LIKE("like"),
    BACK_LIKE("like");

    private String type;

    private QueryType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object transformValue(Object value) {
        return this.type.equals("like")?this.toLikeString((String)value):value;
    }

    private String toLikeString(String value) {
        return value == null?"":(this.equals(BACK_LIKE)?value + "%":(this.equals(FRONT_LIKE)?"%" + value:"%" + value + "%"));
    }
}

