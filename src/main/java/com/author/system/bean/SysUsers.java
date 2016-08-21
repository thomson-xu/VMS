package com.author.system.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.visa.dao.util.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "SYS_USERS")
public class SysUsers  extends BaseEntity implements UserDetails, Serializable {
	private static final long serialVersionUID = -6498309642739707784L;
	private String userId;
	private String username;
	private String name;
	private String password;
	private Date dtCreate;
	private Date lastLogin;
	private Date deadline;
	private String loginIp;
	private String VQzjgid;
	private String VQzjgmc;
	private String depId;
	private String depName;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	@JsonIgnore
	private Set<SysUsersRoles> sysUsersRoleses = new HashSet(0);
	private Collection<GrantedAuthority> authorities;

	public SysUsers() {
	}

	public SysUsers(String userId) {
		this.userId = userId;
	}

	public SysUsers(String userId, String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public SysUsers(String userId, String username, String name, String password, Date dtCreate, Date lastLogin, Date deadline, String loginIp, String VQzjgid, String VQzjgmc, String depId, String depName, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, Set<SysUsersRoles> sysUsersRoleses) {
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.password = password;
		this.dtCreate = dtCreate;
		this.lastLogin = lastLogin;
		this.deadline = deadline;
		this.loginIp = loginIp;
		this.VQzjgid = VQzjgid;
		this.VQzjgmc = VQzjgmc;
		this.depId = depId;
		this.depName = depName;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.sysUsersRoleses = sysUsersRoleses;
	}

	@Id
	@GenericGenerator(
			name = "uuid",
			strategy = "uuid"
	)
	@GeneratedValue(
			generator = "uuid"
	)
	@Column(
			name = "USER_ID",
			unique = true,
			nullable = false,
			length = 100
	)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(
			name = "USERNAME",
			nullable = false,
			length = 100
	)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(
			name = "NAME",
			length = 100
	)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(
			name = "PASSWORD",
			nullable = false,
			length = 100
	)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(
			name = "DT_CREATE",
			length = 7,
			insertable = false,
			updatable = false
	)
	public Date getDtCreate() {
		return this.dtCreate;
	}

	public void setDtCreate(Date dtCreate) {
		this.dtCreate = dtCreate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(
			name = "LAST_LOGIN",
			length = 7
	)
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Temporal(TemporalType.DATE)
	@Column(
			name = "DEADLINE",
			length = 7
	)
	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(
			name = "LOGIN_IP",
			length = 100
	)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(
			name = "V_QZJGID",
			length = 100
	)
	public String getVQzjgid() {
		return this.VQzjgid;
	}

	public void setVQzjgid(String VQzjgid) {
		this.VQzjgid = VQzjgid;
	}

	@Column(
			name = "V_QZJGMC",
			length = 100
	)
	public String getVQzjgmc() {
		return this.VQzjgmc;
	}

	public void setVQzjgmc(String VQzjgmc) {
		this.VQzjgmc = VQzjgmc;
	}

	@Column(
			name = "DEP_ID",
			length = 100
	)
	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Column(
			name = "DEP_NAME",
			length = 100
	)
	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	@JsonProperty("enabled")
	@Column(
			name = "ENABLED",
			precision = 22,
			scale = 0
	)
	public boolean isEnabled() {
		return this.enabled;
	}

	@JsonProperty("enabled")
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@JsonProperty("accountNonExpired")
	@Column(
			name = "ACCOUNT_NON_EXPIRED",
			precision = 22,
			scale = 0
	)
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@JsonProperty("accountNonExpired")
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@JsonProperty("accountNonLocked")
	@Column(
			name = "ACCOUNT_NON_LOCKED",
			precision = 22,
			scale = 0
	)
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@JsonProperty("accountNonLocked")
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@JsonProperty("credentialsNonExpired")
	@Column(
			name = "CREDENTIALS_NON_EXPIRED",
			precision = 22,
			scale = 0
	)
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@JsonProperty("credentialsNonExpired")
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@OneToMany(
			cascade = {CascadeType.ALL},
			fetch = FetchType.LAZY,
			mappedBy = "sysUsers"
	)
	public Set<SysUsersRoles> getSysUsersRoleses() {
		return this.sysUsersRoleses;
	}

	public void setSysUsersRoleses(Set<SysUsersRoles> sysUsersRoleses) {
		this.sysUsersRoleses = sysUsersRoleses;
	}

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Object getPrimaryKey() {
		return (Object)getUserId();
	}
}
