
package com.tms.author.dao;

import com.tms.author.bean.SysUsersRoles;
import com.tms.base.dao.util.BaseJpaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

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
	public List<SysUsersRoles> findRoleByUserId(String userId){
		String sql = "SELECT x.* from SysUsersRoles x where x.userId = ?1";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter(1, userId);
		return query.getResultList();
	}
	public List<SysUsersRoles> findRoleByUserName(String username){
		String sql = "SELECT x.* from SysUsersRoles x where x.SysUsers.userName = ?1";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter(1, username);
		return query.getResultList();
	}
}
