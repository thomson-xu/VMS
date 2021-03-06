/**
 * 
 */
package com.tms.author.service;


import com.tms.author.bean.SysResources;
import com.tms.author.dao.SysResourceDao;
import com.tms.author.bean.SysModules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


@Service
public class SecurityResourcesService {

	
	@Autowired
	private SysResourceDao sysResourceDao;
	
	@Autowired
	private MessageSource messageSource;

	/* (non-Javadoc)
	 * @see SecurityResourcesService#add(SysResources, java.lang.String)
	 */

	public String add(SysResources resource, String moduleId) throws Exception {
		SysModules module = null;
		if(moduleId != null && !"".equals(moduleId))
			module = this.sysResourceDao.find(SysModules.class, moduleId);
		
		resource.setSysModules(module);

		this.sysResourceDao.create(resource);
		
		return "add";
	}

	/* (non-Javadoc)
	 * @see SecurityResourcesService#update(SysResources, java.lang.String)
	 */

	public String update(SysResources resource, String moduleId)
			throws Exception {
		SysModules module = null;
		if(moduleId != null && !"".equals(moduleId))
			module = this.sysResourceDao.find(SysModules.class, moduleId);
		
		resource.setSysModules(module);
		//this.baseDao.update(resource);
		this.sysResourceDao.update(resource);
		
		return "update";
	}

	/* (non-Javadoc)
	 * @see SecurityResourcesService#delete(java.lang.String)
	 */

	public void  delete(String resourceId) throws Exception {
		//this.baseDao.delete(SysResources.class, resourceId);
		this.sysResourceDao.delete(SysResources.class,resourceId);
		
		return;
	}

	/* (non-Javadoc)
	 * @see SecurityResourcesService#query(SysResources)
	 */
	/*
	public Message query(String resourceName,Parameters params) throws Exception {
		PageRequest pageRequest = new PageRequest(params.getSpringDataPage(),params.getLimit());
		Page<SysResources> page = null;
		if(resourceName == null || "".equals(resourceName)){
			page = this.sysResourceDao.findAll(pageRequest);
		}else{
			String likeStr = "%"+resourceName+"%";
			page = this.sysResourceDao.findByResourceNameOrResourcePath(likeStr, likeStr, pageRequest);
		}
		
		Long totalCount = page.getTotalElements();
		Message msg = new Message(totalCount.intValue(),page.getContent());
		return msg;
	}*/

}
