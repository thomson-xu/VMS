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
                    "can not be null haha!",
                    "isnull!");
            throw new ValidatorException(message);
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
