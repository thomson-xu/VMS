/**
 * 
 */
package com.author.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.author.system.bean.SysUsersRoles;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-14 下午1:26:45
 * @version v1.0
 *
 */
public interface SysUsersRolesRepository extends JpaRepository<SysUsersRoles, String> {
	
	public List<SysUsersRoles> findByUserId(String userId);
	
	public void deleteByUserId(String userId);
	
	public void deleteByUserId(String userId,String czybh);
}
