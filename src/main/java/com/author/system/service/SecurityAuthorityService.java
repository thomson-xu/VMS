/**
 * 
 */
package com.author.system.service;

import java.util.List;

import com.visa.dao.util.BaseJpaDao;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.author.base.MessageFactory;
import com.author.base.model.Parameters;
import com.author.system.bean.SysAuthorities;
import com.author.system.dao.SysAuthorityDao;

@Service
public class SecurityAuthorityService  {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private BaseJpaDao baseDao;
	
	@Autowired
	private SysAuthorityDao sysAuthorityDao;
	
	@Autowired
	private SysRolesAuthoritiesDao sysRolesAuthoritiesDao;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MessageFactory messageFactory;

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityAuthorityService#add(com.author.system.bean.SysAuthorities)
	 */
	@Override
	public void add(SysAuthorities entity) throws Exception {
		baseDao.create(entity);
		logger.info("\n添加权限:"+JSONObject.fromObject(entity));
		return ;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityAuthorityService#update(com.author.system.bean.SysAuthorities)
	 */
	@Override
	public void update(SysAuthorities entity) throws Exception {
		
		this.baseDao.update(entity);
		logger.info("\n修改权限:"+JSONObject.fromObject(entity));
		
		return ;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityAuthorityService#delete(com.author.system.bean.SysAuthorities)
	 */
	@Override
	public void delete(SysAuthorities entity) throws Exception {
		// TODO Auto-generated method stub
		return ;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityAuthorityService#delete(java.lang.String)
	 */
	@Override
	public void delete(String entityId) throws Exception {
		this.baseDao.delete(SysAuthorities.class, entityId);
		

		logger.info("\n删除权限:"+entityId);
		
		return ;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityAuthorityService#query()
	 */
	@Override
	public void query(Parameters params) throws Exception {
		/*ResultModel rm = this.baseDao.queryByJPQL(SysAuthorities.class, null, params.getStart(), params.getLimit());
		Message msg = new Message(rm.getTotalCount(), rm.getList());
		return msg;*/
		Order order1 = new Order("moduleId");
		Order order2 = new Order("enable");
		Sort sort = new Sort(order1,order2);
		PageRequest pageRequest = new PageRequest(params.getSpringDataPage(),params.getLimit(),sort);
		Page<SysAuthorities> page = this.sysAuthorityDao.findAll(pageRequest);
		return ;
	}
	
	public List<SysAuthorities> queryAll(){
		List<SysAuthorities> list = this.sysAuthorityDao.findAll();
		return list;
	}
	
	public List<SysAuthorities> queryEnabled(){
		List<SysAuthorities> list = this.sysRolesAuthoritiesDao.findByEnabled();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.author.system.service.SecurityAuthorityService#enable(java.lang.String, boolean)
	 */
	@Override
	public void enable(String entityId, boolean enable) throws Exception {
		
		SysAuthorities bean = (SysAuthorities) this.baseDao.get(SysAuthorities.class, entityId);
		bean.setEnable(enable);
		String msgPropertyName = enable?"ddl.enabled":"ddl.disabled";

		logger.info("ID为："+entityId+"的权限项"+msgPropertyName);
		
		return  ;
	}
	
	/**
	 * 根据ModuleId获取权限集合，如果有moduleId则根据moduleId获取，如果没有就获取moduleId是null的权限
	 * @param moudleId
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
