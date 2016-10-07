package com.tms.base.session;

import com.tms.author.bean.SysUser;

/**
 * Created by Administrator on 2016/8/8.
 */


public interface UserSessionContext {
    SysUser getUser();

    String getUserId();

    String getUsername();
}
