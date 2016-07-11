package com.visa.service;

import com.visa.dao.CountryDao;
import com.visa.entity.CountryEntity;
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
        return (entity== null ? null : new CountryEntity());
    }

    public List<CountryEntity> findAllCountry(){
        String[] filedsName={"id","name","nationalFlag","interContinental"};
        return countryDao.queryByWhere(CountryEntity.class,filedsName,null,null);
    }
    public Long getKeyValue(){
       return countryDao.generateKeyValue(CountryEntity.class) ;
    }
}
