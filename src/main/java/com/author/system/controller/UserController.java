/**
 * 
 */
package com.author.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.author.annotations.AuthorityCollection;
import com.author.base.AuthorityType;
import com.author.base.controller.BaseController;
import com.author.common.web.controller.ControllerTools;
import com.author.system.bean.SysUser;

/**
 * 类功能说明：用户管理
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-11 下午5:10:31
 * @version v1.0
 *
 */

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
		SysUser user = (SysUser) ControllerTools.resolvePayloadEX(request);
		Message msg = this.userService.add(user);
		msg.setData(user);
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