package com.tms.author.bean;

import com.tms.base.annotations.QueryBuilder;
import com.tms.base.annotations.QueryIgnore;
import com.tms.base.enums.QueryType;
import com.tms.base.dao.util.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * SysResources entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_RESOURCES")
public class SysResources  extends BaseEntity implements java.io.Serializable {

	// Fields
// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "RESOURCE_ID", unique = true, nullable = false, length = 100)
	private String resourceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID")
	private SysModules sysModules;
	@Column(name = "MODULE_ID", length = 100,insertable=false,updatable=false)
	private String moduleId;
	@Column(name = "RESOURCE_TYPE", length = 100)
	private String resourceType;

	@Column(name = "RESOURCE_NAME", length = 100)
	@QueryBuilder(QueryType.LIKE)
	private String resourceName;
	@Column(name = "RESOURCE_PATH", length = 100)
	@QueryBuilder(QueryType.BACK_LIKE)
	private String resourcePath;

	@Column(name = "RESOURCE_DESC", length = 200)
	private String resourceDesc;

	@Column(name = "PRIORITY", length = 100)
	private String priority;

	@Column(name = "ENABLE", precision = 22, scale = 0)
	private Boolean enable;

	@Column(name = "ISSYS", precision = 22, scale = 0)
	private Boolean issys;

	@Column(name = "HTTP_METHOD", precision = 22, scale = 0)
	private String httpMethod;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysResources")
	@QueryIgnore
	private Set<SysAuthoritiesResources> sysAuthoritiesResourceses = new HashSet<SysAuthoritiesResources>(
			0);

	// Constructors

	/** default constructor */
	public SysResources() {
	}

	/** minimal constructor */
	public SysResources(String resourceId) {
		this.resourceId = resourceId;
	}

	/** full constructor */
	public SysResources(String resourceId, SysModules sysModules,
			String resourceType, String resourceName, String resourceDesc,
			String priority, Boolean enable, Boolean issys,
			Set<SysAuthoritiesResources> sysAuthoritiesResourceses) {
		this.resourceId = resourceId;
		this.sysModules = sysModules;
		this.resourceType = resourceType;
		this.resourceName = resourceName;
		this.resourceDesc = resourceDesc;
		this.priority = priority;
		this.enable = enable;
		this.issys = issys;
		this.sysAuthoritiesResourceses = sysAuthoritiesResourceses;
	}


	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}


	public SysModules getSysModules() {
		return this.sysModules;
	}

	public void setSysModules(SysModules sysModules) {
		this.sysModules = sysModules;
	}


	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getResourceDesc() {
		return this.resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
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
		return (Object)getResourceId();
	}
}