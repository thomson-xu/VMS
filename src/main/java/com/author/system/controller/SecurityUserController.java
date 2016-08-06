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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.author.base.controller.BaseController;
import com.author.base.model.Message;
import com.author.base.model.Parameters;
import com.author.base.session.UserSessionContext;
import com.author.system.bean.SysUsers;
import com.author.system.service.SecurityUserService;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-13 上午9:49:46
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/security/user")
public class SecurityUserController extends BaseController{
	
	@Autowired
	private SecurityUserService securityUserService;
	
	@Autowired
	private UserSessionContext userSessionContext;
	
	/**
	 * 添加用户
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
	 * 查询用户，支持按照用户名模糊查询
	 * @param request
	 * @param response
	 * @param username
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query")
	public Message query(HttpServletRequest request,HttpServletResponse response,
			String username,@ModelAttribute Parameters params){
		
		return this.securityUserService.query(username, params);
	}
	
	/**
	 * 根据用户查找权限信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findroleby/userid")
	public Message findRoleByUserId(String userId)throws Exception{
		if(userId == null)
			throw new NullPointerException("用户Id不能为空");
		
		return this.securityUserService.findRoleByUserId(userId);
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
	
	@ResponseBody
	@RequestMapping("/updatePassword")
	public Message updatePassword(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword){
		
		return this.securityUserService.modifyPassword(this.userSessionContext.getUserId(), oldPassword,newPassword);
	}
	
}
