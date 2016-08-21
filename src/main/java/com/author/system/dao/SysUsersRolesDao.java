
package com.author.system.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.visa.dao.util.BaseJpaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SysUsersRolesDao extends BaseJpaDao {

	protected Log logger = LogFactory.getLog(getClass());
	
	public void deleteByUserId(String userId){
		String sql = "delete from SysUsersRoles x where x.userId = ?1";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter(1, userId);
		
		int deleted = query.executeUpdate();
		
		logger.info("共删除了"+deleted+"行（用户权限表）");
	}
	
	public void deleteByUserId(String userId,String czybh){
		String sql = "delete from SysUsersRoles x where x.userId = ?1 and x.czybh = ?2";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter(1, userId);
		query.setParameter(2, czybh);
		
		int deleted = query.executeUpdate();
		
		logger.info("共删除了"+deleted+"行（用户权限表）");
	}
}
