package com.visa.entity;

import com.visa.dao.util.BaseEntity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "profession")
public class ProfessionEntity extends BaseEntity {
    private int id;
    private String name;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public ProfessionEntity(){
    }

    public ProfessionEntity(int id, String name){
        this.id = id;
        this.name= name;
    }

    @Transient
    public Object getPrimaryKey() {
        return null;
    }
}
