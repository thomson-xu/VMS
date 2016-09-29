package com.tms.visa.service;

import com.tms.visa.dao.VisaTypeDao;
import com.tms.visa.entity.VisatypeEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class VisaTypeService {


    @Resource
    private VisaTypeDao visaTypeDao;

    public VisaTypeService() {

    }

    public void setVisaTypeDao(VisaTypeDao visaTypeDao) {
        this.visaTypeDao = visaTypeDao;
    }

    public void add(VisatypeEntity entity) {
        visaTypeDao.create(entity);
    }


    public void delete(int id) {
        visaTypeDao.delete(VisatypeEntity.class, (Object) (id));
    }

    public void update(VisatypeEntity entity) {
        visaTypeDao.update(entity);
    }


    public List<VisatypeEntity> findAllVisatype() {
        String fields[] = {"id", "type", "remark"};
        List<VisatypeEntity> listVisaType = visaTypeDao.queryByWhere(VisatypeEntity.class, fields, null, null);
        return ((listVisaType != null) ? listVisaType : null);

    }

    public VisatypeEntity findVisatype(int id) {
        VisatypeEntity visatypeEntity = visaTypeDao.find(VisatypeEntity.class, id);
        return ((visatypeEntity != null) ? visatypeEntity : new VisatypeEntity());
    }

    public int getKeyValue() {
        return visaTypeDao.generateKeyValue(VisatypeEntity.class).intValue();
    }
}
