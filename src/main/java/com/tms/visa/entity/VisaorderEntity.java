package com.tms.visa.entity;

import com.tms.base.dao.util.BaseEntity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "visaorder")
public class VisaorderEntity extends BaseEntity {
    private String id;

    @Id
    @Column(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Transient
    public Object getPrimaryKey() {
        return  (Object) getId();
    }
}
