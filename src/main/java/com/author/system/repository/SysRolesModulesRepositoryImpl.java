/**
 * 
 */
package com.author.system.repository;

import java.util.Collection;
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
 * @date 2014-1-9 下午1:58:30
 * @version v1.0
 *
 */
@SuppressWarnings("unchecked")
public class SysRolesModulesRepositoryImpl {

	protected Log logger = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void deleteByRoleIdAndModuleId(String roleId,Collection<String> moduleId){
		String sql = "delete from SysRolesModules where roleId = ?1 and moduleId in ?2";
		
		Query query = this.entityManager.createQuery(sql);
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
		
		Query query = this.entityManager.createNativeQuery(sql, SysModules.class);
		query.setParameter(1, roleId);
		List<SysModules> list = query.getResultList();
		
		return list;
	}
	
}
