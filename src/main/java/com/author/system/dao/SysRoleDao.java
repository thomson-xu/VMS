/**
 * 
 */
package com.author.system.dao;

import java.util.List;

import com.visa.dao.util.BaseJpaDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.author.system.bean.SysRoles;
import org.springframework.stereotype.Repository;

@Repository
public class SysRoleDao extends BaseJpaDao {
	
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
