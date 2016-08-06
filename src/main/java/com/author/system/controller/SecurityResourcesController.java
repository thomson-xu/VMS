/**
 * 
 */
package com.author.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.author.base.model.Message;
import com.author.base.model.Parameters;
import com.author.system.bean.SysResources;
import com.author.system.security.MethodSecurityMetadataSource;
import com.author.system.security.URLSecurityMetadataSource;
import com.author.system.service.SecurityResourcesService;

/**
 * 类功能说明：资源控制器
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-30 下午3:06:41
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/security/resource")
public class SecurityResourcesController {
	
	@Autowired
	private SecurityResourcesService securityResourcesService;
	
	@RequestMapping("/add")
	@ResponseBody
	public Message add(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysResources resource,@RequestParam("moduleId")String moduleId) throws Exception{
		
		return this.securityResourcesService.add(resource, moduleId);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Message update(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysResources resource,@RequestParam("moduleId")String moduleId) throws Exception{
		
		return this.securityResourcesService.update(resource, moduleId);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Message delete(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("id")String resourceId) throws Exception{
		
		return this.securityResourcesService.delete(resourceId);
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Message query (HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute Parameters params) throws Exception{
		String resourceName = request.getParameter("resourceName");
		if(resourceName != null)
			resourceName = new String(resourceName.getBytes("iso8859-1"),"UTF-8");
		return this.securityResourcesService.query(resourceName,params);
	}
	
	@RequestMapping("/refresh/cache")
	@ResponseBody
	public Message refreshCache(HttpServletRequest request){
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		URLSecurityMetadataSource urlMetadata = context.getBean(URLSecurityMetadataSource.class);
		urlMetadata.refreshResuorceMap();
		MethodSecurityMetadataSource methodMetadata = context.getBean(MethodSecurityMetadataSource.class);
		methodMetadata.bindRequestMap();
		return new Message(true,"资源权限表已刷新");
	}
	
}
