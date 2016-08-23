
package com.author.system.service;

import com.author.base.MessageFactory;

import com.author.base.session.UserSessionContext;
import com.author.system.bean.SysRoles;
import com.author.system.bean.SysUsers;
import com.author.system.bean.SysUsersRoles;
import com.author.system.dao.SysUsersDao;
import com.author.system.dao.SysUsersRolesDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityUserService {
	
	protected Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private SysUsersDao sysUsersDao;
	
	@Autowired
	private SysUsersRolesDao sysUsersRolesDao;
	
	@Autowired
	private MessageFactory messageFactory;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserSessionContext userSessionContext;

	@Autowired
	private UserService userService;

	private final String USER_EXIST = "UserDetails.AlreadyExists";

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityUserService#add(com.author.system.bean.SysUsers)
	 */

	public String add(SysUsers user) throws Exception {
		String username = user.getUsername();
		
		boolean exist = userService.checkRepeat(username);
		if(exist)return USER_EXIST;
		
		String password = user.getPassword();
		password = this.passwordEncoder.encodePassword(password, user.getUsername());
		user.setPassword(password);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		
		this.sysUsersDao.create(user);
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityUserService#update(com.author.system.bean.SysUsers)
	 */

	public String update(SysUsers user) {
		//this.sysUsersRepository.save(user);
		String userId = user.getUserId();
		SysUsers bean = this.sysUsersDao.find(SysUsers.class,userId);
		bean.setEnabled(user.isEnabled());
		bean.setUsername(user.getUsername());
		bean.setName(user.getName());
		this.sysUsersDao.update(bean);
		String msg="用户信息更新成功";
		return msg;
	}
	/*
	 * (non-Javadoc)
	 * @see com.author.system.service.SecurityUserService#modifyPassword(java.lang.String, java.lang.String)
	 */
	public String modifyPassword(String userId,String oldPassword,String password){
		SysUsers user = this.sysUsersDao.find(SysUsers.class,userId);
		String encodeOld = this.passwordEncoder.encodePassword(oldPassword, user.getUsername());
		String msg=null;
		if(user.getPassword().equals(encodeOld)){
			String encode = this.passwordEncoder.encodePassword(password, user.getUsername());
			user.setPassword(encode);
			msg="密码修改成功";
		}else{

			msg="密码输入错误";
		}
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityUserService#delete(java.lang.String)
	 */

	public void delete(String userId) {
		this.sysUsersDao.delete(SysUsers.class,userId);
		return ;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityUserService#query(java.lang.String, com.author.base.model.Parameters)
	 */

/*	public Message query(String username, Parameters params) {
		Page<SysUsers> users = null;
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
	 * @see com.author.system.service.SecurityUserService#findRoleByUserId(java.lang.String)
	 */

	public List<SysUsersRoles> findRolesByUserId(String userId) {
		List<SysUsersRoles> list = this.sysUsersRolesDao.findRoleByUserId(userId);
		

		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityUserService#assginRoles(java.lang.String, java.lang.String[])
	 */

	public String assignRoles(String userId, String[] roles) {
		this.sysUsersRolesDao.deleteByUserId(userId,this.userSessionContext.getUserId());
		
		if(!ObjectUtils.isEmpty(roles)){
			List<SysUsersRoles> list = new ArrayList<SysUsersRoles>();
			for(int i=0;i<roles.length;i++){
				String roleId = roles[i];
				SysUsersRoles entity = new SysUsersRoles();
				entity.setSysUsers(new SysUsers(userId));
				entity.setSysRoles(new SysRoles(roleId));
				entity.setCzybh(this.userSessionContext.getUserId());
				list.add(entity);
			}
			this.sysUsersRolesDao.saveAll(list);
		}
		
		return "successful";
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityUserService#queryByJgid(java.lang.String)
	 */
/*	@Override
	public Message queryByJgid(String jgid,Parameters params) {
		PageRequest pageable = new PageRequest(params.getSpringDataPage(), params.getLimit());
		Page<SysUsers> users = this.sysUsersDao.findByVQzjgid(jgid, pageable);
		return this.messageFactory.query(users);
	}*/

}
