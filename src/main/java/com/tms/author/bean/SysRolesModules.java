package com.tms.author.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tms.base.dao.util.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * SysRolesMoudles entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_ROLES_MODULES")
public class SysRolesModules extends BaseEntity implements java.io.Serializable {

	// Fields

	private String id;
	private String roleId;
	private String moduleId;
	@JsonIgnore
	private SysRoles sysRoles;
	@JsonIgnore
	private SysModules sysModules;

	// Constructors

	/** default constructor */
	public SysRolesModules() {
	}

	/** full constructor */
	public SysRolesModules(String id, SysRoles sysRoles, SysModules sysModules) {
		this.id = id;
		this.sysRoles = sysRoles;
		this.sysModules = sysModules;
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
	
	@Column(name = "ROLE_ID" ,insertable = false,updatable = false)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "MODULE_ID" ,insertable = false,updatable = false)
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public SysRoles getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(SysRoles sysRoles) {
		this.sysRoles = sysRoles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", nullable = false)
	public SysModules getSysModules() {
		return this.sysModules;
	}

	public void setSysModules(SysModules sysModules) {
		this.sysModules = sysModules;
	}

	@Override
	public Object getPrimaryKey() {
		return (Object) getId();
	}
}