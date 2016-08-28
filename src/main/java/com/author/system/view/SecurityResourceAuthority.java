package com.author.system.view;

import com.author.system.service.SecurityResourceAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;


@Named
@Scope("request")
public class SecurityResourceAuthority {
	
	@Autowired
	private SecurityResourceAuthorityService securityResourceAuthorityService;
	

	public void queryByResource(String resourceId){
		
		securityResourceAuthorityService.queryByResource(resourceId);
	}
	

	public SecurityResourceAuthority(String resourceId,
									String[] sysAuthorities){
		System.out.println(sysAuthorities);
		securityResourceAuthorityService.addBatch(resourceId,sysAuthorities);
	}
}
