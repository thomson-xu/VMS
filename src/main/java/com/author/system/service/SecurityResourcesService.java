/**
 * 
 */
package com.author.system.service;

import org.springframework.stereotype.Service;

import com.author.base.model.Message;
import com.author.base.model.Parameters;
import com.author.system.bean.SysResources;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-30 下午3:07:45
 * @version v1.0
 *
 */
@Service
public interface SecurityResourcesService {
	
	public Message add(SysResources resource,String moduleId) throws Exception;
	
	public Message update(SysResources resource,String moduleId) throws Exception;
	
	public Message delete (String resourceId) throws Exception;
	
	public Message query(String resourceName,Parameters params) throws Exception;
}
