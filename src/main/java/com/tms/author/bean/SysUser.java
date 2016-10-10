package com.tms.author.bean;

import com.tms.base.dao.util.BaseEntity;
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
public class SysUser extends BaseEntity implements UserDetails, Serializable {
	private static final long serialVersionUID = -6498309642739707784L;

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
	private String userId;

	@Column(
			name = "USERNAME",
			nullable = false,
			length = 100
	)
	private String username;

	@Column(
			name = "NAME",
			length = 100
	)
	private String name;

	@Column(
			name = "PASSWORD",
			nullable = false,
			length = 100
	)
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(
			name = "DT_CREATE",
			length = 7,
			insertable = false,
			updatable = false
	)
	private Date dtCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(
			name = "LAST_LOGIN",
			length = 7
	)
	private Date lastLogin;

	@Temporal(TemporalType.DATE)
	@Column(
			name = "DEADLINE",
			length = 7
	)
	private Date deadline;

	@Column(
			name = "LOGIN_IP",
			length = 100
	)
	private String loginIp;

	@Column(
			name = "DEP_ID",
			length = 100
	)
	private String depId;

	@Column(
			name = "DEP_NAME",
			length = 100
	)
	private String depName;


	@Column(
			name = "ENABLED",
			precision = 22,
			scale = 0
	)
	private boolean enabled;

	@Column(
			name = "ACCOUNT_NON_EXPIRED",
			precision = 22,
			scale = 0
	)
	private boolean accountNonExpired;
	@Column(
			name = "ACCOUNT_NON_LOCKED",
			precision = 22,
			scale = 0
	)
	private boolean accountNonLocked;

	@Column(
			name = "CREDENTIALS_NON_EXPIRED",
			precision = 22,
			scale = 0
	)
	private boolean credentialsNonExpired;

	@OneToMany(
			cascade = {CascadeType.ALL},
			fetch = FetchType.LAZY,
			mappedBy = "sysUser"
	)
	private Set<SysUsersRoles> sysUsersRoleses = new HashSet(0);
	@Transient
	private Collection<GrantedAuthority> authorities;

	public SysUser() {
	}

	public SysUser(String userId) {
		this.userId = userId;
	}

	public SysUser(String userId, String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public SysUser(String userId, String username, String name, String password, Date dtCreate, Date lastLogin, Date deadline, String loginIp, String depId, String depName, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, Set<SysUsersRoles> sysUsersRoleses) {
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.password = password;
		this.dtCreate = dtCreate;
		this.lastLogin = lastLogin;
		this.deadline = deadline;
		this.loginIp = loginIp;
		this.depId = depId;
		this.depName = depName;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.sysUsersRoleses = sysUsersRoleses;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDtCreate() {
		return this.dtCreate;
	}

	public void setDtCreate(Date dtCreate) {
		this.dtCreate = dtCreate;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getDepId() {
		return this.depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public boolean isEnabled() {
		return this.enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}


	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}


	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

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
	@Transient
	public Object getPrimaryKey() {
		return (Object)getUserId();
	}
}
