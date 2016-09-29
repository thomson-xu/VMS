/**
 * 
 */
package com.tms.author.view;

import com.tms.base.session.UserSessionContext;
import com.tms.author.bean.SysUsers;
import com.tms.author.service.SecurityUserService;
import com.tms.base.model.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类功能说明：
 *
 *
 */
@Named
@Scope("request")
public class SecurityUser {
	
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
	public void add(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysUsers user){

		try {
			this.securityUserService.add(user);
		} catch (Exception e) {
			e.printStackTrace();
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
	public void   update(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysUsers user){
		
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
	 * 查询用户，支持按照用户名模糊查询
	 * @param request
	 * @param response
	 * @param username
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/query")
	public void query(HttpServletRequest request,HttpServletResponse response,
			String username,@ModelAttribute Parameters params){
		
		 this.securityUserService.query(username, params);
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
