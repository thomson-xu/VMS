package com.tms.visa.component;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import java.io.IOException;
import java.util.Map;


/**
 * Created by Administrator on 2016/7/2.
 */
//告诉这个类是渲染哪个组件的，通过componentFamily和rendererType到配置文件中定位组件
@FacesRenderer(componentFamily = BootStrapMenu.COMPONENT_FAMILY, rendererType = BootStrapMenu.COMPONENT_TYPE)
//组件依赖的资源文件，必须位于classes/META-INF/resources目录下，或WebContent/resources目录下，在页面上渲染组件时会自动引入资源文件
@ResourceDependencies({
        @ResourceDependency(library = "test/css", name = "menu.css", target = "head"),
        @ResourceDependency(library = "test/js", name = "menu.js", target = "head")})
//渲染类必须继承Renderer
public class BootStrapMenuRenderer  extends Renderer {
    //渲染函数。在前端页面上输入组件的HTML代码
    @Override
    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        //组件的ID。如果前端页面没有给组件定义ID，JSF框架会自动给组件分配一个ID
        String clientId = component.getClientId(context);
        BootStrapMenu numberInputText = (BootStrapMenu) component;
        //输入一个<input id=[clientId] name=[clientId] class="numberInputText" value=[value] />的HTML元素
        writer.startElement("input", component);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("name", clientId, null);
        writer.writeAttribute("class", "numberInputText", null);
        writer.writeAttribute("value", numberInputText.getValue(), null);
        writer.endElement("input");

        //输入一段javascript脚本，增强input的功能
        writer.startElement("script", component);
        writer.write("createNumberInputText(document.getElementById('" + clientId + "'));");
        writer.endElement("script");
    }

    //解析函数。解析从提交请求中获取的数据，设置组件的属性
    @Override
    public void decode(FacesContext context, UIComponent component) {
        String clientId = component.getClientId(context);
        BootStrapMenu numberInputText = (BootStrapMenu) component;
        //获取请求参数
        Map<String, String> parameterMap = context.getExternalContext().getRequestParameterMap();
        String value = parameterMap.get(clientId);
        try {
            //设置组件值
            numberInputText.setValue(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
