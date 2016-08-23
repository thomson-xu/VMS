/**
 * 
 */
package com.author.system.controller;

import com.author.annotations.AuthorityCollection;
import com.author.base.AuthorityType;
import com.author.base.controller.BaseController;
import com.author.system.bean.SysUsers;
import com.author.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	//添加用户
	@ResponseBody
	@AuthorityCollection(AuthorityType.SYS_USER_ADD)
	@RequestMapping("/add")
	public Message save(HttpServletRequest request,HttpServletResponse response) throws Exception{
		SysUsers user = (SysUsers) ControllerTools.resolvePayloadEX(request);
		Message msg = this.userService.add(user);
		return msg;
	}
	
	//修改密码
	@ResponseBody
	@AuthorityCollection(AuthorityType.SYS_USER_UPDATEPW)
	@RequestMapping("/updatePassword")
	public Message updatePassword(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("userId") String userId, 
			@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
		
		
		Message msg = new Message();
		//out.print(json.toString());
		msg.setSuccess(true);
		msg.setMessage("修改成功");
		
		return msg;
	}
	
	@ResponseBody
	@AuthorityCollection(AuthorityType.SYS_USER_DELETE)
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public Message delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("id") String userId) throws Exception{
		
		return this.userService.delete(userId);
		
	}
}
