/**
 * 
 */
package com.tms.author.service;

import com.tms.base.common.web.MD5Util;
import com.tms.author.bean.SysUser;
import com.tms.author.dao.SysUsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SysUserService {
	
	@Autowired
	private SysUsersDao baseDao;
	
	/* (non-Javadoc)
	 * @see com.author.system.service.UserService#add(SysUser)
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public synchronized String add(SysUser user) throws Exception {
		String username = user.getUsername();
		boolean repeat = this.checkRepeat(username);
		
		System.out.println("检查用户是否重复，结果："+repeat);
		String message=null;
		if(repeat){
			message="用户已经存在";
		}else{
			user.setPassword(MD5Util.getEncryptedPwd(user.getPassword()));
			this.baseDao.create(user);

		}
		
		return message;
	}

	public SysUser findBySso(String ssoId){
		return  null;
	}
	/* (non-Javadoc)
	 * @see com.author.system.service.UserService#remove(SysUser)
	 */

	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void delete(SysUser user) {
		// TODO Auto-generated method stub
		return ;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.UserService#remove(java.lang.String)
	 */

	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public String delete(String userId) throws Exception {
		this.baseDao.delete(SysUser.class, userId);
		String message = "删除成功";
		return message;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.UserService#update(SysUser)
	 */

	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void update(SysUser user) {
		// TODO Auto-generated method stub
		return ;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.UserService#resetPassword(SysUser)
	 */

	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void resetPassword(SysUser user) {
		// TODO Auto-generated method stub
		return ;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.UserService#resetPassword(java.lang.String)
	 */

	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public void resetPassword(String userId) {
		// TODO Auto-generated method stub
		return ;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.UserService#checkReport(java.lang.String)
	 */

	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public boolean checkRepeat(String username) throws Exception {
		String[] params=null;
		params[0]=username;
		return  baseDao.isExistedByWhere(SysUser.class, "o.username=?",params);
	}

}
