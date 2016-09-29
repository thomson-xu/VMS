/**
 * 
 */
package com.tms.author.view;

import com.sun.deploy.services.DefaultService;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;


@Named
@Scope("request")
public class Announcement {
	
	private DefaultService defaultService;


}
