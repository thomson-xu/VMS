package com.tms.base.common.web.controller;

/**
 * Created by Administrator on 2016/8/9.
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ControllerTools {
    public ControllerTools() {
    }

    public static PrintWriter getWriter(HttpServletResponse response) {
        PrintWriter out = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");

        try {
            out = response.getWriter();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return out;
    }



    public static void print(HttpServletResponse response, Object data) {
        PrintWriter out = getWriter(response);
        out.print(data);
    }

    public static String resolvePayload(HttpServletRequest request) throws Exception {
        StringBuffer content = new StringBuffer();
        BufferedReader reader = null;
        reader = request.getReader();

        String line;
        while((line = reader.readLine()) != null) {
            content.append(line + "\r\n");
        }

        return content.toString();
    }

    public static Object resolvePayload(HttpServletRequest request, String className) throws Exception {
        String content = resolvePayload(request);
        if(!"".equals(content) && content != null) {
           // Object entity = JSONFormater.toBean(className, content);
            return null;
        } else {
            throw new NullPointerException("Request Payload解析失败");
        }
    }

    public static Object resolvePayloadEX(HttpServletRequest request) throws Exception {
        String className = request.getParameter("className");
        return resolvePayload(request, className);
    }

    public static UserDetails getUserDetails(HttpServletRequest request) {
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        //DebugUtil.println(requestType);
        return requestType != null;
    }

    public static boolean hasAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        return auth != null;
    }
}
