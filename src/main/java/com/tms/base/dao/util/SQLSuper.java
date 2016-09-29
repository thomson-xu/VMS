package com.tms.base.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * �����������䲢�Ҷ��ڷ���HQL��䷽���ĳ�����
 * @author Administrator
 *
 */
public abstract class SQLSuper {

protected StringBuffer SQL;

public StringBuffer getSQL() {
        return SQL;
        }

        public void setSQL(StringBuffer sQL) {
        SQL = sQL;
        }

        public abstract String getSQL(Object obj , List condition,OrderBy orderBy);

/**
 * ���������������
 * @param tables
 * @return List<String>
 */protected List<String> getClassNames(List<?> tables){
        List<String> classNames = null;
        if(tables != null && tables.size()!=0){
        classNames = new ArrayList<String>();
        for(Object obj : tables){
                /*if(obj instanceof Content){
                    classNames.add(obj.getClass().getSimpleName());
                }else if(obj instanceof Member){
                    classNames.add(obj.getClass().getSimpleName());
                }else if(obj instanceof NewsInfo){
                    classNames.add(obj.getClass().getSimpleName());
                }else if(obj instanceof Topic){
                    classNames.add(obj.getClass().getSimpleName());
                }*/
        classNames.add(obj.getClass().getSimpleName());
        }
        }
        return classNames;
        }

/**
 * �����������
 * @param table
 * @return
 */protected String getClassName(Object table){
        String className = null;
        if(table != null){
                /*if(table instanceof Content){
                    className=table.getClass().getSimpleName();
                }else if(table instanceof Member){
                    className=table.getClass().getSimpleName();
                }else if(table instanceof NewsInfo){
                    className=table.getClass().getSimpleName();
                }else if(table instanceof Topic){
                    className=table.getClass().getSimpleName();
                }*/
        className=table.getClass().getSimpleName();
        }
        return className;
        }

/**
 * ������Ķ���������
 * @param o
 * @return
 */protected Class<?> getClassReverberate(Object o){
        String ClassName = o.getClass().getName();
        Class<?> demo = null;
        try {
        demo = Class.forName(ClassName);
        } catch (Exception e) {
        e.printStackTrace();
        }
        return demo;
        }

/**
 * �������е���������
 * @param o
 * @return List<String>
 */protected List<String> getClassPropertyName(Object o) {
        Class<?> demo = this.getClassReverberate(o);
        List<String> classPropertyNames = null;
        Field[] field = demo.getDeclaredFields();
        classPropertyNames = new ArrayList<String>();
        for (int i = 0; i < field.length; i++) {
        classPropertyNames.add(field[i].getName());
        }
        return classPropertyNames;
        }

/**
 * ʹ�÷�����ö����get����
 * @param obj
 *            �����Ķ���
 * @param att
 *            ����������
 * */public Object getter(Object obj, String att) {
        try {
        Method method = obj.getClass().getMethod("get" + firstLower(att));
        return method.invoke(obj);
        } catch (Exception e) {
        e.printStackTrace();
        return null;
        }
        }

        /**
         * ʹ�÷�����ö����set����
         * @param obj
         *            �����Ķ���
         * @param att
         *            ����������
         * @param value
         *            ���õ�ֵ
         * @param type
         *            ����������
         * */
        public void setter(Object obj, String att, Object value,
        Class<?> type) {
        try {
        Method method = obj.getClass().getMethod("set" + firstLower(att), type);
        method.invoke(obj, value);
        } catch (Exception e) {
        e.printStackTrace();
        }
        }

/**
 * ��setter()�Ĳ�������������ĸ��д
 * @param att setter()����������
 * @return
 */protected String firstLower(String att) {
        StringBuffer sb = new StringBuffer();
        sb.append(att.substring(0,1).toUpperCase());
        sb.append(att.substring(1, att.length()));
        return sb.toString();
        }
        }