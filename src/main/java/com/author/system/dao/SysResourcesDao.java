/**
 * 
 */
package com.author.system.dao;

import com.author.base.model.ResultModel;
import com.author.system.bean.SysResources;

/**
 * 类功能说明：资源管理
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-30 上午11:55:03
 * @version v1.0
 *
 */

public interface SysResourcesDao {
	
	public SysResources findById(String resourceId) throws Exception;
	
	public SysResources add(SysResources resource) throws Exception;
	
	public SysResources update(SysResources resource) throws Exception;
	
	public void delete(SysResources resource) throws Exception;
	
	public void delete(String resourceId) throws Exception;
	
	public ResultModel query(SysResources resource) throws Exception;

}
