package com.tms.author.base.controller;

/**
 * Created by Administrator on 2016/8/8.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping({"/cookie"})
public class CookiesController {
    public CookiesController() {
    }

    @RequestMapping({"/set/{name}/{value}/{day}"})
    @ResponseBody
    public String set(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name, @PathVariable("value") String value, @PathVariable("day") int day) {
        if(day == 0) {
            day = 30;
        }

        this.addCookie(response, name, value, day);
        return "";
    }

    @RequestMapping({"/set/{name}/{value}"})
    @ResponseBody
    public String set(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name, @PathVariable("value") String value) {
        byte day = 30;
        this.addCookie(response, name, value, day);
        return "";
    }

    @RequestMapping({"/get/{name}"})
    @ResponseBody
    public String get(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name) {
        String value = this.getCookieByName(request, name).getValue();
        return value;
    }

    public void addCookie(HttpServletResponse response, String name, String value, int day) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        int maxAge = day * 24 * 60 * 60;
        if(maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }

        response.addCookie(cookie);
    }

    public Cookie getCookieByName(HttpServletRequest request, String name) {
        Map cookieMap = this.ReadCookieMap(request);
        if(cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    private Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        HashMap cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            Cookie[] var7 = cookies;
            int var6 = cookies.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                Cookie cookie = var7[var5];
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }
}
