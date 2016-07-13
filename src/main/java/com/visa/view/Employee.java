package com.visa.view;

import com.visa.entity.EmployeeEntity;
import com.visa.service.EmployeeService;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import java.util.Date;
import java.util.Map;

@Named("emplview")
@Scope("request")
public class Employee {


	private UIData table;

	@Resource
	private EmployeeService service;

	private EmployeeEntity employee;
	public Employee() {
		service = new EmployeeService();
		employee = new EmployeeEntity();
	}

	/**
	 *
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
		this.service.delete(Long.valueOf((Integer)employee.getId()));
		return "removed";
	}

	public UIData getTable() {
		return table;
	}

	public void setTable(UIData table) {
		this.table = table;
	}

	public EmployeeEntity getEmployee() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map requestParams = fc.getExternalContext().getRequestParameterMap();

		if(requestParams.containsKey("emplId")){
			String id = (String) requestParams.get("emplId");
			return service.findEmployee(new Integer(id));
		}
		else {
			employee = new EmployeeEntity();
		}
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

}
