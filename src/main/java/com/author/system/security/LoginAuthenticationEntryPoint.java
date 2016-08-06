/**
 * 
 */
package com.author.system.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.author.base.model.Message;
import com.author.common.web.controller.ControllerTools;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-2-10 下午5:11:29
 * @version v1.0
 *
 */
@SuppressWarnings("deprecation")
public class LoginAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {
    
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		boolean isAjax = ControllerTools.isAjaxRequest(request);
		boolean hasSession = ControllerTools.hasAuthentication();
		System.out.println("isAjax:"+isAjax);
		System.out.println("hasAuthentication："+hasSession);
		if(isAjax && !hasSession){
			this.transformAjaxRequest(request, response);
		}else{
			super.commence(request, response, authException);
		}
	}
	
	private void transformAjaxRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException{
		Message msg = new Message();
		msg.setIsSessionOut(true);
		msg.setMessage("Session超时，请重新登录");
		ControllerTools.print(response, msg);
	}
	
}
