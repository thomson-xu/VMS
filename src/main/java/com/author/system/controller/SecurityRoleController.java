/**
 * 
 */
package com.author.system.controller;

import com.author.base.controller.BaseController;
import com.author.base.model.Parameters;
import com.author.system.bean.SysRoles;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 类功能说明：权限管理
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-3 上午9:51:40
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/security/role")
public class SecurityRoleController extends BaseController{
	
	@Autowired
	private SecurityRoleService sysRoleService;
	
	@ResponseBody
	@RequestMapping("/getbyid")
	public Message getById(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		return this.sysRoleService.getById(roleId);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public Message getById(HttpServletRequest request,@PathVariable("roleId")String roleId){
		return this.sysRoleService.getById(roleId);
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public Message add(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysRoles entity){
		
		return this.sysRoleService.add(entity);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Message update(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute SysRoles entity){
		
		return this.sysRoleService.update(entity);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Message delete(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("roleId") SysRoles roleId){
		
		return this.sysRoleService.delete(roleId);
	}
	
	@ResponseBody
	@RequestMapping("/query")
	public Message query(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute Parameters params,String roleName) throws Exception{
		
		return this.sysRoleService.findByRoleNameLike(roleName, params);
	}
	
	@ResponseBody
	@RequestMapping("/query/all/enabled")
	public Message queryAllEnabled(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute Parameters params,String roleName) throws Exception{
		
		return this.sysRoleService.findByRoleNameLikeAndEnableed(roleName, params);
	}
	
	@ResponseBody
	@RequestMapping("/query/enabled/nonepage")
	public Message queryAllEnabled() throws Exception{
		
		return this.sysRoleService.queryAllEnabled();
	}
	
	@ResponseBody
	@RequestMapping("/save/modules")
	public Message saveRoleModules(HttpServletRequest request,String roleId,
			String[] add,String[] remove){
		/*System.out.println(roleId);
		DebugUtil.printArray(add);
		DebugUtil.printArray(remove);*/
		if(add == null && remove == null){
			return new Message(true,"");
		}
		
		Message message = this.sysRoleService.saveRoleModules(roleId, add, remove);
		
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/save/authorities")
	public Message saveRoleAuthorities(HttpServletRequest request,String roleId,
			String[] sysAuthorities){
		
		if(roleId == null)
			throw new NullPointerException("角色ID不能为空");
		
		Message message = this.sysRoleService.saveRoleAuthorities(roleId, sysAuthorities);
		
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/save/ma") //ma ->  module and authority
	public Message saveModuleAndAuthority(String roleId,String sysModules,String[] sysAuthorities)throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Map<?,?> map = mapper.readValue(sysModules,Map.class);
		
		return this.sysRoleService.saveModuleAndAuthority(roleId, map, sysAuthorities);
	}
	
	@ResponseBody
	@RequestMapping("/get/moduleId")
	public Message getModulesByRole(HttpServletRequest request,String roleId)throws Exception{
		if(roleId == null)
			throw new NullPointerException("角色ID不能为空");
		
		Message msg = this.sysRoleService.getModulesByRoleId(roleId);
		
		return  msg;
	}
	
	@ResponseBody
	@RequestMapping("/get/leafmodule")
	public Message getLeafModulesByRoleId(String roleId){
		if(roleId == null)
			throw new NullPointerException("角色ID不能为空");
		
		return this.sysRoleService.getLeafModulesByRoleId(roleId);
	}
	
	@ResponseBody
	@RequestMapping("/getbyroleid/authority")
	public Message getAuthorityByRoleId(String roleId){
		if(roleId == null)
			throw new NullPointerException("角色ID不能为空");
		
		return this.sysRoleService.getAuthorityByRoleId(roleId);
		
	}
	
	@ResponseBody
	@RequestMapping("/query/authorities")
	public Message queryAuthoritiesByRoleId(String roleId){
		
		return this.sysRoleService.queryByRoleId(roleId);
	}
	
	@ResponseBody
	@RequestMapping("/auth/issys")
	public Message findByIssys(){
		return this.sysRoleService.findByIssys();
		
	}
	
}