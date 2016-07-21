package com.visa.entity;

import com.visa.dao.util.BaseEntity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "consulate")
public class ConsulateEntity extends BaseEntity {
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "ConsulateName")
    private String consulateName;
    @Basic
    @Column(name = "ConsulateArea")
    private String consulateArea;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name="Country_Id",referencedColumnName = "Id",insertable = false,updatable = false,unique = false)
    private CountryEntity countryEntity;
    @Basic
    @Column(name = "Country_Id")
    private Long countryId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConsulateName() {
        return consulateName;
    }

    public void setConsulateName(String consulateName) {
        this.consulateName = consulateName;
    }


    public String getConsulateArea() {
        return consulateArea;
    }

    public void setConsulateArea(String consulateArea) {
        this.consulateArea = consulateArea;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Transient
    public Object getPrimaryKey() {
        return (Object) getId();
    }
}
