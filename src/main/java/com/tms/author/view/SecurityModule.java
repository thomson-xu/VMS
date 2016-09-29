/*
package com.tms.author.view;

import com.tms.base.session.UserSessionContext;
import com.tms.author.bean.SysModules;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * 类功能说明：添加模块、修改模块、删除模块、模块查询
 *
 *//*

@Named
@Scope("request")
public class SecurityModule {
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private SecurityModuleService securityModuleService;
	
	@Autowired
	private UserSessionContext userSessionContext;
	
	@RequestMapping("/add")
	@ResponseBody
	public  void add(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SysModules module) throws Exception{
		
		return this.securityModuleService.add(module);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public void delete(HttpServletRequest request,HttpServletResponse response,@PathVariable("id")String entityId) throws Exception{
		
		return this.securityModuleService.delete(entityId);
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public void query(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("node") String node)throws Exception{
		
		return this.securityModuleService.query(node);
	}
	
	@RequestMapping("/query/{node}")
	@ResponseBody
	public void queryByRest(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("node") String node)throws Exception{
		
		return this.securityModuleService.query(node);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public void update(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SysModules module) throws Exception{
		
		return this.securityModuleService.update(module);
	}
	
	@RequestMapping("/update/payload")
	@ResponseBody
	public void updateByPayload(HttpServletRequest request,HttpServletResponse response) throws Exception{
		SysModules module = (SysModules)ControllerTools.resolvePayloadEX(request);
		return this.securityModuleService.delete(module);
	}
	
	@RequestMapping("/query/leafnode")
	@ResponseBody
	public Message queryAllLeafNode() throws Exception{
		
		return this.securityModuleService.queryAllLeafNode();
	}
	
	@RequestMapping("/querybyuserid/{parent}")
	@ResponseBody
	public Message queryModulesByUserId(HttpServletRequest request,
			@PathVariable("parent")String parent) throws Exception{
		
		return this.securityModuleService.queryModulesByUserId(userSessionContext.getUserId(), parent);
	}
	
}
*/
