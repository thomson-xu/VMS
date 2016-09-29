package com.tms.base.dao.util;


import java.io.Serializable;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * ����ʵ��Bean������ϵͳ���й������ֶ� <br>
 * ��д toString() clone() equals() hashCode()
 *
 * @author
 * @date
 * @version 1.0
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1962905939086138888L;

    private transient EOUtility eoutil ;

    protected boolean selected;

    @Transient
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Transient
    protected EOUtility getBeanUtility() {
        if (eoutil == null) {
            eoutil = new EOUtility(this);
        }
        return eoutil;
    }

    @Override
    public String toString() {
        return getBeanUtility().beanToString();
    }

    @Override
    public boolean equals(Object obj) {
        return getBeanUtility().equalsBean(obj);
    }

    @Override
    public int hashCode() {
        return getBeanUtility().hashCodeBean();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = null;
        try {
            obj = getBeanUtility().cloneBean();
        } catch (Exception e) {
            throw new CloneNotSupportedException(e.getMessage());
        }

        return obj;
    }

    /**
     * �õ����пɳ־û��ֶε�����
     * @author 
     * @date 2009-7-17 ����08:59:34
     * @modifyNote
     * @return
     *      �����б�
     */
    @Transient
    public String[] getAttributeNames(){
        return getBeanUtility().getAttributeNames();
    }

    /**
     * �õ�ĳ���ֶε�ֵ
     * @author 
     * @date 2009-7-17 ����08:59:58
     * @modifyNote
     * @param attributeName
     *      �ֶ���
     * @return
     *      ֵ
     */
    @Transient
    public Object getAttributeValue(String attributeName) {
        return getBeanUtility().getAttributeValue(attributeName);
    }

    /**
     * ����ĳ���ֶε�ֵ
     * @author 
     * @date 2009-7-17 ����09:00:26
     * @modifyNote
     * @param attributeName
     *      �ֶ���
     * @param value
     *      ֵ
     */
    @Transient
    public void setAttributeValue(String attributeName , Object value){
        getBeanUtility().setAttributeValue(attributeName,value);
    }

    @SuppressWarnings("static-access")
    @Transient
    public String getEnumDescription(String enumAttributeName){
        Object value = getAttributeValue(enumAttributeName);

        return getBeanUtility().getEnumDescription(value);
    }

    /**
     * ���ʵ���Ӧ�ı���
     *
     * @author 
     * @date 2009-7-17 ����09:00:57
     * @modifyNote
     * @return
     */
    @Transient
    public String getTableName() {
        return getBeanUtility().getTableName();
    }

    /**
     * �Ƚϴ˶�������һ������Ĳ�𣬲�����ֵ��ͬ���ֶε����ơ�
     *
     * @author 
     * @date 2009-7-17 ����09:34:39
     * @modifyNote
     * @param antherBean
     *            ��Ҫ�ȽϵĶ���
     * @return ֵ��ͬ���ֶ���
     */
    @Transient
    public List<String> getDifferentField(BaseEntity anotherBean) {
        return getBeanUtility().getDifferentField(anotherBean);
    }

    /**
     * ��ȡ����ֵ
     *
     * @author 
     * @date 2009-6-12 ����09:15:11
     * @modifyNote
     * @return ����ֵ
     */
    @Transient
    public abstract Object getPrimaryKey();

    /**
     * �Ƚ�����ֵ�Ƿ���ͬ
     *
     * @author yongtree
     * @date 2009-9-15 ����04:09:21
     * @modifyNote
     * @param obj
     * @return
     */
    @Transient
    public boolean equalsPK(Object obj) {
        if (obj == null)// ����Ϊ�ղ��Ƚ�
            return false;
        // ���Ͳ�ͬ���ؽ��бȽ�
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        // ����BaseEO�����رȽ�
        if (!(obj instanceof BaseEntity)) {
            return false;
        }

        BaseEntity eo = (BaseEntity) obj;

        if (getPrimaryKey()!=null
                && eo.getPrimaryKey()!=null) {
            if (getPrimaryKey().equals(eo.getPrimaryKey()))
                return true;
            return false;
        } else {
            return false;
        }

    }

    /**
     * ������һ��eo�����е��ֶ�ֵ����ǰ������
     * @author 
     * @date 2009-12-6 ����11:04:49
     * @modifyNote
     * @param fromEO            ��������
     * @param copyAttributes    ������Щ�ֶ�
     */
    public void copyAttributeValue(BaseEntity fromEO , String[] copyAttributes){
        if(copyAttributes == null)
            return ;

        for (String attr : copyAttributes) {
            this.setAttributeValue(attr, fromEO.getAttributeValue(attr));
        }
    }

    /**
     * ���������ӳټ����ֶ�
     * @author 
     * @date 2010��4��1��17:22:29
     * @modifyNote
     */
    public void loadLazyAttributes(){
        getBeanUtility().loadLazyField();
    }

}
