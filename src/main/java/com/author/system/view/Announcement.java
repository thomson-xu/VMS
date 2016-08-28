/**
 * 
 */
package com.author.system.view;

import com.author.base.model.Parameters;
import com.sun.deploy.services.DefaultService;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;


@Named
@Scope("request")
public class Announcement {
	
	private DefaultService defaultService;

	public void getByPage( Parameters params){

		
		return ;
	}
}
