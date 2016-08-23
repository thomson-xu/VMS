/**
 * 
 */
package com.author.system.security;

import com.author.system.bean.SysUsers;
import com.author.system.service.SecurityUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 类功能说明：根据用户名获取用户信息及权限信息
 *
 *
 */
public class DefaultUserDetailsService implements UserDetailsService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private SecurityUserService securityUserService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserCache userCache;
	
	private boolean useCache = false;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		SysUsers user = null;
		if(this.useCache){
			user = (SysUsers) this.userCache.getUserFromCache(username);
		}
		if(user == null){
			user = this.securityUserService.findRolesByUserId(username);
			if(user == null)
				throw new UsernameNotFoundException(this.messageSource.getMessage(
						"UserDetailsService.userNotFount", new Object[]{username}, null));
			//得到用户的权限
			auths = this.sysUsersDao.loadUserAuthorities( username );
			
			user.setAuthorities(auths);
		}
		
		logger.info("*********************"+username+"***********************");
		logger.info(user.getAuthorities());
		logger.info("********************************************************");
		
		this.userCache.putUserInCache(user);
		
		return user;
	}

	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}
	
}
