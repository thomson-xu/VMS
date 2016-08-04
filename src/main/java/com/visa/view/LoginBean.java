package com.visa.view;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/4.
 */
public class LoginBean {
    public String doLogin() throws IOException, ServletException {
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

        // It's OK to return null here because Faces is just going to exit.
        return null;
    }
}
