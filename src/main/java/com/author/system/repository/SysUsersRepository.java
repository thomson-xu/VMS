/**
 * 
 */
package com.author.system.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;

import com.author.system.bean.SysUsers;

/**
 * 类功能说明：用户管理Dao层
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-13 上午9:33:00
 * @version v1.0
 *
 */
public interface SysUsersRepository extends JpaRepository<SysUsers, String> {
	
	public Page<SysUsers> findByUsernameLike(String username,Pageable pageable);
	
	@Query("select x from SysUsers x where VQzjgid = ?")
	public Page<SysUsers> findByVQzjgid(String VQzjgid,Pageable pageable);
	
	public SysUsers getByUsername(String username);
	
	public Collection<GrantedAuthority> loadUserAuthorities(String username);
	
}
