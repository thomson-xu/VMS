/**
 * 
 */
package com.tms.author.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import com.tms.base.dao.util.BaseJpaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tms.author.bean.SysModules;
import org.springframework.stereotype.Repository;

@Repository
public class SysRolesModulesDao extends BaseJpaDao {

	protected Log logger = LogFactory.getLog(getClass());

	public void deleteByRoleIdAndModuleId(String roleId,Collection<String> moduleId){
		String sql = "delete from SysRolesModules where roleId = ?1 and moduleId in ?2";
		
		Query query = getEntityManager().createQuery(sql);
		query.setParameter(1, roleId);
		query.setParameter(2, moduleId);
		
		int deleted = query.executeUpdate();
		
		logger.info("共删除了"+deleted+"行（权限模块表）");
	}
	/**
	 * 根据roleId获取对应的子模块
	 * @param roleId
	 * @return
	 */
	public List<SysModules> getModulesByRoleId(String roleId){
		String sql = "select t.* from sys_roles_modules s join sys_modules t "+
				"on s.module_id = t.module_id and s.role_id = ?1 and t.leaf = 1 order by t.priority";
		
		Query query = getEntityManager().createNativeQuery(sql, SysModules.class);
		query.setParameter(1, roleId);
		List<SysModules> list = query.getResultList();
		
		return list;
	}
	
}
