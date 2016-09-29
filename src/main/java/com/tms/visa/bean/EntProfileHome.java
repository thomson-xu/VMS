package com.tms.visa.bean;

import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/30.
 */
@Named
@Scope("request")
public class EntProfileHome {
    @Resource
    private EntProfile entProfile;

    public EntProfileHome() {

    }

    public EntProfile getEntProfile() {
        return entProfile;
    }

    public void setEntProfile(EntProfile entProfile) {
        this.entProfile = entProfile;
    }

    public void changeContinent(){
        entProfile.setCountryName(null);
        entProfile.setConsulateName(null);
    }

    public void changeCountry(){
        entProfile.setConsulateName(null);
    }


}
