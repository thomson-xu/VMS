package com.author.system.view;

import com.author.annotations.AuthorityCollection;
import com.author.base.AuthorityType;
import com.author.system.bean.SysUsers;
import com.author.system.service.SysUserService;
import com.visa.entity.CountryEntity;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectOne;
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

    private UISelectOne selectone;
    private UploadedFile file;
    private String result;
    private CountryEntity countryEntity;
    public SysUser(){

        sysUserService = new SysUserService();

    }

    private SysUserService sysUserService;

    //添加用户

    public void save(){
        result = "Create unsuccessfully";

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);

        sysUserService.add(user);

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
