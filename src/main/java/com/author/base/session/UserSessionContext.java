package com.author.base.session;

import com.author.system.bean.SysUsers;

/**
 * Created by Administrator on 2016/8/8.
 */


public interface UserSessionContext {
    SysUsers getUser();

    String getUserId();

    String getUsername();

    String getVQzjgid();
}
