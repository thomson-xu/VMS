/**
 * 
 */
package com.author.system.service;

import com.author.system.bean.SysAuthorities;
import com.author.system.bean.SysAuthoritiesResources;
import com.author.system.bean.SysResources;
import com.author.system.dao.SysAuthorityDao;
import com.author.system.dao.SysResourceAuthorityDao;
import com.author.system.dao.SysResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityResourceAuthorityService{
	
	@Autowired
	private SysResourceAuthorityDao sysResourceAuthorityDao;
	
	@Autowired
	private SysResourceDao sysResourceDao;
	
	@Autowired
	private SysAuthorityDao sysAuthorityDao;
	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityResourceAuthorityService#queryByResource(java.lang.String)
	 */

	public SysResources queryByResource(String resourceId) {
		return  sysResourceDao.find(SysResources.class,resourceId);

	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityResourceAuthorityService#queryByResource(com.author.system.bean.SysResources)
	 */

	public List<SysAuthoritiesResources> queryByResource(SysResources resource) {
		List<SysAuthoritiesResources> list = this.sysResourceAuthorityDao.findBySysResources(resource);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityResourceAuthorityService#addBatch(java.lang.String, java.lang.String[])
	 */

	public String addBatch(String resourceId, String[] sysAuthorities) {
		this.deleteByResourceId(resourceId);
		
		if(!ObjectUtils.isEmpty(sysAuthorities)){
			List<SysAuthoritiesResources> list = new ArrayList<SysAuthoritiesResources>();
			//SysResources sysResources = this.sysResourceRepository.findOne(resourceId);
			SysResources sysResources = new SysResources(resourceId);
			for(String auid : sysAuthorities){
				SysAuthoritiesResources bean = new SysAuthoritiesResources();
				bean.setSysResources(sysResources);
				SysAuthorities auth = new SysAuthorities();
				auth.setAuthorityId(auid);
				bean.setSysAuthorities(auth);
				list.add(bean);
			}
			this.sysResourceAuthorityDao.saveAll(list);
		}
	
		return "权限分配成功";
	}
	
	public String deleteByResourceId(String resourceId){
		this.sysResourceAuthorityDao.deleteByResourceId(resourceId);
		return "已删除此资源对应的所有权限!";
	}

}
