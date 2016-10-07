/**
 * 
 */
package com.tms.author.view;

import com.tms.author.bean.SysUser;
import com.tms.author.service.SecurityUserService;
import com.tms.base.model.Parameters;
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
public class SystemUser {

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
	public void add(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysUser user){
		 try{
		this.securityUserService.add(user); }
		 catch (Exception e){

		 }
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
	public void update(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute com.tms.author.bean.SysUser user){
		
		this.securityUserService.update(user);
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
	public void delete(HttpServletRequest request,HttpServletResponse response,
			String userId){
		 this.securityUserService.delete(userId);
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
	public void delete(@PathVariable("id")String id){
		
		this.securityUserService.delete(id);
	}
	
	/**
	 * 分配权限
	 * @param userId
	 * @param sysRoles
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/assignroles")
	public void assignRoles(String userId,String[] sysRoles){
		if(userId == null)
			throw new NullPointerException("用户Id不能为空");
		
		this.securityUserService.assignRoles(userId, sysRoles);
	}
	/**
	 * 
	 * @param VQzjgid
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query")
	public void queryByJgid(String VQzjgid,@ModelAttribute Parameters param){
		// this.securityUserService.queryByJgid(VQzjgid, param);
	}
	
	
}
