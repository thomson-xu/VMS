package com.author.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.author.base.controller.BaseController;
import com.author.common.web.GenerateImageCode;

@Controller
public class LoginController extends BaseController{
	
	@RequestMapping(value="/usersession",method=RequestMethod.GET)
	@ResponseBody
	public UserDetails getUserSession(HttpServletRequest request,HttpServletResponse response){
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	@RequestMapping("/getCode")
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
