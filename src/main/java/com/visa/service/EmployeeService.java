package com.visa.service;

import com.visa.dao.EmployeeDao;
import com.visa.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class EmployeeService {


	@Resource
	private EmployeeDao employeeDao;

	public EmployeeService() {
		/*try {
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
		}*/
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void add(EmployeeEntity employee){
		employeeDao.create(employee);
	}


	public void delete(Integer id){
		employeeDao.delete(EmployeeEntity.class,(Object)(id));
	}

	public void update(EmployeeEntity employee){
		employeeDao.update(employee);
	}


	public List<EmployeeEntity> findAllEmployees(){
		String fields[]={"id","name","age","address","registerTime"};
		List<EmployeeEntity> listEmployees = employeeDao.queryByWhere(EmployeeEntity.class,fields,null,null);
		return ((listEmployees != null) ? listEmployees : null);

	}

	public EmployeeEntity findEmployee(Integer id){
		EmployeeEntity empl = employeeDao.find(EmployeeEntity.class,id);
		return ((empl != null) ? empl : new EmployeeEntity());
	}

}
