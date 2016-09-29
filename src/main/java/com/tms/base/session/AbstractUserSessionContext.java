package com.tms.base.session;

import com.tms.author.bean.SysUsers;

/**
 * Created by Administrator on 2016/8/8.
 */


public abstract class AbstractUserSessionContext implements UserSessionContext {
    public AbstractUserSessionContext() {
    }

    public String getUserId() {
        return this.getUser().getUserId();
    }

    public String getUsername() {
        return this.getUser().getUsername();
    }

    public String getVQzjgid() {
        return this.getUser().getVQzjgid();
    }

    public abstract SysUsers getUser();
}
