package com.tms.author.bean;


import com.tms.base.dao.util.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * SysAuthorities entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_AUTHORITIES")
public class SysAuthorities extends BaseEntity implements java.io.Serializable {

	// Fields
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "AUTHORITY_ID", unique = true, nullable = false, length = 100)
	private String authorityId;

	@Column(name = "AUTHORITY_MARK", nullable = false, length = 100)
	private String authorityMark;

	@Column(name = "AUTHORITY_NAME", nullable = false, length = 100)
	private String authorityName;

	@Column(name = "AUTHORITY_DESC", length = 200)
	private String authorityDesc;

	@Column(name = "MESSAGE", length = 100)
	private String message;

	@Column(name = "ENABLE", precision = 22, scale = 0)
	private Boolean enable;

	@Column(name = "ISSYS", precision = 22, scale = 0)
	private Boolean issys;

	@Column(name = "MODULE_ID", length = 100)
	private String moduleId;

	@Column(name = "REMARK", length = 100)
	private String remark;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysAuthorities")
	private Set<SysRolesAuthorities> sysRolesAuthoritieses = new HashSet<SysRolesAuthorities>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysAuthorities")
	private Set<SysAuthoritiesResources> sysAuthoritiesResourceses = new HashSet<SysAuthoritiesResources>(0);

	// Constructors

	/** default constructor */
	public SysAuthorities() {
	}
	
	/** minimal constructor */
	public SysAuthorities(String authorityId) {
		this.authorityId = authorityId;
	}

	/** minimal constructor */
	public SysAuthorities(String authorityId, String authorityName) {
		this.authorityId = authorityId;
		this.authorityName = authorityName;
	}

	/** full constructor */
	public SysAuthorities(String authorityId, String authorityName,
			String authorityDesc, String message, Boolean enable,
			Boolean issys, String moduleId,
			Set<SysRolesAuthorities> sysRolesAuthoritieses,
			Set<SysAuthoritiesResources> sysAuthoritiesResourceses) {
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
		this.message = message;
		this.enable = enable;
		this.issys = issys;
		this.moduleId = moduleId;
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
		this.sysAuthoritiesResourceses = sysAuthoritiesResourceses;
	}

	// Property accessors

	public String getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityMark() {
		return authorityMark;
	}

	public void setAuthorityMark(String authorityMark) {
		this.authorityMark = authorityMark;
	}

	public String getAuthorityName() {
		return this.authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return this.authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<SysRolesAuthorities> getSysRolesAuthoritieses() {
		return this.sysRolesAuthoritieses;
	}

	public void setSysRolesAuthoritieses(
			Set<SysRolesAuthorities> sysRolesAuthoritieses) {
		this.sysRolesAuthoritieses = sysRolesAuthoritieses;
	}

	public Set<SysAuthoritiesResources> getSysAuthoritiesResourceses() {
		return this.sysAuthoritiesResourceses;
	}

	public void setSysAuthoritiesResourceses(
			Set<SysAuthoritiesResources> sysAuthoritiesResourceses) {
		this.sysAuthoritiesResourceses = sysAuthoritiesResourceses;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return (Object) getAuthorityId();
	}
}