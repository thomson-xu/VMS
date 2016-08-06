/**
 * 
 */
package com.author.system.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-14 下午1:40:05
 * @version v1.0
 *
 */
public class SysUsersRolesRepositoryImpl {

	protected Log logger = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void deleteByUserId(String userId){
		String sql = "delete from SysUsersRoles x where x.userId = ?1";
		Query query = this.entityManager.createQuery(sql);
		query.setParameter(1, userId);
		
		int deleted = query.executeUpdate();
		
		logger.info("共删除了"+deleted+"行（用户权限表）");
	}
	
	public void deleteByUserId(String userId,String czybh){
		String sql = "delete from SysUsersRoles x where x.userId = ?1 and x.czybh = ?2";
		Query query = this.entityManager.createQuery(sql);
		query.setParameter(1, userId);
		query.setParameter(2, czybh);
		
		int deleted = query.executeUpdate();
		
		logger.info("共删除了"+deleted+"行（用户权限表）");
	}
}
