package com.tms.visa.service;

import com.tms.visa.dao.ConsulateDao;
import com.tms.visa.entity.ConsulateEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
@Service
public class ConsulateService {


    @Resource
    private ConsulateDao consulateDao;

    public void setConsulateDao(ConsulateDao consulateDao) {
        this.consulateDao = consulateDao;
    }

    public void addCountry(ConsulateEntity consulate){

        consulateDao.create(consulate);
    }
    public void updateCountry(ConsulateEntity consulate){
        consulateDao.update(consulate);
    }
    public void deleteConsulateById(Long id){
        Object[]  obj={(Object)id};
        consulateDao.deleteByWhere(ConsulateEntity.class, "o.id=?", obj);
    }


    public ConsulateEntity findConsulateId(Long id){
        ConsulateEntity entity= consulateDao.find(ConsulateEntity.class,(Object)id);
        return ((entity != null) ? entity : new ConsulateEntity());
    }

    public List<ConsulateEntity> findAllConsulate(){
        return consulateDao.queryByWhere(ConsulateEntity.class,null,null);
    }

    public List<ConsulateEntity> findConsulateByCountry(Long countryId){
        Object[]  obj={(Object)countryId};
        return consulateDao.queryByWhere(ConsulateEntity.class,"o.countryId=?",obj);
    }
    public Long getKeyValue(){
       return consulateDao.generateKeyValue(ConsulateEntity.class) ;
    }
}
