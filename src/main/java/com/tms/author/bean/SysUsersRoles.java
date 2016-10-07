package com.tms.author.bean;

import com.tms.base.dao.util.BaseEntity;
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
	private SysUser sysUser;
	private SysRoles sysRoles;

	// Constructors

	/** default constructor */
	public SysUsersRoles() {
	}

	/** full constructor */
	public SysUsersRoles(String id, SysUser sysUser, SysRoles sysRoles) {
		this.id = id;
		this.sysUser = sysUser;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
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