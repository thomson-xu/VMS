package com.tms.author.bean;

import com.tms.base.dao.util.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * SysModules entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "SYS_MODULES")
public class SysModules extends BaseEntity implements java.io.Serializable {

	// Fields
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	@Column(name = "MODULE_ID", unique = true, nullable = false, length = 100)
	private String moduleId;

	@Column(name = "MODULE_NAME", nullable = false, length = 100)
	private String moduleName;
	@Column(name = "MODULE_DESC", length = 200)
	private String moduleDesc;
	@Column(name = "MODULE_TYPE", length = 100)
	private String moduleType;
	@Column(name = "PARENT", length = 100)
	private String parent;
	@Column(name = "MODULE_URL", length = 100)
	private String moduleUrl;
	@Column(name = "I_LEVEL", precision = 22, scale = 0)
	private Integer ILevel;
	@Column(name = "LEAF", precision = 22, scale = 0)
	private Boolean leaf;
	@Column(name = "APPLICATION", length = 100)
	private String application;
	@Column(name = "CONTROLLER", length = 100)
	private String controller;
	@Column(name = "ENABLE")
	private Boolean enable;
	@Column(name = "ISSYS")
	private Boolean issys;
	@Column(name = "PRIORITY")
	private Integer priority;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysModules")
	private Set<SysResources> sysResourceses = new HashSet<SysResources>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysModules")
	private Set<SysRolesModules> sysRolesMoudleses = new HashSet<SysRolesModules>(
			0);

	// Constructors

	/** default constructor */
	public SysModules() {
	}
	/** minimal constructor */
	public SysModules(String moduleId) {
		this.moduleId = moduleId;
	}

	/** minimal constructor */
	public SysModules(String moduleId, String moduleName) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
	}

	/** full constructor */
	public SysModules(String moduleId, String moduleName, String moduleDesc,
			String moduleType, String parent, String moduleUrl,
			Integer ILevel, Boolean leaf, String application,
			String controller, Set<SysResources> sysResourceses,
			Set<SysRolesModules> sysRolesMoudleses) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleDesc = moduleDesc;
		this.moduleType = moduleType;
		this.parent = parent;
		this.moduleUrl = moduleUrl;
		this.ILevel = ILevel;
		this.leaf = leaf;
		this.application = application;
		this.controller = controller;
		this.sysResourceses = sysResourceses;
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

	// Property accessors

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return this.moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getModuleType() {
		return this.moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getModuleUrl() {
		return this.moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public Integer getILevel() {
		return this.ILevel;
	}

	public void setILevel(Integer ILevel) {
		this.ILevel = ILevel;
	}

	public Boolean getLeaf() {
		return this.leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getIssys() {
		return issys;
	}
	public void setIssys(Boolean issys) {
		this.issys = issys;
	}

	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getController() {
		return this.controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Set<SysResources> getSysResourceses() {
		return this.sysResourceses;
	}

	public void setSysResourceses(Set<SysResources> sysResourceses) {
		this.sysResourceses = sysResourceses;
	}

	public Set<SysRolesModules> getSysRolesMoudleses() {
		return this.sysRolesMoudleses;
	}

	public void setSysRolesMoudleses(Set<SysRolesModules> sysRolesMoudleses) {
		this.sysRolesMoudleses = sysRolesMoudleses;
	}

	@Override
	@Transient
	public Object getPrimaryKey() {
		return (Object)getModuleId();
	}
}