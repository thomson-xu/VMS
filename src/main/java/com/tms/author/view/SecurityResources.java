/**
 * 
 */
package com.tms.author.view;

import com.tms.author.bean.SysResources;
import com.tms.author.security.MethodSecurityMetadataSource;
import com.tms.author.security.URLSecurityMetadataSource;
import com.tms.author.service.SecurityResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.query.Parameters;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * 类功能说明：资源控制器
 *
 */
@Named
@Scope("request")
public class SecurityResources {
	
	@Autowired
	private SecurityResourcesService securityResourcesService;
	

	public void add(SysResources resource, String moduleId) throws Exception{
		
		securityResourcesService.add(resource, moduleId);
	}
	

	public void update(SysResources resource,String moduleId) throws Exception{
		
		securityResourcesService.update(resource, moduleId);
	}
	

	public void delete(String resourceId) throws Exception{
		
		securityResourcesService.delete(resourceId);
	}
	

	public String query (Parameters params) throws Exception{
		return null;
	}

	public String refreshCache(HttpServletRequest request){
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		URLSecurityMetadataSource urlMetadata = context.getBean(URLSecurityMetadataSource.class);
		urlMetadata.refreshResuorceMap();
		MethodSecurityMetadataSource methodMetadata = context.getBean(MethodSecurityMetadataSource.class);
		methodMetadata.bindRequestMap();
		return "资源权限表已刷新";
	}
	
}
