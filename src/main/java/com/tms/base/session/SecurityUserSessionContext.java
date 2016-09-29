package com.tms.base.session;

/**
 * Created by Administrator on 2016/8/8.
 */


import com.tms.author.bean.SysUsers;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUserSessionContext extends AbstractUserSessionContext {
    public SecurityUserSessionContext() {
    }

    public SysUsers getUser() {
        SysUsers user = (SysUsers)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}