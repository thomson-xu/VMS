package com.author.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.author.base.model.Message;
import com.author.base.model.Parameters;
import com.author.base.model.ResultModel;
import com.author.common.debug.DebugUtil;
import com.author.common.utils.MessageManager;
import com.author.common.web.JSONFormat;
import com.author.common.web.controller.ControllerTools;
import com.author.system.bean.BaseXzqh;
import com.author.system.dao.BaseDao;
import com.author.system.service.DefaultService;



@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class DefaultServiceImpl implements DefaultService {
	
	@Autowired
	private BaseDao baseDao;
	
	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public Message getEntity(String className,String id)
			throws Exception {
		Object entity = baseDao.get(Class.forName(className), id);
		Message msg = new Message();
		msg.setSuccess(true);
		msg.setFlag(true);
		msg.setData(entity);
		return msg;
	}
	
	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public Message getEntity(Class clz,String id)
			throws Exception {
		Object entity = baseDao.get(clz, id);
		Message msg = new Message();
		msg.setSuccess(true);
		msg.setFlag(true);
		msg.setData(entity);
		return msg;
	}
	
	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public Message savaEntity(HttpServletRequest request, Parameters params)
			throws Exception {
		Object entity = ControllerTools.resolvePayloadEX(request);
		baseDao.save(entity);
		Message msg = MessageManager.save();
		return msg;
	}

	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public Message updateEntity(HttpServletRequest request, Parameters params)
			throws Exception {
		Object entity = ControllerTools.resolvePayloadEX(request);
		baseDao.update(entity);
		Message msg = MessageManager.save();
		return msg;
	}

	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public Message deleteEntity(String className,String id)
			throws Exception {
		baseDao.delete(className, id);
		Message msg = MessageManager.delete();
		return msg;
	}

	@Override
	//@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public Message query(HttpServletRequest request, Parameters params)
			throws Exception {
		Class clz = null;
		clz = Class.forName(params.getClassName());
		Map<String,Object> map = null;
		if(params.getParams()!=null && !"".equals(params.getParams())){
			map = JSONObject.fromObject(params.getParams());
			map = JSONFormat.bindType(clz, map);
		}
		ResultModel rsm = baseDao.queryByJPQL(clz, map, params.getOrderarr(), params.getStart(), params.getLimit());
		Message msg = new Message(rsm.getTotalCount(),rsm.getList());
		return msg;
	}
	
	@Override
	//@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public Message queryAll(HttpServletRequest request, Parameters params)
			throws Exception {
		Class clz = null;
		clz = Class.forName(params.getClassName());
		Map<String,Object> map = null;
		if(params.getParams()!=null && !"".equals(params.getParams())){
			map = JSONObject.fromObject(params.getParams());
			map = JSONFormat.bindType(clz, map);
		}
		ResultModel rs = baseDao.queryByJPQL(clz, map,params.getOrderarr());  
		Message msg = new Message(rs.getList());
		return msg;
	}
	
	public Message fuzzyQueryAll(HttpServletRequest request, Parameters params)
			throws Exception {
		Class clz = null;
		clz = Class.forName(params.getClassName());
		Map<String,Object> map = null;
		if(params.getParams()!=null && !"".equals(params.getParams())){
			map = JSONObject.fromObject(params.getParams());
			map = JSONFormat.bindType(clz, map);
		}
		List rs = baseDao.fuzzyQuery(clz, map,params.getOrderarr());  
		Message msg = new Message(rs);
		return msg;
	}

	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public Message updateEntityEx(HttpServletRequest request, Parameters params)
			throws Exception {
		Object entity = ControllerTools.resolvePayloadEX(request);
		/*DebugUtil.println(entity);
		WsjdSqjgxx en = (WsjdSqjgxx) entity;
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	    String str = formatDate.format(en.getDtTgsj());
	    System.out.println("entity:" + en.getDtTgsj());
		System.out.println("str:" + str);*/
	    this.baseDao.updateEx(entity);
		Message msg = MessageManager.update();
		return msg;
	}
	
	public Message getDepTreeNode(HttpServletRequest request, String id) 
			throws Exception{
		Message msg = new Message();
		List<BaseXzqh> list = new ArrayList<BaseXzqh>();
		
		if(id==null || "".equals(id)){
			msg.setSuccess(true);
			msg.setRoots(list);
		}else if("root".equals(id)){
			list = this.baseDao.query(BaseXzqh.class,"ilevel", 1);
		}else{
			list = this.baseDao.query(BaseXzqh.class,"parentId",id);
		}
		for(int i=0;i<list.size();i++){
			BaseXzqh bean = list.get(i);
			if(bean.getIlevel()==3)bean.setLeaf(true);
		}
		msg.setRoots(list);
		msg.setSuccess(true);
		return msg;
	}
	
	/* (non-Javadoc)
	 * @see com.author.system.service.DefaultService#savaEntity(java.lang.Object)
	 */
	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public Message savaEntity(Object obj) throws Exception {
		baseDao.save(obj);
		Message msg = MessageManager.save();
		return msg;
	}

	

}
