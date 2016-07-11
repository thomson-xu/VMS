package com.visa.bean;


import com.visa.entity.EmployeeEntity;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.Date;

@Named
@Scope("request")
public class EmployeeBean {
	private Integer id;
	private String name;
	private Integer age;
	private String address;
	private Date registerTime;
	
	public EmployeeBean(){}
	
	public EmployeeBean(EmployeeEntity employee){
		this.id = employee.getId();
		this.name = employee.getName();
		this.age = employee.getAge();
		this.address = employee.getAddress();
		this.registerTime = employee.getRegisterTime();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * VO --> PO
	 * @return
	 */
	public EmployeeEntity getPO(){
		EmployeeEntity employee = new EmployeeEntity();
		
		employee.setId(id);
		employee.setName(name);
		employee.setAge(age);
		employee.setAddress(address);
		employee.setRegisterTime(registerTime);
		
		return employee;
	}
}
