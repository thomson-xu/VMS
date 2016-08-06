/**
 * 
 */
package com.author.system.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-18 上午9:27:56
 * @version v1.0
 *
 */
public class SysResourceRepositoryImpl {
	protected Log logger = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getURLResourceMapping(){
		String sql = "SELECT S3.RESOURCE_PATH,S2.AUTHORITY_MARK FROM SYS_AUTHORITIES_RESOURCES S1 "+
				"JOIN SYS_AUTHORITIES S2 ON S1.AUTHORITY_ID = S2.AUTHORITY_ID "+
				"JOIN SYS_RESOURCES S3 ON S1.RESOURCE_ID = S3.RESOURCE_ID AND S3.RESOURCE_TYPE='URL' ORDER BY S3.PRIORITY DESC";
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		Query query = this.entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		Iterator<Object[]> it = result.iterator();
		
		while(it.hasNext()){
			Object[] o = it.next();
			Map<String,String> map = new HashMap<String,String>();
			map.put("resourcePath", (String)o[0]);
			map.put("authorityMark", (String)o[1]);
			list.add(map);
		}
		
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getMethodResourceMapping(){
		String sql = "SELECT S3.RESOURCE_PATH,S2.AUTHORITY_MARK FROM SYS_AUTHORITIES_RESOURCES S1 "+
				"JOIN SYS_AUTHORITIES S2 ON S1.AUTHORITY_ID = S2.AUTHORITY_ID "+
				"JOIN SYS_RESOURCES S3 ON S1.RESOURCE_ID = S3.RESOURCE_ID AND S3.RESOURCE_TYPE='METHOD' ORDER BY S3.PRIORITY DESC";
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		Query query = this.entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		Iterator<Object[]> it = result.iterator();
		
		while(it.hasNext()){
			Object[] o = it.next();
			Map<String,String> map = new HashMap<String,String>();
			map.put("resourcePath", (String)o[0]);
			map.put("authorityMark", (String)o[1]);
			list.add(map);
		}
		
		return list;
	}
	
}
