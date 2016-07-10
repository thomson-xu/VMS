package com.visa.bean;

import com.visa.entity.CountryEntity;

/**
 * Created by Test-Lab on 2016/6/29.
 */

public class CountryBean {
    private Long id;
    private String countryName;
    private String nationalFlag;
    private int interContinental;

    public CountryBean(){}

    public CountryBean(CountryEntity countryEntity){
        this.id=countryEntity.getId();
        this.countryName=countryEntity.getName();
        this.nationalFlag=countryEntity.getNationalFlag();
        this.interContinental=countryEntity.getInterContinental();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getNationalFlag() {
        return nationalFlag;
    }

    public void setNationalFlag(String nationalFlag) {
        this.nationalFlag = nationalFlag;
    }

    public int getInterContinental() {
        return interContinental;
    }

    public void setInterContinental(int interContinental) {
        this.interContinental = interContinental;
    }
    /**
     * Bean --> Entity
     * @return  Entity
     */
    public CountryEntity getEntity(){
        CountryEntity entity= new CountryEntity();
        entity.setId(id);
        entity.setName(countryName);
        entity.setNationalFlag(nationalFlag);
        entity.setInterContinental(interContinental);
        return entity;
    }
}
