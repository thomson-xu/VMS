package com.tms.base;

/**
 * Created by Administrator on 2016/8/8.
 */
public class Sql {
    private String sql;

    public Sql(String sql) {
        this.sql = sql;
    }

    public Sql(String... sql) {
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < sql.length; ++i) {
            buffer.append(sql[i]);
        }

        this.sql = buffer.toString();
    }

    public String getSql() {
        return this.sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String toString() {
        return this.sql;
    }
}
