package com.tms.author.bean;

import com.tms.base.dao.util.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * SysRoles entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_ROLES")
public class SysRoles extends BaseEntity implements java.io.Serializable {

	// Fields
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "ROLE_ID", unique = true, nullable = false, length = 100)
	private String roleId;

	@Column(name = "ROLE_NAME", length = 100)
	private String roleName;

	@Column(name = "ROLE_DESC", length = 200)
	private String roleDesc;

	@Column(name = "ENABLE", precision = 22, scale = 0)
	private Boolean enable;

	@Column(name = "ISSYS", precision = 22, scale = 0)
	private Boolean issys;

	@Column(name = "USER_ID", length = 200)
	private String userId;
	@Column(name = "MODULE_ID", length = 100)
	private String moduleId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRoles")
	private Set<SysRolesModules> sysRolesMoudleses = new HashSet<SysRolesModules>(
			0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRoles")
	private Set<SysUsersRoles> sysUsersRoleses = new HashSet<SysUsersRoles>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRoles")
	private Set<SysRolesAuthorities> sysRolesAuthoritieses = new HashSet<SysRolesAuthorities>(
			0);

	// Constructors

	/** default constructor */
	public SysRoles() {
	}

	/** minimal constructor */
	public SysRoles(String roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public SysRoles(String roleId, String roleName, String roleDesc,
			Boolean enable, Boolean issys, String moduleId,
			Set<SysRolesModules> sysRolesMoudleses,
			Set<SysUsersRoles> sysUsersRoleses,
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.enable = enable;
		this.issys = issys;
		this.moduleId = moduleId;
		this.sysRolesMoudleses = sysRolesMoudleses;
		this.sysUsersRoleses = sysUsersRoleses;
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getIssys() {
		return this.issys;
	}

	public void setIssys(Boolean issys) {
		this.issys = issys;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Set<SysRolesModules> getSysRolesMoudleses() {
		return this.sysRolesMoudleses;
	}

	public void setSysRolesMoudleses(Set<SysRolesModules> sysRolesMoudleses) {
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

	public Set<SysUsersRoles> getSysUsersRoleses() {
		return this.sysUsersRoleses;
	}

	public void setSysUsersRoleses(Set<SysUsersRoles> sysUsersRoleses) {
		this.sysUsersRoleses = sysUsersRoleses;
	}

	public Set<SysRolesAuthorities> getSysRolesAuthoritieses() {
		return this.sysRolesAuthoritieses;
	}

	public void setSysRolesAuthoritieses(
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return (Object) getRoleId();
	}
}