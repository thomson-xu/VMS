package com.tms.visa.service;

import com.tms.visa.dao.CountryDao;
import com.tms.visa.entity.CountryEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
@Service
public class CountryService {


    @Resource
    private CountryDao countryDao;

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public void addCountry(CountryEntity country){
        countryDao.create(country);
    }
    public void updateCountry(CountryEntity countryEntity){
        countryDao.update(countryEntity);
    }
    public void deleteCountryById(Long id){
        countryDao.delete(CountryEntity.class,(Object)id);
    }


    public CountryEntity findCountryId(Long id){
        CountryEntity entity= countryDao.find(CountryEntity.class,(Object)id);
        return ((entity != null) ? entity : new CountryEntity());
    }

    public List<CountryEntity> findCountryByContinent(int id){
        String wheresql="o.interContinental=?" ;
        Object[] obj={id};
        return countryDao.queryByWhere(CountryEntity.class,wheresql,obj);
    }

    public List<CountryEntity> findAllCountry(){
        String[] filedsName={"id","name","nationalFlag","interContinental"};
        return countryDao.queryByWhere(CountryEntity.class,filedsName,null,null);
    }
    public Long getKeyValue(){
       return countryDao.generateKeyValue(CountryEntity.class) ;
    }
}
