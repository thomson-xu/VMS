package com.visa.entity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "consulate")
public class ConsulateEntity {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsulateEntity that = (ConsulateEntity) o;

        if (id != that.id) return false;
        if (consulateName != null ? !consulateName.equals(that.consulateName) : that.consulateName != null)
            return false;
        if (consulateArea != null ? !consulateArea.equals(that.consulateArea) : that.consulateArea != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (consulateName != null ? consulateName.hashCode() : 0);
        result = 31 * result + (consulateArea != null ? consulateArea.hashCode() : 0);
        return result;
    }
}
