/**
 * 
 */
package com.author.system.view;

import com.author.base.controller.BaseController;
import com.author.system.bean.SysAuthorities;
import com.author.system.service.SecurityAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

/**
 * 类功能说明：权限管理控制器
 *
 *
 */
@Named
@Scope("request")
public class SecurityAuthority extends BaseController {
	
	@Autowired
	private SecurityAuthorityService securityAuthorityService;

	public void add( SysAuthorities bean) throws Exception{
		
		this.securityAuthorityService.add(bean);
	}
	

	public void update( SysAuthorities bean) throws Exception{
		
		securityAuthorityService.update(bean);
	}


	public void queryEnabled() throws Exception{
		
		securityAuthorityService.queryEnabled();
	}
	

	public void enable(String id,boolean enable) throws Exception{
		
		securityAuthorityService.enable(id, enable);
	}
	

	public void restQueryByModuleId(String moduleId) throws Exception{
		
		securityAuthorityService.queryByModuleId(moduleId);
	}
	

	public void queryByModuleId(String moduleId) throws Exception{
		
		securityAuthorityService.queryByModuleId(moduleId);
	}
}
