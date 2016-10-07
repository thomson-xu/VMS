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

	private String resourceId;
	private SysModules sysModules;
	private String moduleId;
	private String resourceType;
	@QueryBuilder(QueryType.LIKE)
	private String resourceName;
	@QueryBuilder(QueryType.BACK_LIKE)
	private String resourcePath;
	private String resourceDesc;
	private String priority;
	private Boolean enable;
	private Boolean issys;
	private String httpMethod;
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

	// Property accessors
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "RESOURCE_ID", unique = true, nullable = false, length = 100)
	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	@Column(name = "MODULE_ID", length = 100,insertable=false,updatable=false)
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID")
	public SysModules getSysModules() {
		return this.sysModules;
	}

	public void setSysModules(SysModules sysModules) {
		this.sysModules = sysModules;
	}

	@Column(name = "RESOURCE_TYPE", length = 100)
	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name = "RESOURCE_NAME", length = 100)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	@Column(name = "RESOURCE_PATH", length = 100)
	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	@Column(name = "RESOURCE_DESC", length = 200)
	public String getResourceDesc() {
		return this.resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	@Column(name = "PRIORITY", length = 100)
	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name = "ENABLE", precision = 22, scale = 0)
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Column(name = "ISSYS", precision = 22, scale = 0)
	public Boolean getIssys() {
		return this.issys;
	}

	public void setIssys(Boolean issys) {
		this.issys = issys;
	}
	
	@Column(name = "HTTP_METHOD", precision = 22, scale = 0)
	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysResources")
	public Set<SysAuthoritiesResources> getSysAuthoritiesResourceses() {
		return this.sysAuthoritiesResourceses;
	}

	public void setSysAuthoritiesResourceses(
			Set<SysAuthoritiesResources> sysAuthoritiesResourceses) {
		this.sysAuthoritiesResourceses = sysAuthoritiesResourceses;
	}

	@Override
	public Object getPrimaryKey() {
		return (Object)getResourceId();
	}
}