/**
 * 
 */
package com.tms.author.dao;

import com.tms.author.bean.SysAuthorities;
import com.tms.base.dao.util.BaseJpaDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class SysUsersDao extends BaseJpaDao {
	
	protected Log logger = LogFactory.getLog(getClass());

	
	/**
	 * 根据用户名获取到用户的权限并封装成GrantedAuthority集合
	 * @param username
	 */
	public Collection<GrantedAuthority> loadUserAuthorities(String username){
		List<SysAuthorities> list = this.getSysAuthoritiesByUsername(username);
		
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		for (SysAuthorities authority : list) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthorityMark());
			auths.add(grantedAuthority);
		}

		return auths;
		
	}
	/**
	 * 先根据用户名获取到SysAuthorities集合
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SysAuthorities> getSysAuthoritiesByUsername(String username){
		String sql = "SELECT * FROM SYS_AUTHORITIES WHERE AUTHORITY_ID IN( "+
				"SELECT DISTINCT AUTHORITY_ID FROM SYS_ROLES_AUTHORITIES  S1 "+
				"JOIN SYS_USERS_ROLES S2 ON S1.ROLE_ID = S2.ROLE_ID "+
				"JOIN SYS_USERS S3 ON S3.USER_ID = S2.USER_ID AND S3.USERNAME=?1)";
		
		Query query = getEntityManager().createNativeQuery(sql, SysAuthorities.class);
		query.setParameter(1, username);
		
		List<SysAuthorities> list = query.getResultList();
		return list;
	}
}
