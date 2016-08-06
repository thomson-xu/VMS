/**
 * 
 */
package com.author.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.author.base.model.Message;
import com.author.base.model.Parameters;
import com.author.system.service.DefaultService;

/**
 * 类功能说明：公告控制器
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:新中软科技有限公司</p>
 * @author 王成委
 * @date 2013-12-16 下午4:03:16
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/gonggao")
public class AnnouncementController {
	
	private DefaultService defaultService;
	
	/**
	 * 获取自己发布的公告
	 * @return
	 */
	@RequestMapping("/getbypage")
	@ResponseBody
	public Message getByPage(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute Parameters params) throws Exception{
		
		Message message = this.defaultService.query(request, params);
		
		return message;
	}
}
