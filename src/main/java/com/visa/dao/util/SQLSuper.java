package com.visa.dao.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 给对象做反射并且定于返回HQL语句方法的抽象类
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
 * 返回所有类的名字
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
 * 返回类的名字
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
 * 给传入的对象做反射
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
 * 返回类中的所有属性
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
 * 使用反射调用对象的get方法
 * @param obj
 *            操作的对象
 * @param att
 *            操作的属性
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
         * 使用反射调用对象的set方法
         * @param obj
         *            操作的对象
         * @param att
         *            操作的属性
         * @param value
         *            设置的值
         * @param type
         *            参数的属性
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
 * 给setter()的操作的属性首字母大写
 * @param att setter()操作的属性
 * @return
 */protected String firstLower(String att) {
        StringBuffer sb = new StringBuffer();
        sb.append(att.substring(0,1).toUpperCase());
        sb.append(att.substring(1, att.length()));
        return sb.toString();
        }
        }