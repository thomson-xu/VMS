package com.author.system.service;

import com.author.system.bean.SysUser;
import com.author.system.dao.SysUsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
	
	public String doLogin(String username,String passWord) throws Exception{
		Map<String, Object> params = new HashMap<String,Object>();
		String sql="";
		params.put("username", username);
		javax.persistence.Query query = sysUsersDao.getEntityManager().createQuery(sql);

		if(list != null){
			if(list.size()>0){
				SysUser entity = list.get(0);
				return null;
			}
		}
		return null;
	}
	
	@Transactional
	public String initPassword(String userId, String password) {
		//boolean flag = wsjdUserDao.initPassword(userId, password);
		boolean flag = true;
		try {
			SysUser user = (SysUser) sysUsersDao.get(SysUser.class, userId);
			user.setVDlkl(password);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		
		String message = flag == false ? "Initnazation failed" : "set up successfully，notice user please";

		return message;
	}
	
	@Transactional
	public Message updatePassword(String userId, String oldPassword,
			String newPassword) {
		Message msg = new Message();
		//Integer flag = wsjdUserDao.updatePassword(userId, oldPassword, newPassword);
		boolean flag = true;
		try {
			SysUser user = (SysUser) baseDao.get(SysUser.class, userId);
			user.setVDlkl(newPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		//String message = flag ? "密码错误或是信息不存在" : "修改成功";
		//msg.setMessage(message);
		msg.setSuccess(true);
		msg.setFlag(flag);
		return msg;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.LoginService#updateLoginTime(com.author.system.bean.SysUser)
	 */
	@Override
	@Transactional
	public SysUser updateLoginTime(SysUser entity) throws Exception {
		// TODO Auto-generated method stub
		entity.setDtZhdl(new Timestamp(System.currentTimeMillis()));
		this.baseDao.update(entity);
		return entity;
	}

}
