package com.visa.service;

import com.visa.dao.ConsulateDao;
import com.visa.entity.ConsulateEntity;
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
        consulateDao.delete(ConsulateEntity.class,(Object)id);
    }


    public ConsulateEntity findConsulateId(Long id){
        ConsulateEntity entity= consulateDao.find(ConsulateEntity.class,(Object)id);
        return ((entity != null) ? entity : new ConsulateEntity());
    }

    public List<ConsulateEntity> findAllConsulate(){
        //String[] filedsName={"id","name","nationalFlag","interContinental"};
        return consulateDao.queryByWhere(ConsulateEntity.class,null,null);
    }
    public Long getKeyValue(){
       return consulateDao.generateKeyValue(ConsulateEntity.class) ;
    }
}
