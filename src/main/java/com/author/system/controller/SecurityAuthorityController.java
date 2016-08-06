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
import com.author.system.bean.SysAuthorities;
import com.author.system.service.SecurityAuthorityService;

/**
 * 类功能说明：权限管理控制器
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-27 上午10:00:11
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/security/authority")
public class SecurityAuthorityController extends BaseController {
	
	@Autowired
	private SecurityAuthorityService securityAuthorityService;
	
	@ResponseBody
	@RequestMapping("/add")
	public Message add(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysAuthorities bean) throws Exception{
		
		return this.securityAuthorityService.add(bean);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Message update(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysAuthorities bean) throws Exception{
		
		return this.securityAuthorityService.update(bean);
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public Message query(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute Parameters params) throws Exception{
		
		return this.securityAuthorityService.query(params);
	}
	
	@ResponseBody
	@RequestMapping("/query/all")
	public Message queryAll() throws Exception{
		
		return this.securityAuthorityService.queryAll();
	}
	
	@ResponseBody
	@RequestMapping("/query/enabled")
	public Message queryEnabled() throws Exception{
		
		return this.securityAuthorityService.queryEnabled();
	}
	
	@ResponseBody
	@RequestMapping("/enable/{id}/{enable}")
	public Message enable(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("id")String id,@PathVariable("enable")boolean enable) throws Exception{
		
		return this.securityAuthorityService.enable(id, enable);
	}
	
	@ResponseBody
	@RequestMapping("/query/module/{moduleId}")
	public Message restQueryByModuleId(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("moduleId")String moduleId) throws Exception{
		
		return this.securityAuthorityService.queryByModuleId(moduleId);
	}
	
	@ResponseBody
	@RequestMapping("/query/module")
	public Message queryByModuleId(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("moduleId")String moduleId) throws Exception{
		
		return this.securityAuthorityService.queryByModuleId(moduleId);
	}
}
