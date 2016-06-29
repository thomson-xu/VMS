package com.visa.entity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "visatype")
public class VisatypeEntity {
    private int id;
    private String type;
    private String remark;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        VisatypeEntity that = (VisatypeEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
