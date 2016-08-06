/**
 * 
 */
package com.author.system.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.author.system.bean.SysRoles;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-2 下午5:04:03
 * @version v1.0
 *
 */
public interface SysRoleRepository extends JpaRepository<SysRoles, String> {
	
	//@Query("select x from SysRoles x where x.roleName like ?1")
	public Page<SysRoles> findByRoleNameLike(String roleName,Pageable pageable);
	
	@Query("select x from SysRoles x where x.enable = true")
	public Page<SysRoles> findAllEnabeld(Pageable pageable);
	
	@Query("select x from SysRoles x where x.enable = true")
	public List<SysRoles> findAllEnabeld();
	
	@Query("select x from SysRoles x where x.enable = true and x.issys = ?")
	public List<SysRoles> findAllEnabeld(boolean issys);
	
	@Query("select x from SysRoles x where x.enable = true and x.roleName like ?1")
	public Page<SysRoles> findByRoleNameLikeAndEnabled(String roleName,Pageable pageable);
	
	public List<SysRoles> findByUserId(String userId);
	
	@Query("select x from SysRoles x where x.enable = ? and x.userId = ?")
	public List<SysRoles> findByUserId(boolean enable,String userId);
	
}
