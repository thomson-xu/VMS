package com.visa.view;

import com.visa.bean.EmployeeBean;
import com.visa.service.EmployeeService;

import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.Date;
import java.util.Map;

/**
 * 员工管理BackingBean
 * 
 * @author qiujy
 * @version 1.0
 */
public class Employee {
	private EmployeeBean employee;

	/** 为DataTable显示行号而设置的 */
	private UIData table;

	private EmployeeService service;

	public Employee() {
		service = new EmployeeService();
		
		//如果请求参数中有emplId,就根据这个ID获取该员工实例,否则创建一个新实例
		FacesContext fc = FacesContext.getCurrentInstance();
		Map requestParams = fc.getExternalContext().getRequestParameterMap();
		String id = (String) requestParams.get("emplId");
		if (id != null) {
			employee = service.findEmployee(new Integer(id));
		} else {
			employee = new EmployeeBean();
		}
	}

	/**
	 * 获得所有员工列表
	 * 
	 * @return
	 */
	public DataModel getAllEmployees() {
		return new ListDataModel(this.service.findAllEmployees());
	}

	public String addAction() {
		employee.setRegisterTime(new Date());
		this.service.add(employee);
		return "persisted";
	}

	public String updateAction() {
		this.service.update(employee);
		return "updated";
	}

	public String deleteAction() {
		this.service.delete(employee);
		return "removed";
	}

	public EmployeeBean getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}

	public UIData getTable() {
		return table;
	}

	public void setTable(UIData table) {
		this.table = table;
	}

}
