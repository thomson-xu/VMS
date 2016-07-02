package com.visa.component;

import javax.faces.component.UIComponentBase;

/**
 * Created by Administrator on 2016/7/2.
 */
public class BootStrapMenu   extends UIComponentBase {

    //用于xml配置和render
    public static final String COMPONENT_FAMILY = "component.BootStrapMenu";
    public static final String COMPONENT_TYPE = "component.BootStrapMenu";

    enum PropertyKeys {
        //组件的属性
        value
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public Integer getValue() {
        //通常用getStateHelper()来存放属性值，stateHelper可以自动处理属性前后台的序列化和反序列化
        return (Integer) getStateHelper().get(PropertyKeys.value);
    }
    public void setValue(Integer value) {
        getStateHelper().put(PropertyKeys.value, value);
    }
}
