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
    private long id;
    private String name;
    private String nationalFlag;
    private int interContinental;
    private List<ConsulateEntity> consulateEntitylist;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "NationalFlag")
    public String getNationalFlag() {
        return nationalFlag;
    }

    public void setNationalFlag(String nationalFlag) {
        this.nationalFlag = nationalFlag;
    }

    @Basic
    @Column(name = "InterContinental")
    public int getInterContinental() {
        return interContinental;
    }

    public void setInterContinental(int interContinental) {
        this.interContinental = interContinental;
    }


 @OneToMany(mappedBy = "countryEntity",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
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
