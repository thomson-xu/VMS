/**
 * 
 */
package com.author.system.dao;

import com.author.system.bean.TestBlob;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2014-2-11 下午5:28:24
 * @version v1.0
 *
 */
public interface BlobTestDao {
	public TestBlob save(TestBlob bean);
	public void updateBlob(String id,String blob);
	
}
