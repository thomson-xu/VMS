/**
 * 
 */
package com.author.system.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.author.system.bean.SysModules;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-15 下午5:27:04
 * @version v1.0
 *
 */
public class SysModulesRepositoryImpl {

	protected Log logger = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 根据UserId获取到分配的模块
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysModules> getModulesByUserId(String userId,String parentId){
		String sql = "select * from sys_modules where module_id in ( "+
			  "select s1.module_id from sys_roles_modules s1 "+
			  "join sys_users_roles s2 on s1.role_id = s2.role_id and s2.user_id = ?1) "+
			"and parent = ?2 order by priority";
		
		Query query = this.entityManager.createNativeQuery(sql, SysModules.class);
		query.setParameter(1, userId);
		query.setParameter(2, parentId);
		
		List<SysModules> list = query.getResultList();
		return list;
	}
}
