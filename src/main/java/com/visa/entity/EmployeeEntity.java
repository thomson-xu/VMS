package com.visa.entity;

import com.visa.dao.util.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
@Entity
@Table(name="employees")
public class EmployeeEntity extends BaseEntity implements Serializable {

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

	public EmployeeEntity(Integer id,String name,Integer age,String address,Date registerTime){
		this.id=id;
		this.name=name;
		this.age=age;
		this.address=address;
		this.registerTime=registerTime;
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

	@Override
	public Object getPrimaryKey() {
		return  (Object) getId();
	}
}
