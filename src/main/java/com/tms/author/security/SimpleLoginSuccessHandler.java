/**
 * 
 */
package com.tms.author.security;

import com.tms.base.exception.InitializationException;
import com.tms.author.bean.SysUser;
import com.tms.author.dao.SysUsersDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 类功能说明：在登录成功后把用户的登录时间及登录IP记录到数据库
 *
 */
public class SimpleLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements InitializingBean{
	
	protected Log logger = LogFactory.getLog(getClass());
	
	private String defaultTargetUrl;
	
	private boolean forwardToDestination = false;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private SysUsersDao sysUsersDao;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		this.saveLoginInfo(request, authentication);
		
		if(this.forwardToDestination){
			logger.info("Login success,Forwarding to "+this.defaultTargetUrl);
			
			request.getRequestDispatcher(this.defaultTargetUrl).forward(request, response);
		}else{
			logger.info("Login success,Redirecting to "+this.defaultTargetUrl);
			this.redirectStrategy.sendRedirect(request, response, this.defaultTargetUrl);
		}
	}
	
	@Transactional(readOnly=false,propagation= Propagation.REQUIRED,rollbackFor={Exception.class})
	public void saveLoginInfo(HttpServletRequest request,Authentication authentication){
		SysUser user = (SysUser)authentication.getPrincipal();
		try {
			String ip = this.getIpAddress(request);
			Date date = new Date();
			user.setLastLogin(date);
			user.setLoginIp(ip);
			this.sysUsersDao.update(user);
		} catch (DataAccessException e) {
			if(logger.isWarnEnabled()){
				logger.info("无法更新用户登录信息至数据库");
			}
		}
	}
	
	public String getIpAddress(HttpServletRequest request){  
	    String ip = request.getHeader("x-forwarded-for");  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}

	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}

	public void setForwardToDestination(boolean forwardToDestination) {
		this.forwardToDestination = forwardToDestination;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if(StringUtils.isEmpty(defaultTargetUrl))
			throw new InitializationException("You must configure defaultTargetUrl");
		
	}

}
