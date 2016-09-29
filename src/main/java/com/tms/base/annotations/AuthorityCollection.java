/**
 * 
 */
package com.tms.base.annotations;

import com.tms.base.AuthorityType;

import java.lang.annotation.*;

/**
 * 类功能说明：权限集合
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-17 上午11:39:27
 * @version v1.0
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorityCollection {
	
	AuthorityType[] value();

}
