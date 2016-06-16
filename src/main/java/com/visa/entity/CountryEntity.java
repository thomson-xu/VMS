package com.visa.entity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "country", schema = "", catalog = "vms")
public class CountryEntity {
    private long id;
    private String name;
    private String nationalFlag;
    private int interContinental;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryEntity that = (CountryEntity) o;

        if (id != that.id) return false;
        if (interContinental != that.interContinental) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nationalFlag != null ? !nationalFlag.equals(that.nationalFlag) : that.nationalFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nationalFlag != null ? nationalFlag.hashCode() : 0);
        result = 31 * result + interContinental;
        return result;
    }
}
