/**
 * ClassName: EmployeeDao.java
 * Author: qiujy
 * CreateTime: Feb 20, 2008
 * 
 * Copyright 2007-2008 ++YONG All rights reserved.
 * EMail: qjyong@gmail.com
 */
package com.visa.dao;

import com.visa.entity.EmployeeEntity;

import java.util.List;

/**
 * Ա������DAO�ӿ�
 * 
 * @author qiujy
 * @version 1.0
 */
public interface EmployeeDao {
	
	/**
	 * ����Ա��
	 * 
	 * @param employee
	 */
	public void persist(EmployeeEntity employee);

	/**
	 * ɾ��Ա��
	 * 
	 * @param employee
	 */
	public void delete(EmployeeEntity employee);

	/**
	 * ����Ա����Ϣ
	 * 
	 * @param employee
	 */
	public void update(EmployeeEntity employee);

	/**
	 * �õ����е�Ա���б�
	 * 
	 * @return
	 */
	public List<EmployeeEntity> findAllEmployees();

	/**
	 * ����ID�õ���Ա��
	 * 
	 * @param id
	 * @return
	 */
	public EmployeeEntity findEmployee(Integer id);

}
