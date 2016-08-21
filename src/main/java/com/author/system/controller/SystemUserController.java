/**
 * 
 */
package com.author.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.author.base.controller.BaseController;
import com.author.base.model.Parameters;
import com.author.system.bean.SysUsers;

/**
 * 类功能说明：用户管理----机构管理员
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-23 下午1:31:18
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/system/user")
public class SystemUserController extends BaseController{

	@Autowired
	private SecurityUserService securityUserService;
	
	/** 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public Message add(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysUsers user){
		
		return this.securityUserService.add(user);
	}
	
	/**
	 * 更新用户
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Message update(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysUsers user){
		
		return this.securityUserService.update(user);
	}
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Message delete(HttpServletRequest request,HttpServletResponse response,
			String userId){
		
		return this.securityUserService.delete(userId);
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete/{id}")
	public Message delete(@PathVariable("id")String id){
		
		return this.securityUserService.delete(id);
	}
	
	/**
	 * 分配权限
	 * @param userId
	 * @param sysRoles
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/assignroles")
	public Message assignRoles(String userId,String[] sysRoles){
		if(userId == null)
			throw new NullPointerException("用户Id不能为空");
		
		return this.securityUserService.assignRoles(userId, sysRoles);
	}
	/**
	 * 
	 * @param VQzjgid
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query")
	public Message queryByJgid(String VQzjgid,@ModelAttribute Parameters param){
		return this.securityUserService.queryByJgid(VQzjgid, param);
	}
	
	
}
