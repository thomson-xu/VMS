/**
 * 
 */
package com.tms.author.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleRememberMeServices extends AbstractRememberMeServices{

	protected SimpleRememberMeServices(String key, UserDetailsService userDetailsService) {
		super(key, userDetailsService);
	}

	/* (non-Javadoc)
         * @see org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices#onLoginSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
         */
	@Override
	protected void onLoginSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication successfulAuthentication) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices#processAutoLoginCookie(java.lang.String[], javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected UserDetails processAutoLoginCookie(String[] cookieTokens,
			HttpServletRequest request, HttpServletResponse response)
			throws RememberMeAuthenticationException, UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


}
