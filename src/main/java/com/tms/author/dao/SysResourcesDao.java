/**
 * 
 */
package com.tms.author.dao;

import com.tms.author.bean.SysResources;
import com.tms.base.dao.util.BaseJpaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SysResourcesDao extends BaseJpaDao {
	
	protected Log logger = LogFactory.getLog(getClass());

	/* (non-Javadoc)
	 * @see SysResourcesDao#findById(java.lang.String)
	 */

	public SysResources findById(String resourceId) throws Exception {
		
		return getEntityManager().find(SysResources.class, resourceId);
	}
	
	/* (non-Javadoc)
	 * @see SysResourcesDao#add(SysResources)
	 */

	public SysResources add(SysResources resource) throws Exception {
		getEntityManager().persist(resource);
		return resource;
	}

	/* (non-Javadoc)
	 * @see SysResourcesDao#update(SysResources)
	 */

	public SysResources update(SysResources resource) throws Exception {
		getEntityManager().merge(resource);
		return resource;
	}

	/* (non-Javadoc)
	 * @see SysResourcesDao#delete(SysResources)
	 */

	public void delete(SysResources resource) throws Exception {
		getEntityManager().remove(resource);
	}

	/* (non-Javadoc)
	 * @see SysResourcesDao#delete(java.lang.String)
	 */

	public void delete(String resourceId) throws Exception {
		getEntityManager().remove(this.findById(resourceId));
	}


}
