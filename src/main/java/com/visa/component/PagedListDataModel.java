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

    public List getPagedData(SomeCriteriaObject someObject, int start, int page) {
        try {
            Criteria criteria = getSession().createCriteria(ClassToBeQueried.class);
            //Build Criteria object here
            criteria.setFirstResult(start);
            criteria.setMaxResults(page);
            return criteria.list();
        } catch (HibernateException hibernateException) {
            //do something here with the exception
        }
    }





    public int getDataCount(SomeCriteriaObject someObject) {
        Criteria criteria = getSession().createCriteria(ClassToBeQueried.class);
        criteria.setProjection(Projections.rowCount());

        // Build Criteria object here
        Number nuofRecords = ((Number) criteria.uniqueResult());
        return nuofRecords == null ? 0 : nuofRecords.intValue();
    }


    public DataModel getMyPagedDataModel() {
        int totalListSize = getSomeBusinessService().getDataCount(getSomeCriteriaObject());
        List pagedList = getSomeBusinessService().getPagedData(getSomeCriteriaObject(), getTable1().getFirst(), getTable1().getRows());
        PagedDataModel dataModel = new PagedDataModel(pagedList, tatalListSize, getTable1().getRows());
        return dataModel;
    }

    public void setDataScroller(UIComponent dataScroller) {
        this.dataScroller = dataScroller;
    }
    public UIComponent getDataScroller() {
        return dataScroller;
    }
    public UIData getData()
    {
        return data;
    }
    public void setData(UIData data)
    {
        this.data = data;
    }

    public int getNumberOfItemsToDisplay()
    {
        if( isDisplayAll())
        {
            return List.size();
        }
        else return 20;//Items per page
    }

    public void displayAll()

    {
        if (data != null && (data instanceof HtmlDataTable) && (dataScroller instanceof HtmlDataScroller))
        {
            HtmlDataScroller dtScroller = (HtmlDataScroller) dataScroller;
            dtScroller.broadcast(new ScrollerActionEvent(dtScroller, 0));

            if (!isDisplayAll())
            {
                setDisplayAllText("Show paged results");
            }
            else
            {
                setDisplayAllText("Show all");
            }
            setDisplayAll(!isDisplayAll());
        }
    }

    private UIComponent dataScroller;
    private UIData data;
    private boolean displayAll = false;
    private String displayAllText = "Show all";
    public boolean isDisplayAll()
    {
        return displayAll;
    }
    public void setDisplayAll(boolean displayAll)
    {
        this.displayAll = displayAll;
    }

    public String getDisplayAllText()
    {
        return displayAllText;
    }
    public void setDisplayAllText(String displayAllText)
    {
        this.displayAllText = displayAllText;
    }
}