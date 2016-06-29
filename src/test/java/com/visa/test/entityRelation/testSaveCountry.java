package com.visa.test.entityRelation;

import com.visa.entity.ConsulateEntity;
import com.visa.entity.CountryEntity;
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

        countryEntity.setId(1000000002);
        countryEntity.setName("USA");
        countryEntity.setInterContinental(1);
        countryEntity.setNationalFlag("img/nationflag/usa.png");
        countryEntity.setConsulateEntitylist(consulateEntityList);

       consulateEntity.setId(1000000007);
        consulateEntity.setConsulateArea("sh,suz,zj");
        consulateEntity.setConsulateName("shanghai_consulate_USA");
        consulateEntity.setCountryEntity(countryEntity);
        consulateEntityList.add(consulateEntity);

        consulateEntity= new ConsulateEntity();
        consulateEntity.setId(1000000011);
        consulateEntity.setConsulateArea("sh,suz,zj");
        consulateEntity.setConsulateName("shanghai_consulate_USA");
        consulateEntity.setCountryEntity(countryEntity);

        consulateEntityList.add(consulateEntity);
        countryEntity.setConsulateEntitylist(consulateEntityList);

        em.persist(consulateEntity);
        userTransaction.commit();
        em.close();
        entityManagerFactory.close();
    }
}

