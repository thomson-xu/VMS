package com.visa.service;

import com.visa.dao.CountryDao;
import com.visa.entity.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

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

    public void addCountry(CountryEntity countryEntity){
        countryDao.create(countryEntity);
    }
    public void updateCountry(CountryEntity countryEntity){
        countryDao.update(countryEntity);
    }
    public void deleteCountryById(CountryEntity countryEntity,Long id){
        countryDao.delete(countryEntity,(Object)id);
    }
    public void findCountryId(CountryEntity countryEntity, Long id){
        //countryDao.find(countryEntity,(Object)id);
    }

    public void findAllCountry(CountryEntity countryEntity){
        //countryDao.find(countryEntity,null);
    }
}
