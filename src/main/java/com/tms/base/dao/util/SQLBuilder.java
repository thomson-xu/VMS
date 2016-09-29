package com.tms.base.dao.util;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Query;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * SQL��乹��������
 * @author slx
 * @date 2009-7-8 ����10:35:28
 * @version 1.0
 */
public class SQLBuilder {

    /**
     * ��ȡʵ�������
     *
     * @param <T>
     * @param entityClass
     *            ʵ����
     * @return
     */
    public <T> String getEntityName(Class<T> entityClass) {
        String entityname = entityClass.getName();
        Entity entity = entityClass.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name())) {
            entityname = entity.name();
        }
        return entityname;
    }

    /**
     * ����Select����Ҫ��ѯ���ֶ������ַ���
     * @author slx
     * @date 2009-7-8 ����10:01:02
     * @modifyNote
     * @param fields
     *          ��Ҫ��ѯ���ֶ�
     * @param alias
     *          ��ı���
     * @return
     *          ƴ�ӳɵ��ֶ����ַ���
     */
    public String buildSelect(String[] fields, String alias) {
        StringBuffer sf_select = new StringBuffer("SELECT");
        for (String field : fields) {
            sf_select.append(" ").append(alias).append(".").append(field)
                    .append(",");
        }
        return (sf_select.substring(0, sf_select.length() - 1)).toString();
    }

    /**
     * ����Select����Ҫ��ѯ���ֶ������ַ���������Ϊʵ����Ĺ��캯��
     * @author yongtree
     * @date 2010-4-13 ����11:59:04
     * @modifyNote
     * @param fields
     * @param alias
     * @return
     */
    public String buildSelect(String className,String[] fields, String alias) {
        StringBuffer sf_select = new StringBuffer("SELECT new ").append(className).append("(");
        for (String field : fields) {
            sf_select.append(" ").append(alias).append(".").append(field)
                    .append(",");
        }
        return (sf_select.substring(0, sf_select.length() - 1))+")";
    }

    /**
     * ��װorder by���
     *
     * @param orderby
     *      ����Ϊkey ,����˳��Ϊvalue��map
     * @return
     *      Order By �Ӿ�
     */
    public String buildOrderby(LinkedHashMap<String, String> orderby) {
        StringBuffer orderbyql = new StringBuffer("");
        if (orderby != null && orderby.size() > 0) {
            orderbyql.append(" order by ");
            for (String key : orderby.keySet()) {
                orderbyql.append("o.").append(key).append(" ").append(
                        orderby.get(key)).append(",");
            }
            orderbyql.deleteCharAt(orderbyql.length() - 1);
        }
        return orderbyql.toString();
    }

    /**
     * �õ�Count�ۺϲ�ѯ�ľۺ��ֶ�,����������
     * @author slx
     * @date 2009-7-8 ����10:26:11
     * @modifyNote
     * @param <T>
     *              ʵ������
     * @param clazz
     *              ʵ����
     * @param alias
     *              �����
     * @return
     *              �ۺ��ֶ���(������)
     */
    public <T> String getPkField(Class<T> clazz, String alias) {
        String out = alias;
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector
                    .getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor propertydesc : propertyDescriptors) {
                Method method = propertydesc.getReadMethod();
                if (method != null && method.isAnnotationPresent(Id.class)) {
//                  PropertyDescriptor[] ps = Introspector.getBeanInfo(
//                          propertydesc.getPropertyType())
//                          .getPropertyDescriptors();
                    out = alias
                            + "."
                            + propertydesc.getName();
//                          + "."
//                          + (!ps[1].getName().equals("class") ? ps[1]
//                                  .getName() : ps[0].getName()
//                                  );
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * ���ò�ѯ����
     * @author slx
     * @date 2009-7-8 ����10:02:55
     * @modifyNote
     * @param query
     *          ��ѯ
     * @param queryParams
     *          ��ѯ����
     */
    public Query setQueryParams(Query query, Object queryParams) {
        if (queryParams != null) {
            if (queryParams instanceof Object[]) {
                Object[] params = (Object[]) queryParams;
                if (params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        query.setParameter(i + 1, params[i]);
                    }
                }
            } else if (queryParams instanceof Map) {
                Map params = (Map) queryParams;
                Iterator<String> it = params.keySet().iterator();
                while(it.hasNext()){
                    String key = it.next();
                    query.setParameter(key, params.get(key));
                }
            }
        }
        return query;
    }

    /**
     * �������е��ַ���ƴ�ӳ�ΪSQL����� in����ʽ 'aaa','bbb','ccc'
     * @author slx
     * @date 2009-5-26 ����10:30:17
     * @modifyNote
     * @param values
     * @return
     */
    public String toSQLIn(Collection<String> values){
        if(values == null || values.isEmpty())
            return null;

        String[] strvalues = new String[0];
        strvalues = (String[]) values.toArray(new String[values.size()]);

        return toSQLIn(strvalues);
    }

    /**
     * ���ַ��������е��ַ���ƴ�ӳ�ΪSQL����� in����ʽ 'aaa','bbb','ccc'
     * @author slx
     * @date 2009-5-26 ����10:30:17
     * @modifyNote
     * @param values
     * @return
     */
    public String toSQLIn(String[] values){
        StringBuffer bf_sqlin = new StringBuffer();
        if(values == null || values.length == 0)
            return null;

        int len = values.length;
        for(int i = 0 ; i < len ; i++){
            bf_sqlin = bf_sqlin.append(", '").append(values[i]).append("' ");
        }
        String str_sqlin = bf_sqlin.substring(1).toString();

        return str_sqlin;
    }
}
