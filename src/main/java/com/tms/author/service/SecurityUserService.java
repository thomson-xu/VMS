
package com.tms.author.service;

import com.tms.author.bean.SysRoles;
import com.tms.author.bean.SysUser;
import com.tms.author.bean.SysUsersRoles;
import com.tms.author.dao.SysUsersDao;
import com.tms.author.dao.SysUsersRolesDao;
import com.tms.base.common.web.MD5Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SecurityUserService {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private SysUsersDao sysUsersDao;
	
	@Autowired
	private SysUsersRolesDao sysUsersRolesDao;

	@Autowired
	private SysUserService sysUserService;

	private final String USER_EXIST = "UserDetails.AlreadyExists";

	/* (non-Javadoc)
	 * @see SecurityUserService#add(SysUser)
	 */

	public String add(SysUser user) throws Exception {
		String username = user.getUsername();
		
		boolean exist = sysUserService.checkRepeat(username);
		if(exist)return USER_EXIST;
		
		String password = MD5Util.getEncryptedPwd(user.getPassword()) ;
		user.setPassword(password);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		
		this.sysUsersDao.create(user);
		
		return null;
	}

	/* (non-Javadoc)
	 * @see SecurityUserService#update(SysUser)
	 */

	public String update(SysUser user) {
		//this.sysUsersRepository.save(user);
		String userId = user.getUserId();
		SysUser bean = this.sysUsersDao.find(SysUser.class,userId);
		bean.setEnabled(user.isEnabled());
		bean.setUsername(user.getUsername());
		bean.setName(user.getName());
		this.sysUsersDao.update(bean);
		String msg="用户信息更新成功";
		return msg;
	}
	/*
	 * (non-Javadoc)
	 * @see SecurityUserService#modifyPassword(java.lang.String, java.lang.String)
	 */
	public String modifyPassword(String userId,String oldPassword,String password)throws Exception{
		SysUser user = this.sysUsersDao.find(SysUser.class,userId);
		String encodeOld = MD5Util.getEncryptedPwd(oldPassword) ;
		String msg=null;
		if(user.getPassword().equals(encodeOld)){
			String encode =  MD5Util.getEncryptedPwd(password);
			user.setPassword(encode);
			msg="密码修改成功";
		}else{

			msg="密码输入错误";
		}
		return msg;
	}

	/* (non-Javadoc)
	 * @see SecurityUserService#delete(java.lang.String)
	 */

	public void delete(String userId) {
		this.sysUsersDao.delete(SysUser.class,userId);
		return ;
	}

	/* (non-Javadoc)
	 * @see SecurityUserService#query(java.lang.String, Parameters)
	 */

/*	public Message query(String username, Parameters params) {
		Page<SysUser> users = null;
		PageRequest pageable = new PageRequest(params.getSpringDataPage(), params.getLimit());
		if(StringUtils.isEmpty(username)){
			users = this.sysUsersDao.findAll(pageable);
		}else{
			username = "%"+username+"%";
			users = this.sysUsersDao.findByUsernameLike(username, pageable);
		}
		return this.messageFactory.query(users);
	}*/

	/* (non-Javadoc)
	 * @see SecurityUserService#findRoleByUserId(java.lang.String)
	 */

	public List<SysUsersRoles> findRolesByUserId(String userId) {
		List<SysUsersRoles> list = this.sysUsersRolesDao.findRoleByUserId(userId);
		

		
		return list;
	}

	public List<SysUsersRoles> findRolesByUserName(String username) {
		List<SysUsersRoles> list = this.sysUsersRolesDao.findRoleByUserName(username);



		return list;
	}

	/* (non-Javadoc)
	 * @see SecurityUserService#assginRoles(java.lang.String, java.lang.String[])
	 */

	public String assignRoles(String userId, String[] roles) {
		this.sysUsersRolesDao.deleteByUserId(userId);
		
		if(!ObjectUtils.isEmpty(roles)){
			List<SysUsersRoles> list = new ArrayList<SysUsersRoles>();
			for(int i=0;i<roles.length;i++){
				String roleId = roles[i];
				SysUsersRoles entity = new SysUsersRoles();
				entity.setSysUser(new SysUser(userId));
				entity.setSysRoles(new SysRoles(roleId));
				list.add(entity);
			}
			this.sysUsersRolesDao.saveAll(list);
		}
		
		return "successful";
	}

	/* (non-Javadoc)
	 * @see SecurityUserService#queryByJgid(java.lang.String)
	 */
/*	@Override
	public Message queryByJgid(String jgid,Parameters params) {
		PageRequest pageable = new PageRequest(params.getSpringDataPage(), params.getLimit());
		Page<SysUser> users = this.sysUsersDao.findByVQzjgid(jgid, pageable);
		return this.messageFactory.query(users);
	}*/

	public Collection<GrantedAuthority> loadUserAuthorities(String username){
		return sysUsersDao.loadUserAuthorities(username);
	}
}
