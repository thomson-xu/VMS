/**
 * 
 */
package com.tms.author.dao;

import java.util.List;

import com.tms.base.dao.util.BaseJpaDao;


import com.tms.author.bean.SysAuthorities;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
@Repository
public class SysAuthorityDao extends BaseJpaDao {
	protected Log logger = LogFactory.getLog(getClass());

	public List<SysAuthorities> findByModuleId(String moduleId){
		String sql = "select x from SysAuthorities x where moduleId = ?1 or moduleId is null";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter(1, moduleId);
		List<SysAuthorities> list= query.getResultList();
		return  list;
	}
	
	public List<SysAuthorities> findByModuleIdIsNull(){
		String sql = "select x from SysAuthorities x where moduleId is null";
		Query query = getEntityManager().createQuery(sql);
		List<SysAuthorities> list= query.getResultList();
		return  list;
	}
	
/*	@Query("select x from SysAuthorities x order by x.moduleId")
	public Page<SysAuthorities> findAll(Pageable pageable);*/
	
}
