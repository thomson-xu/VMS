package com.author.system.bean;

import com.visa.dao.util.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * SysGonggao entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_GONGGAO")
public class SysGonggao  extends BaseEntity implements java.io.Serializable {

	// Fields

	private String VId;
	private String VTitle;
	private String content;
	private Integer IJb;
	private String depId;
	private String depName;
	private String VJgid;
	private String VJgmc;
	private String CCzybh;
	private Date dtCreate;

	// Constructors

	/** default constructor */
	public SysGonggao() {
	}

	/** minimal constructor */
	public SysGonggao(String VId) {
		this.VId = VId;
	}

	/** full constructor */
	public SysGonggao(String VId, String VTitle, String content,
			Integer IJb, String depId, String depName, String VJgid,
			String VJgmc, String CCzybh, Date dtCreate) {
		this.VId = VId;
		this.VTitle = VTitle;
		this.content = content;
		this.IJb = IJb;
		this.depId = depId;
		this.depName = depName;
		this.VJgid = VJgid;
		this.VJgmc = VJgmc;
		this.CCzybh = CCzybh;
		this.dtCreate = dtCreate;
	}

	// Property accessors
	@Id
	@Column(name = "V_ID", unique = true, nullable = false, length = 100)
	public String getVId() {
		return this.VId;
	}

	public void setVId(String VId) {
		this.VId = VId;
	}

	@Column(name = "V_TITLE", length = 500)
	public String getVTitle() {
		return this.VTitle;
	}

	public void setVTitle(String VTitle) {
		this.VTitle = VTitle;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "I_JB", precision = 22, scale = 0)
	public Integer getIJb() {
		return this.IJb;
	}

	public void setIJb(Integer IJb) {
		this.IJb = IJb;
	}

	@Column(name = "DEP_ID", length = 100)
	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Column(name = "DEP_NAME", length = 100)
	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@Column(name = "V_JGID", length = 100)
	public String getVJgid() {
		return this.VJgid;
	}

	public void setVJgid(String VJgid) {
		this.VJgid = VJgid;
	}

	@Column(name = "V_JGMC", length = 100)
	public String getVJgmc() {
		return this.VJgmc;
	}

	public void setVJgmc(String VJgmc) {
		this.VJgmc = VJgmc;
	}

	@Column(name = "C_CZYBH", length = 100)
	public String getCCzybh() {
		return this.CCzybh;
	}

	public void setCCzybh(String CCzybh) {
		this.CCzybh = CCzybh;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CREATE", length = 7)
	public Date getDtCreate() {
		return this.dtCreate;
	}

	public void setDtCreate(Date dtCreate) {
		this.dtCreate = dtCreate;
	}

	@Override
	public Object getPrimaryKey() {
		return (Object)getVId();
	}
}