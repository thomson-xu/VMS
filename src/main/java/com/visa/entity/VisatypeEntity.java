package com.visa.entity;

import com.visa.dao.util.BaseEntity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "visatype")
public class VisatypeEntity extends BaseEntity {
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Type")
    private String type;

    @Basic
    @Column(name = "Remark")
    private String remark;

    @Transient
    boolean editable;

    public VisatypeEntity(int id, String type, String remark) {
        this.id = id;
        this.type = type;
        this.remark = remark;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public Object getPrimaryKey() {
        return (Object) getId();
    }

    public boolean getEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    public VisatypeEntity() {
    }

}
