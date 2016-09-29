package com.tms.visa.validator;


import org.apache.myfaces.custom.fileupload.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Administrator on 2016/7/7.
 */

public class UploadFile implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UploadedFile file=(UploadedFile)value;
        if(file==null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "未选图片，请选择相应国旗图片", "notfile");
            // Add the message into context for a specific component
            throw new ValidatorException(message);
        }
        if(file.getSize()>10000){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "选择图片大小小于10KB，请选择相应图片", "bigfile");
            // Add the message into context for a specific component
            throw new ValidatorException(message);
        }
    }


}
