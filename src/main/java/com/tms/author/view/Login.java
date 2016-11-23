package com.tms.author.view;

import com.tms.author.service.LoginService;
import com.tms.base.common.web.GenerateImageCode;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Named
@Scope("request")
public class Login {
	@Resource
	private LoginService loginService;
	//将请求转发到/j_spring_security_check进行用户登录认证
	public String doLogin() throws IOException, ServletException {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
				.getRequestDispatcher("/j_spring_security_check");

		dispatcher.forward((ServletRequest) context.getRequest(),(ServletResponse) context.getResponse());

		FacesContext.getCurrentInstance().responseComplete();
		// It's OK to return null here because Faces is just going to exit.
		return null;
	}
	public UserDetails getUserSession(HttpServletRequest request,HttpServletResponse response){
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

	public void getCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//禁止缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		//指定生成的响应是图片
		response.setContentType("image/jpeg");
		
		//返回验证码
		GenerateImageCode.generateRandomCode(request, response);
	}
	
}
