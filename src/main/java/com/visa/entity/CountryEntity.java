package com.visa.entity;

import com.visa.dao.util.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "country")

public class CountryEntity extends BaseEntity {
    @Id
    @Column(name = "ID")
    private long id;

    @Basic
    @Column(name = "Name")
    private String name;

    @Basic
    @Column(name = "NationalFlag")
    private String nationalFlag;

    @Basic
    @Column(name = "InterContinental")
    private int interContinental;

    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY, mappedBy = "countryEntity")
    private List<ConsulateEntity> consulateEntitylist;
    public CountryEntity(){}
    public CountryEntity(long id, String name, String nationalFlag,int interContinental){
        this.id = id;
        this.name = name;
        this.nationalFlag = nationalFlag;
        this.interContinental = interContinental;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    //@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE },mappedBy
   public List<ConsulateEntity> getConsulateEntitylist() {
        return consulateEntitylist;
    }

    public void setConsulateEntitylist(List<ConsulateEntity> consulateEntitylist) {
        this.consulateEntitylist = consulateEntitylist;
    }

    @Transient
    public Object getPrimaryKey() {
        return  (Object) getId();

    }
}
