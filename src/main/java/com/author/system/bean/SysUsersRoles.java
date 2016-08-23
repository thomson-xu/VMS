package com.author.system.bean;

import com.visa.dao.util.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * SysUsersRoles entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_USERS_ROLES")
public class SysUsersRoles extends BaseEntity implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String roleId;
	private String czybh;
	private SysUsers sysUsers;
	private SysRoles sysRoles;

	// Constructors

	/** default constructor */
	public SysUsersRoles() {
	}

	/** full constructor */
	public SysUsersRoles(String id, SysUsers sysUsers, SysRoles sysRoles) {
		this.id = id;
		this.sysUsers = sysUsers;
		this.sysRoles = sysRoles;
	}

	// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "USER_ID", nullable = false,insertable=false,updatable=false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "ROLE_ID", nullable = false,insertable=false,updatable=false)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(name = "CZYBH")
	public String getCzybh() {
		return czybh;
	}

	public void setCzybh(String czybh) {
		this.czybh = czybh;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public SysUsers getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public SysRoles getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}

	@Override
	public Object getPrimaryKey() {
		return (Object)getId();
	}
}