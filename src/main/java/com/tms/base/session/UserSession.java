package com.tms.base.session;

/**
 * Created by Administrator on 2016/8/8.
 */

import java.util.Date;

public class UserSession {
    private String CCzybh;
    private String VJgrybh;
    private String VMc;
    private String depId;
    private String depName;
    private String VJgid;
    private String VJgmc;
    private String CCzymc;
    private String VZcmc;
    private String VYhsf;
    private Date lastLoginTime;

    public UserSession() {
    }

    public String getCCzybh() {
        return this.CCzybh;
    }

    public void setCCzybh(String cCzybh) {
        this.CCzybh = cCzybh;
    }

    public String getVJgrybh() {
        return this.VJgrybh;
    }

    public void setVJgrybh(String vJgrybh) {
        this.VJgrybh = vJgrybh;
    }

    public String getVMc() {
        return this.VMc;
    }

    public void setVMc(String vMc) {
        this.VMc = vMc;
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

    public String getVJgid() {
        return this.VJgid;
    }

    public void setVJgid(String vJgid) {
        this.VJgid = vJgid;
    }

    public String getCCzymc() {
        return this.CCzymc;
    }

    public void setCCzymc(String cCzymc) {
        this.CCzymc = cCzymc;
    }

    public String getVZcmc() {
        return this.VZcmc;
    }

    public void setVZcmc(String vZcmc) {
        this.VZcmc = vZcmc;
    }

    public String getVYhsf() {
        return this.VYhsf;
    }

    public void setVYhsf(String vYhsf) {
        this.VYhsf = vYhsf;
    }

    public String getVJgmc() {
        return this.VJgmc;
    }

    public void setVJgmc(String vJgmc) {
        this.VJgmc = vJgmc;
    }

    public Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
