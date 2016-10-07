package com.tms.author.view;

import com.tms.author.service.SysUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Test-Lab on 2016/6/29.
 */
@Named
@Scope("request")
public class SysUser {
    @Resource
    private SysUserService sysUserService;
    private String result;
    public SysUser(){

        sysUserService = new SysUserService();

    }

    public UserDetails getUserSession(HttpServletRequest request, HttpServletResponse response){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    public void getCode(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        //指定生成的响应是图片
        response.setContentType("image/jpeg");

        //返回验证码
        //GenerateImageCode.generateRandomCode(request, response);
    }

    public com.tms.author.bean.SysUser login(String username, String password){

             return null;
    }
    //添加用户

    public void save(ActionEvent event){
        result = "Create unsuccessfully";

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);

        //sysUserService.add(user);

    }

    //修改密码

    public void updatePassword( String oldPassword, String newPassword) {


        result = "修改成功";

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);

    }


    public void delete(String userId) throws Exception{

        sysUserService.delete(userId);

    }
    }
