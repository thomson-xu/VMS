/*
package com.tms.visa.validator;

*/
/**
 * Created by Administrator on 2016/8/4.
 *//*

import org.springframework.security.authentication.BadCredentialsException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

 //http://blog.csdn.net/jaune161/article/details/18354599
public class LoginErrorPhaseListener implements PhaseListener {
    private static final long serialVersionUID = -1216620620302322995L;

    public void beforePhase(final PhaseEvent arg0) {
        Exception e = (Exception) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap();

        if (e instanceof BadCredentialsException) {
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap();
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Username or password not valid.", null));
        }
    }

    public void afterPhase(final PhaseEvent arg0) {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}*/
