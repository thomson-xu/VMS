/**
 * 
 */
package com.tms.author.security;

import com.tms.base.common.web.controller.ControllerTools;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 */

public class LoginAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

	/**
	 * @param loginFormUrl URL where the login page can be found. Should either be
	 *                     relative to the web-app context path (include a leading {@code /}) or an absolute
	 *                     URL.
	 */
	public LoginAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		boolean isAjax = ControllerTools.isAjaxRequest(request);
		boolean hasSession = ControllerTools.hasAuthentication();
		//System.out.println("isAjax:"+isAjax);
		//System.out.println("hasAuthentication："+hasSession);
		if(isAjax && !hasSession){
			this.transformAjaxRequest(request, response);
		}else{
			super.commence(request, response, authException);
		}
	}
	
	private String transformAjaxRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException{
		return "Session timeout，please login again";

	}
	
}
