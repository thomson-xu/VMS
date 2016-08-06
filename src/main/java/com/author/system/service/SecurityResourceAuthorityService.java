/**
 * 
 */
package com.author.system.service;

import com.author.base.model.Message;
import com.author.system.bean.SysResources;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-31 下午1:41:21
 * @version v1.0
 *
 */
public interface SecurityResourceAuthorityService {

	public Message queryByResource(String resourceId);
	
	public Message queryByResource(SysResources resource);

	/**
	 * @param resourceId
	 * @param sysAuthorities
	 * @return
	 */
	public Message addBatch(String resourceId, String[] sysAuthorities);
}
