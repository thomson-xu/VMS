package com.tms.visa.bean;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/30.
 */
@Named("intercountryconsulate")
@Scope("request")
public class EntProfile {
    private String continentName;
    private String countryName;
    private String consulateName;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getConsulateName() {
        return consulateName;
    }

    public void setConsulateName(String consulateName) {
        this.consulateName = consulateName;
    }
}
