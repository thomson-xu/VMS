package com.author.system.security;

import com.author.system.dao.SysResourceDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 类功能说明：通过数据库来管理资源，通过数据获取到资源权限列表
 *
 *
 */
public class URLSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource,InitializingBean {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections.emptyList();
	
	//private MatcherType requestMatcher = MatcherType.ant; 
	
	private final static String RES_KEY = "resourcePath";
	private final static String AUTH_KEY = "authorityMark";
	
	//权限集合
	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
	
	@Autowired
	private SysResourceDao sysResourceDao;

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        
        Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;
        
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
            	attrs =  entry.getValue();
            	break;
            }
        }
        logger.info("URL source:"+request.getRequestURI()+ " " + attrs);
        return attrs;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	private Map<String,String> loadResuorce(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		
		List<Map<String,String>> list = this.sysResourceDao.getURLResourceMapping();
		Iterator<Map<String,String>> it = list.iterator();
		while(it.hasNext()){
			Map<String,String> rs = it.next();
			String resourcePath = rs.get(RES_KEY);
			String authorityMark = rs.get(AUTH_KEY);
			
			if(map.containsKey(resourcePath)){
				String mark = map.get(resourcePath);
				map.put(resourcePath, mark+","+authorityMark);
			}else{
				map.put(resourcePath, authorityMark);
			}
		}
		return map;
	}
	
	protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap(){
		Map<RequestMatcher, Collection<ConfigAttribute>> map = 
				new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		
		Map<String,String> resMap = this.loadResuorce();
		for(Map.Entry<String,String> entry:resMap.entrySet()){
			String key = entry.getKey();
			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
			atts = SecurityConfig.createListFromCommaDelimitedString(entry.getValue());
			map.put(new AntPathRequestMatcher(key), atts);
		}
		
		return map;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		this.requestMap = this.bindRequestMap();
		logger.info("资源权限列表"+this.requestMap);
	}
	
	public void refreshResuorceMap(){
		this.requestMap = this.bindRequestMap();
	}

	/*public void setRequestMatcher(MatcherType requestMatcher) {
		this.requestMatcher = requestMatcher;
	}*/

}
