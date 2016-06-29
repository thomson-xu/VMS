package com.visa.dao.util;

/**
 * Created by Test-Lab on 2016/6/26.
 */
public class OrderBy {
    private String column = "id";
    private String type = "asc";
    public String getColumn() {
        return column;
    }
    public void setColumn(String column) {
        this.column = column;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}

