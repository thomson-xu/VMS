package com.visa.entity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "visaproduct")
public class VisaproductEntity {
    private long id;
    private int price;
    private String remark;

    @Id
    @Column(name = "Id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisaproductEntity that = (VisaproductEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + price;
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
