package com.tms.base.dao.util;

import java.util.List;

/**
 * Created by Test-Lab on 2016/6/26.
 */
public class SQLUtil extends SQLSuper{
    /**
     * ����HQL���Ĺ����࣬ʵ��SQLSuper������
     * @author Administrator
     *
     */
    @Override
        public String getSQL(Object obj , List condition ,OrderBy orderBy) {
        /*if(condition == null){
            condition = new ArrayList();
        }*/
            StringBuffer sb = new StringBuffer();
            sb.append(" from ");
            sb.append(this.getClassName(obj));
            //sb.append(" where ");
            StringBuffer conditionSQL = new StringBuffer();
            List<String> classPropertyName = this.getClassPropertyName(obj);
            for (int i = 0; i < classPropertyName.size(); i++) {
                Object gett = this.getter(obj, classPropertyName.get(i).toString());
                if (gett == null || gett.equals("-1") || gett.toString().equals("-1")) {
                    continue;
                }
            /*if (i > 0) {
                conditionSQL.append(" and ");
            }*/if(gett instanceof List){
                    //Object[] array = (Object[])gett;
                    //Array array = (Array) gett;
                    List array = (List) gett;
                    if(array.size()==1){
                        conditionSQL.append(classPropertyName.get(i).replace('_', '.')+" > ?");
                        condition.add(array.get(0));
                    }else if(array.size()==2){
                        conditionSQL.append(classPropertyName.get(i).replace('_', '.')+" between ? and ?");
                        condition.add(array.get(0));
                        condition.add(array.get(1));
                    }
                }else{
                    conditionSQL.append(classPropertyName.get(i).replace('_', '.')+" = ? ");

                    condition.add(gett);
                }
                conditionSQL.append(" and ");

            }
            if(conditionSQL.toString().length()>0){
                sb.append(" where ");
                sb.append(conditionSQL.toString());
                sb.append(" 1 = 1 ");
            }
            sb.append(" order by ");
            sb.append(orderBy.getColumn());
            sb.append("");
            sb.append(orderBy.getType());
            System.out.println(sb.toString());
            for (Object o : condition) {
                System.out.println(o);
            }
            return sb.toString();
        }

    /*public static void main(String[] args) {
        House house = new House();
        house.setId(2);
        house.setStreet_id(3);
        SQLUtil sql = new SQLUtil();
        System.out.println(sql.getSQL(house));
    }*/



}
