/**
 * 
 */
package com.tms.author.view;

import com.tms.author.bean.SysUsers;
import com.tms.author.service.SecurityUserService;
import com.tms.author.base.model.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类功能说明：用户管理----机构管理员
 *
 *
 */
@Named
@Scope("request")
public class SystemUser extends BaseController {

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
