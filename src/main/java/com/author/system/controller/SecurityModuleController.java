package com.author.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.author.base.model.Message;
import com.author.base.session.UserSessionContext;
import com.author.common.web.controller.ControllerTools;
import com.author.system.bean.SysModules;
import com.author.system.service.SecurityModuleService;

/**
 * 类功能说明：添加模块、修改模块、删除模块、模块查询
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-26 上午10:19:03
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/security/module")
public class SecurityModuleController {
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private SecurityModuleService securityModuleService;
	
	@Autowired
	private UserSessionContext userSessionContext;
	
	@RequestMapping("/add")
	@ResponseBody
	public Message add(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SysModules module) throws Exception{
		
		return this.securityModuleService.add(module);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Message delete(HttpServletRequest request,HttpServletResponse response,@PathVariable("id")String entityId) throws Exception{
		
		return this.securityModuleService.delete(entityId);
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Message query(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("node") String node)throws Exception{
		
		return this.securityModuleService.query(node);
	}
	
	@RequestMapping("/query/{node}")
	@ResponseBody
	public Message queryByRest(HttpServletRequest request,HttpServletResponse response,
			@PathVariable("node") String node)throws Exception{
		
		return this.securityModuleService.query(node);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Message update(HttpServletRequest request,HttpServletResponse response,@ModelAttribute SysModules module) throws Exception{
		
		return this.securityModuleService.update(module);
	}
	
	@RequestMapping("/update/payload")
	@ResponseBody
	public Message updateByPayload(HttpServletRequest request,HttpServletResponse response) throws Exception{
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
