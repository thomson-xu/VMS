package com.visa.service;

import com.visa.bean.EmployeeBean;
import com.visa.dao.EmployeeDao;
import com.visa.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Service
public class EmployeeService {
	@Resource
	private EmployeeDao employeeDao;

	public EmployeeService() {
		try {
			Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			props.setProperty(Context.PROVIDER_URL, "localhost:1099");
			InitialContext ctx = new InitialContext(props);
			employeeDao = (EmployeeDao) ctx.lookup("EmployeeDaoImpl/remote");

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	public void add(EmployeeBean employee){
		employeeDao.create(employee.getPO());
	}


	public void delete(Long id){
		employeeDao.delete(EmployeeEntity.class,(Object)(id));
	}

	public void update(EmployeeBean employee){
		employeeDao.update(employee.getPO());
	}


	public List<EmployeeBean> findAllEmployees(){
		String fields[]={"id","name","age","address","registerTime"};
		List<EmployeeBean> result = new ArrayList<EmployeeBean>();

		List<EmployeeEntity> list = employeeDao.queryByWhere(EmployeeEntity.class,fields,null,null);
		int size = list == null ? 0 : list.size();
		for (int i = 0; i < size; i++) {
			result.add(new EmployeeBean(list.get(i)));
		}
		
		return result;
	}

	public EmployeeBean findEmployee(Integer id){
		EmployeeEntity empl = employeeDao.find(EmployeeEntity.class,id);
		return (empl == null ? null : new EmployeeBean(empl));
	}

}
