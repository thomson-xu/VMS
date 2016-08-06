/**
 * 
 */
package com.author.system.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.author.system.bean.SysModules;
import com.author.system.bean.SysRolesModules;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-9 上午11:48:11
 * @version v1.0
 *
 */
public interface SysRolesModulesRepository  extends JpaRepository<SysRolesModules, String>{
	
	
	public List<SysRolesModules> findByRoleId(String roleId);
	
	public void deleteByRoleIdAndModuleId(String roleId,Collection<String> moduleId);
	
	public List<SysModules> getModulesByRoleId(String roleId);
	
}
