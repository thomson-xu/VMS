package com.tms.base.dao.util;


import java.util.List;

/**
 * ��ѯ�������
 *
 * @author yongtree
 * @date 2009-4-30 ����09:00:12
 * @version 1.0
 */
public class QueryResult<T> {
    private List<T> resultlist;
    private Long totalrecord;

    public List<T> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }

    public Long getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(Long totalrecord) {
        this.totalrecord = totalrecord;
    }
}