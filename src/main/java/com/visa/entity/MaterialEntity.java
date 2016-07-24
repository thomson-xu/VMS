package com.visa.entity;

import com.visa.dao.util.BaseEntity;

import javax.persistence.*;

/**
 * Created by Test-Lab on 2016/6/15.
 */
@Entity
@Table(name = "material")
public class MaterialEntity extends BaseEntity {

    @Id
    @Column(name = "Id")
    private long id;

/*    @Basic
    @Column(name="Consulate_Id")
    private long consulateId;

    @Basic
    @Column(name="Consulate_Id")
    private int visatypeId;

    @Basic
    @Column(name="Consulate_Id")
    private int professionId;*/

    @Basic
    @Column(name = "Document")
    private String document;

    @Basic
    @Column(name = "Remark")
    private String remark;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Consulate_Id", referencedColumnName = "Id",insertable = false,updatable = false)
    private ConsulateEntity consulate ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Visatype_Id", referencedColumnName = "Id",insertable = false,updatable = false)
    private VisatypeEntity visaType ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profession_Id", referencedColumnName = "Id",insertable = false,updatable = false)
    private ProfessionEntity profession ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

  /*  public long getConsulateId() {
        return consulateId;
    }

    public void setConsulateId(long consulatId) {
        this.consulateId = consulateId;
    }

    public int getVisatypeId() {
        return visatypeId;
    }

    public void setVisatypeId(int visatypeId) {
        this.visatypeId = visatypeId;
    }

    public int getProfessionId() {
        return professionId;
    }

    public void setProfessionId(int professionId) {
        this.professionId = professionId;
    }*/

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ConsulateEntity getConsulate() {
        return consulate;
    }

    public void setConsulate(ConsulateEntity consulate) {
        this.consulate = consulate;
    }

    public VisatypeEntity getVisaType() {
        return visaType;
    }

    public void setVisaType(VisatypeEntity visaType) {
        this.visaType = visaType;
    }

    public ProfessionEntity getProfession() {
        return profession;
    }

    public void setProfession(ProfessionEntity profession) {
        this.profession = profession;
    }

    @Transient
    public Object getPrimaryKey() {
        return  (Object) getId();
    }
}
