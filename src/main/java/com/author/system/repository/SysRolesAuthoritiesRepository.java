/**
 * 
 */
package com.author.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.author.system.bean.SysAuthorities;
import com.author.system.bean.SysRolesAuthorities;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-10 上午10:10:54
 * @version v1.0
 *
 */
public interface SysRolesAuthoritiesRepository extends JpaRepository<SysRolesAuthorities, String>{
	
	/**
	 * 根据roleId获取到可以分配的权限列表
	 * roleId -> modules -> authorities
	 * @param roleId
	 * @return
	 */
	public List<SysAuthorities> getAssignationAuthority(String roleId);
	
	public List<SysAuthorities> findByIssys(boolean issys);
	
	public List<SysRolesAuthorities> findByRoleId(String roleId);
	
	public List<SysAuthorities> findByEnabled();
	
	public void deleteByRoleId(String roleId);

}
