package com.tms.base.dao.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ѯ��������ǰ��̨�����ѯ������
 * ƴ�Ӳ�ѯ��䲢���ز���
 * @author slx
 * @date 2009-6-24 ����10:46:05
 * @version 1.0
 */
public class QueryObject implements Serializable{

    private static final long serialVersionUID = 1L;

    /** �����б� **/
    private List<QueryCondition> queryConditions ;

    /** �����б� **/
    private Object[] queryParams ;

    /** where��� **/
    private String whereQL ;

    /**
     * ��Ӳ�ѯ����
     * @author slx
     * @date 2009-6-24 ����11:18:16
     * @modifyNote
     * @param field �ֶ���
     * @param operator  ������
     * @param value ֵ
     */
    public void addCondition(String field , String operator , Object value ){
        addCondition(new QueryCondition(field,operator,value));
    }

    /**
     * ��Ӳ�ѯ����
     * @author slx
     * @date 2009-6-24 ����11:19:03
     * @modifyNote
     * @param condition ��������
     */
    public void addCondition(QueryCondition condition ){
        getQueryConditions().add(condition);
    }

    /**
     * ���������б�
     * @author slx
     * @date 2009-6-24 ����11:19:41
     * @modifyNote
     * @param queryConditions
     */
    public void setQueryConditions(List<QueryCondition> queryConditions){
        this.queryConditions = queryConditions;
    }

    public List<QueryCondition> getQueryConditions() {
        if(queryConditions == null){
            queryConditions = new ArrayList<QueryCondition>();
        }
        return queryConditions;
    }

    /**
     * �õ�where���
     * <br> table.field1 = ? AND table.field2 = ?
     * @author slx
     * @date 2009-6-24 ����11:20:17
     * @modifyNote
     * @return
     */
    public String getWhereQL(){
        buildQuery();
        return whereQL ;
    }

    /**
     * �õ������б�
     * @author slx
     * @date 2009-6-24 ����11:03:54
     * @modifyNote
     * @return
     */
    public Object[] getQueryParams(){
        buildQuery();
        return queryParams ;
    }

    /**
     * �����ѯ����ʼ��where��params
     * @author slx
     * @date 2009-6-24 ����11:31:08
     * @modifyNote
     */
    protected void buildQuery(){
        StringBuffer sf_where = new StringBuffer("");
        int size = getQueryConditions().size();
        queryParams = new Object[size];

        for(int i = 0 ; i < size ; i++){
            QueryCondition condition = getQueryConditions().get(i);
            if(condition.getValue() == null || condition.getValue().toString().trim().equals("")){
                continue;
            }
            sf_where.append(" AND ").append(condition.getField()).append(" ")
                    .append(condition.getOperator()).append(" ? ");
            queryParams[i] = condition.getValue();
        }
        whereQL = sf_where.toString();
        whereQL = whereQL. replaceFirst("AND", "");
    }

    public QueryCondition findQueryCondition(String field , String operator){

        for (QueryCondition queryCondition : queryConditions) {
            if (field.equals(queryCondition.getField())
                    && operator.equals(queryCondition.getOperator())) {
                return queryCondition ;
            }
        }
        return null;
    }
}

