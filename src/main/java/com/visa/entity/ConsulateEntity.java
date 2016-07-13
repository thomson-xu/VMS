package com.visa.entity;

import com.visa.dao.util.BaseEntity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "consulate")
public class ConsulateEntity extends BaseEntity {
    private long id;
    private String consulateName;
    private String consulateArea;

    private CountryEntity countryEntity;
    @Id
    @Column(name = "Id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ConsulateName")
    public String getConsulateName() {
        return consulateName;
    }

    public void setConsulateName(String consulateName) {
        this.consulateName = consulateName;
    }

    @Basic
    @Column(name = "ConsulateArea")
    public String getConsulateArea() {
        return consulateArea;
    }

    public void setConsulateArea(String consulateArea) {
        this.consulateArea = consulateArea;
    }


    @ManyToOne
    @JoinColumn(name="Country_Id",foreignKey = @ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT) )
    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }

    @Override
    public Object getPrimaryKey() {
        return (Object) getId();
    }
}
