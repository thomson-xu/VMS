package com.tms.visa.entity;

import com.tms.base.dao.util.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "profession")
public class ProfessionEntity extends BaseEntity {
    private int id;
    private String name;
    private List<MaterialEntity> materialEntityList = new ArrayList<MaterialEntity>();


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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "profession")
    public List<MaterialEntity> getMaterialEntityList() {
        return materialEntityList;
    }

    public void setMaterialEntityList(List<MaterialEntity> materialEntityList) {
        this.materialEntityList = materialEntityList;
    }

    @Transient
    public Object getPrimaryKey() {
        return null;
    }
}
