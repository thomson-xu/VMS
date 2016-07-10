package com.visa.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Test-Lab on 2016/7/7.
 */
public class Country implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String field = (String) value;
        if(field!=null&& field.equals("")) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "国家名称不能为空，请输入数据！",
                    "isnull!");
            throw new ValidatorException(message);
        }else {
            if(field.length()<2){
                FacesMessage message = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "国家名称不能少于2字符！",
                        "tooshort!");
                throw new ValidatorException(message);
            }
            if(field.length()>10){
                FacesMessage message = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "国家名称不能超过10字符！",
                        "toolong!");
                throw new ValidatorException(message);
            }
        }
     /*   if(field.length() < 1) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "�ַ�����С��1",
                    "�ַ����Ȳ���С��1");
            throw new ValidatorException(message);
        }
        if(!field.matches(".+[0-9]+")) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "�����������ַ�������",
                    "����������ַ������������");
            throw new ValidatorException(message);
        }*/
    }
}
