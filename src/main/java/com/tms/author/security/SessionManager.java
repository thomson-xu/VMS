package com.tms.author.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SessionManager implements SessionAuthenticationStrategy {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.session.SessionAuthenticationStrategy#onAuthentication(org.springframework.security.core.Authentication, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onAuthentication(Authentication authentication,
			HttpServletRequest request, HttpServletResponse response)
			throws SessionAuthenticationException {
		// TODO Auto-generated method stub

	}

}
