package com.visa.dao;

import com.visa.dao.util.BaseEntity;
import com.visa.dao.util.BaseJpaDao;

import javax.persistence.EntityManager;

/**
 * Created by Test-Lab on 2016/6/29.
 */
public class CountryDao extends BaseJpaDao {

    public <T extends BaseEntity> void delete(Class<T> entityClass, Object entityid) {

    }

    @Override
    public EntityManager getEntityManager() {

        return null;

    }


}
