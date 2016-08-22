package com.author.system.service;

import com.author.system.bean.SysUser;
import com.author.system.bean.SysUsers;
import com.author.system.dao.SysUsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService{
	
	@Autowired
	private SysUsersDao sysUsersDao;
	
	public SysUsers doLogin(String username,String password) throws Exception{
		Map<String, Object> params = new HashMap<String,Object>();
		String sql="Select u from SysUsers u where u.username=:username and u.password=:password";
		params.put("username", username);
		params.put("password", password);
		javax.persistence.Query query = sysUsersDao.getEntityManager().createQuery(sql);
		List<SysUsers> list= query.getResultList();
		if(list != null){
			if(list.size()>0){
				SysUsers user = list.get(0);
				return user;
			}
		}
		return null;
	}
	
	@Transactional
	public String initPassword(String userId, String password) {
		//boolean flag = wsjdUserDao.initPassword(userId, password);
		boolean flag = true;
		try {
			SysUsers user = (SysUsers) sysUsersDao.find(SysUsers.class, userId);
			user.setPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		
		String message = flag == false ? "Initnazation failed" : "set up successfully，notice user please";

		return message;
	}
	
	@Transactional
	public SysUsers updatePassword(String userId, String oldPassword,
			String newPassword) {

		//Integer flag = wsjdUserDao.updatePassword(userId, oldPassword, newPassword);
		//boolean flag = true;
		try {
			SysUsers user = (SysUsers) sysUsersDao.find(SysUsers.class, userId);
			user.setPassword(newPassword);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String message = flag ? "密码错误或是信息不存在" : "修改成功";
		//msg.setMessage(message);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.LoginService#updateLoginTime(com.author.system.bean.SysUser)
	 */

	@Transactional
	public SysUsers updateLoginTime(SysUsers entity) throws Exception {
		// TODO Auto-generated method stub
		entity.setLastLogin(new Timestamp(System.currentTimeMillis()));
		this.sysUsersDao.update(entity);
		return entity;
	}

}
