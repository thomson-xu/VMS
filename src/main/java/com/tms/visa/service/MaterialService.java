package com.tms.visa.service;

import com.tms.visa.dao.MaterialDao;
import com.tms.visa.entity.MaterialEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
@Service
public class MaterialService {


    @Resource
    private MaterialDao materialDao;


    public MaterialDao getMaterialDao() {
        return materialDao;
    }

    public void setMaterialDao(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

    public void addMaterial(MaterialEntity material){

        materialDao.create(material);
    }
    public void updateMaterial(MaterialEntity material){
        materialDao.update(material);
    }
    public void deleteMaterialById(Long id){
        Object[]  obj={(Object)id};
        materialDao.deleteByWhere(MaterialEntity.class, "o.id=?", obj);
    }


    public MaterialEntity findMaterialId(Long id){
        MaterialEntity entity= materialDao.find(MaterialEntity.class,(Object)id);
        return ((entity != null) ? entity : new MaterialEntity());
    }

    public List<MaterialEntity> findAllMaterial(){
        return materialDao.queryByWhere(MaterialEntity.class,null,null);
    }

    public List<MaterialEntity> findMaterialByCountry(Long countryId){
        Object[]  obj={(Object)countryId};
        return materialDao.queryByWhere(MaterialEntity.class,"o.countryId=?",obj);
    }
    public Long getKeyValue(){
       return materialDao.generateKeyValue(MaterialEntity.class) ;
    }
}
