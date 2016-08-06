/**
 * 
 */
package com.author.system.service;

import javax.servlet.http.HttpServletRequest;

import com.author.base.model.Message;
import com.author.base.model.Parameters;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-3 下午4:36:49
 * @version v1.0
 *
 */
@SuppressWarnings("rawtypes")
public interface DefaultService {
	public Message getEntity(String className,String id) throws Exception;
	public Message savaEntity(HttpServletRequest request,Parameters params) throws Exception;
	public Message savaEntity(Object obj) throws Exception;
	public Message updateEntity(HttpServletRequest request,Parameters params) throws Exception;
	public Message updateEntityEx(HttpServletRequest request,Parameters params) throws Exception;
	public Message deleteEntity(String className,String id) throws Exception;
	public Message query(HttpServletRequest request,Parameters params) throws Exception;
	public Message fuzzyQueryAll(HttpServletRequest request, Parameters params)
			throws Exception ;
	public Message getDepTreeNode(HttpServletRequest request,String id) 
			throws Exception;
	
	public Message getEntity(Class clz, String id) throws Exception;
	public Message queryAll(HttpServletRequest request, Parameters params)
			throws Exception;
	
}
