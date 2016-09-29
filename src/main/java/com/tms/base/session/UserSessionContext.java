package com.tms.base.session;

import com.tms.author.bean.SysUsers;

/**
 * Created by Administrator on 2016/8/8.
 */


public interface UserSessionContext {
    SysUsers getUser();

    String getUserId();

    String getUsername();

    String getVQzjgid();
}
