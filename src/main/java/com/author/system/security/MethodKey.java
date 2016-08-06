/**
 * 
 */
package com.author.system.security;

import java.lang.reflect.Method;

import org.springframework.util.StringUtils;


/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-24 下午2:55:24
 * @version v1.0
 *
 */
public class MethodKey {
	private String className;
	private String methodName;
	
	public MethodKey(){};
	
	public MethodKey(String fullName){
		this.className = StringUtils.stripFilenameExtension(fullName);
		this.methodName = StringUtils.getFilenameExtension(fullName);
	};
	
	public MethodKey(Method method) {
		super();
		this.className = method.getDeclaringClass().getName();
		this.methodName = method.getName();
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getFullMethodName(){
		return this.className + "." + this.methodName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MethodKey))return false;
		
		MethodKey target = (MethodKey)obj;
		
		if(this.className.equals(target.getClassName()) && 
				this.methodName.equals(target.getMethodName()))return true;
		
		return false;
	}
	
}
