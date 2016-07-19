package com.visa.component;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;

import javax.faces.model.DataModel;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class PagedListDataModel extends DataModel {

    private int rowIndex = -1;

    private int totalNumRows;

    private int pageSize;

    private List list;

    public PagedListDataModel() {
        super();
    }

    public PagedListDataModel(List list, int totalNumRows, int pageSize) {
        super();
        setWrappedData(list);
        this.totalNumRows = totalNumRows;
        this.pageSize = pageSize;
    }

    public boolean isRowAvailable() {
        if(list == null)
            return false;

        int rowIndex = getRowIndex();
        if(rowIndex >=0 && rowIndex < list.size())
            return true;
        else
            return false;
    }

    public int getRowCount() {
        return totalNumRows;
    }

    public Object getRowData() {
        if(list == null)
            return null;
        else if(!isRowAvailable())
            throw new IllegalArgumentException();
        else {
            int dataIndex = getRowIndex();
            return list.get(dataIndex);
        }
    }

    public int getRowIndex() {
        return (rowIndex % pageSize);
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Object getWrappedData() {
        return list;
    }

    public void setWrappedData(Object list) {
        this.list = (List) list;
    }


}