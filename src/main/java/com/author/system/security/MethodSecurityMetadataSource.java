/**
 * 
 */
package com.author.system.security;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.method.AbstractMethodSecurityMetadataSource;

import com.author.system.repository.SysResourceRepository;

/**
 * 类功能说明：通过数据库管理方法
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-1-18 上午11:46:47
 * @version v1.0
 *
 */
public class MethodSecurityMetadataSource extends
		AbstractMethodSecurityMetadataSource implements InitializingBean{
	
	protected Log logger = LogFactory.getLog(getClass());
	
	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections.emptyList();
	
	private final static String RES_KEY = "resourcePath";
	private final static String AUTH_KEY = "authorityMark";
	
	private Map<MethodKey, Collection<ConfigAttribute>> requestMap;
	
	@Autowired
	private SysResourceRepository sysResourceRepository;
	
	/**
	 * 根据方法获取到访问方法所需要的权限
	 * @param method 访问的方法
	 * @param targetClass 方法所属的类
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Method method,
			Class<?> targetClass) {
		MethodKey key = new MethodKey(method);
		Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;
		
		for (Map.Entry<MethodKey, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().equals(key)) {
            	attrs =  entry.getValue();
            	break;
            }
        }
		//logger.info("METHOD资源："+key.getFullMethodName()+ " -> " +attrs);
		
		return attrs;
	}

	/**
	 * 获取到所有方法对应的权限集合
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<MethodKey, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
	}

	/**
	 * 初始化方法权限对应集合，绑定方法权限集合
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		this.requestMap = this.bindRequestMap();
	}
	
	/**
	 * 从数据库中获取方法及权限对应信息
	 * @return
	 */
	private Map<String,String> loadMehod(){
		Map<String,String> resMap = new LinkedHashMap<String, String>();
		List<Map<String,String>> list = this.sysResourceRepository.getMethodResourceMapping();
		
		for(Map<String,String> map : list){
			String resourcePath = map.get(RES_KEY);
			String authorityMark = map.get(AUTH_KEY);
			
			if(resMap.containsKey(resourcePath)){
				String mark = resMap.get(resourcePath);
				resMap.put(resourcePath, mark+","+authorityMark);
			}else{
				resMap.put(resourcePath, authorityMark);
			}
		}
		
		return resMap;
	}
	
	/**
	 * 封装从数据库中获取的方法权限集合
	 * @return
	 */
	public Map<MethodKey, Collection<ConfigAttribute>> bindRequestMap(){
		Map<MethodKey, Collection<ConfigAttribute>> resMap = 
				new LinkedHashMap<MethodKey, Collection<ConfigAttribute>>();
		
		Map<String,String> map = this.loadMehod();
		for(Map.Entry<String, String> entry : map.entrySet()){
			MethodKey key = new MethodKey(entry.getKey());
			Collection<ConfigAttribute> atts = 
					SecurityConfig.createListFromCommaDelimitedString(entry.getValue());
			
			resMap.put(key, atts);
		}
		
		return resMap;
	}

}
