/**
 * 
 */
package com.tms.base.annotations;

import com.tms.base.enums.QueryType;

import java.lang.annotation.*;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-30 下午3:56:22
 * @version v1.0
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryBuilder {
	QueryType value();
}
