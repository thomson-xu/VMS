package com.visa.service;

import com.visa.bean.EmployeeBean;
import com.visa.dao.EmployeeDao;
import com.visa.entity.EmployeeEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



/**
 * Ա��ҵ���߼���
 * 
 * @author qiujy
 * @version 1.0
 */
public class EmployeeService {
	
	private EmployeeDao employeeDao;

	public EmployeeService() {
		try {
			Properties props = new Properties();
			// ����Jboss��������JNDI����������
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			// ����Jboss�������ṩ���������URL
			props.setProperty(Context.PROVIDER_URL, "localhost:1099");
			InitialContext ctx = new InitialContext(props);

			// looking up SessionBean
			employeeDao = (EmployeeDao) ctx.lookup("EmployeeDaoImpl/remote");

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * ����Ա��
	 * 
	 * @param employee
	 */
	public void add(EmployeeBean employee){
		employeeDao.persist(employee.getPO());
	}

	/**
	 * ɾ��Ա��
	 * 
	 * @param employee
	 */
	public void delete(EmployeeBean employee){
		employeeDao.delete(employee.getPO());
	}

	/**
	 * ����Ա����Ϣ
	 * 
	 * @param employee
	 */
	public void update(EmployeeBean employee){
		employeeDao.update(employee.getPO());
	}

	/**
	 * �õ����е�Ա���б�
	 * 
	 * @return
	 */
	public List<EmployeeBean> findAllEmployees(){
		List<EmployeeBean> result = new ArrayList<EmployeeBean>();

		List<EmployeeEntity> list = employeeDao.findAllEmployees();
		int size = list == null ? 0 : list.size();
		for (int i = 0; i < size; i++) {
			result.add(new EmployeeBean(list.get(i)));
		}
		
		return result;
	}

	/**
	 * ����ID�õ���Ա��
	 * 
	 * @param id
	 * @return
	 */
	public EmployeeBean findEmployee(Integer id){
		EmployeeEntity empl = employeeDao.findEmployee(id);
		return (empl == null ? null : new EmployeeBean(empl));
	}

}
