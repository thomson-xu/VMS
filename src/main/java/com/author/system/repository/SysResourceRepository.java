/**
 * 
 */
package com.author.system.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.author.system.bean.SysResources;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-31 上午9:06:57
 * @version v1.0
 *
 */
public interface SysResourceRepository extends JpaRepository<SysResources,String>{
	
	public Page<SysResources> findByResourceNameLike(String resourceName,Pageable pageable);
	
	@Query("select x from SysResources x where x.resourceName like ?1 or x.resourcePath like ?2")
	public Page<SysResources> findByResourceNameOrResourcePath(String resourceName,String resourcePath,Pageable pageable);
	
	public List<Map<String,String>> getURLResourceMapping();
	
	public List<Map<String,String>> getMethodResourceMapping();
}
