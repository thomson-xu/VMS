package com.tms.author.view;

import com.tms.base.common.web.GenerateImageCode;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Named
@Scope("request")
public class Login {
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
