package com.author.system.service;

import com.author.base.model.Message;
import com.author.system.bean.SysUser;

public interface LoginService {
	
	public SysUser doLogin(String VUsername) throws Exception;
	
	/**
	 * 根据操作员编号初始化密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public Message initPassword(String userId, String password);
	
	/**
	 * 根据操作员编号修改密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public Message updatePassword(String userId, String oldPassword,String newPassword);
	
	/**
	 * 更新用户登录时间
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public SysUser updateLoginTime(SysUser entity) throws Exception;
	
}
