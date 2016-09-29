package com.tms.base;

/**
 * Created by Administrator on 2016/8/8.
 */
public enum AuthorityType {
    SYS_USER_ADMIN_MOD("USER0100", "SYS_USER_ADMIN_MOD", "用户管理（超管模式）访问权限", "sys_user"),
    SYS_USER_NORMAL_MOD("USER0200", "SYS_USER_NORMAL_MOD", "用户管理 访问权限", "sys_user"),
    SYS_USER_ADD("USER0001", "SYS_USER_ADD", "添加用户", "sys_user"),
    SYS_USER_UPDATE("USER0002", "SYS_USER_UPDATE", "修改用户信息", "sys_user"),
    SYS_USER_DELETE("USER0003", "SYS_USER_DELETE", "删除用户", "sys_user"),
    SYS_USER_QUERY("USER0004", "SYS_USER_QUERY", "查询用户", "sys_user"),
    SYS_USER_UPDATEPW("USER0005", "SYS_USER_UPDATEPW", "修改密码", "sys_user");

    private String name;
    private String authorityName;
    private String id;
    private String group;

    private AuthorityType(String id, String authorityName, String name, String group) {
        this.id = id;
        this.authorityName = authorityName;
        this.name = name;
        this.group = group;
    }

    private AuthorityType(String id, String authorityName, String group) {
        this.id = id;
        this.authorityName = authorityName;
        this.group = group;
    }

    private AuthorityType(String id, String authorityName) {
        this.id = id;
        this.authorityName = authorityName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorityName() {
        return this.authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
