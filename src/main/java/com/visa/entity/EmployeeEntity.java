package com.visa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 员工实体类
 * @author qiujy
 * @version 1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name="employees")
public class EmployeeEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer age;
	private String address;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date registerTime;

	public EmployeeEntity() {
	}

	public Integer getId() {
		return this.id;
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

}
