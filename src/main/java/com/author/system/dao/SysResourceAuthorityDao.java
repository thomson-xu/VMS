/**
 * 
 */
package com.author.system.dao;

import javax.persistence.Query;

import com.author.system.bean.SysAuthoritiesResources;
import com.author.system.bean.SysResources;
import com.visa.dao.util.BaseJpaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SysResourceAuthorityDao extends BaseJpaDao {

	protected Log logger = LogFactory.getLog(getClass());
	
	public void deleteByResourceId(String resourceId){
		String sql = "DELETE FROM SysAuthoritiesResources x WHERE x.resourceId = :resourceId";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("resourceId", resourceId);
		int deleted = query.executeUpdate();
		
		logger.info("共删除了"+deleted+"行（资源权限表）");
	}
	public List<SysAuthoritiesResources> findBySysResources(SysResources resource){
		return null;
	}
	

}
