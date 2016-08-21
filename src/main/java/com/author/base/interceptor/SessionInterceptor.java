package com.author.base.interceptor;

/**
 * Created by Administrator on 2016/8/8.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionInterceptor implements HandlerInterceptor {
    private String[] excludeMapping;
    private String expiredUrl;

    public SessionInterceptor() {
    }

    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hanlder) throws Exception {
        String url = request.getRequestURI();
        return false;
    }

    public void setExcludeMapping(String[] excludeMapping) {
        this.excludeMapping = excludeMapping;
    }

    public void setExpiredUrl(String expiredUrl) {
        this.expiredUrl = expiredUrl;
    }
}
