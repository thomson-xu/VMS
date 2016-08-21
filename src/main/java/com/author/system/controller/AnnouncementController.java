/**
 * 
 */
package com.author.system.controller;

import com.author.base.model.Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类功能说明：公告控制器
 * 
 * <p>Copyright: Copyright © 2012-2013 author.com Inc.</p>
 * <p>Company:</p>
 * @author
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
