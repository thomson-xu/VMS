package com.author.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.author.base.controller.BaseController;
import com.author.base.session.UserSessionContext;
import com.author.common.web.controller.ControllerTools;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-24 上午9:29:00
 * @version v1.0
 *
 */
@Controller
@RequestMapping("system/role")
public class SystemRoleController extends BaseController{
	
	@Autowired
	private SecurityModuleService securityModuleService;
	
	@Autowired
	private SecurityRoleService securityRoleService;
	
	@Autowired
	private UserSessionContext userSessionContext;
	
	@ResponseBody
	@RequestMapping("/module/node/{node}")
	public Message queryNodeByParent(@PathVariable("node") String node) throws Exception{
		if(StringUtils.isEmpty(node))node = "root";
		return this.securityModuleService.queryByParentAndIssys(node);
	}
	
	@ResponseBody
	@RequestMapping("/query/userId")
	public Message queryByUserId(String userId) throws Exception{
		return this.securityRoleService.findByUserId(userId);
	}
	
	@ResponseBody
	@RequestMapping("/save/ma") //ma ->  module and authority
	public Message saveModuleAndAuthority(String roleId,String sysModules,String[] sysAuthorities)throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Map<?,?> map = mapper.readValue(sysModules,Map.class);
		
		return this.securityRoleService.saveModuleAndAuthority(roleId, map, sysAuthorities);
	}
	/**
	 * 根据用户Id查找角色
	 * @nopage
	 * @enabled
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("query/userId/enabled")
	public Message queryEnabledByUserid() throws Exception{
		
		return this.securityRoleService.findByUserId(true,this.userSessionContext.getUserId());
	}
}
