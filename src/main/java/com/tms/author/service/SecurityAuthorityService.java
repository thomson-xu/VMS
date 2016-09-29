/**
 * 
 */
package com.tms.author.service;

import com.tms.author.base.model.Parameters;
import com.tms.author.dao.SysAuthorityDao;
import com.tms.author.dao.SysRolesAuthoritiesDao;
import com.tms.author.bean.SysAuthorities;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SecurityAuthorityService  {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private SysAuthorityDao sysAuthorityDao;
	
	@Autowired
	private SysRolesAuthoritiesDao sysRolesAuthoritiesDao;
	
	@Autowired
	private MessageSource messageSource;


	/* (non-Javadoc)
	 * @see SecurityAuthorityService#add(SysAuthorities)
	 */

	public void add(SysAuthorities entity) throws Exception {
		sysAuthorityDao.create(entity);
		logger.info("\n添加权限:"+entity.getAuthorityName());
		return ;
	}

	/* (non-Javadoc)
	 * @see SecurityAuthorityService#update(SysAuthorities)
	 */

	public void update(SysAuthorities entity) throws Exception {
		
		this.sysAuthorityDao.update(entity);
		logger.info("\n修改权限:"+entity.getAuthorityName());
		
		return ;
	}

	/* (non-Javadoc)
	 * @see SecurityAuthorityService#delete(SysAuthorities)
	 */

	public void delete(SysAuthorities entity) throws Exception {
		// TODO Auto-generated method stub
		return ;
	}

	/* (non-Javadoc)
	 * @see SecurityAuthorityService#delete(java.lang.String)
	 */

	public void delete(String entityId) throws Exception {
		this.sysAuthorityDao.delete(SysAuthorities.class, entityId);
		

		logger.info("\n删除权限:"+entityId);
		
		return ;
	}

	/* (non-Javadoc)
	 * @see SecurityAuthorityService#query()
	 */

	public void query(Parameters params) throws Exception {
		/*ResultModel rm = this.baseDao.queryByJPQL(SysAuthorities.class, null, params.getStart(), params.getLimit());
		Message msg = new Message(rm.getTotalCount(), rm.getList());
		return msg;
		Order order1 = new Order("moduleId");
		Order order2 = new Order("enable");
		Sort sort = new Sort(order1,order2);
		PageRequest pageRequest = new PageRequest(params.getSpringDataPage(),params.getLimit(),sort);
		Page<SysAuthorities> page = this.sysAuthorityDao.find(pageRequest);  */
		return ;
	}
	
	public List<SysAuthorities> queryAll(){
		//List<SysAuthorities> list = this.sysAuthorityDao.findAll();
		return null;
	}
	
	public List<SysAuthorities> queryEnabled(){
		List<SysAuthorities> list = this.sysRolesAuthoritiesDao.findByEnabled();
		return list;
	}

	/* (non-Javadoc)
	 * @see SecurityAuthorityService#enable(java.lang.String, boolean)
	 */

	public void enable(String entityId, boolean enable) throws Exception {
		
		SysAuthorities bean = (SysAuthorities) this.sysAuthorityDao.find(SysAuthorities.class, entityId);
		bean.setEnable(enable);
		String msgPropertyName = enable?"ddl.enabled":"ddl.disabled";

		logger.info("ID为："+entityId+"的权限项"+msgPropertyName);
		
		return  ;
	}
	
	/**
	 * 根据ModuleId获取权限集合，如果有moduleId则根据moduleId获取，如果没有就获取moduleId是null的权限
	 * @param
	 * @return
	 */
	public List<SysAuthorities> queryByModuleId(String moduleId){
		List<SysAuthorities> list = null;
		if(StringUtils.isEmpty(moduleId)){
			list = this.sysAuthorityDao.findByModuleIdIsNull();
		}else{
			list = this.sysAuthorityDao.findByModuleId(moduleId);
		}
		
		return list;
	}
	
}
