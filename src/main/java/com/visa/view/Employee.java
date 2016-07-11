package com.visa.view;

import com.visa.bean.EmployeeBean;
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

/**
 * Ա������BackingBean
 * 
 * @author qiujy
 * @version 1.0
 */
@Named("emplview")
@Scope("request")
public class Employee {
	@Resource
	private EmployeeBean employee;

	/** ΪDataTable��ʾ�кŶ����õ� */
	private UIData table;

	@Resource
	private EmployeeService service;

	public Employee() {
		service = new EmployeeService();
		
		//��������������emplId,�͸������ID��ȡ��Ա��ʵ��,���򴴽�һ����ʵ��
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
	 * �������Ա���б�
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
