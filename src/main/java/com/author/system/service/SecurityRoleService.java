package com.author.system.service;

import com.author.base.model.Parameters;
import com.author.base.session.UserSessionContext;
import com.author.system.bean.*;
import com.author.system.dao.SysRoleDao;
import com.author.system.dao.SysRolesAuthoritiesDao;
import com.author.system.dao.SysRolesModulesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class SecurityRoleService {
	
	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRolesModulesDao sysRolesModulesDao;
	
	@Autowired
	private SysRolesAuthoritiesDao sysRolesAuthoritiesDao;

	
	@Autowired
	private UserSessionContext userSessionContext;

	/*
	 * (non-Javadoc)
	 * @see com.author.system.service.SysRoleService#add(com.author.system.bean.SysRoles)
	 */
	public SysRoles add(SysRoles entity) {
		entity.setUserId(this.userSessionContext.getUserId());
		this.sysRoleDao.create(entity);
		return entity;
	}

	public SysRoles update(SysRoles entity) {
		entity.setUserId(this.userSessionContext.getUserId());
		this.sysRoleDao.update(entity);
		return entity;
	}


	public SysRoles delete(SysRoles entity) {
		this.sysRoleDao.delete(SysRoles.class,entity.getRoleId());
		return entity;
	}


	public String delete(String roleId) {
		this.sysRoleDao.delete(SysRoles.class,roleId);
		return "delete SysRoles";
	}
	
	/*
	 * @see com.author.system.service.SysRoleService#queryByPage(com.author.base.model.Parameters)
	 */
	/*@Override
	public Message queryByPage(Parameters params) {
		PageRequest pageable = new PageRequest(params.getSpringDataPage(), params.getLimit());
		Page<SysRoles> page = this.sysRoleDao.findAll(pageable);
		Long totalCount = page.getTotalElements();
		Message msg = new Message(totalCount.intValue(),page.getContent());
		return msg;
	}
	*/
	/*
	 * @see com.author.system.service.SysRoleService#queryAllEnabled(Parameters)
	 */
	/*@Override
	public Message queryAllEnabled(Parameters params){
		PageRequest pageable = new PageRequest(params.getSpringDataPage(), params.getLimit());
		Page<SysRoles> page = this.sysRoleDao.findAllEnabeld(pageable);
		Long totalCount = page.getTotalElements();
		Message msg = new Message(totalCount.intValue(),page.getContent());
		return msg;
	}*/
	
	public Page<SysRoles> findByRoleNameLikeAndEnableed(String roleName,Parameters params) throws Exception{
		PageRequest pageable = new PageRequest(params.getSpringDataPage(), params.getLimit());
		Page<SysRoles> page = null;
		if(roleName == null){
			page = this.sysRoleDao.findAllEnabeld(pageable);
		}else{
			roleName = "%"+roleName+"%";
			page = this.sysRoleDao.findByRoleNameLikeAndEnabled(roleName, pageable);
		}
		//Message msg = new Message(totalCount.intValue(),page.getContent());
		return page;
	}
	/*
	 * @see com.author.system.service.SysRoleService#getById(String)
	 */

	public SysRoles getById(String roleId) {
		SysRoles entity = this.sysRoleDao.find(SysRoles.class,roleId);
		return entity;
	}

	/* 
	 * @see com.author.system.service.SysRoleService#enabled(boolean)
	 */

	public SysRoles enabled(String roleId,boolean enable) {
		SysRoles entity = this.sysRoleDao.find(SysRoles.class,roleId);
		entity.setEnable(enable);
		return entity;
	}
	/*
	 * @see com.author.system.service.SysRoleService#getModulesByRoleId(java.lang.String)
	 */

	public String[] getModulesByRoleId(String roleId){
		List<SysModules> list = this.sysRolesModulesDao.getModulesByRoleId(roleId);
		String[] moduleIds = new String[list.size()];
		for(int i=0;i<list.size();i++){
			SysModules bean = list.get(i);
			moduleIds[i] = bean.getModuleId();
		}
		return moduleIds;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityRoleService#saveRoleModules(java.lang.String, java.lang.String[], java.lang.String)
	 */

	public void saveRoleModules(String roleId, String[] adds, String[] removes) {
		//批量删除已撤销的模块
		if(!ObjectUtils.isEmpty(removes)){
			Collection<String> modules =null;
			  for(int i=0; i<removes.length ; i++){
				      modules.add(removes[i]);
			  }
			this.sysRolesModulesDao.deleteByRoleIdAndModuleId(roleId, modules);
		}
		
		List<SysRolesModules> list = new ArrayList<SysRolesModules>();
		//批量添加新添加的模块
		if(!ObjectUtils.isEmpty(adds)){
			for(int i=0;i<adds.length;i++){
				String moduleId = adds[i];
				SysRolesModules bean = new SysRolesModules();
				bean.setModuleId(moduleId);
				bean.setRoleId(roleId);
				bean.setSysRoles(new SysRoles(roleId));
				bean.setSysModules(new SysModules(moduleId));
				list.add(bean);
			}
			this.sysRolesModulesDao.saveAll(list);
		}

	}
	
	public String saveRoleAuthorities(String roleId,String[] sysAuthorities){
		this.sysRolesAuthoritiesDao.deleteByRoleId(roleId);
		
		List<SysRolesAuthorities> list = new ArrayList<SysRolesAuthorities>();
		
		if(!ObjectUtils.isEmpty(sysAuthorities)){
			for(int i=0;i<sysAuthorities.length;i++){
				String authorityId = sysAuthorities[i];
				SysRolesAuthorities bean = new SysRolesAuthorities();
				bean.setRoleId(roleId);
				bean.setAuthorityId(authorityId);
				bean.setSysRoles(new SysRoles(roleId));
				bean.setSysAuthorities(new SysAuthorities(authorityId));
				list.add(bean);
			}
			this.sysRolesAuthoritiesDao.saveAll(list);
		}
		
		return "seccessfull";
	}
	
	public List<SysModules> getLeafModulesByRoleId(String roleId){
		
		List<SysModules> list = this.sysRolesModulesDao.getModulesByRoleId(roleId);
		
		return list;
	}
	
	/*
	 * @see com.author.system.service.SecurityRoleService#getAuthorityByRoleId(java.lang.String)
	 */
	public List<SysAuthorities> getAuthorityByRoleId(String roleId){
		
		List<SysAuthorities> list = this.sysRolesAuthoritiesDao.getAssignationAuthority(roleId);
		
		return list;
	}
	
	public String[]  queryByRoleId(String roleId){
		 String [] params=null;
		params[0]= roleId;
		List<SysRolesAuthorities> list = this.sysRolesAuthoritiesDao.queryByWhere(SysRolesAuthorities.class,"o.roleId=:roleId",params);
		
		String[] idPools = new String[list.size()];
		
		int index = 0;
		
		for(SysRolesAuthorities bean : list){
			idPools[index] = bean.getAuthorityId();
			index++;
		}
		return  idPools;

	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityRoleService#findByRoleNameLike(java.lang.String, com.author.base.model.Parameters)
	 */
	/*@Override
	public Message findByRoleNameLike(String roleName, Parameters params) throws Exception {
		Order order = new Order("issys");
		Sort sort = new Sort(order);
		PageRequest pageable = new PageRequest(params.getSpringDataPage(), params.getLimit(),sort);
		//pageable.
		Page<SysRoles> page = null;
		if(roleName == null){
			page = this.sysRoleDao.findAll(pageable);
		}else{
			roleName = "%"+ServletUtils.transcoding(roleName)+"%";
			page = this.sysRoleDao.findByRoleNameLike(roleName, pageable);
		}
		Message msg = this.messageFactory.query(page);
		return msg;
	}*/

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityRoleService#queryAllEnabled()
	 */

	public List<SysRoles> queryAllEnabled() {
		List<SysRoles> list = this.sysRoleDao.findAllEnabeld(true);

		return list;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityRoleService#findByUserId(java.lang.String)
	 */

	public List<SysRoles>  findByUserId(String userId) throws Exception {
		List<SysRoles> list = this.sysRoleDao.findByUserId(userId);
		return list;
	}
	

	public List<SysRoles> findByUserId(boolean enable,String userId){
		List<SysRoles> list = this.sysRoleDao.findByUserId(enable,userId);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityRoleService#findByIssys()
	 */

	public List<SysAuthorities> findByIssys() {
		List<SysAuthorities> list = this.sysRolesAuthoritiesDao.findByIssys(false);
		return (list);
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityRoleService#saveModuleAndAuthority(java.lang.String, java.util.Map, java.lang.String[])
	 */

	public String saveModuleAndAuthority(String roleId, Map<?, ?> sysModules,
			String[] sysAuthorities) {
		//String[] adds = ;
		//this.saveRoleModules(roleId, adds, removes);
		List<String> addList = (List<String>)sysModules.get("add");
		List<String> removeList = (List<String>)sysModules.get("remove");
		
		String[] adds = new String[addList.size()];
		addList.toArray(adds);
		String[] removes = new String[removeList.size()];
		removeList.toArray(removes);
		
		//this.saveRoleModules(roleId, adds, removes);
		this.saveRoleAuthorities(roleId, sysAuthorities);
		
		return "0";
	}

}
