/**
 * 
 */
package com.author.system.view;

import com.author.base.model.Parameters;
import com.author.system.bean.SysAuthorities;
import com.author.system.bean.SysModules;
import com.author.system.bean.SysRoles;
import com.author.system.service.SecurityRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 类功能说明：权限管理
 *
 *
 */
@Named
@Scope("request")
public class SecurityRole{
	
	@Autowired
	private SecurityRoleService sysRoleService;

	public void getById(){
		sysRoleService.getById("");
		return ;
	}
	

	public void getById(String roleId){
		sysRoleService.getById(roleId);
	}
	

	public String add(SysRoles entity){
		
		sysRoleService.add(entity);
		return null;
	}

	public void update( SysRoles entity){
		
		sysRoleService.update(entity);
	}
	

	public void delete( SysRoles roleId){
		
		sysRoleService.delete(roleId);
	}
	

	public void queryAllEnabled(Parameters params,String roleName) throws Exception{
		
		sysRoleService.findByRoleNameLikeAndEnableed(roleName, params);
	}
	

	public void queryAllEnabled() throws Exception{
		
		sysRoleService.queryAllEnabled();
	}

	public void saveRoleModules(HttpServletRequest request,String roleId,
			String[] add,String[] remove){
		/*System.out.println(roleId);
		DebugUtil.printArray(add);
		DebugUtil.printArray(remove);*/
		if(add == null && remove == null){
			return ;
		}
		
		 this.sysRoleService.saveRoleModules(roleId, add, remove);
		
		return;
	}
	

	public void saveRoleAuthorities(HttpServletRequest request,String roleId,
			String[] sysAuthorities){
		
		if(roleId == null)
			throw new NullPointerException("角色ID不能为空");
		
		this.sysRoleService.saveRoleAuthorities(roleId, sysAuthorities);
		
		return;
	}
	
	//ma ->  module and authority
	public void saveModuleAndAuthority(String roleId,String sysModules,String[] sysAuthorities)throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		Map<?,?> map = mapper.readValue(sysModules,Map.class);
		
		this.sysRoleService.saveModuleAndAuthority(roleId, map, sysAuthorities);
	}
	

	public void getModulesByRole(String roleId)throws Exception{
		if(roleId == null)
			throw new NullPointerException("角色ID不能为空");
		
		this.sysRoleService.getModulesByRoleId(roleId);
		
		return  ;
	}
	

	public SysModules getLeafModulesByRoleId(String roleId){
		if(roleId == null)
			throw new NullPointerException("角色ID不能为空");
		
		return this.sysRoleService.getLeafModulesByRoleId(roleId).get(0);
	}
	

	public SysAuthorities getAuthorityByRoleId(String roleId){
		if(roleId == null)
			throw new NullPointerException("角色ID不能为空");
		
		return this.sysRoleService.getAuthorityByRoleId(roleId).get(0);
		
	}
	

	public String[] queryAuthoritiesByRoleId(String roleId){
		
		return this.sysRoleService.queryByRoleId(roleId);
	}

	
}
