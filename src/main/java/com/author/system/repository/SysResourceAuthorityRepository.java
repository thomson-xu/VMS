package com.author.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.author.system.bean.SysAuthoritiesResources;
import com.author.system.bean.SysResources;

/**
 * 类功能说明：
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-31 下午1:35:15
 * @version v1.0
 *
 */
public interface SysResourceAuthorityRepository extends JpaRepository<SysAuthoritiesResources, String>{
	
	List<SysAuthoritiesResources> findBySysResources(SysResources resource);
	
	public void deleteByResourceId(String resourceId);
	
}
