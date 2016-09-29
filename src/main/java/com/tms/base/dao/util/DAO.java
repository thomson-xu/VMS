package com.tms.base.dao.util;

import javax.persistence.EntityManager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2016/6/26.
 */
public interface DAO {

    /**
     * ���һ�����������
     */
    public void clear();

    /**
     * ����ʵ��
     *
     * @param entity
     *            ʵ��
     */
    public <T extends BaseEntity> void create(T entity);

    /**
     * ��������ʵ��
     *
     * @param entitys
     *            ʵ���б�
     */
    public <T extends BaseEntity> void createBatch(List<T> entitys);

    /**
     * ����ʵ��
     *
     * @param entity
     *            ʵ��
     */
    public <T extends BaseEntity> void update(T entity);

    /**
     * ɾ��ʵ��
     *
     * @param entityClass
     *            ʵ����
     * @param entityid
     *            ʵ��id
     */
    public <T extends BaseEntity> void delete(Class<T> entityClass, Object entityid);

    /**
     * ɾ��ʵ��
     *
     * @param entityClass
     *            ʵ����
     * @param entityids
     *            ʵ��id����
     */
    public <T extends BaseEntity> void delete(Class<T> entityClass,
                                              Object[] entityids);

    /**
     * ��������ɾ��
     *
     * @author
     * @date
     * @modifyNote
     * @param entityClass
     * @param where
     * @param delParams
     */
    public <T extends BaseEntity> void deleteByWhere(Class<T> entityClass,
                                                     String where, Object[] delParams);

    /**
     * ��ȡʵ��
     *
     * @param <T>
     * @param entityClass
     *            ʵ����
     * @param entityId
     *            ʵ��id
     * @return
     */
    public <T extends BaseEntity> T find(Class<T> entityClass, Object entityId);

    /**
     * ����where������ѯ��������
     * @author
     * @date
     * @modifyNote
     * @param <T>
     * @param entityClass
     *          ����
     * @param where
     *          ����
     * @param params
     *          ����
     * @return
     */
    public <T extends BaseEntity> T findByWhere(Class<T> entityClass, String where , Object[] params);

    /**
     * ��ȡʵ�壬�����ӳټ��ص����ã���find��ȣ�
     *
     * @param <T>
     * @param entityClass
     *            ʵ����
     * @param entityId
     *            ʵ��id
     * @return
     */
    public <T extends BaseEntity> T load(Class<T> entityClass, Object entityId);

    /**
     * ���������ж�ʵ���Ƿ����
     *
     * @author
     * @date
     * @modifyNote
     * @param entityClass
     *            ʵ����
     * @param whereql
     *            ��ѯ����(�ɿ�,��Ϊ field1=? and field2=? ��ʽ,Ҳ��Ϊfield1='value1' and
     *            field2='value2'����ʽ)
     * @param queryParams
     *            ����(�ɿգ����ǵ�����ʹ����field1=? and field2=? ����ʽ���������Ϊ��)
     * @return �Ƿ����
     */
    public <T extends BaseEntity> boolean isExistedByWhere(Class<T> entityClass,
                                                           String whereql, Object[] queryParams);

    /**
     * ��ȡ��¼����
     *
     * @param entityClass
     *            ʵ����
     * @return
     */
    public <T extends BaseEntity> long getCount(Class<T> entityClass);

    /**
     * ���������Ͳ�����ȡ��¼����
     *
     * @author
     * @date
     * @modifyNote
     * @param <T>
     * @param entityClass
     *            ʵ����
     * @param whereql
     *            ��ѯ����(�ɿ�,��Ϊ field1=? and field2=? ��ʽ,Ҳ��Ϊfield1='value1' and
     *            field2='value2'����ʽ)
     * @param queryParams
     *            ����(�ɿգ����ǵ�����ʹ����field1=? and field2=? ����ʽ���������Ϊ��)
     * @return ��¼����
     */
    public <T extends BaseEntity> long getCountByWhere(Class<T> entityClass,
                                                       String whereql, Object[] queryParams);

    /**
     * ��ȡ��ҳ����
     *
     * @param <T>
     * @param entityClass
     *            ʵ����
     * @param firstindex
     *            ��ʼ����
     * @param maxresult
     *            ��Ҫ��ȡ�ļ�¼��
     * @return
     */
    public <T extends BaseEntity> QueryResult<T> getScrollData(
            Class<T> entityClass, int firstindex, int maxresult,
            String wherejpql, Object[] queryParams,
            LinkedHashMap<String, String> orderby);

    public <T extends BaseEntity> QueryResult<T> getScrollData(
            Class<T> entityClass, int firstindex, int maxresult,
            String wherejpql, List<Object> queryParams,
            LinkedHashMap<String, String> orderby);

    public <T extends BaseEntity> QueryResult<T> getScrollData(
            Class<T> entityClass, int firstindex, int maxresult,
            String wherejpql, Map<String, Object> queryParams,
            LinkedHashMap<String, String> orderby);

    /**
     * ��ѯʵ�岿���ֶΣ���ȡ��ҳ����
     *
     * ���صĽ����������װ��ʵ�������У�û�в�ѯ���ֶ�ΪNULL<br>
     * ע�⣺ʹ�øýӿ�ʱ��Ҫȷ��ʵ�������ж�Ӧ�Ĳ�ѯ�ֶε��в������췽�������Ҳ�����˳��Ҫ�ʹ˴���queryfields�����Ԫ��һ��
     *
     * @author
     * @date
     * @modifyNote
     * @param <T>
     * @param entityClass
     * @param queryfields
     * @param firstindex
     * @param maxresult
     * @param wherejpql
     * @param queryParams
     * @param orderby
     * @return
     */
    public <T extends BaseEntity> QueryResult<T> getScrollData(
            Class<T> entityClass, String[] queryfields, int firstindex,
            int maxresult, String wherejpql, Object[] queryParams,
            LinkedHashMap<String, String> orderby);

    public <T extends BaseEntity> QueryResult<T> getScrollData(
            Class<T> entityClass, String[] queryfields, int firstindex,
            int maxresult, String wherejpql, List<Object> queryParams,
            LinkedHashMap<String, String> orderby);

    public <T extends BaseEntity> QueryResult<T> getScrollData(
            Class<T> entityClass, String[] queryfields, int firstindex,
            int maxresult, String wherejpql, Map<String, Object> queryParams,
            LinkedHashMap<String, String> orderby);

    /**
     * ����������ѯʵ���е�ָ�������ֶ� <br>
     * ���ؽ��List<String[]>��ʽ���£� <br>
     * ��1�� �ֶ�1value , �ֶ�2value , �ֶ�3value <br>
     * ��2�� �ֶ�1value , �ֶ�2value , �ֶ�3value
     *
     * @author
     * @date
     * @modifyNote
     * @param <T>
     * @param entityClass
     * @param queryfields
     * @param wheresql
     * @param queryParams
     * @return
     */
    public <T extends BaseEntity> List<Object[]> queryFieldValues(
            Class<T> entityClass, String[] queryfields, String wheresql,
            Object[] queryParams);

    public <T extends BaseEntity> List<Object[]> queryFieldValues(
            Class<T> entityClass, String[] queryfields, String wheresql,
            Object[] queryParams, int startRow, int rows);

    /**
     * ����������ѯʵ���е�ָ�������ֶ� <br>
     * ���صĽ����������װ��ʵ�������У�û�в�ѯ���ֶ�ΪNULL<br>
     * ע�⣺ʹ�øýӿ�ʱ��Ҫȷ��ʵ�������ж�Ӧ�Ĳ�ѯ�ֶε��в������췽�������Ҳ�����˳��Ҫ�ʹ˴���queryfields�����Ԫ��һ��
     *
     * @author
     * @date
     * @modifyNote
     * @param <T>
     * @param entityClass
     * @param queryfields
     * @param wheresql
     * @param queryParams
     * @return
     */
    public <T extends BaseEntity> List<T> queryByWhere(Class<T> entityClass,
                                                       String[] queryfields, String wheresql, Object[] queryParams);

    public <T extends BaseEntity> List<T> queryByWhere(Class<T> entityClass,
                                                       String[] queryfields, String wheresql, Object[] queryParams,
                                                       int startRow, int rows);

    /**
     * ����where������ѯʵ��bean�б� <br>
     * where��queryParams�ɿ�
     *
     * @author
     * @date
     * @modifyNote
     * @param <T>
     * @param entityClass
     * @param wheresql
     * @param queryParams
     * @return
     */
    public <T extends BaseEntity> List<T> queryByWhere(Class<T> entityClass,
                                                       String wheresql, Object[] queryParams);

    /**
     * ����where������ѯʵ��bean�б�,��ָ��ȡ�ڼ��е��ڼ��� <br>
     * where��queryParams�ɿ�
     *
     * @author
     * @date
     * @modifyNote
     * @param <T>
     * @param entityClass
     * @param wheresql
     * @param queryParams
     * @param startRow
     *            ��ʼ��
     * @param rows
     *            ��������
     * @return
     */
    public <T extends BaseEntity> List<T> queryByWhere(Class<T> entityClass,
                                                       String wheresql, Object[] queryParams, int startRow, int rows);

    /**
     * �õ�EM�������龭��ʹ�ã�����ʹ�ù����������ϵͳ��ά���ѶȼӴ����չ�Ա��
     *
     * @author
     * @date
     * @modifyNote
     * @return
     */
    public EntityManager getEntityManager();
}
