/**
 * 
 */
package com.author.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.author.system.bean.SysModules;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-15 下午5:26:04
 * @version v1.0
 *
 */
public interface SysModulesRepository extends JpaRepository<SysModules, String>{
	
	public List<SysModules> getModulesByUserId(String userId,String parentId);
	
	@Query("select x from SysModules x where x.parent = ?1 and x.enable = true and x.issys = false order by x.priority")
	public List<SysModules> findByParentAndIssys(String parent); 
}
