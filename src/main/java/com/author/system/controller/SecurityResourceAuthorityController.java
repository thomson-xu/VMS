package com.author.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.author.base.model.Message;
import com.author.system.service.SecurityResourceAuthorityService;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-31 下午2:05:36
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/security/resau")
public class SecurityResourceAuthorityController {
	
	@Autowired
	private SecurityResourceAuthorityService securityResourceAuthorityService;
	
	@RequestMapping("/query/resource")
	@ResponseBody
	public Message queryByResource(@RequestParam("resourceId")String resourceId){
		
		return this.securityResourceAuthorityService.queryByResource(resourceId);
	}
	
	@RequestMapping("/add/batch")
	@ResponseBody
	public Message addBatch(@RequestParam("resourceId")String resourceId,
			@RequestParam("sysAuthorities")String[] sysAuthorities){
		System.out.println(sysAuthorities);
		return this.securityResourceAuthorityService.addBatch(resourceId,sysAuthorities);
	}
}
