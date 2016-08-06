package com.author.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.author.base.MessageFactory;
import com.author.base.controller.BaseController;
import com.author.base.model.Message;
import com.author.base.model.Parameters;
import com.author.common.web.controller.ControllerTools;
import com.author.system.service.DefaultService;


/**
 * 
 * @author 王成委
 * 
 * 提供公共增删改查及获取机构树的方法
 *
 */
@Controller
@RequestMapping("/base")
public class DefaultController extends BaseController{
	
	@Autowired
	//@Resource(name = "defaultService")
	private DefaultService defaultService;
	
	@Autowired
	private MessageFactory messageFactory;
	
	@RequestMapping(value = "/get",method = RequestMethod.GET)
	public String getEntity(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute Parameters params) throws Exception{
		Message msg = this.defaultService.getEntity(params.getClassName(), params.getId());
		ControllerTools.print(response, msg);
		return null;
	}
	
	@RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
	public String getEntity(HttpServletRequest request,
			HttpServletResponse response,@PathVariable("id") String id,
			@ModelAttribute Parameters params) throws Exception{
		Message msg = this.defaultService.getEntity(params.getClassName(), id);
		ControllerTools.print(response, msg);
		return null;
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String saveEntity(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute Parameters params) throws Exception{
		Message msg = this.defaultService.savaEntity(request, params);
		ControllerTools.print(response, msg);
		return null;
	}
	
	
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST)//GET
	public String deleteEntity(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute Parameters params) throws Exception{
		Message msg = this.defaultService.deleteEntity(params.getClassName(), params.getId());
		ControllerTools.print(response, msg);
		return null;
	}
	
	@RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
	public String deleteEntity(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute Parameters params,@PathVariable("id") String id) throws Exception{
		Message msg = this.defaultService.deleteEntity(params.getClassName(), id);
		ControllerTools.print(response, msg);
		return null;
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String updateEntity(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute Parameters params) throws Exception{
		Message msg = defaultService.updateEntity(request, params);
		ControllerTools.print(response, msg);
		return null;
	}
	@RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
	public String updateEntity(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute Parameters params,@PathVariable("id") String id) throws Exception{
		params.setId(id);
		Message msg = defaultService.updateEntityEx(request, params);
		ControllerTools.print(response, msg);
		return null;
	}
	
	@RequestMapping(value = "/query")
	@ResponseBody
	public Message query(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute Parameters params) throws Exception{
		Message msg = defaultService.query(request, params);
		//ControllerTools.print(response, msg);
		return msg;
	}
	
	@RequestMapping(value = "/query/all")
	@ResponseBody
	public Message queryAll(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute Parameters params) throws Exception{
		Message msg = defaultService.queryAll(request, params);
		//ControllerTools.print(response, msg);
		return msg;
	}
	
	@RequestMapping(value = "/fuzzyquery/all")
	public void fuzzyQueryAll(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute Parameters params) throws Exception{
		Message msg = defaultService.fuzzyQueryAll(request, params);
		ControllerTools.print(response, msg);
	}
	
	@RequestMapping(value = "/tree/dep/{id}")
	@ResponseBody
	public Message getTree(HttpServletRequest request,
			HttpServletResponse response,@PathVariable("id")String id) throws Exception{
		Message msg = defaultService.getDepTreeNode(request, id);
		//ControllerTools.print(response, msg);
		return msg;
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Message test(){
		
		return this.messageFactory.successTest();
	}
	
}
