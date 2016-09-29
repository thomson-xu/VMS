/**
 * 
 */
package com.tms.author.dao;

import com.tms.author.bean.SysModules;
import com.tms.base.dao.util.BaseJpaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
@Repository
public class SysModulesDao extends BaseJpaDao {

	protected Log logger = LogFactory.getLog(getClass());


	public List<SysModules> getModulesByUserId(String userId,String parentId){
		String sql = "select * from sys_modules where module_id in ( "+
			  "select s1.module_id from sys_roles_modules s1 "+
			  "join sys_users_roles s2 on s1.role_id = s2.role_id and s2.user_id = ?1) "+
			"and parent = ?2 order by priority";
		
		Query query = getEntityManager().createNativeQuery(sql, SysModules.class);
		query.setParameter(1, userId);
		query.setParameter(2, parentId);
		
		List<SysModules> list = query.getResultList();
		return list;
	}

	public List<SysModules> findByParentAndIssys(String parent){
		String sql = "select x from SysModules x where x.parent = ?1 and x.enable = true and x.issys = false order by x.priority";

		Query query = getEntityManager().createNativeQuery(sql, SysModules.class);
		query.setParameter(1, parent);

		List<SysModules> list = query.getResultList();
		return list;
	}
}
