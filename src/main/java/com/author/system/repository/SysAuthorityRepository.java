/**
 * 
 */
package com.author.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.author.system.bean.SysAuthorities;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-31 下午12:03:26
 * @version v1.0
 *
 */
public interface SysAuthorityRepository extends JpaRepository<SysAuthorities, String> {
	
	@Query("select x from SysAuthorities x where moduleId = ?1 or moduleId is null")
	public List<SysAuthorities> findByModuleId(String moduleId);
	
	public List<SysAuthorities> findByModuleIdIsNull();
	
/*	@Query("select x from SysAuthorities x order by x.moduleId")
	public Page<SysAuthorities> findAll(Pageable pageable);*/
	
}
