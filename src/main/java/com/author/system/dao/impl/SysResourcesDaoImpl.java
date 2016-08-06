/**
 * 
 */
package com.author.system.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.author.base.model.ResultModel;
import com.author.system.bean.SysResources;
import com.author.system.dao.SysResourcesDao;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-30 下午3:18:01
 * @version v1.0
 *
 */
public class SysResourcesDaoImpl implements SysResourcesDao {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	/* (non-Javadoc)
	 * @see com.author.system.dao.SysResourcesDao#findById(java.lang.String)
	 */
	@Override
	public SysResources findById(String resourceId) throws Exception {
		
		return this.entityManager.find(SysResources.class, resourceId);
	}
	
	/* (non-Javadoc)
	 * @see com.author.system.dao.SysResourcesDao#add(com.author.system.bean.SysResources)
	 */
	@Override
	public SysResources add(SysResources resource) throws Exception {
		this.entityManager.persist(resource);
		return resource;
	}

	/* (non-Javadoc)
	 * @see com.author.system.dao.SysResourcesDao#update(com.author.system.bean.SysResources)
	 */
	@Override
	public SysResources update(SysResources resource) throws Exception {
		this.entityManager.merge(resource);
		return resource;
	}

	/* (non-Javadoc)
	 * @see com.author.system.dao.SysResourcesDao#delete(com.author.system.bean.SysResources)
	 */
	@Override
	public void delete(SysResources resource) throws Exception {
		this.entityManager.remove(resource);
	}

	/* (non-Javadoc)
	 * @see com.author.system.dao.SysResourcesDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String resourceId) throws Exception {
		this.entityManager.remove(this.findById(resourceId));
	}

	/* (non-Javadoc)
	 * @see com.author.system.dao.SysResourcesDao#query(com.author.system.bean.SysResources)
	 */
	@Override
	public ResultModel query(SysResources resource) throws Exception {
		
		return null;
	}

}
