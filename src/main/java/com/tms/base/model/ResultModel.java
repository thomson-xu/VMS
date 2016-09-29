package com.tms.base.model;

/**
 * Created by Administrator on 2016/8/8.
 */

import java.util.List;

public class ResultModel {
    private Integer totalCount;
    private Integer page;
    private List list;

    public ResultModel() {
    }

    public ResultModel(Integer totalCount, List list) {
        this.totalCount = totalCount;
        this.list = list;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List getList() {
        return this.list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
