package com.author.system.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.author.base.model.Message;
import com.author.system.bean.SysUser;
import com.author.system.dao.BaseDao;
import com.author.system.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private BaseDao baseDao;
	
	public SysUser doLogin(String VUsername) throws Exception{
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("VZcmc", VUsername);
		List<SysUser> list = baseDao.query(SysUser.class, params);
		if(list != null){
			if(list.size()>0){
				SysUser entity = list.get(0);
				return entity;
			}
		}
		return null;
	}
	
	@Transactional
	public Message initPassword(String userId, String password) {
		Message msg = new Message();
		//boolean flag = wsjdUserDao.initPassword(userId, password);
		boolean flag = true;
		try {
			SysUser user = (SysUser) baseDao.get(SysUser.class, userId);
			user.setVDlkl(password);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		
		String message = flag == false ? "初始化失败" : "初始化成功，请及时通知该用户";
		msg.setMessage(message);
		msg.setSuccess(true);
		msg.setFlag(flag);
		return msg;
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
		String message = flag ? "密码错误或是信息不存在" : "修改成功";
		msg.setMessage(message);
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
