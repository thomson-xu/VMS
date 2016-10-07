package com.tms.base.session;

/**
 * Created by Administrator on 2016/8/8.
 */


import com.tms.author.bean.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUserSessionContext extends AbstractUserSessionContext {
    public SecurityUserSessionContext() {
    }

    public SysUser getUser() {
        SysUser user = (SysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}