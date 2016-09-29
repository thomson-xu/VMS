package com.tms.visa.service;

import com.tms.visa.dao.ProfessionDao;
import com.tms.visa.entity.ProfessionEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ProfessionService {


	@Resource
	private ProfessionDao professionDao;

	public ProfessionService() {
	}

	public void setProfessionDao(ProfessionDao professionDao) {
		this.professionDao = professionDao;
	}

	public void add(ProfessionEntity entity){
		professionDao.create(entity);
	}


	public void delete(Long id){
		professionDao.delete(ProfessionEntity.class,(Object)(id));
	}

	public void update(ProfessionEntity entity){
		professionDao.update(entity);
	}


	public List<ProfessionEntity> findAllProfession(){
		String fields[]={"id","name"};
		List<ProfessionEntity> listProfession = professionDao.queryByWhere(ProfessionEntity.class,fields,null,null);
		return ((listProfession != null) ? listProfession : null);

	}

	public ProfessionEntity findProfession(Integer id){
		ProfessionEntity professionentity = professionDao.find(ProfessionEntity.class,id);
		return ((professionentity != null) ? professionentity : new ProfessionEntity());
	}

	public int getKeyValue(){
		return  professionDao.generateKeyValue(ProfessionEntity.class).intValue() ;
	}
}
