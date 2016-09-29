package com.tms.base.dao.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 瀹炰綋bean宸ュ叿绫�<br>
 * <b> 娉ㄦ剰锛氭宸ュ叿绫诲彧鑳藉簲鐢ㄤ笌BaseEntityBean鍙婂叾瀛愮被锛� </b><br>
 * 瀹炵幇鍔熻兘锛� <li>瀹炰綋bean杞崲涓簊tring <li>瀹炰綋bean姣旇緝 <li>寰楀埌瀹炰綋bean鍝堝笇鍊� <li>娣卞厠闅嗗疄浣揵ean
 *
 * @author 瀹嬮粠鏅�
 *
 */
@SuppressWarnings("unchecked")
public class EOUtility {

    // /** 褰撳墠bean涓墍鏈夌殑瀛楁(鍖呮嫭姝ょ被浠ュ強鐖剁被涓殑鍏湁鍜岄潪鍏湁瀛楁) <get鏂规硶鍚�,瀛楁> **/
    // private HashMap<String,Field> hm_Field;

    /** 褰撳墠bean绫讳互鍙婄埗绫讳腑鍏湁鐨刧et鏂规硶 <瀛楁鍚�,get鏂规硶> **/
    private HashMap<String, Method> hm_Geters;

    /** 褰撳墠bean绫讳互鍙婄埗绫讳腑鍏湁鐨剆et鏂规硶 <瀛楁鍚�,get鏂规硶> **/
    private HashMap<String, Method> hm_Seters;

    /** 褰撳墠bean绫讳互鍙婄埗绫讳腑寤惰繜鍔犺浇瀛楁鐨刧et鏂规硶 <瀛楁鍚�,get鏂规硶> **/
    private HashMap<String, Method> hm_LazyGeters;
    /** 褰撳墠bean绫讳互鍙婄埗绫讳腑寤惰繜鍔犺浇瀛楁鐨刧et鏂规硶 <瀛楁鍚�,set鏂规硶> **/
    private HashMap<String, Method> hm_LazySeters;

    /** 搴旂敤鏈伐鍏风被鐨勫疄浣撳璞� **/
    private BaseEntity bean;

    /** 搴旂敤鏈伐鍏风被鐨勫疄浣撳璞＄被鍨� **/
    private Class<? extends BaseEntity> clazz;

    private String beanDispName;

    /** 瀛樺偍瀛楁瀵瑰簲涓枃鍚� **/
    private HashMap<String, String> hm_DispNames;

    public EOUtility(BaseEntity bean) {
        init(bean);
    }

    private void init(BaseEntity bean) {
        // 褰撳墠bean涓庢柊浼犲叆鐨刡ean鏄悓涓�涓猙ean鏃朵笉蹇呰繘琛屽垵濮嬪寲
        if (this.bean == bean)
            return;

        this.bean = bean;
        clazz = bean.getClass();

        initGetterAndSetters();
        // initField();
        // initGeters();
    }

    private void buildGetterANDSetters(Class beanclass) {
        // 寰楀埌褰撳墠绫诲瓧娈靛悕绉�
        Field[] fields = beanclass.getDeclaredFields();
        String fieldname = null;
        // 鎷兼帴瀛楁瀵瑰簲鐨勬柟娉曞悕
        for (Field field : fields) {
            // 涓�瀵瑰瀛楁涓嶈鍙傚姞toString锛宧ashcode鍜宔quals鏂规硶锛屼笉闇�瑕佸姞杞斤紝涓�鏃﹀姞杞藉弽鑰屼細寮曡捣鏁版嵁绾ц仈鏇存柊鏃跺嚭閿欙紒锛�
            if (isLazyField(field.getAnnotations())) {
                continue;
            }
            fieldname = field.getName();
            for (PropertyDescriptor property : propertyDescriptors) {
                if (fieldname.equals(property.getName())) {
                    Method reader = property.getReadMethod();
                    Method writer = property.getWriteMethod();

                    Transient t = reader.getAnnotation(Transient.class);
                    if(t==null){
                        if (reader != null
                                && !(isLazyField(reader.getAnnotations()))){
                            hm_Geters.put(fieldname, reader);// 闈炲欢杩�
                            if (writer != null)
                                hm_Seters.put(fieldname, writer);
                        }else{
                            hm_LazyGeters.put(fieldname, reader); //寤惰繜
                            if (writer != null)
                                hm_LazySeters.put(fieldname, writer);
                        }
                    }
                }
            }
        }
        // 褰撳墠绫讳笉鏄� BaseEntityBean鏃讹紝閫掑綊璋冪敤
        if (!beanclass.equals(BaseEntity.class)) {
            buildGetterANDSetters((Class<? extends BaseEntity>) beanclass
                    .getSuperclass());
        }
    }

    /**
     * 寰楀埌EO瑕佹樉绀虹殑涓枃鍚�
     * @author slx
     * @date 2010-7-2 涓嬪崍05:22:44
     * @modifyNote
     * @return
     */
    public String getEODisplayName(){
        if(beanDispName == null){
            EODisplayName ea = clazz.getAnnotation(EODisplayName.class);
            if(ea != null){
                beanDispName = ea.value();
            }else{
                beanDispName = clazz.getSimpleName();
            }
        }
        return beanDispName;
    }

    /**
     * 寰楀埌瀛楁鏄剧ず鐨勫悕绉�
     * @author slx
     * @date 2010-7-2 涓嬪崍05:23:03
     * @modifyNote
     * @param fieldName
     * @return
     */
    public String getFieldDisplayName(String fieldName){
        String dispName = hm_DispNames.get(fieldName);
        if(dispName == null){
            dispName = getFieldDisplayName(clazz,fieldName);
            hm_DispNames.put(fieldName,dispName );
        }

        return dispName;
    }

    private String getFieldDisplayName(Class clz ,String fieldName){
        String dispName = null;
        Field f;
        try {
            f = clz.getDeclaredField(fieldName);
        } catch (SecurityException e) {
            return fieldName;
        } catch (NoSuchFieldException e) {
            if(!clz.getSuperclass().equals(BaseEntity.class))
                return getFieldDisplayName(clz.getSuperclass(),fieldName);
            else
                return fieldName;
        }
        FieldDisplayName am = f.getAnnotation(FieldDisplayName.class);
        if(am!=null){
            dispName = am.value();
        }else{
            dispName = fieldName;
        }
        return dispName;
    }

    /*
     * 鏍规嵁娉ㄨВ鍒ゆ柇鏄惁鏄欢杩熷姞杞界殑瀛楁
     */
    public static boolean isLazyField(Annotation[] annotations) {
        // 婊¤冻寰幆鍐呬换浣曚竴涓潯浠跺垯涓哄欢杩熷姞杞藉瓧娈�
        for (Annotation annotation : annotations) {
            if (annotation instanceof OneToOne) {
                if (FetchType.LAZY.equals(((OneToOne) annotation).fetch())) {
                    return true;
                }
            }
            if (annotation instanceof ManyToOne) {
                if (FetchType.LAZY.equals(((ManyToOne) annotation).fetch())) {
                    return true;
                }
            }
            // OneToMany 榛樿涓哄欢杩熷姞杞�,濡傛灉娌℃湁鏍囨敞绔嬪嵆鍔犺浇鍒欓兘鏄欢杩熷姞杞�
            if (annotation instanceof OneToMany) {
                if (!FetchType.EAGER.equals(((OneToMany) annotation).fetch())) {
                    return true;
                }
            }
            // ManyToMany 鍚屼笂
            if (annotation instanceof ManyToMany) {
                if (!FetchType.EAGER.equals(((ManyToMany) annotation).fetch())) {
                    return true;
                }
            }

            // Lob瀛楁鍐呭搴炲ぇ,涓嶇鏄笉鏄欢杩熷姞杞�,鍏ㄩ儴涓嶈繘琛屽鐞�
            if (annotation instanceof Lob) {
                return true;
            }

            // 闈炴寔涔呭瓧娈典笉澶勭悊
            if (annotation instanceof Transient) {
                return true;
            }
        }

        return false;
    }

    PropertyDescriptor[] propertyDescriptors = null;

    /**
     * 鍒濆鍖杇et鍜宻et鏂规硶
     *
     * @author slx
     * @date 2009-7-17 涓婂崍08:51:50
     * @modifyNote
     */
    private void initGetterAndSetters() {
        try {
            propertyDescriptors = Introspector.getBeanInfo(clazz)
                    .getPropertyDescriptors();
            if (hm_Geters == null)
                hm_Geters = new HashMap<String, Method>();
            hm_Geters.clear();
            if (hm_LazyGeters == null)
                hm_LazyGeters = new HashMap<String, Method>();
            hm_LazyGeters.clear();
            if (hm_Seters == null)
                hm_Seters = new HashMap<String, Method>();
            hm_Seters.clear();
            if (hm_LazySeters == null)
                hm_LazySeters = new HashMap<String, Method>();
            hm_LazySeters.clear();
            if (hm_DispNames == null)
                hm_DispNames = new HashMap<String, String>();
            hm_DispNames.clear();
            buildGetterANDSetters(clazz);
        } catch (IntrospectionException e) {
        }
    }

    /**
     * 璁剧疆鎸囧畾灞炴�х殑鍊�
     *
     * @author slx
     * @date 2009-7-17 涓婂崍08:51:28
     * @modifyNote
     * @param attName
     *            灞炴�у悕
     * @param value
     *            鍊�
     */
    public void setAttributeValue(String attName, Object value) {
        try {
            Method m = hm_Seters.get(attName);
            if(m == null) m = hm_LazySeters.get(attName);
            m.invoke(bean, new Object[] { value });
        } catch (Exception e) {
        }
    }

    /**
     * 寰楀埌鎸囧畾灞炴�х殑鍊�
     *
     * @author slx
     * @date 2009-7-17 涓婂崍08:51:12
     * @modifyNote
     * @param attName
     *            灞炴�у悕
     * @return 鍊�
     */
    public Object getAttributeValue(String attName) {
        Object o = null;
        try {
            Object[] os = null;
            Method m = hm_Geters.get(attName);
            if(m == null) m = hm_LazyGeters.get(attName);
            o = m.invoke(bean, os);
        } catch (Exception e) {
        }
        return o;
    }

    /**
     * 寰楀埌琛ㄥ悕绉�
     *
     * @author slx
     * @date 2009-7-17 涓婂崍08:50:56
     * @modifyNote
     * @return 琛ㄥ悕
     */
    public String getTableName() {
        javax.persistence.Table table = clazz
                .getAnnotation(javax.persistence.Table.class);
        String tablename = null;
        if (table == null) {
            Class clazzp = clazz.getSuperclass();
            while (!clazzp.equals(BaseEntity.class)) {
                table = (Table) clazzp
                        .getAnnotation(javax.persistence.Table.class);
                if (table != null) {
                    tablename = table.name();
                    break;
                } else {
                    clazzp = clazzp.getSuperclass();
                }
            }
        } else {
            tablename = table.name();
        }
        if (tablename == null || tablename.length() == 0) {
            tablename = clazz.getSimpleName().toUpperCase();
        }
        return tablename;
    }

    private String[] fieldNames;

    /**
     * 寰楀埌瀹炰綋涓墍鏈夋寔涔呭寲瀛楁鍚�
     *
     * @author slx
     * @date 2009-7-17 涓婂崍08:53:21
     * @modifyNote
     * @return 瀛楁鍚嶇О鏁扮粍
     */
    public String[] getAttributeNames() {
        if (fieldNames == null) {
            Set<String> set_fieldnames = hm_Geters.keySet();
            fieldNames = new String[0];
            fieldNames = set_fieldnames.toArray(fieldNames);
        }
        return fieldNames;
    }

    /**
     * 灏嗕竴涓璞tring鍖� <br>
     * 鏍煎紡濡備笅锛� <br>
     * TABLE_NAME::琛ㄥ悕 <br>
     * 瀛楁鍚�::瀛楁鍊� 瀛楁鍚�::瀛楁鍊�
     *
     * @param bean
     * @return
     */
    public String beanToString() {
        StringBuffer sb_tostring = new StringBuffer();
        sb_tostring.append("瀵硅薄:[").append(getEODisplayName())
                .append("] ");

        String[] fieldnames = getAttributeNames();

        for (String fieldname : fieldnames) {
            Object obj_value = null;
            try {
                obj_value = getAttributeValue(fieldname);
                if (obj_value instanceof Date) {
                    obj_value = DateUtil.formatDate((Date) obj_value, null);
                } else if (obj_value instanceof BaseEntity) {
                    obj_value = ((BaseEntity) obj_value).getPrimaryKey();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb_tostring.append(getFieldDisplayName(fieldname)).append(":[").append(obj_value)
                    .append("]\t");
        }

        return sb_tostring.toString();
    }

    /**
     * 鍒ゆ柇褰撳墠bean鏄惁涓庡弬鏁板璞＄浉鍚�
     *
     * @param obj
     * @return
     */
    public boolean equalsBean(Object obj) {
        if (obj == null)// 瀵硅薄涓虹┖涓嶆瘮杈�
            return false;

        // 涓嶆槸BaseEntity锛屼笉蹇呮瘮杈�
        if (!(obj instanceof BaseEntity)) {
            return false;
        }

        // 绫诲瀷涓嶅悓涓嶅繀杩涜姣旇緝
        if (!clazz.equals(obj.getClass())) {
            return false;
        }

        // 渚濇姣旇緝瀛楁鍊硷紝閬囧埌涓嶅悓鐨勫垯杩斿洖false
        String[] fieldnames = getAttributeNames();
        for (String fieldname : fieldnames) {
            boolean same = equalsField(fieldname, bean, obj);
            if (!same) {
                return false;
            }
        }

        return true;
    }

    /**
     * 姣旇緝褰撳墠瀵硅薄涓庡彟涓�涓璞＄殑宸埆锛屽苟杩斿洖鍊间笉鍚岀殑瀛楁鐨勫悕绉般��
     *
     * @author slx
     * @date 2009-7-17 涓婂崍09:34:39
     * @modifyNote
     * @param antherBean
     *            灏嗚姣旇緝鐨勫璞�
     * @return 鍊间笉鍚岀殑瀛楁鍚�
     */
    public List<String> getDifferentField(BaseEntity anotherBean) {
        // 绫诲瀷涓嶅悓涓嶅繀杩涜姣旇緝
        if (!clazz.equals(anotherBean.getClass())) {
            throw new ClassCastException(anotherBean.getClass().getName()
                    + "Cann't Cast to " + clazz.getName());
        }
        List<String> differents = new ArrayList<String>();
        String[] fieldnames = getAttributeNames();
        for (String fieldname : fieldnames) {
            boolean same = equalsField(fieldname, bean, anotherBean);
            if (!same) {
                differents.add(fieldname);
            }
        }
        return differents;
    }

    /**
     * 姣旇緝涓や釜瀵硅薄锛屾寚瀹氱殑瀛楁鍊兼槸鍚︾浉鍚�
     *
     * @author slx
     * @date 2009-7-17 涓婂崍09:51:58
     * @modifyNote
     * @param fieldName
     *            闇�瑕佹瘮杈冪殑瀛楁
     * @param obj1
     *            瀵硅薄1
     * @param obj2
     *            瀵硅薄2
     * @return 鍊肩浉鍚屽垯涓簍rue
     */
    private boolean equalsField(String fieldName, Object obj1, Object obj2) {
        try {
            Object obj_value = null;
            Object current_value = null;
            Method getter = hm_Geters.get(fieldName);
            Object[] os = null;
            current_value = getter.invoke(obj1, os);
            obj_value = getter.invoke(obj2, os);

            if (current_value == null && obj_value == null) {
                return true;
            }else if(current_value != null && obj_value != null){
                if(current_value instanceof BaseEntity && obj_value instanceof BaseEntity){// 閬垮厤閫掑綊姣旇緝,鍐呴儴瀛楁濡傛灉鏄疊aseEntity瀛愮被鍒欏彧姣旇緝pk
                    return ((BaseEntity)current_value).equalsPK(obj_value);
                }
                if(current_value instanceof Date && obj_value instanceof Date){ // 鏃ユ湡绫诲瀷姣旇緝鐗规畩澶勭悊
                    return DateUtil.equalsDate((Date)current_value, (Date)obj_value);
                }

                return obj_value.equals(current_value);
            }else if (current_value != null) {
                return current_value.equals(obj_value);
            } else if (obj_value != null) {
                return obj_value.equals(current_value);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 杩斿洖璇ュ璞＄殑鍝堝笇鐮佸��
     */
    public int hashCodeBean() {

        // 鐢熸垚绠�鍗曠殑浣嶈繍绠梙ash鏁ｅ垪鐮�
        String key = bean.toString();
        int prime = key.hashCode();
        int hash = prime;
        for (int i = 0; i < key.length(); i++) {
            hash ^= (hash << 23 >> 17) ^ key.charAt(i) * 13131;
        }
        // 杩斿洖缁撴灉
        return (hash % prime) * 33;
    }

    /**
     * 鍒╃敤娴佹繁搴﹀厠闅嗗疄浣撶被
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object cloneBean() throws IOException, ClassNotFoundException {
        return cloneObject(bean);
    }

    /**
     * 鍙栧緱涓�涓灇涓惧�间笂鐨勬弿杩版敞瑙�.
     *
     * @author slx
     * @date 2009-9-3 涓婂崍09:13:53
     * @modifyNote yongtree 2010-1-17淇敼
     * @param emumValue
     *            鏋氫妇鍊�
     * @return 濡傛灉浼犲叆鐨勪笉鏄灇涓惧�硷紝鍒欒繑鍥炵┖涓�,鎴栬�呮灇涓炬病鏈夋爣娉ㄦ敞瑙�,鍒欒繑鍥炴灇涓総oString.
     */
    public static String getEnumDescription(Object emumValue) {
        String desValue = "";
        if (emumValue != null) {
            try {
                String enumName = ((Enum) emumValue).name();
                desValue = emumValue.getClass().getField(enumName)
                        .getAnnotation(EnumDescription.class).value();
            } catch (Exception e) {
                return emumValue.toString();
            }
        }
        return desValue;
    }

    public static Object cloneObject(Object obj) throws IOException,
            ClassNotFoundException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        Object cloneObj = (oi.readObject());
        bo.close();
        oo.close();
        bi.close();
        oi.close();
        return cloneObj;
    }

    /**
     * 鍔犺浇鎵�鏈夊欢杩熷姞杞界殑瀛楁.
     *
     * @author slx
     * @date 2010骞�4鏈�1鏃�17:09:44
     */
    void loadLazyField(){
        Iterator<Method> i_mds = hm_LazyGeters.values().iterator();
        while(i_mds.hasNext()){
            Method m = i_mds.next();
            try {
                Object[] os = null;
                Object o = m.invoke(bean, os);
                if(o!=null)
                    o.toString();
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
    }
}