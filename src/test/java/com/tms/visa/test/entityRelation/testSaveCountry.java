package com.tms.visa.test.entityRelation;

import com.tms.visa.dao.CountryDao;
import com.tms.base.dao.util.DAO;
import com.tms.visa.entity.ConsulateEntity;
import com.tms.visa.entity.CountryEntity;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Test-Lab on 2016/6/17.
 */
public class testSaveCountry {
    private EntityManagerFactory entityManagerFactory;
    private EntityTransaction userTransaction;
    private EntityManager em;

   @Before
    public void setUp(){

       entityManagerFactory =
               Persistence.createEntityManagerFactory("DBPersistenceUnit");

       em = entityManagerFactory.createEntityManager();
       userTransaction = em.getTransaction();


   }
    @Test
    public void saveCountry(){
        userTransaction.begin();
        CountryEntity countryEntity = new CountryEntity();
        ConsulateEntity consulateEntity= new ConsulateEntity();
        List<ConsulateEntity> consulateEntityList = new ArrayList<ConsulateEntity>();

        countryEntity.setId(1000000005);
        countryEntity.setName("USA");
        countryEntity.setInterContinental(1);
        countryEntity.setNationalFlag("img/nationflag/usa.png");
        countryEntity.setConsulateEntitylist(consulateEntityList);

       consulateEntity.setId(1000000000);
        consulateEntity.setConsulateArea("sh,suz,zj");
        consulateEntity.setConsulateName("shanghai_consulate_USA");
        consulateEntity.setCountryEntity(countryEntity);
        consulateEntityList.add(consulateEntity);

       consulateEntity= new ConsulateEntity();
        consulateEntity.setId(1000000001);
        consulateEntity.setConsulateArea("sh,suz,zj");
        consulateEntity.setConsulateName("shanghai_consulate_USA");
        consulateEntity.setCountryEntity(countryEntity);

        consulateEntityList.add(consulateEntity);
        countryEntity.setConsulateEntitylist(consulateEntityList);

        DAO countryDAO = new CountryDao();
        countryDAO.create(countryEntity);
        userTransaction.commit();
        em.close();
        entityManagerFactory.close();
    }
}

